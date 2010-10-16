package logic;

import logic.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import rzd.model.objects.*;

import java.sql.Timestamp;
import java.util.*;

public class BusinessManager implements BusinessLogic {

    private static BusinessManager instance;

    public synchronized static BusinessManager get() throws HibernateInitializeException {
        if (instance == null) instance = new BusinessManager();
        return instance;
    }

    private BusinessManager() throws HibernateInitializeException {
        SessionManager.init();
    }

    private <H, M> void convertHB2Model(Collection<H> src) {
//        Object o = null; o.getClass().getField("ss").get
    }

    public Session getSession() {
        return SessionManager.getSession();
    }

    public Date getCurrentDate() {
        return HibernateUtils.getDate(SessionManager.getSession());
    }

    public ArrayList<Route> getRoutes() {
        ArrayList<Route> list = null;
        try {
            Collection<RouteEntity> objects = SessionManager.getAllObjects(new RouteEntity());
            list = new ArrayList<Route>(objects.size());
            for (RouteEntity re : objects) {
                Route route = EntityConverter.convertRoute(re);
                list.add(route);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        } finally {
            SessionManager.closeSession();
        }
        return list;
    }

    public Route getRouteById(int idRoute) {
        Route route = null;
        try {
            RouteEntity re = SessionManager.getEntityById(new RouteEntity(), idRoute);
            route = EntityConverter.convertRoute(re);
        } catch (Exception e) {
            e.printStackTrace();
            route = null;
        } finally {
            SessionManager.closeSession();
        }
        return route;
    }

    public ArrayList<SheduleType> getSheduleTypes() {
        ArrayList<SheduleType> types = null;
        try {
            Collection<SheduleTypeEntity> ste = SessionManager.getAllObjects(new SheduleTypeEntity());
            types = new ArrayList<SheduleType>(ste.size());
            for (SheduleTypeEntity s : ste) types.add(EntityConverter.convertSheduleType(s));
        } catch (Exception e) {
            e.printStackTrace();
            types = null;
        } finally {
            SessionManager.closeSession();
        }
        return types;
    }

    public boolean addRoute(Route r) {
        try {
            SessionManager.beginTran();
            RouteEntity re = EntityConverter.convertRoute(r);
            SheduleEntity sfe = re.getSheduleForward();
            SheduleEntity sbe = re.getSheduleBack();
            SessionManager.saveOrUpdateEntities(sfe, sbe, re);
            if (sfe.getSheduleDays() != null)
                for (SheduleDaysEntity sde : sfe.getSheduleDays()) SessionManager.saveOrUpdateEntities(sde);
            if (sfe.getSheduleDays() != null)
                for (SheduleDaysEntity sde : sbe.getSheduleDays()) SessionManager.saveOrUpdateEntities(sde);
            if (re.isEnabled()) {
                Date currentDate = getCurrentDate();
                TrainStatusEntity statusPlanned = new TrainStatusEntity(BusinessLogic.PLANNED, "");
                // генерируем отправляющиеся поезда с текущего момента
                for (Timestamp dateFrom : generateDatesOfDeparture(sfe, currentDate, 20)) {
                    Timestamp dateTo = DateUtils.getDatePlusTime(dateFrom, sfe.getHoursInWay(), sfe.getMinutesInWay());
                    TrainEntity train = new TrainEntity(null, dateFrom, dateTo, sfe, statusPlanned);
                    SessionManager.saveOrUpdateEntities(train);
                }
                // генерируем прибывающие поезда с момента: текущее время - время в пути. (чтобы прибыл уже ближайший)
                currentDate = DateUtils.getDateMinusTime(currentDate, sbe.getHoursInWay(), sbe.getMinutesInWay());
                for (Timestamp dateFrom : generateDatesOfDeparture(sbe, currentDate, 20)) {
                    Timestamp dateTo = DateUtils.getDatePlusTime(dateFrom, sbe.getHoursInWay(), sbe.getMinutesInWay());
                    TrainEntity train = new TrainEntity(null, dateFrom, dateTo, sbe, statusPlanned);
                    SessionManager.saveOrUpdateEntities(train);
                }
            }
            SessionManager.getSession().flush();
            SessionManager.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            SessionManager.rollback();
            return false;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean updateRoute(Route r) {
        try {
            SessionManager.beginTran();
            RouteEntity re = SessionManager.getEntityById(new RouteEntity(), r.getId());
            if (re == null) throw new Exception("Route not found");
            SheduleEntity sfe = re.getSheduleForward();
            SheduleEntity sbe = re.getSheduleBack();
            // удалим старые записи дней по которым работаем расписание
            if (sfe.getSheduleDays() != null)
                for (SheduleDaysEntity sde : sfe.getSheduleDays()) SessionManager.deleteEntities(sde);
            if (sbe.getSheduleDays() != null)
                for (SheduleDaysEntity sde : sbe.getSheduleDays()) SessionManager.deleteEntities(sde);
            // заставляем выполнить делит сейчас же
            SessionManager.getSession().flush();
            // и очищаем сессию, для удаления одинаковых идентификаторов
            SessionManager.getSession().clear();
            re = EntityConverter.convertRoute(r, re.getIdRoute(), sfe.getIdShedule(), sbe.getIdShedule());
            SessionManager.saveOrUpdateEntities(sfe, sbe, re);
            if (sfe.getSheduleDays() != null)
                for (SheduleDaysEntity sde : sfe.getSheduleDays()) SessionManager.saveOrUpdateEntities(sde);
            if (sbe.getSheduleDays() != null)
                for (SheduleDaysEntity sde : sbe.getSheduleDays()) SessionManager.saveOrUpdateEntities(sde);
            SessionManager.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            SessionManager.rollback();
            return false;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<Car> getCars() {
        ArrayList<Car> list = null;
        try {
            Collection<CarEntity> objects = SessionManager.getAllObjects(new CarEntity());
            list = new ArrayList<Car>(objects.size());
            for (CarEntity ce : objects) {
                Car car = EntityConverter.convertCar(ce);
                list.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        } finally {
            SessionManager.closeSession();
        }
        return list;
    }

    public Car getCarByNumber(int carNumber) {
        Car car = null;
        try {
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), carNumber);
            if (ce == null) throw new Exception("Вагон не найден!");
            car = EntityConverter.convertCar(ce);
        } catch (Exception e) {
            e.printStackTrace();
            car = null;
        } finally {
            SessionManager.closeSession();
        }
        return car;
    }

    public ArrayList<CarType> getCarParentTypes() {
        ArrayList<CarType> types = null;
        try {
            Criteria crit = SessionManager.getSession().createCriteria(CarTypeEntity.class).
                    add(Restrictions.isNull("carParentType"));
            List list = crit.list();
            types = new ArrayList<CarType>(list.size());
            for (Object o : list) {
                types.add(EntityConverter.convertCarType((CarTypeEntity) o));
            }
        } catch (Exception e) {
            e.printStackTrace();
            types = null;
        } finally {
            SessionManager.closeSession();
        }
        return types;
    }

    public ArrayList<CarType> getCarSubTypes(CarType parentType) {
        ArrayList<CarType> types = null;
        try {
            CarTypeEntity cte = SessionManager.getEntityById(new CarTypeEntity(), parentType.getIdType());
            types = new ArrayList<CarType>(cte.getCarSubTypes().size());
            for (CarTypeEntity subType : cte.getCarSubTypes()) {
                types.add(EntityConverter.convertCarType(subType));
            }
        } catch (Exception e) {
            e.printStackTrace();
            types = null;
        } finally {
            SessionManager.closeSession();
        }
        return types;
    }

    public CarType getCarParentType(CarType subType) {
        try {
            CarTypeEntity cte = SessionManager.getEntityById(new CarTypeEntity(), subType.getIdType());
            CarTypeEntity parentType = cte.getCarParentType();
            return EntityConverter.convertCarType(parentType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean addCar(Car car) {
        try {
            SessionManager.beginTran();
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            if (ce != null) throw new Exception("Вагон уже существует!");
            ce = EntityConverter.convertCar(car);
            SessionManager.saveOrUpdateEntities(ce);
            SessionManager.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            SessionManager.rollback();
            return false;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean editCar(Car car) {
        try {
            SessionManager.beginTran();
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            if (ce == null) throw new Exception("Вагона не существует!");
            SessionManager.getSession().evict(ce);
            ce = EntityConverter.convertCar(car);
            SessionManager.saveOrUpdateEntities(ce);
            SessionManager.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            SessionManager.rollback();
            return false;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<RoadType> getRoadTypes() {
        ArrayList<RoadType> list = null;
        try {
            Collection<RoadTypeEntity> types = SessionManager.getAllObjects(new RoadTypeEntity());
            list = new ArrayList<RoadType>(types.size());
            for (RoadTypeEntity rte : types) list.add(new RoadType(rte.getIdType(), rte.getTypeName()));
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        } finally {
            SessionManager.closeSession();
        }
        return list;
    }

    public ArrayList<Road> getRoadsByType(RoadType roadType) {
        ArrayList<Road> list;
        try {
            RoadTypeEntity rt = SessionManager.getEntityById(new RoadTypeEntity(), roadType.getId());
            Collection<RoadEntity> roads = rt.getRoads();
            list = new ArrayList<Road>(roads.size());
            for (RoadEntity road : roads) {
                list.add(EntityConverter.convertRoad(road));
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        } finally {
            SessionManager.closeSession();
        }
        return list;
    }

    public Train getTrainById(int idTrain) {
        try {
            TrainEntity te = SessionManager.getEntityById(new TrainEntity(), idTrain);
            return EntityConverter.convertTrain(te);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public Train getTrainByRoad(Road road) {
        try {
            RoadEntity re = SessionManager.getEntityById(new RoadEntity(), road.getId());
            for (RoadDetEntity rde : re.getRoadDets()) {
                TrainEntity te = rde.getTrain();
                if (te != null) return EntityConverter.convertTrain(te);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<Train> getTrainsOnRoads() {
        try {
            Collection<RoadEntity> res = SessionManager.getAllObjects(new RoadEntity());
            ArrayList<RoadDetEntity> rdes = new ArrayList<RoadDetEntity>();
            for (RoadEntity re : res) rdes.addAll(re.getRoadDets());
            ArrayList<Train> list = new ArrayList<Train>();
            for (RoadDetEntity rde : rdes) {
                TrainEntity te = rde.getTrain();
                if (te != null) list.add(EntityConverter.convertTrain(te));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<Train> getGoingTrains(int forHours) {
        try {
            TrainStatusEntity tse = SessionManager.getEntityById(new TrainStatusEntity(), BusinessLogic.PLANNED);
            Collection<RouteEntity> r = SessionManager.getAllObjects(new RouteEntity());
            ArrayList<SheduleEntity> se = new ArrayList<SheduleEntity>(r.size());
            for (RouteEntity re : r) se.add(re.getSheduleForward());
            Criteria crit = SessionManager.getSession().createCriteria(TrainEntity.class).
                    add(Restrictions.in("shedule", se)).add(Restrictions.eq("trainStatus", tse));
            ArrayList<Train> list = new ArrayList<Train>();
            for (Object te : crit.list()) {
                list.add(EntityConverter.convertTrain((TrainEntity) te));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean makeTrainForGoing(Train train) throws Exception {
        try {
            SessionManager.beginTran();
            TrainEntity te = SessionManager.getEntityById(new TrainEntity(), train.getId());
            RoadEntity re = SessionManager.getEntityById(new RoadEntity(), train.getRoad().getId());
            if (te.getTrainStatus().getIdStatus() != BusinessLogic.PLANNED)
                throw new Exception("Поезд уже был сформирован!");
            if (re.getRoadDets() != null && re.getRoadDets().size() > 0) throw new Exception("Путь занят!");
            ArrayList<CarEntity> ces = new ArrayList<CarEntity>(train.getCarsIn().size());
            for (Car car : train.getCarsIn()) ces.add(SessionManager.getEntityById(new CarEntity(), car.getNumber()));
            if (ces.size() == 0) throw new Exception("Нет вагонов!");
            TrainStatusEntity tse = SessionManager.getEntityById(new TrainStatusEntity(), BusinessLogic.MAKED);
            te.setTrainChief(train.getChief());
            te.setTrainStatus(tse);
            SessionManager.saveOrUpdateEntities(te);
            Date currentDate = getCurrentDate();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            for (CarEntity ce : ces) {
                if (ce.getTrainDets() != null && ce.getTrainDets().size() > 0)
                    throw new Exception("Вагон " + ce.getCarNumber() + " уже в составе другого поезда!");
                for (RepairEntity rep : ce.getRepairs()) {
                    if (rep.getDateEnd() == null) throw new Exception("Вагон " + ce.getCarNumber() + " в ремонте!");
                }
                // удаляем вагон с пути, если на нем нет поезда - удаляем запись
                for (RoadDetEntity rde : ce.getRoadDets()) {
                    if (rde.getTrain() == null) {
                        SessionManager.deleteEntities(rde);
                    } else {
                        rde.setCar(null);
                        SessionManager.saveOrUpdateEntities(rde);
                    }
                }
                CarLocationEntity cle = EntityConverter.convertCarLocation(new CarLocation(BusinessLogic.IN_TRAIN, ""));
                ce.setCarLocation(cle);
                TrainDetEntity tde = new TrainDetEntity(ce, te);
                CarHistoryEntity che = new CarHistoryEntity(date, cle, te, null, ce, null);
                SessionManager.saveOrUpdateEntities(ce, tde, che);
            }
            RoadDetEntity rde = new RoadDetEntity(re, null, te);
            SessionManager.saveOrUpdateEntities(rde);
            SessionManager.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            SessionManager.rollback();
            throw e;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<Train> getArrivingTrains(int forHours) {
        try {
            TrainStatusEntity tse = SessionManager.getEntityById(new TrainStatusEntity(), BusinessLogic.PLANNED);
            Collection<RouteEntity> r = SessionManager.getAllObjects(new RouteEntity());
            ArrayList<SheduleEntity> se = new ArrayList<SheduleEntity>(r.size());
            for (RouteEntity re : r) se.add(re.getSheduleBack());
            Criteria crit = SessionManager.getSession().createCriteria(TrainEntity.class).
                    add(Restrictions.in("shedule", se)).add(Restrictions.eq("trainStatus", tse));
            ArrayList<Train> list = new ArrayList<Train>();
            for (Object te : crit.list()) {
                list.add(EntityConverter.convertTrain((TrainEntity) te));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<CarLocation> getCarLocations() {
        try {
            Collection<CarLocationEntity> cles = SessionManager.getAllObjects(new CarLocationEntity());
            ArrayList<CarLocation> list = new ArrayList<CarLocation>(cles.size());
            for (CarLocationEntity cle : cles) {
                list.add(EntityConverter.convertCarLocation(cle));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean setCarLocation(Car car, Road road, Repair repair) throws Exception {
        try {
            SessionManager.beginTran();
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            CarLocationEntity cle = ce.getCarLocation();
            if (cle.getIdLocation() == BusinessLogic.IN_TRAIN) throw new Exception("Вагон в составе поезда!");
            Date currentDate = getCurrentDate();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            Timestamp time = new Timestamp(currentDate.getTime());
            // заканчиваем ремонты
            for (RepairEntity rep : ce.getRepairs()) {
                if (rep.getDateEnd() == null) {
                    rep.setDateEnd(time);
                    SessionManager.saveOrUpdateEntities(rep);
                }
            }
            // удаляем вагон с пути, если на нем нет поезда - удаляем запись
            for (RoadDetEntity rde : ce.getRoadDets()) {
                if (rde.getTrain() == null) {
                    SessionManager.deleteEntities(rde);
                } else {
                    rde.setCar(null);
                    SessionManager.saveOrUpdateEntities(rde);
                }
            }
            CarHistoryEntity che = null;
            CarLocationEntity newCle = null;
            // записи о пути и ремонте, если они будут заполнены по условию локации вагона, мы их сохраним
            RepairEntity rep = null;
            RoadDetEntity rde = null;
            switch (car.getCarLocation().getIdLocation()) {
                case BusinessLogic.IN_TRAIN:
                    throw new Exception("Вагон нельзя включить в поезд!");
                case BusinessLogic.ON_ROAD:
                    RoadEntity re = EntityConverter.convertRoad(road);
                    rde = new RoadDetEntity(re, ce, null);
                    newCle = new CarLocationEntity(BusinessLogic.ON_ROAD, "");
                    che = new CarHistoryEntity(date, newCle, null, re, ce, null);
                    break;
                case BusinessLogic.UNKNOWN:
                    newCle = new CarLocationEntity(BusinessLogic.UNKNOWN, "");
                    che = new CarHistoryEntity(date, newCle, null, null, ce, null);
                    break;
                case BusinessLogic.REPAIR:
                    rep = EntityConverter.convertRepair(repair);
                    // идентификатор в налл, он должен сохраниться в базе, а геша туда мог какую нибудь ню записать.
                    rep.setIdRepair(null);
                    rep.setDateBegin(time);
                    rep.setDateEnd(null);
                    // путь на котором прозводится ремонт
                    if (repair.getRoad() != null) {
                        RoadEntity reproad = EntityConverter.convertRoad(repair.getRoad());
                        rde = new RoadDetEntity(reproad, ce, null);
                    }
                    newCle = EntityConverter.convertCarLocation(new CarLocation(BusinessLogic.REPAIR, ""));
                    che = new CarHistoryEntity(date, newCle, null, null, ce, rep);
                    break;
            }
            ce.setCarLocation(newCle);
            SessionManager.saveOrUpdateEntities(ce);
            if (rep != null) SessionManager.saveOrUpdateEntities(rep);
            if (rde != null) SessionManager.saveOrUpdateEntities(rde);
            SessionManager.saveOrUpdateEntities(che);
            SessionManager.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            SessionManager.rollback();
            throw e;
        } finally {
            SessionManager.closeSession();
        }
    }

    public Road getRoadByCar(Car car) {
        try {
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            for (RoadDetEntity rde : ce.getRoadDets()) {
                if (rde != null) return EntityConverter.convertRoad(rde.getRoad());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<RepairType> getRepairTypes() {
        try {
            Collection<RepairTypeEntity> rtes = SessionManager.getAllObjects(new RepairTypeEntity());
            ArrayList<RepairType> list = new ArrayList<RepairType>(rtes.size());
            for (RepairTypeEntity rte : rtes) {
                list.add(EntityConverter.convertRepairType(rte));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public Repair getRepairByCar(Car car) {
        try {
            Repair repair = null;
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            for (RepairEntity re : ce.getRepairs()) {
                if (re.getDateEnd() == null) repair = EntityConverter.convertRepair(re);
            }
            return repair;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean updateRepair(Repair repair) {
        try {
            SessionManager.beginTran();
            Session s = getSession();
            RepairEntity re = SessionManager.getEntityById(new RepairEntity(), repair.getIdRepair());
            // удаляем старую запись о пути, вдруг путь обновился! хотя с чего бы, но геша просит
            for (RoadDetEntity rde : re.getRoad().getRoadDets()) {
                if (rde.getCar() != null && rde.getCar().equals(re.getCar())) {
                    s.delete(rde);
                    s.flush();
                }
            }
            // клирим сессию для очистки идентификаторов, т.к. мы не обновляем поля set'ами, а создаем новый объект.
            getSession().clear();
            re = EntityConverter.convertRepair(repair);
            RoadEntity newRoad = re.getRoad();
            if (newRoad != null) {
                s.save(new RoadDetEntity(newRoad, re.getCar(), null));
            }
            s.update(re);
            SessionManager.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            SessionManager.rollback();
            return false;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<Car> getCarsOnRoad(Road road) {
        try {
            RoadEntity re = SessionManager.getEntityById(new RoadEntity(), road.getId());
            ArrayList<Car> list = new ArrayList<Car>();
            for (RoadDetEntity rde : re.getRoadDets()) {
                if (rde.getCar() != null) list.add(EntityConverter.convertCar(rde.getCar()));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public Train getTrainByCar(Car car) {
        try {
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            for (TrainDetEntity tde : ce.getTrainDets()) {
                if (tde.getTrain().getTrainStatus().getIdStatus() != BusinessLogic.DESTROYED)
                    return EntityConverter.convertTrain(tde.getTrain());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public Collection<Timestamp> generateDatesOfDeparture(SheduleEntity shedule, Date dateBegin, int count) {
        GregorianCalendar firstDate = new GregorianCalendar();
        firstDate.setTime(dateBegin);
        firstDate.set(Calendar.HOUR_OF_DAY, DateUtils.getHours(shedule.getTimeFrom()));
        firstDate.set(Calendar.MINUTE, DateUtils.getMinutes(shedule.getTimeFrom()));
        if (dateBegin.getTime() > firstDate.getTimeInMillis()) firstDate.add(Calendar.DAY_OF_MONTH, 1);
        ArrayList<Integer> days = new ArrayList<Integer>();
        int calendarType = Calendar.DAY_OF_MONTH;
        switch (shedule.getSheduleType().getIdSheduleType()) {
            case BusinessLogic.NONPAIR:
                List<Integer> d = Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31);
                days.addAll(d);
                break;
            case BusinessLogic.PAIR:
                List<Integer> dd = Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30);
                days.addAll(dd);
                break;
            case BusinessLogic.DAYS_WEEK:
                calendarType = Calendar.DAY_OF_WEEK;
            case BusinessLogic.DAYS_MONTH:
                for (SheduleDaysEntity sd : shedule.getSheduleDays()) {
                    days.add(sd.getDay());
                }
                break;
        }
        return DateUtils.getDates(firstDate.getTime(), days, count, calendarType);
    }

    public void test() {
        SheduleEntity e = (SheduleEntity) SessionManager.getAllObjects(new SheduleEntity()).toArray()[0];
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, DateUtils.getHours(e.getTimeFrom()));
        c.set(Calendar.MINUTE, DateUtils.getMinutes(e.getTimeFrom()));
        System.out.println(c.getTime());
    }
}