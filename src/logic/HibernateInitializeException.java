package logic;

import java.io.PrintWriter;
import java.io.StringWriter;

public class HibernateInitializeException extends Exception {

    public HibernateInitializeException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Ошибка инициализации Hibernate";
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        getCause().printStackTrace(new PrintWriter(sw));
        return sw.getBuffer().toString();
    }
}
