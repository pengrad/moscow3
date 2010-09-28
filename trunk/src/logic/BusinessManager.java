package logic;

import logic.model.*;
import rzd.model.objects.*;

import java.util.ArrayList;
import java.util.Collection;

public class BusinessManager implements BusinessLogic {

    private static BusinessManager instance;

    public synchronized static BusinessManager get() throws HibernateInitializeException {
        if (instance == null) instance = new BusinessManager();
        return instance;
    }

    private BusinessManager() throws HibernateInitializeException {
        SessionManager.init();
    }

    private <H, M> void convertHB2Model(Collection<H> src) {
//        Object o = null; o.getClass().getField("ss").get()
    }

    public ArrayList<Route> getRoutes() {
        Collection<RouteEntity> objects = SessionManager.getAllObjects(new RouteEntity());
        SessionManager.closeSession();
        if (objects == null) return null;
        ArrayList<Route> list = new ArrayList<Route>(objects.size());
        for (RouteEntity re : objects) {
//            Route route = new Route();
        }
        return list;
    }

    public Route getRouteById(int idRoute) {
        return null;
    }

    public ArrayList<SheduleType> getSheduleTypes() {
        return null;
    }

    public boolean addRoute(Route r) {
        return false;
    }

    public boolean updateRoute(Route r) {
        return false;
    }
}