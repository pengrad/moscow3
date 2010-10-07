package logic;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * User: Стас
 * Date: 07.10.2010
 * Time: 21:56:11
 */

public class DateUtils {

    public static int getHours(Time t) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(t);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinutes(Time t) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(t);
        return c.get(Calendar.MINUTE);
    }

}
