package logic;

import rzd.model.objects.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

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
//        testGetRoutes();
//        testGetSheduleTypes();
//        testAddRoute();
//        testUpdateRoute();
//        testGetParentsTypes();
//        testAddUpdateCar();
//        testRoads();
//        testDate();
        test();
    }


    public static void test() {
        manager.test();
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
        SheduleType st = new SheduleType(1, "nonPair");
        Shedule sf = new Shedule(9, time, time, time, st, new int[]{1,2,34,4});
        Shedule sb = new Shedule(9, time, time, time, st, new int[]{0,6,1,3});
        Route r = new Route(9, "love", "love", "Evgen", "Ekaterina", sf, sb, true, 101, 101);
        manager.addRoute(r);
    }

    public static void testUpdateRoute() {
        Time time = new Time(new Date().getTime());
        SheduleType st = new SheduleType(1, "nonPair");
        Shedule sf = new Shedule(9, time, time, time, st, new int[]{1999});
        Shedule sb = new Shedule(9, time, time, time, st, new int[]{0,6,1,3});
        Route r = new Route(5, "love", "hate", "Evgen", "Ekaterina", sf, sb, true, 101, 101);
        manager.updateRoute(r);
    }

    public static void testGetParentsTypes() {
        for(CarType ct : manager.getCarParentTypes()) {
            System.out.println(ct.getType());
        }
    }

    public static void testAddUpdateCar() {
        Car car = new Car(12312345, "1", new CarLocation(1, "1"), new CarType(1, "1"), "c", "g", "gp", "a", "ed", "red", true, 101, 100, 101, 100); 
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
        System.out.println(manager.getDate().toString());
    }
}
