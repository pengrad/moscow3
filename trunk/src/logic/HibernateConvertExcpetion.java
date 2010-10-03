package logic;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * User: Стас
 * Date: 03.10.2010
 * Time: 17:36:22
 */

public class HibernateConvertExcpetion extends RuntimeException {

    public HibernateConvertExcpetion(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Ошибка перевода объекта Hibernate в модель!";
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        getCause().printStackTrace(new PrintWriter(sw));
        return sw.getBuffer().toString();
    }


}
