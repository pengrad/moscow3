/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.dispStatinonFleet;

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
        if (e.getSource() == pDispStation.cTimeBeforeBack) {
            update();
        }
        if (e.getSource() == pDispStation.cTimeBeforeGoingTrains) {
            update();
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTable && e.getButton() == 3) {
            int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
            ((JTable) e.getSource()).addRowSelectionInterval(row, row);
            menuTrain.show((JTable) e.getSource(), e.getX(), e.getY());
            activeTable = (JTable) e.getSource();
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
//        if (e.getSource() == pDispStation.bAddDeparture) {
//            addDepartureTrain();
//        } else if (e.getSource() == pDispStation.bAddDestination) {
//            addDestinationTrain();
//        } else if (e.getSource() == editTrain) {
//            editTrain(activeTable);
//        } else if (e.getSource() == viewTrain) {
//            viewTrain(activeTable);
//        }
    }

    public void update() {
        ArrayList<Train> trainsOnRoad = TestModel.get().getTrainsGoing();
        ((ModelTable) pDispStation.tTrainOnRoad.getModel()).setDate(getTrainTabView(trainsOnRoad));
        ArrayList<Train> goingTrains = Model.getModel().getGoingTrains(new Integer(pDispStation.cTimeBeforeGoingTrains.getSelectedItem().toString()));
        ((ModelTable) pDispStation.tGoingTrains.getModel()).setDate(getTrainTabView(goingTrains));
        ArrayList<Train> trainsBack = TestModel.get().getTrainsSentToday();
        ((ModelTable) pDispStation.tTrainBack.getModel()).setDate(getTrainTabView(trainsBack));
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

    }

    private void viewTrain(JTable activeTab) {

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
                        Utils.convertDateToStr(train.getDtDeparture()),
                        Utils.convertDateToStr(train.getDtDestination()),
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
