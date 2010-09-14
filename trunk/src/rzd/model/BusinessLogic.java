package rzd.model;

import java.util.ArrayList;

import rzd.objects.*;

public interface BusinessLogic {

    public ArrayList<RoadType> getRoadTypes();

    public ArrayList<Road> getRoadsByType(RoadType roadType);

    public ArrayList<Route> getRoutes();

    public boolean updateRoute(Route route);

    public boolean removeRoute(Route route);

    public ArrayList<Train> getTrains();

    public boolean updateTrain(Train train);

    public Route getRouteByTrain(Train train);

    public boolean addTrainInRoad(Road road, Train train);

    public boolean removeTrainFromRoad(Road road, Train train);

    public ArrayList<Train> getTrainsByRoad();

    public ArrayList<Car> getCars();

    public boolean updateCar(Car car);

    public boolean removeCar();

    public ArrayList<Car> getCarsByTrain(Train train);

    public ArrayList<Car> getCarsByRoad(Road train);

    public ArrayList<Car> getCarsByAnotherLocation(CarAnotherLocation carAnotherLocation);

    public boolean addCarInTrain(Train train, Car road);

    //  public boolean removeCarFromTrain(Train train, Car car);

    public boolean addCarInRoad(Train train, Car car);

    // public boolean removeCarFromRoad(Road road, Car car);

    public boolean addCarInAnotherLocation(CarAnotherLocation carAnotherLocation, Car car);

    //  public boolean removeCarFromAnotherLocation(CarAnotherLocation carAnotherLocation, Car car);


}
