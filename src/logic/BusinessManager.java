package logic;

import logic.model.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import rzd.model.BusinessLogic;
import rzd.model.objects.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
//        Object o = null; o.getClass().getField("ss").get()
    }

    public ArrayList<RoadType> getRoadTypes() {
        Collection<RoadTypeEntity> objects = SessionManager.getAllObjects(new RoadTypeEntity());
        SessionManager.closeSession();
        if (objects == null) return null;
        ArrayList<RoadType> list = new ArrayList<RoadType>(objects.size());
        for (RoadTypeEntity rt : objects) {
            list.add(new RoadType(rt.getId(), rt.getName()));
        }
        return list;
    }

    public ArrayList<Road> getRoadsByType(RoadType roadType) {
        if (roadType == null) return null;
        RoadTypeEntity rt = SessionManager.getEntityById(new RoadTypeEntity(), roadType.getId());
        SessionManager.closeSession();
        if (rt == null) return null;
        Collection<RoadEntity> roads = rt.getRoadsById();
        ArrayList<Road> list = new ArrayList<Road>(roads.size());
        for (RoadEntity road : roads) {
            list.add(new Road(road.getId(), road.getName(), road.getComments(), road.getPosition()));
        }
        return list;
    }

    public Road getRoadByTrain(Train train) {
        try {
            TrainEntity te = SessionManager.getEntityById(new TrainEntity(), train.getId());
            RoadEntity re = te.getRoadByIdRoad();
            return new Road(re.getId(), re.getName(), re.getComments(), re.getPosition());
        } catch (Throwable t) {
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public Road getRoadByCar(Car car) {
        try {
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            RoadEntity re = ce.getCarLocationByIdLocation().getRoadByIdRoad();
            return new Road(re.getId(), re.getName(), re.getComments(), re.getPosition());
        } catch (Throwable t) {
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<Route> getRoutes() {
        Collection<RouteEntity> objects = SessionManager.getAllObjects(new RouteEntity());
        SessionManager.closeSession();
        if (objects == null) return null;
        ArrayList<Route> list = new ArrayList<Route>(objects.size());
        for (RouteEntity re : objects) {
            list.add(new Route(re.getIdRoute(), re.getNumber(), re.getPointDeparture(), re.getPointDestination()));
        }
        return list;
    }

    public boolean addRoute(Route route) {
        if (route == null) return false;
        RouteEntity r = new RouteEntity(route.getNumber(), route.getPointDeparture(), route.getPointDestination());
        SessionManager.getSession().saveOrUpdate(r);
        SessionManager.closeSession();
        return true;
    }

    public boolean updateRoute(Route route) {
        if (route == null) return false;
        RouteEntity r = new RouteEntity(route.getNumber(), route.getPointDeparture(), route.getPointDestination());
        r.setIdRoute(route.getId());
        SessionManager.getSession().saveOrUpdate(r);
        SessionManager.closeSession();
        return true;
    }

    public boolean removeRoute(Route route) {
        if (route == null) return false;
        RouteEntity r = SessionManager.getEntityById(new RouteEntity(), route.getId());
        if (r == null) {
            SessionManager.closeSession();
            return false;
        }
        SessionManager.getSession().delete(r);
        SessionManager.closeSession();
        return true;
    }

    public ArrayList<Schedule> getSchedules() {
        Collection<RouteScheduleEntity> objects = SessionManager.getAllObjects(new RouteScheduleEntity());
        SessionManager.closeSession();
        if (objects == null) return null;
        ArrayList<Schedule> list = new ArrayList<Schedule>(objects.size());
        for (RouteScheduleEntity rs : objects) {
            list.add(new Schedule(rs.getId(), rs.getTimeDeparture(), rs.getTimeDestination(), rs.getDateBegin(),
                    rs.getDayMove(), rs.getDayStop()));
        }
        return list;
    }

    public boolean addSchedule(Schedule schedule, Route route) {
        if (schedule == null || route == null) return false;
        RouteEntity r = SessionManager.getEntityById(new RouteEntity(), route.getId());
        if (r == null) {
            SessionManager.closeSession();
            return false;
        }
        java.sql.Date dateBegin = new java.sql.Date(schedule.getDateBegin().getTime());
        Time timeDep = new Time(schedule.getTimeDeparture().getTime());
        Time timeDest = new Time(schedule.getTimeDestination().getTime());
        RouteScheduleEntity rs = new RouteScheduleEntity(timeDep, timeDest, dateBegin,
                schedule.getDayMove(), schedule.getDayStop(), r);
        SessionManager.getSession().saveOrUpdate(r);
        SessionManager.closeSession();
        return true;
    }

    public boolean updateSchedule(Schedule schedule, Route route) {
        if (schedule == null || route == null) return false;
        RouteEntity r = SessionManager.getEntityById(new RouteEntity(), route.getId());
        if (r == null) {
            SessionManager.closeSession();
            return false;
        }
        java.sql.Date dateBegin = new java.sql.Date(schedule.getDateBegin().getTime());
        Time timeDep = new Time(schedule.getTimeDeparture().getTime());
        Time timeDest = new Time(schedule.getTimeDestination().getTime());
        RouteScheduleEntity rs = new RouteScheduleEntity(timeDep, timeDest, dateBegin,
                schedule.getDayMove(), schedule.getDayStop(), r);
        rs.setId(schedule.getId());
        SessionManager.getSession().saveOrUpdate(r);
        SessionManager.closeSession();
        return true;
    }

    public boolean removeSchedule(Schedule schedule) {
        if (schedule == null) return false;
        RouteScheduleEntity r = SessionManager.getEntityById(new RouteScheduleEntity(), schedule.getId());
        if (r == null) {
            SessionManager.closeSession();
            return false;
        }
        SessionManager.getSession().delete(r);
        SessionManager.closeSession();
        return true;
    }

    public ArrayList<Schedule> getSchedulesByRoute(Route route) {
        if (route == null) return null;
        RouteEntity rt = SessionManager.getEntityById(new RouteEntity(), route.getId());
        SessionManager.closeSession();
        if (rt == null) return null;
        Collection<RouteScheduleEntity> rs = rt.getRouteSchedulesByIdRoute();
        ArrayList<Schedule> list = new ArrayList<Schedule>(rs.size());
        for (RouteScheduleEntity s : rs) {
            list.add(new Schedule(s.getId(), s.getTimeDeparture(), s.getTimeDestination(), s.getDateBegin(),
                    s.getDayMove(), s.getDayStop()));
        }
        return list;
    }

    public Route getRouteBySchedule(Schedule schedule) {
        if (schedule == null) return null;
        RouteScheduleEntity rs = SessionManager.getEntityById(new RouteScheduleEntity(), schedule.getId());
        SessionManager.closeSession();
        if (rs == null) return null;
        RouteEntity re = rs.getRouteByIdRoute();
        if (re == null) return null;
        return new Route(re.getIdRoute(), re.getNumber(), re.getPointDeparture(), re.getPointDestination());
    }

    public ArrayList<Train> getTrains(Date dBegin, Date dEnd) {
        Criteria crit = SessionManager.getSession().createCriteria(TrainEntity.class)
                .add(Restrictions.between("dt_departure", new Timestamp(dBegin.getTime()), new Timestamp(dEnd.getTime())));
        List list = crit.list();
        if (list != null) {
            ArrayList<Train> res = new ArrayList<Train>(list.size());
            for (Object o : list) {
                TrainEntity te = (TrainEntity) o;
                res.add(new Train(te.getIdTrain(), te.getDtDeparture(), te.getDtDestination()));
            }
            SessionManager.closeSession();
            return res;
        } else {
            SessionManager.closeSession();
            return null;
        }
    }

    public ArrayList<Train> getTrainsGoing() {
        return null;
    }

    public ArrayList<Train> getTrainsArriving() {
        return null;
    }

    public ArrayList<Train> getTrainsGoneToday() {
        return null;
    }

    public ArrayList<Train> getTrainsArrivedToday() {
        return null;
    }

    public boolean addTrain(Train train, Schedule schedule) {
        if (train == null || schedule == null) return false;
        RouteScheduleEntity rs = SessionManager.getEntityById(new RouteScheduleEntity(), schedule.getId());
        if (rs == null) {
            SessionManager.closeSession();
            return false;
        }
        Timestamp timeDep = new Timestamp(train.getDtDeparture().getTime());
        Timestamp timeDest = new Timestamp(train.getDtDestination().getTime());
        TrainEntity te = new TrainEntity(timeDep, timeDest, rs);
        SessionManager.getSession().saveOrUpdate(te);
        SessionManager.closeSession();
        return true;
    }

    public boolean updateTrain(Train train, Schedule schedule) {
        if (train == null || schedule == null) return false;
        RouteScheduleEntity rs = SessionManager.getEntityById(new RouteScheduleEntity(), schedule.getId());
        if (rs == null) {
            SessionManager.closeSession();
            return false;
        }
        Timestamp timeDep = new Timestamp(train.getDtDeparture().getTime());
        Timestamp timeDest = new Timestamp(train.getDtDestination().getTime());
        TrainEntity te = new TrainEntity(timeDep, timeDest, rs);
        te.setIdTrain(train.getId());
        SessionManager.getSession().saveOrUpdate(te);
        SessionManager.closeSession();
        return true;
    }

    public Route getRouteByTrain(Train train) {
        try {
            TrainEntity te = SessionManager.getEntityById(new TrainEntity(), train.getId());
            RouteEntity re = te.getRouteScheduleByIdSchedule().getRouteByIdRoute();
            return new Route(re.getIdRoute(), re.getNumber(), re.getPointDeparture(), re.getPointDestination());
        } catch (Throwable t) {
            return null;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean addTrainInRoad(Train train, Road road) {
        return false;
    }

    public boolean removeTrainFromRoad(Train train, Road road) {
        return false;
    }

    public Train getTrainByRoad(Road road) {
        return null;
    }

    public ArrayList<Car> getCars() {
        Collection<CarEntity> objects = SessionManager.getAllObjects(new CarEntity());
        SessionManager.closeSession();
        if (objects == null) return null;
        ArrayList<Car> list = new ArrayList<Car>(objects.size());
        for (CarEntity ce : objects) {
            list.add(new Car(ce.getNumber()));
        }
        return list;
    }

    public boolean addCar(Car car, CarLocation carLocation) {
        try {
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            if (ce != null) return false;
            Road r = carLocation.getRoad();
            CarAnotherLocation cal = carLocation.getOtherLocation();
            Train t = carLocation.getTrain();
            RoadEntity re = null;
            CarAnotherLocationEntity cale = null;
            TrainEntity te = null;
            if (r != null) re = SessionManager.getEntityById(new RoadEntity(), r.getId());
            if (cal != null) cale = SessionManager.getEntityById(new CarAnotherLocationEntity(), cal.getId());
            if (t != null) te = SessionManager.getEntityById(new TrainEntity(), t.getId());
            // поезд не может быть на пути или в составе поезда и при этом в каком то другом местоположении
            if (cale != null && (re != null || t != null)) return false;
            CarLocationEntity cle = new CarLocationEntity(te, cale, re);
            ce = new CarEntity(car.getNumber(), new Timestamp(new Date().getTime()), cle);
            SessionManager.beginTran();
            SessionManager.saveOrUpdateEntities(cle, ce);
            SessionManager.commit();
            return true;
        } catch (Throwable t) {
            try {
                SessionManager.rollback();
            } catch (Throwable ignored) {
            }
            return false;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean updateCar(Car car, CarLocation carLocation) {
        try {
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            if (ce == null) return false;
            Road r = carLocation.getRoad();
            CarAnotherLocation cal = carLocation.getOtherLocation();
            Train t = carLocation.getTrain();
            RoadEntity re = null;
            CarAnotherLocationEntity cale = null;
            TrainEntity te = null;
            if (r != null) re = SessionManager.getEntityById(new RoadEntity(), r.getId());
            if (cal != null) cale = SessionManager.getEntityById(new CarAnotherLocationEntity(), cal.getId());
            if (t != null) te = SessionManager.getEntityById(new TrainEntity(), t.getId());
            // поезд не может быть на пути или в составе поезда и при этом в каком то другом местоположении
            if (cale != null && (re != null || t != null)) return false;
            // проверка что существующее местоположение и новое - не одно и то же
            CarLocationEntity cle = new CarLocationEntity(te, cale, re);
            CarLocationEntity cleOld = ce.getCarLocationByIdLocation();
            if (cle.equals(cleOld)) return false;
            Timestamp time = new Timestamp(new Date().getTime());
            ce.setDateUpdate(time);
            ce.setCarLocationByIdLocation(cle);
            CarLocationHistoryEntity clhe = new CarLocationHistoryEntity(time, cleOld, ce);
            SessionManager.beginTran();
            SessionManager.saveOrUpdateEntities(clhe, cle, ce);
            SessionManager.commit();
            return true;
        } catch (Throwable t) {
            try {
                SessionManager.rollback();
            } catch (Throwable ignored) {
            }
            return false;
        } finally {
            SessionManager.closeSession();
        }
    }

    public boolean removeCar(Car car) {
        try {
            CarEntity ce = SessionManager.getEntityById(new CarEntity(), car.getNumber());
            CarLocationEntity cle = ce.getCarLocationByIdLocation();

            return true;
        } catch (Exception e) {
            try {
                SessionManager.rollback();
            } catch (Throwable ignored) {
            }
            return false;
        } finally {
            SessionManager.closeSession();
        }
    }

    public ArrayList<Car> getCarsByTrain(Train train) {
        return null;
    }

    public ArrayList<Car> getCarsByRoad(Road train) {
        return null;
    }

    public ArrayList<Car> getCarsByAnotherLocation(CarAnotherLocation carAnotherLocation) {
        return null;
    }

    public boolean addCarInTrain(Car road, Train train) {
        return false;
    }

    public boolean addCarInRoad(Car car, Road road) {
        return false;
    }

    public boolean addCarInAnotherLocation(Car car, CarAnotherLocation carAnotherLocation) {
        return false;
    }
}