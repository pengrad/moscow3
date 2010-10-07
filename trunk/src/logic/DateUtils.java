package logic;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: Стас
 * Date: 07.10.2010
 * Time: 21:56:11
 */

public class DateUtils {

    public static int getHours(Date date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinutes(Date date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getDay(Date date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static Collection<Timestamp> getDates(Date dateFrom, Collection<Integer> days, int count) {
        ArrayList<Timestamp> dates = new ArrayList<Timestamp>(count);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dateFrom);
        while(dates.size() < count) {
            if(days.contains(getDay(dateFrom))) {
                dates.add(new Timestamp(dateFrom.getTime()));
            }
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }

}
