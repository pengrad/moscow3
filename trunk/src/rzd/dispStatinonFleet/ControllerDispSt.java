/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.dispStatinonFleet;

import rzd.ControllerMain;
import rzd.ModelTable;
import rzd.Updateble;
import rzd.model.Model;
import rzd.model.TestModel;
import rzd.utils.Utils;
import rzd.model.objects.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author ЧерныхЕА
 */
public class ControllerDispSt implements MouseListener, ActionListener, ItemListener, Updateble {

    private PDispStation pDispStation;
    private JPopupMenu menuTrain;
    private JMenuItem editTrain;
    private JMenuItem viewTrain;
    private JTable activeTable;
    private DEditTrain dEditTrain;

    public ControllerDispSt(PDispStation pDispStation) {
        this.pDispStation = pDispStation;
        menuTrain = new JPopupMenu();
        //   editTrain = new JMenuItem("Редактировать", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        editTrain = new JMenuItem("Редактировать", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        viewTrain = new JMenuItem("Посмотреть на карте станции", new ImageIcon(getClass().getResource("/rzd/resurce/eye.png")));
        menuTrain.add(editTrain);
        menuTrain.add(viewTrain);
        editTrain.addActionListener(this);
        viewTrain.addActionListener(this);
        dEditTrain = new DEditTrain(null, true);
        update();
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == pDispStation.cTimeBeforeArrivingTrains) {
            update();
        }
        if (e.getSource() == pDispStation.cTimeBeforeGoingTrains) {
            update();
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTable && e.getButton() == 3) {
            if (e.getSource() == pDispStation.tTrainOnRoad) {
                viewTrain.setEnabled(true);
            } else {
                viewTrain.setEnabled(false);
            }
            int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
            ((JTable) e.getSource()).addRowSelectionInterval(row, row);
            menuTrain.show((JTable) e.getSource(), e.getX(), e.getY());
            activeTable = (JTable) e.getSource();
        } else if (e.getSource() instanceof JTable && e.getButton() == 1 && e.getClickCount() == 2) {
            int row = ((JTable) e.getSource()).getSelectedRow();
            int idTrain = new Integer(((JTable) e.getSource()).getValueAt(row, 0).toString());
            ControllerMain.getInstans().showTrainInf((JTable) e.getSource(), e.getX(), e.getY(), idTrain);
        }

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editTrain) {
            editTrain(activeTable);
        } else if (e.getSource() == viewTrain) {
            viewTrain(activeTable);
        }
    }

    public void update() {
        ArrayList<Train> trainsOnRoad = Model.getModel().getTrainsOnRoads();
        ((ModelTable) pDispStation.tTrainOnRoad.getModel()).setDate(getTrainTabView(trainsOnRoad));
        ArrayList<Train> goingTrains = Model.getModel().getGoingTrains(new Integer(pDispStation.cTimeBeforeGoingTrains.getSelectedItem().toString()));
        ((ModelTable) pDispStation.tGoingTrains.getModel()).setDate(getTrainTabView(goingTrains));
        ArrayList<Train> arrivingTrains = Model.getModel().getArrivingTrains(new Integer(pDispStation.cTimeBeforeArrivingTrains.getSelectedItem().toString()));
        ((ModelTable) pDispStation.tArrivingTrains.getModel()).setDate(getTrainTabView(arrivingTrains));
    }
