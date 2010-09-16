package rzd.model;

import java.util.ArrayList;

import rzd.model.objects.*;

public interface BusinessLogic {

    public ArrayList<RoadType> getRoadTypes();

    public ArrayList<Road> getRoadsByType(RoadType roadType);

    public ArrayList<Route> getRoutes();

    public boolean updateRoute(Route route);

    public boolean removeRoute(Route route);

    public ArrayList<Schedule> getSchedules();

    public boolean updateSchedule(Schedule schedule, Route route);

    public boolean removeSchedule(Schedule schedule);

    public ArrayList<Schedule> getSchedulesByRoute(Route route);

    public Route getRouteBySchedule(Schedule schedule);

    public boolean addRouteInSchedule(Route route, Schedule schedule);

    public ArrayList<Train> getTrains();

    public boolean updateTrain(Train train);

    public Route getRouteByTrain(Train train);

    public boolean addTrainInRoad(Train train, Road road);

    public boolean removeTrainFromRoad(Train train, Road road);

    public Train getTrainByRoad(Road road);

    public ArrayList<Car> getCars();

    public boolean updateCar(Car car);

    public boolean removeCar();

    public ArrayList<Car> getCarsByTrain(Train train);

    public ArrayList<Car> getCarsByRoad(Road train);

    public ArrayList<Car> getCarsByAnotherLocation(CarAnotherLocation carAnotherLocation);

    public boolean addCarInTrain(Car road, Train train);

    //  public boolean removeCarFromTrain(Train train, Car car);

    public boolean addCarInRoad(Car car, Road road);

    // public boolean removeCarFromRoad(Road road, Car car);

    public boolean addCarInAnotherLocation(Car car, CarAnotherLocation carAnotherLocation);

    //  public boolean removeCarFromAnotherLocation(CarAnotherLocation carAnotherLocation, Car car);

}
