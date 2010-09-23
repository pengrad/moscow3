/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd;

import logic.BusinessManager;
import logic.HibernateInitializeException;
import rzd.model.objects.Route;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author ЧерныхЕА
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws HibernateInitializeException {
        Date dB = new Date();
        Date dE = new GregorianCalendar(2010, 11, 25).getTime();
        Calendar dd=Calendar.getInstance();
        dd.setTime(dB);
        Calendar c = Calendar.getInstance();
        c.setTime(new GregorianCalendar(2010, 11, 25).getTime());
        System.out.println("y=" + c.get(Calendar.MONTH));
        while (dd.before(dE)){

        
        }
    }

}
