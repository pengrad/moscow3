package rzd.routeFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import rzd.ModelTable;
import rzd.Utils;
import rzd.model.TestModel;
import rzd.model.objects.Schedule;
import rzd.model.objects.Train;


import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
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
    private DEditRoute dEditRoute;
    private DEditSchedule dEditSchedule;


    private JPopupMenu menuRoute;
    private JPopupMenu menuSchedule;

    private JMenuItem editRoute;
    private JMenuItem deleteRoute;
    private JMenuItem editSchedule;
    private JMenuItem deleteSchedule;

    public Controller(PRoute p) {
        this.pTrains = p;
        dEditRoute = new DEditRoute(null, true);
        dEditSchedule = new DEditSchedule(null, true);

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
            ((ModelTable) pTrains.tRoute.getModel()).setDate(getRoutesTabView());
            ((ModelTable) pTrains.tSchedule.getModel()).setDate(getScheduleTabView());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pTrains, e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == pTrains.tRoute && e.getButton() == 3) {
            int row = pTrains.tRoute.rowAtPoint(e.getPoint());
            pTrains.tRoute.addRowSelectionInterval(row, row);
            menuRoute.show(pTrains.tRoute, e.getX(), e.getY());
        } else if (e.getSource() == pTrains.tSchedule && e.getButton() == 3) {
            int row = pTrains.tSchedule.rowAtPoint(e.getPoint());
            pTrains.tSchedule.addRowSelectionInterval(row, row);
            menuSchedule.show(pTrains.tSchedule, e.getX(), e.getY());
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
        if (e.getSource() == pTrains.bCreateRout) {
            insertRoute();
        } else if (e.getSource() == editRoute) {
            editRoute();
        } else if (e.getSource() == deleteRoute) {
            deleteRoute();
        }
        if (e.getSource() == pTrains.bCreateSchedule) {
            insertSchedule();
        } else if (e.getSource() == editSchedule) {
            editSchedule();
        } else if (e.getSource() == deleteSchedule) {
            deleteSchedule();
        }
    }


    private void insertRoute() {
        dEditRoute.setLocationRelativeTo(pTrains);
        Route data = dEditRoute.open(null);
        if (data != null) {
            try {
                boolean b = TestModel.get().addRoute(data);
                if (b) {
                    ((ModelTable) pTrains.tRoute.getModel()).setDate(getRoutesTabView());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pTrains, e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void editRoute() {
        int row = pTrains.tRoute.getSelectedRow();
        if (row != -1) {
            Route data = getRouteByTabRow(row);
            dEditRoute.setLocationRelativeTo(pTrains);
            data = dEditRoute.open(data);
            if (data != null) {
                try {
                    boolean b = TestModel.get().updateRoute(data);
                    if (b) {
                        ((ModelTable) pTrains.tRoute.getModel()).setDate(getRoutesTabView());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(pTrains, e.getMessage());
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    private void deleteRoute() {
        int row = pTrains.tRoute.getSelectedRow();
        if (row != -1) {
            Route data = getRouteByTabRow(row);
            try {
                boolean b = TestModel.get().removeRoute(data);
                if (b) {
                    ((ModelTable) pTrains.tRoute.getModel()).setDate(getRoutesTabView());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pTrains, e.getMessage());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void insertSchedule() {
        dEditRoute.setLocationRelativeTo(pTrains);
        Object[] data = dEditSchedule.open(null,TestModel.get().getRoutes(),null);
        // if (data != null) {
        try {
            //   int n = TestModel.get().updateTrain(new Train(0,));
//                if (n > 0) {
//                    ((ModelTable) pTrains.tTarins.getModel()).addRow(data.toArray());
//                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pTrains, e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        // }
    }

    private void editSchedule() {
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

    private void deleteSchedule() {
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


    //Методы конверторы

    public ArrayList<Object[]> getRoutesTabView() {
        ArrayList<Route> routes = TestModel.get().getRoutes();
        if (routes != null) {
            ArrayList<Object[]> res = new ArrayList<Object[]>(routes.size());
            for (Route r : routes) {
                Object[] o = new Object[4];
                o[0] = r.getId();
                o[1] = r.getNumber();
                o[2] = r.getPointDeparture();
                o[3] = r.getPointDestination();
                res.add(o);
            }
            res.add(new Object[]{"ID", "Номер маршрута", "Станция отправления", "Станция назначения"});
            return res;
        }
        return null;
    }

    public ArrayList<Object[]> getScheduleTabView() {
        ArrayList<Schedule> schedules = TestModel.get().getSchedules();
        if (schedules != null) {
            ArrayList<Object[]> res = new ArrayList<Object[]>(schedules.size());
            for (Schedule s : schedules) {
                Route r = TestModel.get().getRouteBySchedule(s);
                Object[] o = new Object[7];
                o[0] = s.getId();
                o[1] = s.getTimeDeparture();
                o[2] = s.getTimeDestination();
                o[3] = s.getDateBegin();
                o[4] = s.getDayMove();
                o[5] = s.getDayStop();
                if (r != null)
                    o[6] = r.toString();
                else
                    o[6] = "Маршрут не назначен";
                res.add(o);
            }
            res.add(new Object[]{"ID", "Время отправления", "Время прибытия", "Начало действия маршрута", "Время в пути", "Время простоя", "Маршрут"});
            return res;
        }
        return null;
    }

    public Route getRouteByTabRow(int row) {
        if (row < 0 || row > pTrains.tRoute.getModel().getRowCount()) return null;
        return new Route(
                new Integer(pTrains.tRoute.getModel().getValueAt(row, 0).toString()),
                pTrains.tRoute.getModel().getValueAt(row, 1).toString(),
                pTrains.tRoute.getModel().getValueAt(row, 2).toString(),
                pTrains.tRoute.getModel().getValueAt(row, 2).toString()
        );
    }

    public Schedule getScheduleByTabRow(int row) {
        if (row < 0 || row > pTrains.tSchedule.getModel().getRowCount()) return null;
        return new Schedule(
                new Integer(pTrains.tSchedule.getModel().getValueAt(row, 0).toString()),
                Utils.convertStrToTime(pTrains.tSchedule.getModel().getValueAt(row, 1).toString()),
                Utils.convertStrToTime(pTrains.tSchedule.getModel().getValueAt(row, 2).toString()),
                Utils.convertStrToDate(pTrains.tSchedule.getModel().getValueAt(row, 3).toString()),
                new Integer(pTrains.tSchedule.getModel().getValueAt(row, 4).toString()),
                new Integer(pTrains.tSchedule.getModel().getValueAt(row, 5).toString())
        );
    }
}
