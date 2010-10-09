package logic;

import logic.model.*;
import org.hibernate.Criteria;
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

    public Date getCurrentDate() {
        return HibernateUtils.getDate(SessionManager.getSession());
    }

    public ArrayList<Route> getRoutes() {
        ArrayList<Route> list = null;
        try {
            Collection<RouteEntity> objects = SessionManager.getAllObjects(new RouteEntity());
            list = new ArrayList<Route>(objects.size());
            for (RouteEntity re : objects) {
                SheduleTypeEntity ste = re.getSheduleForward().getSheduleType();
                SheduleType stF = new SheduleType(ste.getIdSheduleType(), ste.getcSheduleType());
                ste = re.getSheduleBack().getSheduleType();
                SheduleType stB = new SheduleType(ste.getIdSheduleType(), ste.getcSheduleType());
                SheduleEntity se = re.getSheduleForward();
                int[] days = null;
                Collection<SheduleDaysEntity> sde = se.getSheduleDays();
                if (sde != null && sde.size() > 0) {
                    days = new int[sde.size()];
                    for (int i = 0; i < sde.size(); i++)
                        days[i] = sde.toArray(new SheduleDaysEntity[1])[i].getDay();
                }
                Shedule sF = new Shedule(se.getIdShedule(), se.getTimeFrom(), se.getTimeTo(), se.getTimeInWay(), stF, days);
                se = re.getSheduleBack();
                sde = se.getSheduleDays();
                if (sde != null && sde.size() > 0) {
                    days = new int[sde.size()];
                    for (int i = 0; i < sde.size(); i++)
                        days[i] = sde.toArray(new SheduleDaysEntity[1])[i].getDay();
                }
                Shedule sB = new Shedule(se.getIdShedule(), se.getTimeFrom(), se.getTimeTo(), se.getTimeInWay(), stB, days);
                Route route = new Route(re.getIdRoute(), re.getNumberForward(), re.getNumberBack(), re.getCityFrom(),
                        re.getCityTo(), sF, sB, re.isEnabled(), re.getLengthForward(), re.getLengthBack());
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
            SheduleTypeEntity ste = re.getSheduleForward().getSheduleType();
            SheduleType stF = new SheduleType(ste.getIdSheduleType(), ste.getcSheduleType());
            ste = re.getSheduleBack().getSheduleType();
            SheduleType stB = new SheduleType(ste.getIdSheduleType(), ste.getcSheduleType());
            SheduleEntity se = re.getSheduleForward();
            int[] days = null;
            Collection<SheduleDaysEntity> sde = se.getSheduleDays();
            if (sde != null && sde.size() > 0) {
                days = new int[sde.size()];
                for (int i = 0; i < sde.size(); i++)
                    days[i] = sde.toArray(new SheduleDaysEntity[1])[i].getDay();
            }
            Shedule sF = new Shedule(se.getIdShedule(), se.getTimeFrom(), se.getTimeTo(), se.getTimeInWay(), stF, days);
            se = re.getSheduleBack();
            sde = se.getSheduleDays();
            if (sde != null && sde.size() > 0) {
                days = new int[sde.size()];
                for (int i = 0; i < sde.size(); i++)
                    days[i] = sde.toArray(new SheduleDaysEntity[1])[i].getDay();
            }
            Shedule sB = new Shedule(se.getIdShedule(), se.getTimeFrom(), se.getTimeTo(), se.getTimeInWay(), stB, days);
            route = new Route(re.getIdRoute(), re.getNumberForward(), re.getNumberBack(), re.getCityFrom(),
                    re.getCityTo(), sF, sB, re.isEnabled(), re.getLengthForward(), re.getLengthBack());
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
            for (SheduleTypeEntity s : ste) types.add(new SheduleType(s.getIdSheduleType(), s.getcSheduleType()));
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
            Shedule sb = r.getSheduleBack();
            Shedule sf = r.getSheduleForward();
            SheduleTypeEntity sfte = SessionManager.getEntityById(new SheduleTypeEntity(), sf.getScheduleType().getId());
            SheduleTypeEntity sbte = SessionManager.getEntityById(new SheduleTypeEntity(), sb.getScheduleType().getId());
            SheduleEntity sfe = new SheduleEntity(sf.getTimeDeparture(), sf.getTimeDestination(), sf.getTimeInWay(), sfte);
            SheduleEntity sbe = new SheduleEntity(sb.getTimeDeparture(), sb.getTimeDestination(), sb.getTimeInWay(), sbte);
            RouteEntity re = new RouteEntity(r.getCityFrom(), r.getCityTo(), r.getNumberForward(), r.getNumberBack(),
                    r.getLengthForward(), r.getLengthBack(), sfe, sbe, r.isEnabled());
            SessionManager.saveOrUpdateEntities(sfe, sbe, re);
            int[] days = sf.getDays();
            if (days != null)
                for (int day : days) {
                    SheduleDaysEntity sde = new SheduleDaysEntity(day, sfe);
                    SessionManager.saveOrUpdateEntities(sde);
                }
            days = sb.getDays();
            if (days != null)
                for (int day : days) {
                    SheduleDaysEntity sde = new SheduleDaysEntity(day, sbe);
                    SessionManager.saveOrUpdateEntities(sde);
                }
            Date currentDate = getCurrentDate();
            TrainStatusEntity statusPlanned = new TrainStatusEntity(BusinessLogic.PLANNED, "");
            // генерируем отправляющиеся поезда с текущего момента
            for(Timestamp dateFrom : generateDatesOfDeparture(sfe, currentDate, 20)) {
                Timestamp dateTo = null; //todo вызов метода прибавления к дате - времени
                TrainEntity train = new TrainEntity(null, dateFrom, dateTo, sfe, statusPlanned);
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
            Shedule sb = r.getSheduleBack();
            Shedule sf = r.getSheduleForward();
            SheduleEntity sfe = re.getSheduleForward();
            SheduleEntity sbe = re.getSheduleBack();
            for (SheduleDaysEntity sde : sfe.getSheduleDays()) SessionManager.deleteEntities(sde);
            for (SheduleDaysEntity sde : sbe.getSheduleDays()) SessionManager.deleteEntities(sde);
            SessionManager.getSession().flush();
            SessionManager.getSession().clear();
            int[] days = sf.getDays();
            if (days != null)
                for (int day : days) SessionManager.saveOrUpdateEntities(new SheduleDaysEntity(day, sfe));
            days = sb.getDays();
            if (days != null)
                for (int day : days) SessionManager.saveOrUpdateEntities(new SheduleDaysEntity(day, sbe));
            SheduleTypeEntity sfte = SessionManager.getEntityById(new SheduleTypeEntity(), sf.getScheduleType().getId());
            SheduleTypeEntity sbte = SessionManager.getEntityById(new SheduleTypeEntity(), sb.getScheduleType().getId());
            int saveId = sfe.getIdShedule();
            sfe = new SheduleEntity(sf.getTimeDeparture(), sf.getTimeDestination(), sf.getTimeInWay(), sfte);
            sfe.setIdShedule(saveId);
            saveId = sbe.getIdShedule();
            sbe = new SheduleEntity(sb.getTimeDeparture(), sb.getTimeDestination(), sb.getTimeInWay(), sbte);
            sbe.setIdShedule(saveId);
            re = new RouteEntity(r.getCityFrom(), r.getCityTo(), r.getNumberForward(), r.getNumberBack(),
                    r.getLengthForward(), r.getLengthBack(), sfe, sbe, r.isEnabled());
            re.setIdRoute(r.getId());
            SessionManager.saveOrUpdateEntities(sfe, sbe, re);
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