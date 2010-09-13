package logic;

import logic.HibernateInitializeException;
import logic.SessionManager;

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
        session = SessionManager.get();
    }
}