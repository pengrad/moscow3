package logic;

import rzd.model.objects.Route;
import rzd.model.objects.ScheduleType;

import java.util.ArrayList;

/**
 * User: Стас
 * Date: 28.09.2010
 * Time: 22:18:54
 */

public interface BusinessLogic {

    public ArrayList<Route> getRoutes();

    public Route getRouteById(int idRoute);

    public ArrayList<ScheduleType> getSheduleTypes();

    public boolean addRoute(Route r);

    public boolean updateRoute(Route r);

}
