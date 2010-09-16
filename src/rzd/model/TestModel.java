package rzd.model;

import rzd.model.objects.*;

import java.util.ArrayList;

public class TestModel implements BusinessLogic {
    ArrayList<RoadType> roadTypes = new ArrayList<RoadType>();
    ArrayList<Road> roads = new ArrayList<Road>();

    public TestModel() {
    RoadType roadType=new RoadType(1,"rt 1");
    }

    public ArrayList<RoadType> getRoadTypes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Road> getRoadsByType(RoadType roadType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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

    public ArrayList<Train> getTrains() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean updateTrain(Train train) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Route getRouteByTrain(Train train) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addTrainInRoad(Road road, Train train) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeTrainFromRoad(Road road, Train train) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ArrayList<Train> getTrainsByRoad() {
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

    public boolean addCarInTrain(Train train, Car road) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCarInRoad(Train train, Car car) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addCarInAnotherLocation(CarAnotherLocation carAnotherLocation, Car car) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
