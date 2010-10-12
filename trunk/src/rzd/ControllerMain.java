/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd;

import rzd.MainFrame;
import rzd.carFleet.PCar;
import rzd.carFleet.PCarInformation;
import rzd.dispStatinonFleet.PDispStation;
import rzd.dispStatinonFleet.PTrainInformation;
import rzd.model.objects.Car;
import rzd.model.objects.Train;
import rzd.routeFleet.PRoute;
import rzd.scheduleFleet.PSchedule;
import rzd.stationFleet.PStationFleet;

import javax.swing.*;
import java.awt.*;

/**
 * @author ЧерныхЕА
 */
public class ControllerMain {

    private final static ControllerMain cm = new ControllerMain();
    private MainFrame mf;
    private PStationFleet pStationFleet;
    private PSchedule pSchedule;
    private PDispStation pDispStation;
    private PCar pCars;
    private PRoute pRoute;
    private JPopupMenu popCarInf;
    private PCarInformation pCarInformation;
    private JPopupMenu popTrainInf;
    private PTrainInformation pTrainInformation;

    public static ControllerMain getInstans() {
        return cm;
    }

    private ControllerMain() {
        mf = new MainFrame();
        init();
        mf.setVisible(true);
    }

    private void init() {
        pStationFleet = new PStationFleet();
        pSchedule = new PSchedule();
        pDispStation = new PDispStation();
        pCars = new PCar();
        pRoute = new PRoute();
        mf.tabbedtMain.add("Станция", pStationFleet);
        mf.tabbedtMain.add("Расписание", pSchedule);
        mf.tabbedtMain.add("Диспетчер станции", pDispStation);
        mf.tabbedtMain.add("Парк вагонов", pCars);
        mf.tabbedtMain.add("Маршруты", pRoute);
        popCarInf = new JPopupMenu();
        pCarInformation = new PCarInformation();
        popCarInf.add(pCarInformation);
        popTrainInf = new JPopupMenu();
        pTrainInformation = new PTrainInformation();
        popTrainInf.add(pTrainInformation);
    }

    public void showCarInf(Component c, int x, int y, Car car) {
        pCarInformation.setData(car);
        popCarInf.show(c, x, y);
    }

    public void showCarInf(JComponent c, int x, int y, int numberCar) {
        pCarInformation.setData(numberCar);
        popCarInf.show(c, x, y);
    }

    public void showTrainInf(Component c, int x, int y, Train train) {
        pTrainInformation.setData(train);
        popTrainInf.show(c, x, y);
    }

    public void showTrainInf(JComponent c, int x, int y, int idTrain) {
        pTrainInformation.setData(idTrain);
        popTrainInf.show(c, x, y);
    }

    public boolean searchCar(int numberCar) {
        boolean search = pStationFleet.getController().searchCar(numberCar);
        if (search) {
            mf.tabbedtMain.setSelectedComponent(pStationFleet);
            pStationFleet.getController().searchCar(numberCar);
        }
        return search;
    }

    public boolean searchTrain(int idTrain) {
        boolean search = pStationFleet.getController().searchTrain(idTrain);
        if (search) {
            mf.tabbedtMain.setSelectedComponent(pStationFleet);
            pStationFleet.getController().searchTrain(idTrain);
        }
        return search;
    }

    public static void main(String[] args) {
        ControllerMain.getInstans();
    }

    public void update() {

    }
}
