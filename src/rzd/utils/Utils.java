package rzd.utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
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

    public static Time convertStrToTime(String time) {
        StringTokenizer t = new StringTokenizer(time, ":");
        int hh = new Integer(t.nextToken());
        int mm = new Integer(t.nextToken());
        return new Time(new GregorianCalendar(0, 0, 0, hh, mm).getTime().getTime());
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
        if (t == null || value == null || column < 0 || column > t.getColumnCount()) {
            return false;
        }
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

    public static String convertMasToDayOfWeek(int[] days) {
        String res = "";
        for (int i = 0; i < days.length; i++) {
            if (days[i] == 0) {
                res = res + "ПН ";
            }
            if (days[i] == 1) {
                res = res + "ВТ ";
            }
            if (days[i] == 2) {
                res = res + "СР ";
            }
            if (days[i] == 3) {
                res = res + "ЧТ ";
            }
            if (days[i] == 4) {
                res = res + "ПТ ";
            }
            if (days[i] == 5) {
                res = res + "СБ ";
            }
            if (days[i] == 6) {
                res = res + "ВС ";
            }
        }
        return res;
    }

    public static int[] convertDayOfWeekToMas(String days) {
        StringTokenizer tk = new StringTokenizer(days, " ");
        int[] res = new int[tk.countTokens()];
        int i = 0;
        while (tk.hasMoreElements()) {
            String t = tk.nextElement().toString();
            if (t.equals("ПН")) {
                res[i] = 0;
            }
            if (t.equals("ВТ")) {
                res[i] = 1;
            }
            if (t.equals("СР")) {
                res[i] = 2;
            }
            if (t.equals("ЧТ")) {
                res[i] = 3;
            }
            if (t.equals("ПТ")) {
                res[i] = 4;
            }
            if (t.equals("СБ")) {
                res[i] = 5;
            }
            if (t.equals("ВС")) {
                res[i] = 6;
            }
            i++;
        }
        return res;
    }

    public static String convertMasToStr(int[] mas) {
        String res = "";
        for (int i = 0; i < mas.length; i++) {
            res = res + mas[i] + " ";
        }
        return res;
    }

    public static int[] convertStrToMas(String s) {
        StringTokenizer tk = new StringTokenizer(s, " ");
        int[] res = new int[tk.countTokens()];
        int i = 0;
        while (tk.hasMoreElements()) {
            res[i] = new Integer(tk.nextElement().toString());
            i++;
        }
        return res;
    }
}
