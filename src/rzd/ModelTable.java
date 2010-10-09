package rzd;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 10.09.2010
 * Time: 16:51:30
 * To change this template use File | Settings | File Templates.
 */
public class ModelTable extends DefaultTableModel {

    public void setDate(ArrayList data) {
        Object[] header = null;
        if (data != null) {
            header = (Object[]) data.get(data.size() - 1);
            data.remove(data.size() - 1);
        }
        super.setDataVector(convertToVector(data), convertToVector(header));
    }

    private Vector convertToVector(ArrayList list) {
        if (list == null) return null;
        Vector v = new Vector(list.size());
        for (int i = 0; i < list.size(); i++) {
            v.addElement(convertToVector((Object[]) list.get(i)));
        }
        return v;
    }
     public boolean isCellEditable(int row, int column) {
        return false;
    }

}