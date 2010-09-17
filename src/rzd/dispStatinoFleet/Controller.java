/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd.dispStatinoFleet;

import rzd.ModelTable;
import rzd.Utils;
import rzd.model.TestModel;
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

    public Controller(PDispStation pDispStation) {
        this.pDispStation = pDispStation;
    }

    public void itemStateChanged(ItemEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

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

    }

    public void update() {
        ArrayList<Train> trainsGoing = TestModel.get().getTrainsGoing(new Integer(pDispStation.cTimeViewDeparture.getSelectedItem().toString()));
        ((ModelTable) pDispStation.tTrainDeparture.getModel()).setDate(getTrainTabView(trainsGoing));
        ArrayList<Train> trainsArriving = TestModel.get().getTrainsArriving(new Integer(pDispStation.cTimeViewDestination.getSelectedItem().toString()));
        ((ModelTable) pDispStation.tTrainDestination.getModel()).setDate(getTrainTabView(trainsArriving));
        ArrayList<Train> trainsSentToday = TestModel.get().getTrainsSentToday();
        ((ModelTable) pDispStation.tTrainDepartureToday.getModel()).setDate(getTrainTabView(trainsSentToday));
        ArrayList<Train> trainsArrivedToday = TestModel.get().getTrainsArrivedToday();
        ((ModelTable) pDispStation.tTrainDestinationToday.getModel()).setDate(getTrainTabView(trainsArrivedToday));
    }

    //Методы конвертации

    public ArrayList<Object[]> getTrainTabView(ArrayList<Train> trains) {
        if (trains != null) {
            ArrayList<Object[]> data = new ArrayList<Object[]>(trains.size());
            for (Train train : trains) {
                Route route = TestModel.get().getRouteByTrain(train);
                Road road = TestModel.get().getRoadByTrain(train);
                data.add(new Object[]{
                        train.getId(),
                        route.toString(),
                        Utils.convertDateToStr(train.getDtDeparture()),
                        Utils.convertDateToStr(train.getDtDestination()),
                        road.getName()
                });
            }
            data.add(new Object[]{"ID", "Маршрут", "Дата и время прибытия", "Дата и время отправления", "Путь"});
            return data;
        }
        return null;
    }

    public Train getTrainByTabRow(int row, JTable table) {
        if (row < 0 || row > table.getModel().getRowCount()) return null;
        return new Train(
                new Integer(table.getModel().getValueAt(row, 0).toString()),
                Utils.convertStrToDateTime(table.getModel().getValueAt(row, 2).toString()),
                Utils.convertStrToDateTime(table.getModel().getValueAt(row, 3).toString())
        );
    }

}
