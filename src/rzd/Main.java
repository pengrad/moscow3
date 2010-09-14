/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd;

import logic.BusinessManager;
import logic.HibernateInitializeException;
import rzd.objects.Route;

import java.util.ArrayList;

/**
 *
 * @author ЧерныхЕА
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws HibernateInitializeException {
        ArrayList<Route> r = BusinessManager.get().getRoutes();
        for(Route rr : r) {
            System.out.println(rr.getNumber());
        }
    }

}
