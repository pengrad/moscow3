package logic;

import logic.HibernateInitializeException;
import logic.SessionManager;
import rzd.model.BusinessLogic;
import rzd.objects.*;

import java.util.ArrayList;
//import org.hibernate.HibernateException;

public class BusinessManager implements BusinessLogic {

    private static final BusinessManager instance = new BusinessManager();

    public static BusinessManager get() {
        return instance;
    }

    private BusinessManager(){}

    public ArrayList<RoadType> getRoadTypes() {
        return null;
    }

    public ArrayList<Road> getRoadsByType(RoadType roadType) {
        return null;
    }

    public ArrayList<Route> getRoutes() {
        return null;
    }

    public boolean updateRoute(Route route) {
        return false;
    }

    public boolean removeRoute(Route route) {
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