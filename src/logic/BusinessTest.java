package logic;

import rzd.model.objects.Route;
import rzd.model.objects.Shedule;
import rzd.model.objects.SheduleType;

import java.util.ArrayList;

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
        testGetRoutes();
        testGetSheduleTypes();
        testAddRoute();
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
//        Shedule sf = new Shedule(9, );
//        Shedule sb;
//        Route r = new Route(9, "love", "love", "Evgen", "Ekaterina", sf, sb);
    }

}
