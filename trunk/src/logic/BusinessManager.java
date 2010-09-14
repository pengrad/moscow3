package logic;

import logic.HibernateInitializeException;
import logic.SessionManager;
import logic.model.RoadTypeEntity;
import logic.model.RouteEntity;
import logic.model.RouteScheduleEntity;
import rzd.model.BusinessLogic;
import rzd.objects.*;

import java.util.ArrayList;
import java.util.Collection;
//import org.hibernate.HibernateException;

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
        Collection<RouteScheduleEntity> objects = SessionManager.getAllObjects(new RouteScheduleEntity());
        SessionManager.closeSession();
        if(objects == null) return null;
        ArrayList<Route> list = new ArrayList<Route>(objects.size());
        for(RouteScheduleEntity rs : objects) {
            RouteEntity re = rs.getRouteByIdRoute();
            list.add(new Route(rs.getId(), re.getNumber(), re.getPointDeparture(), re.getPointDestination(),
                    rs.getTimeDeparture(), rs.getTimeDeparture(), rs.getDateBegin(), rs.getDayMove(), rs.getDayStop()));
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

    public ArrayList<Train> getTrains() {
        return null;
    }

    public boolean updateTrain(Train train) {
        return false;
    }

    public Route getRouteByTrain(Train train) {
        return null;
    }

    public boolean addTrainInRoad(Road road, Train train) {
        return false;
    }

    public boolean removeTrainFromRoad(Road road, Train train) {
        return false;
    }

    public ArrayList<Train> getTrainsByRoad() {
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

    public boolean addCarInTrain(Train train, Car road) {
        return false;
    }

    public boolean addCarInRoad(Train train, Car car) {
        return false;
    }

    public boolean addCarInAnotherLocation(CarAnotherLocation carAnotherLocation, Car car) {
        return false;
    }
}