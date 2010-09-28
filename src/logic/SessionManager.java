package logic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SessionManager {

    private SessionManager() {
    }

//    private static SessionManager instance = new SessionManager();

    private static SessionFactory sessionFactory;
    private static ThreadLocal<Session> localSession;

    public static void init() throws HibernateInitializeException {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            localSession = new ThreadLocal<Session>();
        } catch (Throwable e) {
            throw new HibernateInitializeException(e);
        }
    }

    public static Session getSession() throws HibernateException {
        Session session = localSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            localSession.set(session);
        }
        return session;
    }

    public static void closeSession() throws HibernateException {
        Session session = localSession.get();
        localSession.set(null);
        if (session != null) {
            session.close();
        }
    }

    public static void beginTran() {
        getSession().beginTransaction();
    }

    public static void rollback() {
        getSession().getTransaction().rollback();
    }

    public static void commit() {
        localSession.get().getTransaction().commit();
    }

    public static <T> T getEntityById(T t, Serializable id) throws DatabaseException {
        try {
            Object o = getSession().get(t.getClass(), id);
            return (T) o;
        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    public static boolean saveOrUpdateEntities(Object... entities) throws DatabaseException {
        try {
            // todo возможно вставить сюда свою внутренюю транзакцию и закрывать ее по выходу
            for (Object o : entities) {
                getSession().saveOrUpdate(o);
            }
            return true;
        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

    public static <T> Collection<T> getAllObjects(T t) throws DatabaseException {
        ArrayList<T> res = null;
        try {
            List list = getSession().createCriteria(t.getClass()).list();
            if (list != null) {
                res = new ArrayList<T>(list.size());
                for (Object o : list) {
                    res.add((T) o);
                }
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return res;
    }

    public static boolean deleteEntities(Object... entities) throws DatabaseException {
        try {
            // todo возможно вставить сюда свою внутренюю транзакцию и закрывать ее по выходу
            for (Object o : entities) {

                getSession().delete(o);
            }
            return true;
        } catch (HibernateException e) {
            throw new DatabaseException(e);
        }
    }

}