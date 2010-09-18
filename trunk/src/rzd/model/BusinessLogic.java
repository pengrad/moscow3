package rzd.model;

import java.util.ArrayList;
import java.util.Date;

import rzd.model.objects.*;

public interface BusinessLogic {

    public ArrayList<RoadType> getRoadTypes();

    public ArrayList<Road> getRoadsByType(RoadType roadType);

    public Road getRoadByTrain(Train train);

    public Road getRoadByCar(Car car);

    public ArrayList<Route> getRoutes();

    public boolean addRoute(Route route);

    public boolean updateRoute(Route route);

    public boolean removeRoute(Route route);

    public ArrayList<Schedule> getSchedules();

    public boolean addSchedule(Schedule schedule, Route route);

    public boolean updateSchedule(Schedule schedule, Route route);

    public boolean removeSchedule(Schedule schedule);

    public ArrayList<Schedule> getSchedulesByRoute(Route route);

    public Route getRouteBySchedule(Schedule schedule);

    public ArrayList<Train> getTrains(Date dBegin, Date dEnd);

    //Отправляющиеся поезда за часов
    public ArrayList<Train> getTrainsGoing();

    //Прибывающие поезда за часов
    public ArrayList<Train> getTrainsArriving();

    //Отправленные поезда сегодня
    public ArrayList<Train> getTrainsGoneToday();

    //Прибывшие поезда сегодня
    public ArrayList<Train> getTrainsArrivedToday();

    public boolean addTrain(Train train, Schedule route);

    public boolean updateTrain(Train train, Schedule route);

    public Route getRouteByTrain(Train train);

    public boolean addTrainInRoad(Train train, Road road);

    public boolean removeTrainFromRoad(Train train, Road road);

    public Train getTrainByRoad(Road road);

    public ArrayList<Car> getCars();

    public boolean addCar(Car car, Location carLocation);

    public boolean updateCar(Car car, Location carLocation);

    public boolean removeCar(Car car);

    public ArrayList<Car> getCarsByTrain(Train train);

    public ArrayList<Car> getCarsByRoad(Road train);

    public ArrayList<Car> getCarsByAnotherLocation(LocationAnother carAnotherLocation);

    public boolean addCarInTrain(Car road, Train train);

    //  public boolean removeCarFromTrain(Train train, Car car);

    public boolean addCarInRoad(Car car, Road road);

    // public boolean removeCarFromRoad(Road road, Car car);

    public boolean addCarInAnotherLocation(Car car, LocationAnother carAnotherLocation);

    //  public boolean removeCarFromAnotherLocation(LocationAnother carAnotherLocation, Car car);

}
