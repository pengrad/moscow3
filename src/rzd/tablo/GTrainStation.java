/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.tablo;

import java.awt.*;
import java.util.ArrayList;

import rzd.model.objects.Car;
import rzd.model.objects.Train;
import rzd.stationFleet.Figure;
import rzd.test.ColorHelper;


import javax.swing.*;

/**
 * @author ЧерныхЕА
 */
public class GTrainStation extends JComponent {
    private ControllerStation c;
    private Train train;
    private ArrayList<GCar> gVagons;

    public GTrainStation(Train train, ControllerStation c) {
        this.c = c;
        gVagons = new ArrayList<GCar>();
        setBackground(Color.pink);
//        setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.train = train;
        setLayout(new BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        ArrayList<Car> cars = train.getCarsIn();
        if (cars != null && cars.size() > 0) {
            System.out.println("size=" + cars.size());
            for (int i = 0; i < cars.size(); i++) {
                GCar gc = new GCar((cars.get(i)), c);
                gc.setBackground(Color.lightGray);
                add(gc);
                if (i<(cars.size()-1)) {
                    JLabel l = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/rzd/resurce/connectCar.gif")));
                    add(l);
                }
                gVagons.add(gc);
            }
        } else {
            setBorder(BorderFactory.createLineBorder(ColorHelper.COLOR_VAGON_BORDER));
                  
            add(new JLabel(" В состав поезда отсутсвуют вагоны "));
            //setPreferredSize(new Dimension(200,22));
        }
    }

    public void addCar(GCar vagon, int position) {
        gVagons.add(position, vagon);
    }

    public void removeCar(GCar vagon) {
        gVagons.remove(vagon);
    }

    public ArrayList<GCar> getCars() {
        return gVagons;
    }

    public Train getTrain() {
        return train;
    }
         }
