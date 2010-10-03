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

/**
 * @author ЧерныхЕА
 */
public class GTrainStation extends Figure {
    private Controller c;
    private Train train;
    private ArrayList<GCar> gVagons;

    public GTrainStation(Train train, Controller c) {
        this.c = c;
        gVagons = new ArrayList<GCar>();
        setBackground(Color.pink);
        this.train = train;
        setLayout(new FlowLayout());
        for (int i = 0; i < 5; i++) {
            GCar gc = new GCar(new Car(111, "", null, null, "", "", "", "", "", "", false, 10, 10, 10, 10), c);
            add(gc);
            gVagons.add(gc);
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


    public void paint(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2.0f));
        g2.setColor(Color.RED);
//        System.out.println(getX() + "   " + getY() + "    " + getWidth() + "    " + getHeight());
        g2.draw(new Rectangle(2, 2, getWidth() - 5, getHeight() - 5));
    }
}
