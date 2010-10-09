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
        //  GregorianCalendar gc=new GregorianCalendar
        //   gc.add(Calendar.DAY_OF_YEAR,1);
        //   sdf.format(gc);
        // gc.
        Date d = new Date(new Date().getTime() + new Time(new GregorianCalendar(0, 0, 0, 36, 30).getTimeInMillis()).getTime());
        System.out.println(sdf.format(d));
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
}
