package logic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionManager {

    private SessionManager() {
    }        

    private static SessionManager instance = new SessionManager();
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

    public static Session openSession() throws HibernateException {
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

}