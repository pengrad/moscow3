package rzd.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
        DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT);
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return df.format(time);
    }

    public static String convertDateTimeToStr(Date dt) {
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return df.format(dt);
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

//       public static String[] convertStrTimeToMas(String time) {
//        StringTokenizer t = new StringTokenizer(time, ":");
//        int hh = new Integer(t.nextToken());
//        int mm = new Integer(t.nextToken());
//        return new String[]{t.nextToken(),t.nextToken()};
//    }


    public static boolean searchByTable(JTable t, String value, int... columns) {
        Arrays.sort(columns);
        //System.out.println("value=" + value.length());
        if (t == null || value == null || columns == null || columns.length == 0 || columns[0] < 0 || columns[columns.length - 1] > t.getColumnCount()) {
            return false;
        }
        int rowCount = t.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columns.length; j++) {
                //      System.out.println("j="+j);
                //  System.out.println("a=" + value+"-");
                //  System.out.println("columns[j]="+columns[j]);
                //   System.out.println("b="+t.getValueAt(i, columns[j]).toString()+"-");
                if (t.getValueAt(i, columns[j]).equals(value)) {
                    //   System.out.println("****");
                    Rectangle rect = t.getCellRect(i, columns[j], false);
                    JViewport viewport = (JViewport) t.getParent();
                    viewport.setViewPosition(new Point((int) rect.getX(), (int) rect.getY()));
                    t.setRowSelectionInterval(i, i);
                    viewport.revalidate();
                    viewport.repaint();
                    return true;
                }
            }
        }
        return false;
    }

    public static String convertMasToDayOfWeek(int[] days) {
        String res = "";
        for (int i = 0; i < days.length; i++) {
            if (days[i] == 1) {
                res = res + "ПН ";
            }
            if (days[i] == 2) {
                res = res + "ВТ ";
            }
            if (days[i] == 3) {
                res = res + "СР ";
            }
            if (days[i] == 4) {
                res = res + "ЧТ ";
            }
            if (days[i] == 5) {
                res = res + "ПТ ";
            }
            if (days[i] == 6) {
                res = res + "СБ ";
            }
            if (days[i] == 7) {
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
                res[i] = 1;
            }
            if (t.equals("ВТ")) {
                res[i] = 2;
            }
            if (t.equals("СР")) {
                res[i] = 3;
            }
            if (t.equals("ЧТ")) {
                res[i] = 4;
            }
            if (t.equals("ПТ")) {
                res[i] = 5;
            }
            if (t.equals("СБ")) {
                res[i] = 6;
            }
            if (t.equals("ВС")) {
                res[i] = 7;
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
