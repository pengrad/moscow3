package logic;

import logic.model.TrainEntity;
import rzd.model.objects.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * User: Стас
 * Date: 28.09.2010
 * Time: 22:18:54
 */

public interface BusinessLogic {

    // Типы расписаний
    public static final int NONPAIR = 1;
    public static final int PAIR = 2;
    public static final int DAYS_WEEK = 3;
    public static final int DAYS_MONTH = 4;

    // Статусы поезда
    public static final int PLANNED = 1;
    public static final int MAKED = 2;
    public static final int IN_WAY = 3;
    public static final int ARRIVED = 4;
    public static final int DESTROYED = 5;

    // Местоположения вагона
    public static final int UNKNOWN = 1;
    public static final int IN_TRAIN = 2;
    public static final int ON_ROAD = 3;
    public static final int REPAIR = 4;

    public Date getCurrentDate();

    public ArrayList<Route> getRoutes();

    public Route getRouteById(int idRoute);

    public ArrayList<SheduleType> getSheduleTypes();

    public boolean addRoute(Route r);

    public boolean updateRoute(Route r);

    public ArrayList<Car> getCars();

    public Car getCarByNumber(int carNumber);

    public ArrayList<CarType> getCarParentTypes();

    public ArrayList<CarType> getCarSubTypes(CarType parentType);

    public CarType getCarParentType(CarType subType);

    public boolean addCar(Car car);

    public boolean editCar(Car car);

    public ArrayList<RoadType> getRoadTypes();

    public ArrayList<Road> getRoadsByType(RoadType roadType);

    public Train getTrainById(int idTrain);

    public ArrayList<Train> getTrainsOnRoads();

    public ArrayList<Train> getGoingTrains(int forHours);

    public ArrayList<Train> getArrivingTrains(int forHours);

    public boolean makeTrainForGoing(Train train) throws Exception;
}
