package logic;

import logic.model.RouteEntity;
import logic.model.SheduleDaysEntity;
import logic.model.SheduleEntity;
import logic.model.SheduleTypeEntity;
import rzd.model.objects.Route;
import rzd.model.objects.Shedule;
import rzd.model.objects.SheduleType;

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
        if (objects == null) return null;
        ArrayList<Route> list = new ArrayList<Route>(objects.size());
        for (RouteEntity re : objects) {
            SheduleTypeEntity ste = re.getSheduleForward().getSheduleType();
            SheduleType stF = new SheduleType(ste.getIdSheduleType(), ste.getcSheduleType());
            ste = re.getSheduleBack().getSheduleType();
            SheduleType stB = new SheduleType(ste.getIdSheduleType(), ste.getcSheduleType());
            SheduleEntity se = re.getSheduleForward();
            int[] days = null;
            Collection<SheduleDaysEntity> sde = se.getSheduleDays();
            if (sde != null && sde.size() > 0) {
                days = new int[sde.size()];
                for (int i = 0; i < sde.size(); i++)
                    days[i] = sde.toArray(new SheduleDaysEntity[1])[i].getDay();
            }
            Shedule sF = new Shedule(se.getIdShedule(), se.getTimeFrom(), se.getTimeTo(), se.getTimeInWay(), stF, days);
            se = re.getSheduleBack();
            sde = se.getSheduleDays();
            if (sde != null && sde.size() > 0) {
                days = new int[sde.size()];
                for (int i = 0; i < sde.size(); i++)
                    days[i] = sde.toArray(new SheduleDaysEntity[1])[i].getDay();
            }
            Shedule sB = new Shedule(se.getIdShedule(), se.getTimeFrom(), se.getTimeTo(), se.getTimeInWay(), stB, days);
            Route route = new Route(re.getIdRoute(), re.getNumberForward(), re.getNumberBack(), re.getCityFrom(), re.getCityTo(), sF, sB);
            list.add(route);
        }
        SessionManager.closeSession();
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