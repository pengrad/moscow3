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
    public static void main(String[] args) {
        ControllerMain.getInstans().init();
}
}
