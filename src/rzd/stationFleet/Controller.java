/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.stationFleet;

import rzd.stationFleet.GCar;
import rzd.stationFleet.GTrainStation;
import rzd.model.TestModel;
import rzd.model.objects.Road;
import rzd.model.objects.RoadType;

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 * @author ЧерныхЕА
 */
public class Controller implements ActionListener {

    private PStationFleet pStationFleet;
    private HashMap<PRoad, ContainerRoad> roadContainers;
    private HashMap<RoadType, HashMap> roadType;

    public Controller(PStationFleet p) {
        this.pStationFleet = p;
        roadType = new HashMap<RoadType, HashMap>();
        roadContainers = new HashMap<PRoad, ContainerRoad>();
        makeTabs();
        update();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pStationFleet.fSearchCarByNumber && !pStationFleet.fSearchCarByNumber.getText().trim().equals("") && !pStationFleet.fSearchCarByNumber.getText().trim().equals("Поиск по номеру вагона")) {
            try {
                if (!searchCar(new Integer(pStationFleet.fSearchCarByNumber.getText()))) {
                    throw new Exception();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pStationFleet, "Ничего не найдено");
            }
        }
    }

    //Метод обновления состояния всех путей станции

    public void update() {
        try {
            //Очищаем пути от поездов и вагонов  и добавляем поезда и вагоны
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
                    //todo Надо заполнить поезда вагонпи не забыть!!!!
                    roads.get(k).addTrain(TestModel.get().getTrainByRoad(k));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(pStationFleet, e.getMessage());
        }
    }
    //Формирование структуры табов на основе...

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
                            roadContainers.put(road, cRoad);
                            cRoad.addRoad(road);
                            roads.put(r.get(k), road);
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

    //Поиск вагонов в.т.ч которые в составе поезда
    //плюс перемотка скролов согласно положению вагона на карте

    public boolean searchCar(int numberCar) {
        Set<RoadType> keyType = roadType.keySet();
        Iterator<RoadType> itType = keyType.iterator();
        while (itType.hasNext()) {
            RoadType key = itType.next();
            HashMap<Road, PRoad> roads = roadType.get(key);
            Set<Road> keyRoad = roads.keySet();
            Iterator<Road> itRoad = keyRoad.iterator();
            while (itRoad.hasNext()) {
                Road k = itRoad.next();
                Component[] components = roads.get(k).getTrainAndCar();
                if (components != null && components.length > 0) {
                    for (int i = 0; i < components.length; i++) {
                        //Если это поезд
                        if (components[i] instanceof GTrainStation) {
                            GTrainStation train = (GTrainStation) components[i];
                            ArrayList<GCar> cars = train.getCars();
                            if (cars != null && cars.size() > 0) {
                                for (GCar car : cars) {
                                    int number = car.getCar().getNumber();
                                    if (number == numberCar) {
                                        PRoad pr = roads.get(k);
                                        ContainerRoad cr = roadContainers.get(pr);
                                        cr.setViewRoad(roads.get(k));
                                        pr.jScrollPane1.getHorizontalScrollBar().setValue((int) (train.getLocation().getX() + car.getLocation().getX()));
                                        pStationFleet.tabbedStation.setSelectedComponent(cr);
                                        new ViewSearchCar(car).execute();
                                        return true;
                                    }
                                }
                            }
                            //Если это вагон
                        } else if (components[i] instanceof GCar) {
                            GCar car = (GCar) components[i];
                            int number = car.getCar().getNumber();
                            if (number == numberCar) {
                                PRoad pr = roads.get(k);
                                ContainerRoad cr = roadContainers.get(pr);
                                cr.setViewRoad(roads.get(k));
                                pr.jScrollPane1.getHorizontalScrollBar().setValue((int) car.getLocation().getX());
                                pStationFleet.tabbedStation.setSelectedComponent(cr);
                                new ViewSearchCar(car).execute();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private class ViewSearchCar extends SwingWorker {
        private GCar gCar;

        public ViewSearchCar(GCar gCar) {
            this.gCar = gCar;
        }

        @Override
        protected Object doInBackground() throws Exception {
            for (int i = 0; i < 5; i++) {
                gCar.setSelected(true);
                publish(new List());
                Thread.sleep(100);
                gCar.setSelected(false);
                publish(new List());
                Thread.sleep(100);
            }
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        protected void process(java.util.List chunks) {
            gCar.repaint();
        }
    }
}


