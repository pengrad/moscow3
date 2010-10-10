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
            if (sfe.getSheduleDays() != null)
                for (SheduleDaysEntity sde : sfe.getSheduleDays()) SessionManager.deleteEntities(sde);
            if (sbe.getSheduleDays() != null)
                for (SheduleDaysEntity sde : sbe.getSheduleDays()) SessionManager.deleteEntities(sde);
            SessionManager.getSession().flush();
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

    public List<TrainEntity> getGoingTrains() {
        try {
            Collection<RouteEntity> r = SessionManager.getAllObjects(new RouteEntity());
            ArrayList<SheduleEntity> se = new ArrayList<SheduleEntity>(r.size());
            for(RouteEntity re : r) se.add(re.getSheduleForward());
            Criteria crit = SessionManager.getSession().createCriteria(TrainEntity.class).
                    add(Restrictions.in("shedule", se));
            return (List<TrainEntity>)crit.list();
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Train> getGoingTrains(int forHours) {
        try {
            Collection<RouteEntity> r = SessionManager.getAllObjects(new RouteEntity());
            ArrayList<SheduleEntity> se = new ArrayList<SheduleEntity>(r.size());
            for(RouteEntity re : r) se.add(re.getSheduleForward());
            Criteria crit = SessionManager.getSession().createCriteria(TrainEntity.class).
                    add(Restrictions.in("shedule", se));
            ArrayList<Train> list = new ArrayList<Train>();
            for(TrainEntity te : (List<TrainEntity>)crit.list()){
                TrainStatus ts = new TrainStatus(te.getTrainStatus().getIdStatus(), te.getTrainStatus().getcStatus());
                RouteEntity re = (RouteEntity)te.getShedule().getRoutesBySheduleForward().toArray()[0];
                Route rr = EntityConverter.convertRoute(re);
                Train t = new Train(te.getIdTrain(), te.getDateFrom(), te.getDateTo(), te.getTrainChief(), rr.getSheduleForward(), rr, ts, null, null);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Train> getArrivingTrains(int forHours) {
        try {
            TrainStatusEntity tse = SessionManager.getEntityById(new TrainStatusEntity(), BusinessLogic.PLANNED);
            Collection<RouteEntity> r = SessionManager.getAllObjects(new RouteEntity());
            ArrayList<SheduleEntity> se = new ArrayList<SheduleEntity>(r.size());
            for(RouteEntity re : r) se.add(re.getSheduleBack());
            Criteria crit = SessionManager.getSession().createCriteria(TrainEntity.class).
                    add(Restrictions.in("shedule", se)).add(Restrictions.eq("trainStatus", tse));
            ArrayList<Train> list = new ArrayList<Train>();
            for(TrainEntity te : (List<TrainEntity>)crit.list()){
                TrainStatus ts = new TrainStatus(te.getTrainStatus().getIdStatus(), te.getTrainStatus().getcStatus());
                RouteEntity re = (RouteEntity)te.getShedule().getRoutesBySheduleBack().toArray()[0];
                Route rr = EntityConverter.convertRoute(re);
                Train t = new Train(te.getIdTrain(), te.getDateFrom(), te.getDateTo(), te.getTrainChief(), rr.getSheduleForward(), rr, ts, null, null);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            return null;
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