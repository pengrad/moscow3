/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd;

import logic.BusinessManager;
import logic.DateUtils;
import logic.HibernateInitializeException;
import rzd.model.objects.Route;
import rzd.utils.Utils;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author ЧерныхЕА
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        int[] dd=new int[]{};
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm");
        Calendar c = Calendar.getInstance();
        c.set(0, 1, 10, 5, 5);
//        System.out.println(c.get(Calendar.DATE));
//       c.set(Calendar.YEAR,0);
//        c.set(Calendar.MONTH,0);
//        c.set(Calendar.DAY_OF_MONTH,0);
//        c.set(Calendar.HOUR_OF_DAY,0);
//        c.set(Calendar.MINUTE,0);


        //  GregorianCalendar gc=new GregorianCalendar
        //   gc.add(Calendar.DAY_OF_YEAR,1);
        //   sdf.format(gc);
        // gc.
        // Time t=new Time(new GregorianCalendar(0, 0, 0, 10, 30).getTime().getTime());

        //   System.out.println(Utils.convertTimeToStr(t));
           Date d = new Date(new Date().getTime()+c.getTime().getTime());
        System.out.println(sdf.format(c.getTime()));
//        SimpleDateFormat sdhh = new SimpleDateFormat("hh:mm");

//        GregorianCalendar gc = new GregorianCalendar();
//        gc.setTime(new Date());
//        System.out.println(sdf.format(gc.getTime()));
//        gc.add(Calendar.HOUR_OF_DAY, 50);
//        gc.add(Calendar.MINUTE, 10);
//
//        System.out.println(sdf.format(gc.getTime()));

        //   System.out.println( sdf.format(gc));
//        String r= Utils.convertMasToDayOfWeek(dd);
//        System.out.println(r);
//        int[] d=Utils.convertDayOfWeekToMas(r);
//        System.out.println(Arrays.toString(d));
//      r= Utils.convertMasToDayOfWeek(d);
//                System.out.println(r);


    }

    public static Timestamp getDatePlusTime(Date date, Time time) {
        return null; //todo
    }
}
