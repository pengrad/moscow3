package logic;

import rzd.model.objects.Route;
import rzd.model.objects.Shedule;
import rzd.model.objects.SheduleType;

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
        testAddRoute();
//        testUpdateRoute();
    }

    public static void testGetRoutes() {
        ArrayList<Route> r = manager.getRoutes();
        for(Route rr : r) {
            System.out.println(rr.getPointDeparture() + " - " + rr.getPointDestination() + " : " + rr.getNumberForward() + "/" + rr.getNumberBack());
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
        Route r = new Route(9, "love", "love", "Evgen", "Ekaterina", sf, sb);
        manager.addRoute(r);
    }

    public static void testUpdateRoute() {
        Time time = new Time(new Date().getTime());
        SheduleType st = new SheduleType(1, "nonPair");
        Shedule sf = new Shedule(9, time, time, time, st, new int[]{1999});
        Shedule sb = new Shedule(9, time, time, time, st, new int[]{0,6,1,3});
        Route r = new Route(5, "love", "hate", "Evgen", "Ekaterina", sf, sb);
        manager.updateRoute(r);
    }

}
