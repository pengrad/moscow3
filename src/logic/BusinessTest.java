package logic;

import logic.model.SheduleEntity;
import logic.model.TrainEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rzd.model.objects.*;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/**
 * User: Стас
 * Date: 28.09.2010
 * Time: 23:46:25
 */

public class BusinessTest {

    static BusinessManager manager;

    static {
        try {
            manager = BusinessManager.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws HibernateInitializeException {
        testGetCars();
//        testGetRoutes();
//        testGetSheduleTypes();
//        testAddRoute();
//        testUpdateRoute();
//        testGetParentsTypes();
//        testAddUpdateCar();
//        testRoads();
//        testDate();
//        test();
//        testGoingTrains();
//        testArrivingTrains();
//        testMakeTrainForGoing();
//        testGetTrainsOnRoads();
//        testRepairTypes();
//        testUpdateRepair();
//        testGetFreeCars();
//        testRoadReadyForTrain();
//        testTransaction();
    }


    public static void test() {
        ArrayList l = null;
        for(Object o : l) {
            System.out.println(o);
        }
    }

    public static void testGetCars() {
        for(Car car : manager.getCars()) {
            System.out.println(car.getNumber() + " = " + car.getCarLocation().getLocation());
        }
    }

    public static void testGetRoutes() {
        ArrayList<Route> r = manager.getRoutes();
        for(Route rr : r) {
            System.out.println(rr.getCityFrom() + " - " + rr.getCityTo() + " : " + rr.getNumberForward() + "/" + rr.getNumberBack());
        }
    }

    public static void testGetSheduleTypes() {
        ArrayList<SheduleType> r = manager.getSheduleTypes();
        for(SheduleType rr : r) {
            System.out.println(rr.getId() + " - " + rr.getName());
        }
    }

    public static void testAddRoute() {
        Time time = new Time(new Date().getTime());
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.HOUR_OF_DAY, 5);
        Time tback = new Time(gc.getTimeInMillis());
        SheduleType st = new SheduleType(1, "nonPair");
        Shedule sf = new Shedule(9, time, time, 10, 11, st, null);//new int[]{1,2,34,4});
        Shedule sb = new Shedule(9, tback, tback, 11, 20, st, null);//new int[]{0,6,1,3});
        Route r = new Route(9, "тест", "тест", "Москва", "Питер", sf, sb, true, 101, 101);
        manager.addRoute(r);
    }

    public static void testUpdateRoute() {
        Time time = new Time(new Date().getTime());
        SheduleType st = new SheduleType(1, "nonPair");
        Shedule sf = new Shedule(9, time, time, 38, 11, st, new int[]{1, 2, 3, 4});
        Shedule sb = new Shedule(9, time, time, 36, 66, st, null);
        Route r = new Route(5, "love!!!", "hate!!!", "Evgen", "Ekaterina", sf, sb, true, 101, 101);
        manager.updateRoute(r);
    }

    public static void testGetParentsTypes() {
        for(CarType ct : manager.getCarParentTypes()) {
            System.out.println(ct.getType());
        }
    }

    public static void testAddUpdateCar() {
        Car car = new Car(12312345, "1", new CarLocation(1, "1"), new CarType(1, "1"), "c", "g", "gp", "a", "ed", "red", true, 101, 100, 101, 100, 0);
        System.out.println(manager.addCar(car));
        car.setBodyColor("green");
        System.out.println(manager.editCar(car));
    }

    public static void testRoads() {
        for(RoadType rt : manager.getRoadTypes()) {
            System.out.println(rt.getId() + " : " + rt.getName());
            for(Road r : manager.getRoadsByType(new RoadType(rt.getId(), rt.getName()))) {
                System.out.println("    " + r.getName());
            }
        }
    }

    public static void testDate() {
        System.out.println(manager.getCurrentDate().toString());
    }

    public static void testGoingTrains() {
        for(Train te : manager.getGoingTrains(1)) {
            System.out.println(te.getDtDeparture() + " " + te.getDtDestination());
        }
    }

    public static void testArrivingTrains() {
        for(Train te : manager.getArrivingTrains(1)) {
            System.out.println(te.getDtDeparture() + " " + te.getDtDestination());
        }
    }

    public static void testMakeTrainForGoing() {
        try {
            ArrayList<Car> cars = new ArrayList<Car>();
            cars.add(new Car(12312345));
            manager.makeTrainForGoing(new Train(128, new Road(14), "Petrov", cars, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testGetTrainsOnRoads() {
        for(Train t :manager.getTrainsOnRoads()) {
            System.out.println(t.getChief() + " - " + t.getRoad().getName());
        }
    }

    public static void testGetTrainByID() {
        
    }

    public static void testRepairTypes() {
        for(RepairType rt : manager.getRepairTypes()) {
            System.out.println(rt.getType());
        }
        System.out.println("sleep/////");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(RepairType rt : manager.getRepairTypes()) {
            System.out.println(rt.getType());
        }
        for(RepairType rt : manager.getRepairTypes()) {
            System.out.println(rt.getType());
        }
        for(RepairType rt : manager.getRepairTypes()) {
            System.out.println(rt.getType());
        }
    }

    public static void testUpdateRepair() {
        manager.updateRepair(new Repair(11, new RepairType(1,""), new Car(34324242, new CarLocation(1, ""),
                new CarType(1, "")), null, new Timestamp(new Date().getTime()), null, "sdfsdfsdfs"));
    }

    public static void testGetFreeCars() {
        for(Car c : manager.getFreeCars()) {
            System.out.println(c.getNumber() + " - " + c.getCarLocation().getLocation());
        }
    }

    public static void testRoadReadyForTrain() {
        System.out.println(manager.isRoadReadyForTrain(new Train(128, null, null, null, true), new Road(14)));
    }

    public static void testTransaction() {
        Session s = manager.getSession();
        Transaction t = s.beginTransaction();
        Transaction t1 = s.beginTransaction();
        t1.commit();
        t.commit();

    }

}
