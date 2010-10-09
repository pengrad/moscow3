package logic;

import java.sql.Time;
import java.sql.Timestamp;
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

    public static Collection<Timestamp> getDates(Date dateFrom, Collection<Integer> days, int count, int calendarType) {
        if (calendarType < 0 || calendarType >= Calendar.ZONE_OFFSET) {
            return null;
        }
        ArrayList<Timestamp> dates = new ArrayList<Timestamp>(count);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dateFrom);
        while (dates.size() < count) {
            if (days.contains(getDay(c.getTime()))) {
                dates.add(new Timestamp(c.getTimeInMillis()));
            }
            c.add(calendarType, 1);
        }
        return dates;
    }

    public static Timestamp getDatePlusTime(Date date, Time time) {
        return getDatePlusMinusTime(date, time, true);
    }

    public static Timestamp getDateMinusTime(Date date, Time time) {
        return getDatePlusMinusTime(date, time, false);
    }

    private static Timestamp getDatePlusMinusTime(Date date, Time time, boolean plus) {
        return plus ? new Timestamp(date.getTime() + time.getTime()) : new Timestamp(date.getTime() - time.getTime());
    }
}
