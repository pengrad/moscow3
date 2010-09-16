package rzd.routeFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import rzd.ModelTable;
import rzd.model.TestModel;
import rzd.model.objects.Train;


import javax.swing.*;
import java.util.ArrayList;
import rzd.model.objects.Route;

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

    private JPopupMenu menuRoute;
    private JPopupMenu menuSchedule;

    private JMenuItem editRoute;
    private JMenuItem deleteRoute;
    private JMenuItem editSchedule;
    private JMenuItem deleteSchedule;

    public Controller(PRoute p) {
        this.pTrains = p;
        dEditTrain = new DEditRoute(null, true);

        menuRoute = new JPopupMenu();
        menuSchedule = new JPopupMenu();
        editRoute = new JMenuItem("Редактировать маршрут", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        editRoute.addActionListener(this);
        deleteRoute = new JMenuItem("Удалить маршрут", new ImageIcon(getClass().getResource("/rzd/resurce/bt12.gif")));
        deleteRoute.addActionListener(this);
        menuRoute.add(editRoute);
        menuRoute.add(deleteRoute);
        editSchedule = new JMenuItem("Редактировать расписание", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        editSchedule.addActionListener(this);
        deleteSchedule = new JMenuItem("Удалить расписание", new ImageIcon(getClass().getResource("/rzd/resurce/bt12.gif")));
        deleteSchedule.addActionListener(this);
        menuSchedule.add(editSchedule);
        menuSchedule.add(deleteSchedule);
        update();

    }

    public void update() {
        try {
           ArrayList<Route> routes = TestModel.get().getRoutes();
          ((ModelTable) pTrains.tRoute.getModel()).setDate(routes);
//          ArrayList<> trains = TestModel.get().g;
//            ((ModelTable) pTrains.tRoute.getModel()).setDate(trains);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(pTrains, e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void mouseClicked(MouseEvent e) {
          if (e.getSource() == pTrains.tRoute && e.getButton() == 3) {
            int row = pTrains.tRoute.rowAtPoint(e.getPoint());
//            pTrains.tTarins.addRowSelectionInterval(row, row);
//            menuRoute.show(pTrains.tTarins,e.getX(), e.getY());
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
//        if (e.getSource() == pTrains.bCreateTrain) {
//            insertTrain();
//        } else if (e.getSource() == editRoute) {
//            editTrain();
//        } else if (e.getSource() == deleteRoute) {
//            deleteTrain();
//        }
    }


    private void insertTrain() {
        dEditTrain.setLocationRelativeTo(pTrains);
        ArrayList data = dEditTrain.open(null);
        if (data != null) {
            try {
             //   int n = TestModel.get().updateTrain(new Train(0,));
//                if (n > 0) {
//                    ((ModelTable) pTrains.tTarins.getModel()).addRow(data.toArray());
//                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pTrains, e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void editTrain() {
//        int row = pTrains.tTarins.getSelectedRow();
//        ArrayList data = new ArrayList();
//        data.add(pTrains.tTarins.getModel().getValueAt(row, 0));
//        data.add(pTrains.tTarins.getModel().getValueAt(row, 1));
//        data.add(pTrains.tTarins.getModel().getValueAt(row, 2));
//        data.add(pTrains.tTarins.getModel().getValueAt(row, 3));
//        data.add(pTrains.tTarins.getModel().getValueAt(row, 4));
//
//        data.add(pTrains.tTarins.getModel().getValueAt(row, 5));
//        data.add(pTrains.tTarins.getModel().getValueAt(row, 6));
//        dEditTrain.setLocationRelativeTo(pTrains);
//        ArrayList newData = dEditTrain.open(data);
//        if (newData != null) {
            try {
//                int n = Base.getInstance().updateTrain(newData);
//                if (n > 0) {
//                    pTrains.tTarins.getModel().setValueAt(newData.get(0), row, 0);
//                    pTrains.tTarins.getModel().setValueAt(newData.get(1), row, 1);
//                    pTrains.tTarins.getModel().setValueAt(newData.get(2), row, 2);
//                    pTrains.tTarins.getModel().setValueAt(newData.get(3), row, 3);
//                    pTrains.tTarins.getModel().setValueAt(newData.get(4), row, 4);
//                    pTrains.tTarins.getModel().setValueAt(newData.get(5), row, 5);
//                    pTrains.tTarins.getModel().setValueAt(newData.get(6), row, 6);
//                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pTrains, e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
//        }
    }

    private void deleteTrain() {
//        int row = pTrains.tTarins.getSelectedRow();
//        int number = new Integer(pTrains.tTarins.getModel().getValueAt(row, 0).toString());
        try {
//            int n = Base.getInstance().deleteTrain(number);
//            if (n > 0) {
//                ((ModelTable) pTrains.tTarins.getModel()).removeRow(row);
//            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pTrains, e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
