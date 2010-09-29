package rzd.model;

import java.sql.Date;

import rzd.model.objects.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class TestModel implements BusinessLogic_old {

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
        roads.add(new Road(3, "Путь 3", "", 30));
        roads.add(new Road(4, "Путь 3", "", 30));
        roads.add(new Road(5, "Путь 3", "", 30));
        roads.add(new Road(6, "Путь 3", "", 30));
        roads.add(new Road(7, "Путь 3", "", 30));
        roads.add(new Road(8, "Путь 3", "", 30));
        roads.add(new Road(9, "Путь 3", "", 30));
        roads.add(new Road(10, "Путь 3", "", 30));
        roads.add(new Road(11, "Путь 3", "", 30));

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

    public Road getRoadByTrain(Train train) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Road getRoadByCar(Car car) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Route> getRoutes() {
        ArrayList<Route> r = new ArrayList<Route>(5);
        r.add(new Route(12, "", "", "", "", null, null));
        r.add(new Route(12, "", "", "", "", null, null));
        r.add(new Route(12, "", "", "", "", null, null));
        r.add(new Route(12, "", "", "", "", null, null));
        return r;  //To change body of implemented methods use File | Settings | File Templates.
//   return null;
    }

    public boolean addRoute(Route route) {
        System.out.println("add");
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateRoute(Route route) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeRoute(Route route) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Shedule> getSchedules() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addSchedule(Shedule shedule, Route route) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateSchedule(Shedule shedule, Route route) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeSchedule(Shedule shedule) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Shedule> getSchedulesByRoute(Route route) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Route getRouteBySchedule(Shedule shedule) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrains(java.util.Date dBegin, java.util.Date dEnd) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrainsGoing() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrainsArriving() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrainsGoneToday() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrainsSentToday() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrainsArrivedToday() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addRouteInSchedule(Route route, Shedule shedule) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrains() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addTrain(Train train, Shedule route) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateTrain(Train train, Route route) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateTrain(Train train, Shedule shedule) {
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
        return new Train(1, new Date(new java.util.Date().getTime()), new Date(new java.util.Date().getTime()), "");
        //  return null;
    }

    public ArrayList<Car> getCars() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCar(Car car, Location carLocation) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateCar(Car car, Location carLocation) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateCar(Car car) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeCar(Car car) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Car> getCarsByTrain(Train train) {
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(new Car(00000000));
        cars.add(new Car(11111111));
        cars.add(new Car(333));
        return cars;
    }

    public ArrayList<Car> getCarsByRoad(Road train) {
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(new Car(12345678));
        cars.add(new Car(22345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(32345678));
        cars.add(new Car(111));

        return cars;
    }

    public ArrayList<Car> getCarsByAnotherLocation(LocationAnother carAnotherLocation) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCarInTrain(Car road, Train train) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCarInRoad(Car car, Road road) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCarInAnotherLocation(Car car, LocationAnother carAnotherLocation) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
