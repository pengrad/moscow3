/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd;

import rzd.MainFrame;
import rzd.carFleet.PCar;
import rzd.dispStatinonFleet.PDispStation;
import rzd.routeFleet.PRoute;
import rzd.scheduleFleet.PSchedule;
import rzd.stationFleet.PStationFleet;

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

    public static ControllerMain getInstans() {
        return cm;
    }

    private ControllerMain() {
        mf = new MainFrame();
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
        mf.setVisible(true);
    }

    public boolean searchCar(int numberCar) {
        boolean search = pStationFleet.getController().searchCar(numberCar);
        if (search) {
            mf.tabbedtMain.setSelectedComponent(pStationFleet);
            pStationFleet.getController().searchCar(numberCar);
        }
        return search;
    }

    public static void main(String[] args) {
        ControllerMain.getInstans();
    }

    public void update() {

    }
}