//
//    private void addDepartureTrain() {
//        dEditTrain.setLocationRelativeTo(pDispStation);
//        Train train = null;
//        HashMap<RoadType, ArrayList<Road>> roadTypes = new HashMap<RoadType, ArrayList<Road>>();
//        ArrayList<RoadType> rt = TestModel.get().getRoadTypes();
//        if (roadTypes != null && roadTypes.size() > 0) {
//            for (RoadType rtTmp : rt) {
//                roadTypes.put(rtTmp, TestModel.get().getRoadsByType(rtTmp));
//            }
//
//        }
//        Road road = TestModel.get().getRoadByTrain(train);
//        ArrayList<Route> routes = TestModel.get().getRoutes();
//        Route route = TestModel.get().getRouteByTrain(train);
//        ArrayList<Car> carInTran = TestModel.get().getCarsByTrain(train);
//        ArrayList<Car> carAll = TestModel.get().getCars();
//        //  Object[] res = dEditTrain.open(train, roadTypes, road, routes, route, carInTran, carAll);
////        if (res != null) {
////            train = (Train) res[0];
////            route = (Route) res[1];
////            road = (Road) res[2];
////            carInTran = (ArrayList<Car>) res[3];
//        //   boolean  b=TestModel.get().addTrain(train,)
//        //   TestModel.get().add
//        //  }
//
//    }
//
//    private void addDestinationTrain() {
//        dEditTrain.setLocationRelativeTo(pDispStation);
//    }

    private void editTrain(JTable activeTab) {
        Train train = null;
        int row = activeTab.getSelectedRow();
        if (row > -1 && row < activeTab.getRowCount()) {
            dEditTrain.setLocationRelativeTo(pDispStation);
            int idTrain = new Integer(activeTab.getValueAt(row, 0).toString());
            if (activeTab == pDispStation.tGoingTrains)
                train = dEditTrain.open(Model.getModel().getTrainById(idTrain));
            if (activeTab == pDispStation.tArrivingTrains)
                train = dEditTrain.open(Model.getModel().getTrainById(idTrain));

            if (activeTab == pDispStation.tTrainOnRoad)
                train = dEditTrain.open(Model.getModel().getTrainById(idTrain));
            if (train != null) {
                boolean b = false;
                try {
                    b = Model.getModel().makeTrainForGoing(train);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(pDispStation, e.getMessage(), "Внимание...", JOptionPane.INFORMATION_MESSAGE);
                }
                if (b) {
                    JOptionPane.showMessageDialog(pDispStation, "Информация о поезде изменена", "Внимание...", JOptionPane.INFORMATION_MESSAGE);
                    update();
                }
            }
        }
    }

    private void viewTrain(JTable activeTab) {
        int row = activeTab.getSelectedRow();
        boolean b = ControllerMain.getInstans().searchTrain(new Integer(activeTab.getValueAt(row, 0).toString()));
        if (!b) {
            JOptionPane.showMessageDialog(pDispStation, "Выгон не найден", "Сообщение...", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/rzd/resurce/lightbulb.png")));
        }
    }

    //Методы конвертации

    private ArrayList<Object[]> getTrainTabView(ArrayList<Train> trains) {
        if (trains != null) {
            ArrayList<Object[]> data = new ArrayList<Object[]>(trains.size());
            for (Train train : trains) {
                ArrayList<Car> cars = TestModel.get().getCarsByTrain(train);
                String route = "";
                if (train.getRoute().getSheduleForward().equals(train.getShedule())) {
                    route = train.getRoute().getNumberForward() + "  " + train.getRoute().getCityFrom() + " - " + train.getRoute().getCityTo();
                }
                if (train.getRoute().getSheduleBack().equals(train.getShedule())) {
                    route = train.getRoute().getNumberBack() + "  " + train.getRoute().getCityTo() + " - " + train.getRoute().getCityFrom();
                }
                data.add(new Object[]{
                        train.getId(),
                        route,
                        Utils.convertDateTimeToStr(train.getDtDeparture()),
                        Utils.convertDateTimeToStr(train.getDtDestination()),
                        train.getChief(),
                        (train.getRoad() != null ? train.getRoad().getName() : "Путь не задан"),
                        ((cars == null) ? 0 : cars.size())
                });
            }
            data.add(new Object[]{"ID", "Маршрут", "Дата и время прибытия", "Дата и время отправления", "Начальник", "Путь", "Кол-во вагонов"});
            return data;
        }
        return null;
    }
}
