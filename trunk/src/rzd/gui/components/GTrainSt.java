/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.gui.components;

import java.awt.*;
import java.util.ArrayList;
import rzd.objects.Car;
import rzd.objects.Train;

/**
 * @author ЧерныхЕА
 */
public class GTrainSt extends Figure {
    private Train train;
    private ArrayList<GCar> gVagons;

    public GTrainSt(Train train) {
        this.train=train;
        setLayout(new FlowLayout());
        for (int i = 0; i < 30; i++) {
//            add(new GCar(new Car()));
        }
        gVagons = new ArrayList<GCar>(0);
    }

    public void addCar(GCar vagon, int position) {
        gVagons.add(position, vagon);
    }

    public void removeCar(GCar vagon) {
        gVagons.remove(vagon);
    }
    



    public void paint(Graphics2D g) {
        //   super.paintComponent(g);
    }
}
