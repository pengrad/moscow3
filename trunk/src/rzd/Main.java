/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd;

import logic.BusinessManager;
import logic.HibernateInitializeException;
import rzd.model.objects.Route;
import rzd.utils.Utils;

import javax.xml.crypto.Data;
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
        SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
        GregorianCalendar gc=new GregorianCalendar();
        gc.add(Calendar.DAY_OF_YEAR,1);
        sdf.format(gc);

        System.out.println( sdf.format(gc));
//        String r= Utils.convertMasToDayOfWeek(dd);
//        System.out.println(r);
//        int[] d=Utils.convertDayOfWeekToMas(r);
//        System.out.println(Arrays.toString(d));
//      r= Utils.convertMasToDayOfWeek(d);
//                System.out.println(r);


    }
}
