package logic;

import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User: Стас
 * Date: 06.10.2010
 * Time: 0:05:44
 */

public class HibernateUtils {

    public static Date getDate(Session session) {
        Timestamp t = Timestamp.valueOf(session.createSQLQuery("select CURRENT_TIMESTAMP;").uniqueResult().toString());
        return new Date(t.getTime());
    }


}
