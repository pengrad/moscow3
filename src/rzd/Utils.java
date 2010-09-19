package rzd;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

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
        StringTokenizer t = new StringTokenizer(date, ".");
        int dd = new Integer(t.nextToken());
        int mm = new Integer(t.nextToken());
        int yy = new Integer(t.nextToken());
        return new GregorianCalendar(yy, mm, dd).getTime();
    }

    public static String convertTimeToStr(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(time);
    }

    public static String convertDateTimeToStr(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return sdf.format(dt);
    }

    public static Date convertStrToTime(String time) {
        StringTokenizer t = new StringTokenizer(time, ":");
        int hh = new Integer(t.nextToken());
        int mm = new Integer(t.nextToken());
        return new GregorianCalendar(0, 0, 0, hh, mm).getTime();
    }

    public static Date convertStrToDateTime(String dt) {
        StringTokenizer t = new StringTokenizer(dt);
        int dd = new Integer(t.nextToken("."));
        int MM = new Integer(t.nextToken("."));
        int yy = new Integer(t.nextToken(" "));
        int hh = new Integer(t.nextToken(":"));
        int mm = new Integer(t.nextToken());

        return new GregorianCalendar(yy, MM, dd, hh, mm).getTime();
    }

    public static String getDateByDateBeginAndPeriod(Date dateBegin, int dayMove, int dayStop) {
        return "";
    }

    public static boolean searchByTable(JTable t, int column, String value) {
        if (t == null || value == null || column < 0 || column > t.getColumnCount()) return false;
        int rowCount = t.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (t.getValueAt(i, column).equals(value)) {
                Rectangle rect = t.getCellRect(i, column, false);
                JViewport viewport = (JViewport) t.getParent();
                viewport.setViewPosition(new Point((int) rect.getX(), (int) rect.getY()));
                t.setRowSelectionInterval(i, i);
                return true;
            }
        }
        return false;
    }
}
