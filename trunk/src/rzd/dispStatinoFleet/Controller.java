/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.dispStatinoFleet;

import rzd.ModelTable;
import rzd.Utils;
import rzd.model.TestModel;
import rzd.model.objects.Car;
import rzd.model.objects.Road;
import rzd.model.objects.Route;
import rzd.model.objects.Train;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author ЧерныхЕА
 */
public class Controller implements MouseListener, ActionListener, ItemListener {

    private PDispStation pDispStation;
    private JPopupMenu menuTrain;
    private JMenuItem editTrain;
    private JMenuItem viewTrain;
    private JTable activeTable;

    public Controller(PDispStation pDispStation) {
        this.pDispStation = pDispStation;
        menuTrain = new JPopupMenu();
        editTrain = new JMenuItem("Редактировать", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        viewTrain = new JMenuItem("Посмотреть на карте станции", new ImageIcon(getClass().getResource("/rzd/resurce/eye.png")));
        menuTrain.add(editTrain);
        menuTrain.add(viewTrain);
        editTrain.addActionListener(this);
        viewTrain.addActionListener(this);

    }

    public void itemStateChanged(ItemEvent e) {
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

        if (e.getSource() == pDispStation.bAddDeparture) {
            addDepartureTrain();
        } else if (e.getSource() == pDispStation.bAddDestination) {
            addDestinationTrain();
        } else if (e.getSource() == editTrain) {
            editTrain(activeTable);
        } else if (e.getSource() == viewTrain) {
            viewTrain(activeTable);
        }
    }

    public void update() {
        ArrayList<Train> trainsGoing = TestModel.get().getTrainsGoing();
        ((ModelTable) pDispStation.tTrainDeparture.getModel()).setDate(getTrainTabView(trainsGoing));
        ArrayList<Train> trainsArriving = TestModel.get().getTrainsArriving();
        ((ModelTable) pDispStation.tTrainDestination.getModel()).setDate(getTrainTabView(trainsArriving));
        ArrayList<Train> trainsSentToday = TestModel.get().getTrainsSentToday();
        ((ModelTable) pDispStation.tTrainDepartureToday.getModel()).setDate(getTrainTabView(trainsSentToday));
        ArrayList<Train> trainsArrivedToday = TestModel.get().getTrainsArrivedToday();
        ((ModelTable) pDispStation.tTrainDestinationToday.getModel()).setDate(getTrainTabView(trainsArrivedToday));
    }

    private void addDepartureTrain() {
    }

    private void addDestinationTrain() {
    }

    private void editTrain(JTable activeTab) {
    }

    private void viewTrain(JTable activeTab) {
    }

    //Методы конвертации
    private ArrayList<Object[]> getTrainTabView(ArrayList<Train> trains) {
        if (trains != null) {
            ArrayList<Object[]> data = new ArrayList<Object[]>(trains.size());
            for (Train train : trains) {
                Route route = TestModel.get().getRouteByTrain(train);
                Road road = TestModel.get().getRoadByTrain(train);
                ArrayList<Car> cars = TestModel.get().getCarsByTrain(train);
                data.add(new Object[]{
                            train.getId(),
                            route.toString(),
                            Utils.convertDateToStr(train.getDtDeparture()),
                            Utils.convertDateToStr(train.getDtDestination()),
                            "Начальник",
                            road.getName(),
                            ((cars == null) ? 0 : cars.size())
                        });
            }
            data.add(new Object[]{"ID", "Маршрут", "Дата и время прибытия", "Дата и время отправления", "Начальник", "Путь", "Кол-во вагонов"});
            return data;
        }
        return null;
    }

    public Train getTrainByTabRow(int row, JTable table) {
        if (row < 0 || row > table.getModel().getRowCount()) {
            return null;
        }
        return new Train(
                new Integer(table.getModel().getValueAt(row, 0).toString()),
                Utils.convertStrToDateTime(table.getModel().getValueAt(row, 2).toString()),
                Utils.convertStrToDateTime(table.getModel().getValueAt(row, 3).toString()));
    }
}
