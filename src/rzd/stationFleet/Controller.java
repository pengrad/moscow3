/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.stationFleet;

import rzd.bd.Base;
import rzd.gui.components.GCar;
import rzd.gui.components.GTrainSt;
import rzd.objects.Car;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;

/**
 * @author ЧерныхЕА
 */
public class Controller implements ActionListener {

    private PStationFleet pStationFleet;
    private HashMap<Integer, HashMap> roadType;

    public Controller(PStationFleet p) {
        this.pStationFleet = p;
        roadType = new HashMap<Integer, HashMap>();
        makeTabs();
        update();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pStationFleet.fSearchCarByNumber && !pStationFleet.fSearchCarByNumber.getText().trim().equals("")) {
            searchCar(new Integer(pStationFleet.fSearchCarByNumber.getText()));
        }
    }

    //Метод обновления состояния всех путей станции

    public void update() {
        try {
            //Очищаем пути от поездов и вагонов
            Set<Integer> keyType = roadType.keySet();
            Iterator<Integer> itType = keyType.iterator();
            while (itType.hasNext()) {
                Integer key = itType.next();
                HashMap<Integer, Road> roads = roadType.get(key);
                Set<Integer> keyRoad = roads.keySet();
                Iterator<Integer> itRoad = keyRoad.iterator();
                while (itRoad.hasNext()) {
                    Integer k = itRoad.next();
                    roads.get(k).deleteAll();
                }
            }

            //Заполняем пути поездами и вагонами
            ArrayList cars = Base.getInstance().getCarsOnRoad();
            if (cars != null) {
                for (int i = 0; i < cars.size(); i++) {
                    int idTypeRoad = new Integer(((Object[]) cars.get(i))[0].toString());
                    int idRoad = new Integer(((Object[]) cars.get(i))[1].toString());
                    int numberCar = new Integer(((Object[]) cars.get(i))[2].toString());
                    ((Road) roadType.get(idTypeRoad).get(idRoad)).addCar(new GCar(new Car()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(pStationFleet, e.getMessage());
        }
    }

    private void makeTabs() {
        JTabbedPane tabPanel = pStationFleet.tabbedStation;
        try {
            ArrayList rt = Base.getInstance().getRoadTypes();
            if (rt != null) {
                ContainerRoad cRoad;
                HashMap<Integer, Road> roads;
                for (int i = 0; i < rt.size(); i++) {
                    int idType = new Integer(((Object[]) rt.get(i))[0].toString());
                    String nameType = ((Object[]) rt.get(i))[1].toString();
                    ArrayList r = Base.getInstance().getRoadsByType(idType);
                    if (r != null) {
                        cRoad = new ContainerRoad();
                        roads = new HashMap<Integer, Road>();
                        for (int k = 0; k < r.size(); k++) {
                            int id = new Integer(((Object[]) rt.get(i))[0].toString());
                            String number = ((Object[]) r.get(k))[1].toString();
                            Road road = new Road(number);
                            cRoad.addRoad(road);
                            roads.put(id, road);
                        }
                        tabPanel.add(nameType, cRoad);
                        roadType.put(idType, roads);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(pStationFleet, e.getMessage());
        }
    }

    public boolean searchCar(int numberCar) {
        Set<Integer> keyType = roadType.keySet();
        Iterator<Integer> itType = keyType.iterator();
        while (itType.hasNext()) {
            Integer key = itType.next();
            HashMap<Integer, Road> roads = roadType.get(key);
            Set<Integer> keyRoad = roads.keySet();
            Iterator<Integer> itRoad = keyRoad.iterator();
            while (itRoad.hasNext()) {
                Integer k = itRoad.next();
                Component[] components = roads.get(k).getComponents();
                if (components != null && components.length > 0) {
                    for (int i = 0; i < components.length; i++) {
                        if (components[i] instanceof GTrainSt) {
                            GTrainSt train = (GTrainSt) components[i];
                            Component[] cars = train.getComponents();
                            if (cars != null && cars.length > 0) {
                                for (int n = 0; n < cars.length; n++) {
                                    if (cars[n] instanceof GCar) {
                                        GCar car = (GCar) cars[i];
                                        int number = car.getNumber();
                                        if (number == numberCar) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        } else if (components[i] instanceof GCar) {
                            GCar car = (GCar) components[i];
                            int number = car.getNumber();
                            if (number == numberCar) {
                           
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}


