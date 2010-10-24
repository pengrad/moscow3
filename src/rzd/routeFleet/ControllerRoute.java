package rzd.routeFleet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import rzd.ControllerMain;
import rzd.ModelTable;


import javax.swing.*;
import java.util.ArrayList;

import rzd.Updateble;
import rzd.model.Model;
import rzd.model.objects.Route;
import rzd.utils.MakerDefaultTextInField;
import rzd.utils.Utils;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 12.09.2010
 * Time: 17:43:47
 * To change this template use File | Settings | File Templates.
 */
public class ControllerRoute implements ActionListener, MouseListener, Updateble {

    private PRoute pRoute;
    private DRouteEdit dEditRoute;
    private JPopupMenu menuRoute;
    private JMenuItem editRoute;
    private JMenuItem deleteRoute;

    public ControllerRoute(PRoute p) {
        this.pRoute = p;
        dEditRoute = new DRouteEdit(null, true);

        menuRoute = new JPopupMenu();
        editRoute = new JMenuItem("Редактировать", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        editRoute.addActionListener(this);
        deleteRoute = new JMenuItem("Удалить", new ImageIcon(getClass().getResource("/rzd/resurce/bt12.gif")));
        deleteRoute.addActionListener(this);
        menuRoute.add(editRoute);
        menuRoute.add(deleteRoute);
        new MakerDefaultTextInField("Поиск по номеру маршрута", pRoute.fSearch);

        pRoute.bCreateRoute.addActionListener(this);
        pRoute.tRoute.addMouseListener(this);
        pRoute.fSearch.addActionListener(this);
        pRoute.bSearch.addActionListener(this);
    }

    public void update() {
        ((ModelTable) pRoute.tRoute.getModel()).setDate(getRoutesTabView());
        pRoute.tRoute.getColumnModel().getColumn(0).setMinWidth(0);
        pRoute.tRoute.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    public Component getPanel() {
        return pRoute;
    }


    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == pRoute.tRoute && e.getButton() == 3) {
            int row = pRoute.tRoute.rowAtPoint(e.getPoint());
            pRoute.tRoute.addRowSelectionInterval(row, row);
            menuRoute.show(pRoute.tRoute, e.getX(), e.getY());
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
        if (e.getSource() == pRoute.fSearch || e.getSource() == pRoute.bSearch) {
            search();
        } else if (e.getSource() == pRoute.bCreateRoute) {
            addRoute();
        } else if (e.getSource() == editRoute) {
            editRoute();
        } else if (e.getSource() == deleteRoute) {
            deleteRoute();
        }
    }

    private void addRoute() {
        dEditRoute.setLocationRelativeTo(pRoute);
        Route data = dEditRoute.open(null, Model.getModel().getSheduleTypes());
        if (data != null) {
            try {
                boolean b = Model.getModel().addRoute(data);
                if (b) {
                    JOptionPane.showMessageDialog(pRoute, "Расписание успешно добавлено.");
                    ControllerMain.getInstans().update(this);
                } else {
                    JOptionPane.showMessageDialog(pRoute, "Ошибка...попробуйте еще раз.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pRoute, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void search() {
        boolean b = Utils.searchByTable(pRoute.tRoute, pRoute.fSearch.getText(), 1, 2);
        if (!b) {
            JOptionPane.showMessageDialog(pRoute, "Ничего не найдено", "Внимание...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editRoute() {
        int row = pRoute.tRoute.getSelectedRow();
        if (row != -1) {
            //  System.out.println(pRoute.tRoute.getValueAt(row, 0).toString());
            Route data = Model.getModel().getRouteById(new Integer(pRoute.tRoute.getValueAt(row, 0).toString()));
            dEditRoute.setLocationRelativeTo(pRoute);
            data = dEditRoute.open(data, Model.getModel().getSheduleTypes());
            //   System.out.println("editRoute");
            if (data != null) {
                try {
                    //          System.out.println("!!updateRoute");
                    boolean b = Model.getModel().updateRoute(data);
                    if (b) {
                        JOptionPane.showMessageDialog(pRoute, "Расписание успешно изменено.");
                        ControllerMain.getInstans().update(this);
                    } else {
                        JOptionPane.showMessageDialog(pRoute, "Ошибка...попробуйте еще раз.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(pRoute, e.getMessage());
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    private void deleteRoute() {
        int row = pRoute.tRoute.getSelectedRow();
        if (row != -1) {
            Route route = (Route) pRoute.tRoute.getValueAt(row, 0);
            boolean b = Model.getModel().deleteRoute(route);
            if (b) {
                JOptionPane.showMessageDialog(pRoute, "Маршрут успешно удален", "", JOptionPane.INFORMATION_MESSAGE);
                ControllerMain.getInstans().update(this);
            }
        }
    }

    //Методы конверторы

    private ArrayList<Object[]> getRoutesTabView() {
        ArrayList<Route> routes = Model.getModel().getRoutes();
        if (routes != null) {
            ArrayList<Object[]> res = new ArrayList<Object[]>(routes.size());
            for (Route r : routes) {
                Object[] o = new Object[6];
                o[0] = r;
                o[1] = r.getNumberForward();
                o[2] = r.getNumberBack();
                o[3] = r.getCityFrom();
                o[4] = r.getCityTo();
                o[5] = r.isEnabled();
                res.add(o);
            }
            res.add(new Object[]{"ID", "Номер маршрута", "Номер обратного маршрута", "Станция отправления", "Станция назначения", "Активность"});
            return res;
        }
        return null;
    }

//
//    public ArrayList<Object[]> getScheduleTabView() {
//        ArrayList<Shedule> shedules = TestModel.get().getSchedules();
//        if (shedules != null) {
//            ArrayList<Object[]> res = new ArrayList<Object[]>(shedules.size());
//            for (Shedule s : shedules) {
//                Route r = TestModel.get().getRouteBySchedule(s);
//                Object[] o = new Object[7];
//                o[0] = s.getId();
//                o[1] = s.getTimeDeparture();
//                o[2] = s.getTimeDestination();
////                o[3] = s.getDateBegin();
////                o[4] = s.getDayMove();
////                o[5] = s.getDayStop();
//                if (r != null)
//                    o[6] = r.toString();
//                else
//                    o[6] = "Маршрут не назначен";
//                res.add(o);
//            }
//            res.add(new Object[]{"ID", "Время отправления", "Время прибытия", "Начало действия маршрута", "Время в пути", "Время простоя", "Маршрут"});
//            return res;
//        }
//        return null;
//    }

//    public Route getRouteByTabRow(int row) {
//        if (row < 0 || row > pTrains.tRoute.getModel().getRowCount()) return null;
//        return new Route(
//                new Integer(pTrains.tRoute.getModel().getValueAt(row, 0).toString()),
//                pTrains.tRoute.getModel().getValueAt(row, 1).toString(),
//                pTrains.tRoute.getModel().getValueAt(row, 2).toString(),
//                pTrains.tRoute.getModel().getValueAt(row, 2).toString()
//        );
//    }

//    public Shedule getScheduleByTabRow(int row) {
//        if (row < 0 || row > pTrains.tSchedule.getModel().getRowCount()) return null;
//        return new Shedule(
//                new Integer(pTrains.tSchedule.getModel().getValueAt(row, 0).toString()),
//                Utils.convertStrToTime(pTrains.tSchedule.getModel().getValueAt(row, 1).toString()),
//                Utils.convertStrToTime(pTrains.tSchedule.getModel().getValueAt(row, 2).toString()),
//                Utils.convertStrToDate(pTrains.tSchedule.getModel().getValueAt(row, 3).toString()),
//                new Integer(pTrains.tSchedule.getModel().getValueAt(row, 4).toString()),
//                new Integer(pTrains.tSchedule.getModel().getValueAt(row, 5).toString())
//        );
//    }
}
