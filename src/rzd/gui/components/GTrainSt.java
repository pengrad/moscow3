/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.gui.components;

import java.awt.*;
import java.util.ArrayList;

import rzd.model.objects.Car;
import rzd.model.objects.Train;

/**
 * @author ЧерныхЕА
 */
public class GTrainSt extends Figure {
    private Train train;
    private ArrayList<GCar> gVagons;

    public GTrainSt(Train train) {
        setBackground(Color.pink);
        this.train = train;
        setLayout(new FlowLayout());
        for (int i = 0; i < 5; i++) {
            add(new GCar(new Car(12345678)));
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
        //      System.out.println(this.getWidth());
//           super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.YELLOW);
        System.out.println(getX() +"   "+ getY() +"    "+ getWidth() +"    "+ getHeight());
        g2.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));
    }
}
