package logic;

import logic.HibernateInitializeException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionManager {

    private static final SessionFactory sessionFactory;
    private static final ThreadLocal<Session> localSession;
    
    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            localSession = new ThreadLocal<Session>();
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка соединения с базой - " + e.getMessage(), e);
        }
    }

    public static Session openSession() throws HibernateException{
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
        if(session != null) {
            session.close();
        }
    }

}