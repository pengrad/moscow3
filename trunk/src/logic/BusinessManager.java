package logic;

import logic.model.RoadEntity;
import logic.model.RoadTypeEntity;
import logic.model.RouteEntity;
import logic.model.RouteScheduleEntity;
import rzd.model.BusinessLogic;
import rzd.model.objects.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
        RouteScheduleEntity rs = new RouteScheduleEntity(schedule.getTimeDeparture(), schedule.getTimeDestination(),
                schedule.getDateBegin(), schedule.getDayMove(), schedule.getDayStop(), r);
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
        RouteScheduleEntity rs = new RouteScheduleEntity(schedule.getTimeDeparture(), schedule.getTimeDestination(),
                schedule.getDateBegin(), schedule.getDayMove(), schedule.getDayStop(), r);
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
        if(schedule == null) return null;
        RouteScheduleEntity rs = SessionManager.getEntityById(new RouteScheduleEntity(), schedule.getId());
        SessionManager.closeSession();
        if(rs == null) return null;
        RouteEntity re = rs.getRouteByIdRoute();
        if (re == null) return null;
        return new Route(re.getIdRoute(), re.getNumber(), re.getPointDeparture(), re.getPointDestination());
    }

    public ArrayList<Train> getTrains(Date dBegin, Date dEnd) {
        return null;
    }

    public boolean addTrain(Train train, Route route) {
        return false;
    }

    public boolean updateTrain(Train train, Route route) {
        return false;
    }

    public Route getRouteByTrain(Train train) {
        return null;
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
        return null;
    }

    public boolean addCar(Car car, CarLocation carLocation) {
        return false;
    }

    public boolean updateCar(Car car, CarLocation carLocation) {
        return false;
    }

    public boolean removeCar() {
        return false;
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