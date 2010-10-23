/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd;

import rzd.MainFrame;
import rzd.carFleet.PCar;
import rzd.carFleet.PCarInformation;
import rzd.carFleet.PCarInformation_1;
import rzd.dispStatinonFleet.PDispStation;
import rzd.dispStatinonFleet.PTrainInformation;
import rzd.dispStatinonFleet.PTrainInformation_1;
import rzd.model.Model;
import rzd.model.objects.Car;
import rzd.model.objects.Train;
import rzd.routeFleet.PRoute;
import rzd.scheduleFleet.PSchedule;
import rzd.stationFleet.PStationFleet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author ЧерныхЕА
 */
public class ControllerMain implements ChangeListener, ActionListener, MouseListener {

    private final static ControllerMain cm = new ControllerMain();
    private MainFrame mf;
    private PStationFleet pStationFleet;
    private PSchedule pSchedule;
    private PDispStation pDispStation;
    private PCar pCars;
    private PRoute pRoute;
    private JPopupMenu popCarInf;
    private PCarInformation_1 pCarInformation;
    private JPopupMenu popTrainInf;
    private PTrainInformation_1 pTrainInformation;
    private DLoading dLoading;

    public static ControllerMain getInstans() {
        return cm;
    }

    private ControllerMain() {
    }

    public MainFrame getMF() {
        return mf;
    }

    public void init() {
        mf = new MainFrame();
        mf.setVisible(true);
        dLoading = new DLoading(mf, true);
        //Инициализируем панели
        pStationFleet = new PStationFleet();
        pSchedule = new PSchedule();
        pDispStation = new PDispStation();
        pCars = new PCar();
        pRoute = new PRoute();
        //-----

        //Добавляем в таргет панель
        JPanel p = new JPanel(new BorderLayout());
        p.add(pStationFleet);
        mf.tabbedtMain.add("Станция", p);
        p = new JPanel(new BorderLayout());
        p.add(pSchedule);
        mf.tabbedtMain.add("Расписание", p);
        p = new JPanel(new BorderLayout());
        p.add(pDispStation);
        mf.tabbedtMain.add("Диспетчер станции", p);
        p = new JPanel(new BorderLayout());
        p.add(pCars);
        mf.tabbedtMain.add("Парк вагонов", p);
        p = new JPanel(new BorderLayout());
        p.add(pRoute);
        mf.tabbedtMain.add("Маршруты", p);
//              mf.tabbedtMain.add("Станция", pStationFleet);
//              mf.tabbedtMain.add("Расписание", pSchedule);
//              mf.tabbedtMain.add("Диспетчер станции", pDispStation);
//              mf.tabbedtMain.add("Парк вагонов", pCars);
//              mf.tabbedtMain.add("Маршруты", pRoute);


        popCarInf = new JPopupMenu();
        pCarInformation = new PCarInformation_1();
        popCarInf.add(pCarInformation);
        popTrainInf = new JPopupMenu();
        pTrainInformation = new PTrainInformation_1();
        popTrainInf.add(pTrainInformation);

        //Добавляем листноров
        //todo Должно быть в конце INITa
        mf.tabbedtMain.addChangeListener(ControllerMain.getInstans());
        mf.mUpdateThis.addActionListener(ControllerMain.getInstans());
        mf.tabbedtMain.setSelectedIndex(0);
        update(pStationFleet.getController());

    }


    public void stateChanged(ChangeEvent e) {
        updateStateTab();
    }

    public void actionPerformed(ActionEvent e) {
        updateStateTab();
    }

    private void updateStateTab() {
        JPanel c = (JPanel) mf.tabbedtMain.getSelectedComponent();
        if (c.getComponents()[0] instanceof PSchedule) {
            update(pSchedule.getController());
        }
        if (c.getComponents()[0] instanceof PStationFleet) {
            update(pStationFleet.getController());
        }
        if (c.getComponents()[0] instanceof PDispStation) {
            update(pDispStation.getController());
        }
        if (c.getComponents()[0] instanceof PCar) {
            update(pCars.getController());
        }

        if (c.getComponents()[0] instanceof PRoute) {
            update(pRoute.getController());
        }
    }


    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

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

    public void showCarInf(Component c, int x, int y, Car car) {
        System.out.println(c.getClass());
        pCarInformation.setData(car);
        popCarInf.show(c, x, y);
    }

    public void showCarInf(JComponent c, int x, int y, int numberCar) {
        showCarInf(c, x, y, Model.getModel().getCarByNumber(numberCar));
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
            mf.tabbedtMain.setSelectedComponent(pStationFleet.getParent());
            pStationFleet.getController().searchCar(numberCar);
        }
        return search;
    }

    public boolean searchTrain(int idTrain) {
        boolean search = pStationFleet.getController().searchTrain(idTrain);
        if (search) {
            mf.tabbedtMain.setSelectedComponent(pStationFleet.getParent());
            pStationFleet.getController().searchTrain(idTrain);
        }
        return search;
    }

    public static void main(String[] args) {
        ControllerMain.getInstans();
    }

    synchronized public void update(final Updateble source) {
        final JPanel panel = ((JPanel) mf.tabbedtMain.getSelectedComponent());
        source.getPanel().setVisible(false);
        panel.repaint();
        dLoading.setLocationRelativeTo(mf);
        new SwingWorker() {
            private JPanel p = panel;
            private Updateble s = source;

            @Override
            protected Object doInBackground() throws Exception {
                source.update();
                return null;
            }

            public void done() {
                Component tmp = s.getPanel();
                tmp.setVisible(true);
                p.repaint();
                dLoading.setVisible(false);
            }
        }.execute();
        dLoading.setVisible(true);
    }
}
