package logic;

import rzd.model.objects.Route;
import rzd.model.objects.SheduleType;

import java.util.ArrayList;

/**
 * User: Стас
 * Date: 28.09.2010
 * Time: 22:18:54
 */

public interface BusinessLogic {

    public static final int NONPAIR = 1;
    public static final int PAIR = 2;
    public static final int DAYS_WEEK = 3;
    public static final int DAYS_MONTH = 4;

    public ArrayList<Route> getRoutes();

    public Route getRouteById(int idRoute);

    public ArrayList<SheduleType> getSheduleTypes();

    public boolean addRoute(Route r);

    public boolean updateRoute(Route r);

}
