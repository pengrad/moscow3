package rzd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 17.09.2010
 * Time: 15:38:08
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    public static String convertDateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    public static Date convertStrToDate(String date) {
        String[] d = date.split(".");
        int dd = new Integer(d[0]);
        int mm = new Integer(d[1]);
        int yy = new Integer(d[2]);
        return new GregorianCalendar(yy, mm, dd).getTime();
    }

    public static String convertTimeToStr(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(time);
    }

    public static Date convertStrToTime(String time) {
        String[] d = time.split(":");
        int hh = new Integer(d[0]);
        int mm = new Integer(d[1]);
        return new GregorianCalendar(0, 0, 0, hh, mm).getTime();
    }

}
