package rzd.model;

import rzd.model.objects.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class TestModel implements BusinessLogic {
    private final static TestModel tm = new TestModel();

    public static TestModel get() {
        return tm;
    }

    HashMap<RoadType, ArrayList<Road>> roadTypes = new HashMap<RoadType, ArrayList<Road>>();

    private TestModel() {
        RoadType roadType = new RoadType(1, "Тип 1");
        ArrayList<Road> roads = new ArrayList<Road>();
        roads.add(new Road(1, "Путь 1", "", 10));
        roads.add(new Road(2, "Путь 2", "", 20));
        roads.add(new Road(3, "Путь 3", "", 30));
        roadTypes.put(roadType, roads);
        roadType = new RoadType(2, "Тип 1");
        roads = new ArrayList<Road>();
        roads.add(new Road(4, "Путь 4", "", 40));
        roads.add(new Road(5, "Путь 5", "", 50));
        roads.add(new Road(6, "Путь 6", "", 60));
        roadTypes.put(roadType, roads);
    }


    public ArrayList<RoadType> getRoadTypes() {
        Set<RoadType> tr = roadTypes.keySet();
        ArrayList<RoadType> atr = new ArrayList<RoadType>();
        Iterator<RoadType> it = tr.iterator();
        while (it.hasNext()) {
            atr.add(it.next());
        }
        return atr;
    }

    public ArrayList<Road> getRoadsByType(RoadType roadType) {
        return roadTypes.get(roadType);
    }

    public ArrayList<Route> getRoutes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateRoute(Route route) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeRoute(Route route) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Schedule> getSchedules() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateSchedule(Schedule schedule) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeSchedule(Schedule schedule) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Schedule> getSchedulesByRoute(Route route) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Route getRouteBySchedule(Schedule schedule) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addRouteInSchedule(Route route, Schedule schedule) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrains() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateTrain(Train train) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Route getRouteByTrain(Train train) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addTrainInRoad(Train train, Road road) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeTrainFromRoad(Train train, Road road) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Train getTrainByRoad(Road road) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Car> getCars() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateCar(Car car) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeCar() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Car> getCarsByTrain(Train train) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Car> getCarsByRoad(Road train) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Car> getCarsByAnotherLocation(CarAnotherLocation carAnotherLocation) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCarInTrain(Car road, Train train) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCarInRoad(Car car, Road road) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCarInAnotherLocation(Car car, CarAnotherLocation carAnotherLocation) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
