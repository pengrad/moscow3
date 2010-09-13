package routeFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import rzd.ModelTable;
import rzd.bd.Base;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 12.09.2010
 * Time: 17:43:47
 * To change this template use File | Settings | File Templates.
 */
public class Controller implements ActionListener, MouseListener {

    private PRoute pTrains;
    private DEditRoute dEditTrain;

    private JPopupMenu menu;
    private JMenuItem editTrain;
    private JMenuItem deleteTrain;

    public Controller(PRoute p) {
        this.pTrains = p;
        dEditTrain = new DEditRoute(null, true);

        menu = new JPopupMenu();
        editTrain = new JMenuItem("Редактировать информацию о поезде", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        editTrain.addActionListener(this);
        deleteTrain = new JMenuItem("Удалить поезд", new ImageIcon(getClass().getResource("/rzd/resurce/bt12.gif")));
        deleteTrain.addActionListener(this);
        menu.add(editTrain);
        menu.add(deleteTrain);

        update();

    }

    public void update() {
        try {
            ArrayList trains = Base.getInstance().getTrainsAll();
            ((ModelTable) pTrains.tTarins.getModel()).setDate(trains);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pTrains, e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void mouseClicked(MouseEvent e) {
          if (e.getSource() == pTrains.tTarins && e.getButton() == 3) {
            int row = pTrains.tTarins.rowAtPoint(e.getPoint());
            pTrains.tTarins.addRowSelectionInterval(row, row);
            menu.show(pTrains.tTarins,e.getX(), e.getY());
        }
    }

    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pTrains.bCreateTrain) {
            insertTrain();
        } else if (e.getSource() == editTrain) {
            editTrain();
        } else if (e.getSource() == deleteTrain) {
            deleteTrain();
        }
    }


    private void insertTrain() {
        dEditTrain.setLocationRelativeTo(pTrains);
        ArrayList data = dEditTrain.open(null);
        if (data != null) {
            try {
                int n = Base.getInstance().insertTrain(data);
                if (n > 0) {
                    ((ModelTable) pTrains.tTarins.getModel()).addRow(data.toArray());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pTrains, e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void editTrain() {
        int row = pTrains.tTarins.getSelectedRow();
        ArrayList data = new ArrayList();
        data.add(pTrains.tTarins.getModel().getValueAt(row, 0));
        data.add(pTrains.tTarins.getModel().getValueAt(row, 1));
        data.add(pTrains.tTarins.getModel().getValueAt(row, 2));
        data.add(pTrains.tTarins.getModel().getValueAt(row, 3));
        data.add(pTrains.tTarins.getModel().getValueAt(row, 4));
        
        data.add(pTrains.tTarins.getModel().getValueAt(row, 5));
        data.add(pTrains.tTarins.getModel().getValueAt(row, 6));
        dEditTrain.setLocationRelativeTo(pTrains);
        ArrayList newData = dEditTrain.open(data);
        if (newData != null) {
            try {
                int n = Base.getInstance().updateTrain(newData);
                if (n > 0) {
                    pTrains.tTarins.getModel().setValueAt(newData.get(0), row, 0);
                    pTrains.tTarins.getModel().setValueAt(newData.get(1), row, 1);
                    pTrains.tTarins.getModel().setValueAt(newData.get(2), row, 2);
                    pTrains.tTarins.getModel().setValueAt(newData.get(3), row, 3);
                    pTrains.tTarins.getModel().setValueAt(newData.get(4), row, 4);
                    pTrains.tTarins.getModel().setValueAt(newData.get(5), row, 5);
                    pTrains.tTarins.getModel().setValueAt(newData.get(6), row, 6);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pTrains, e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void deleteTrain() {
        int row = pTrains.tTarins.getSelectedRow();
        int number = new Integer(pTrains.tTarins.getModel().getValueAt(row, 0).toString());
        try {
            int n = Base.getInstance().deleteTrain(number);
            if (n > 0) {
                ((ModelTable) pTrains.tTarins.getModel()).removeRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pTrains, e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
