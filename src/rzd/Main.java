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
        int[] dd=new int[]{0,10,20};
        String r= Utils.convertMasToStr(dd);
        System.out.println(r);
        int[] d=Utils.convertStrToMas(r);
        System.out.println(Arrays.toString(d));
      r= Utils.convertMasToStr(d);
                System.out.println(r);


    }
}
