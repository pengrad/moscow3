package logic;

import logic.HibernateInitializeException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionManager {

    private static SessionManager instance;

    public static SessionManager get() throws HibernateInitializeException {
        if (instance == null) instance = new SessionManager();
        return instance;
    }

    private final SessionFactory sessionFactory;
    private final ThreadLocal<Session> localSession;
    private static final Logger log = Logger.getAnonymousLogger();

    private SessionManager() throws HibernateInitializeException {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            localSession = new ThreadLocal<Session>();
        } catch (ExceptionInInitializerError e) {
            throw new HibernateInitializeException(e);
        }
    }

    public Session getSession() {
        Session session = localSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            localSession.set(session);
        }
        return session;
    }

    public void close() {
        try {
            getSession().close();
        } catch (Throwable e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        localSession.set(null);
    }

    protected void beginTran() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
    }

    public <T> T getEntityById(T t, int id) {
        Object o = getSession().get(t.getClass(), id);
        return (T) o;
    }

    public boolean saveOrUpdateEntities(Object... entities) {
        try {
            beginTran();
            for (Object o : entities) {
                getSession().saveOrUpdate(o);
            }
            commit();
            return true;
        } catch (HibernateException e) {
            rollback();
            return false;
        }
    }
}