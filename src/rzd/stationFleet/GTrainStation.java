/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.stationFleet;

import java.awt.*;
import java.util.ArrayList;

import rzd.model.objects.Car;
import rzd.model.objects.Train;
import rzd.stationFleet.Figure;
import rzd.stationFleet.GCar;

import javax.swing.*;

/**
 * @author ЧерныхЕА
 */
public class GTrainStation extends Figure {
    private ControllerStation c;
    private Train train;
    private ArrayList<GCar> gVagons;

    public GTrainStation(Train train, ControllerStation c) {
        this.c = c;
        gVagons = new ArrayList<GCar>();
        setBackground(Color.pink);
        this.train = train;
        setLayout(new FlowLayout());
        ArrayList<Car> cars = train.getCarsIn();
        if (cars != null && cars.size() > 0) {
            for (Car car : cars) {
                GCar gc = new GCar((car), c);
                add(gc);
                gVagons.add(gc);
            }
        } else {
            add(new JLabel("В состав поезда отсутсвуют вагоны"));
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


    public void paint(Graphics2D g2) {
        g2.setStroke(new BasicStroke(2.0f));
        if (selected) {
            g2.setColor(Color.BLUE);
            g2.fill(new Rectangle(2, 2, getWidth() - 5, getHeight() - 5));
        } else {
            g2.setColor(Color.RED);
//        System.out.println(getX() + "   " + getY() + "    " + getWidth() + "    " + getHeight());
            g2.draw(new Rectangle(2, 2, getWidth() - 5, getHeight() - 5));
        }
    }
}
