package logic;

import logic.model.*;
import rzd.model.BusinessLogic;
import rzd.model.objects.*;

import java.util.ArrayList;
import java.util.Collection;

public class BusinessManager implements BusinessLogic {

    private static BusinessManager instance;

    public synchronized static BusinessManager get() throws HibernateInitializeException {
        if (instance == null) instance = new BusinessManager();
        return instance;
    }

    private BusinessManager() throws HibernateInitializeException {
        SessionManager.init();
    }

    private <H,M> void convertHB2Model(Collection<H> src) {
//        Object o = null; o.getClass().getField("ss").get()
    }

    public ArrayList<RoadType> getRoadTypes() {
        Collection<RoadTypeEntity> objects = SessionManager.getAllObjects(new RoadTypeEntity());
        SessionManager.closeSession();
        if(objects == null) return null;
        ArrayList<RoadType> list = new ArrayList<RoadType>(objects.size());
        for(RoadTypeEntity rt : objects) {
            list.add(new RoadType(rt.getId(), rt.getName()));
        }
        return list;
    }

    public ArrayList<Road> getRoadsByType(RoadType roadType) {
        return null;
    }

    public ArrayList<Route> getRoutes() {
        Collection<RouteEntity> objects = SessionManager.getAllObjects(new RouteEntity());
        SessionManager.closeSession();
        if(objects == null) return null;
        ArrayList<Route> list = new ArrayList<Route>(objects.size());
        for(RouteEntity re : objects) {
            list.add(new Route(re.getIdRoute(), re.getNumber(), re.getPointDeparture(), re.getPointDestination()));
        }
        return list;
    }

    public boolean updateRoute(Route route) {
        SessionManager.getSession().saveOrUpdate(null);
        return true;
    }

    public boolean removeRoute(Route route) {
        SessionManager.getSession().delete(null);
        return true;
    }

    public ArrayList<Schedule> getSchedules() {
        Collection<RouteScheduleEntity> objects = SessionManager.getAllObjects(new RouteScheduleEntity());
        SessionManager.closeSession();
        if(objects == null) return null;
        ArrayList<Schedule> list = new ArrayList<Schedule>(objects.size());
        for(RouteScheduleEntity rs : objects) {
            list.add(new Schedule(rs.getId(), rs.getTimeDeparture(), rs.getTimeDestination(), rs.getDateBegin(), 
                    rs.getDayMove(), rs.getDayStop()));
        }
        return list;
    }

    public boolean updateSchedule(Schedule schedule) {
        return false;
    }

    public boolean removeSchedule(Schedule schedule) {
        return false;
    }

    public ArrayList<Schedule> getSchedulesByRoute(Route route) {
        return null;
    }

    public Route getRouteBySchedule(Schedule schedule) {
        return null;
    }

    public boolean addRouteInSchedule(Route route, Schedule schedule) {
        return false;
    }

    public ArrayList<Train> getTrains() {
        return null;
    }

    public boolean updateTrain(Train train) {
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

    public boolean updateCar(Car car) {
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