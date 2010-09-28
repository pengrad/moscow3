package rzd.model;

import logic.BusinessLogic;
import logic.BusinessManager;
import logic.HibernateInitializeException;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 28.09.2010
 * Time: 23:13:50
 * To change this template use File | Settings | File Templates.
 */
public class Model {
    public static BusinessLogic getModel() {
        try {
            return (BusinessLogic) BusinessManager.get();
        } catch (HibernateInitializeException e) {
            JOptionPane.showMessageDialog(null, "Не возможно инициализировать Hibernate!");
            System.exit(0);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
