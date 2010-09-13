package logic;

import logic.HibernateInitializeException;
import logic.SessionManager;
import org.hibernate.HibernateException;

public class BusinessManager {

    private static final BusinessManager instance;
    private SessionManager session;

    static {
        try {
            instance = new BusinessManager();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static BusinessManager get() {
        return instance;
    }

    private BusinessManager() throws HibernateInitializeException {
//        session = SessionManager.get();
    }

    public <T> T getEntityById(T t, int id) {
//        Object o = getSession().get(t.getClass(), id);
//        return (T) o;
        return null;
    }

    public boolean saveOrUpdateEntities(Object... entities) {
        try {
//            beginTran();
            for (Object o : entities) {
//                getSession().saveOrUpdate(o);
            }
//            commit();
            return true;
        } catch (HibernateException e) {
//            rollback();
            return false;
        }
    }
}