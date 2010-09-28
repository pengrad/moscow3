package logic;

import logic.model.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import rzd.model.BusinessLogic_old;
import rzd.model.objects.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
        return null;
    }

    public Route getRouteById(int idRoute) {
        return null;
    }

    public ArrayList<ScheduleType> getSheduleTypes() {
        return null;
    }

    public boolean addRoute(Route r) {
        return false;
    }

    public boolean updateRoute(Route r) {
        return false;
    }
}