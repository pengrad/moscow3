package rzd.carFleet.hist;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 09.10.2010
 * Time: 23:44:21
 * To change this template use File | Settings | File Templates.
 */
public class TableCarHistRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        setText(value.toString());
  //      System.out.println(table.getValueAt(row, 5));
        if (!isSelected&&!(table.getValueAt(row, 0)).toString().equals("")) {
            setBackground(new Color(100,149,237));
            setForeground(Color.BLACK);
        } else {
            if (isSelected) {
                setBackground(new Color(51,153,255));
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
        }

        return this;
    }
}
