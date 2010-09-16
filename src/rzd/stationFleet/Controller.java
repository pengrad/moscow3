/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.stationFleet;

import rzd.bd.Base;
import rzd.gui.components.GCar;
import rzd.gui.components.GTrainSt;
import rzd.model.TestModel;
import rzd.model.objects.Road;
import rzd.model.objects.RoadType;

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
    private HashMap<RoadType, HashMap> roadType;

    public Controller(PStationFleet p) {
        this.pStationFleet = p;
        roadType = new HashMap<RoadType, HashMap>();
        makeTabs();
        update();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pStationFleet.fSearchCarByNumber && !pStationFleet.fSearchCarByNumber.getText().trim().equals("")) {
            //    searchCar(new Integer(pStationFleet.fSearchCarByNumber.getText()));
        }
    }

    //Метод обновления состояния всех путей станции

    public void update() {
        try {
            //Очищаем пути от поездов и вагонов
            Set<RoadType> keyType = roadType.keySet();
            Iterator<RoadType> itType = keyType.iterator();
            while (itType.hasNext()) {
                RoadType key = itType.next();
                HashMap<Road, PRoad> roads = roadType.get(key);
                Set<Road> keyRoad = roads.keySet();
                Iterator<Road> itRoad = keyRoad.iterator();
                while (itRoad.hasNext()) {
                    Road k = itRoad.next();
                    roads.get(k).deleteAll();
                    roads.get(k).addCars(TestModel.get().getCarsByRoad(k));
                }
            }

            //Заполняем пути поездами и вагонами
//            ArrayList cars = TestModel.get().getCarsByRoad();
//            if (cars != null) {
//                for (int i = 0; i < cars.size(); i++) {
//                    int idTypeRoad = new Integer(((Object[]) cars.get(i))[0].toString());
//                    int idRoad = new Integer(((Object[]) cars.get(i))[1].toString());
//                    int numberCar = new Integer(((Object[]) cars.get(i))[2].toString());
////                    ((Road) roadType.get(idTypeRoad).get(idRoad)).addCar(new GCar(new Car()));
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(pStationFleet, e.getMessage());
        }
    }

    private void makeTabs() {
        JTabbedPane tabPanel = pStationFleet.tabbedStation;
        try {
            ArrayList<RoadType> rTypes = TestModel.get().getRoadTypes();
            if (rTypes != null) {
                ContainerRoad cRoad;
                HashMap<Road, PRoad> roads;
                for (int i = 0; i < rTypes.size(); i++) {
                    RoadType rType = rTypes.get(i);
                    ArrayList<Road> r = TestModel.get().getRoadsByType(rType);
                    if (r != null) {
                        cRoad = new ContainerRoad();
                        roads = new HashMap<Road, PRoad>();
                        for (int k = 0; k < r.size(); k++) {
                            PRoad road = new PRoad(r.get(k).getName());
                            cRoad.addRoad(road);
                            roads.put(r.get(i), road);
                        }
                        tabPanel.add(rType.getName(), cRoad);
                        roadType.put(rType, roads);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(pStationFleet, e.getMessage());
        }
    }

//    public boolean searchCar(int numberCar) {
//        Set<Integer> keyType = roadType.keySet();
//        Iterator<Integer> itType = keyType.iterator();
//        while (itType.hasNext()) {
//            Integer key = itType.next();
//            HashMap<Integer, PRoad> roads = roadType.get(key);
//            Set<Integer> keyRoad = roads.keySet();
//            Iterator<Integer> itRoad = keyRoad.iterator();
//            while (itRoad.hasNext()) {
//                Integer k = itRoad.next();
//                Component[] components = roads.get(k).getComponents();
//                if (components != null && components.length > 0) {
//                    for (int i = 0; i < components.length; i++) {
//                        if (components[i] instanceof GTrainSt) {
//                            GTrainSt train = (GTrainSt) components[i];
//                            Component[] cars = train.getComponents();
//                            if (cars != null && cars.length > 0) {
//                                for (int n = 0; n < cars.length; n++) {
//                                    if (cars[n] instanceof GCar) {
//                                        GCar car = (GCar) cars[i];
//                                        int number = car.getNumber();
//                                        if (number == numberCar) {
//                                            return true;
//                                        }
//                                    }
//                                }
//                            }
//                        } else if (components[i] instanceof GCar) {
//                            GCar car = (GCar) components[i];
//                            int number = car.getNumber();
//                            if (number == numberCar) {
//
//                                return true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
}


