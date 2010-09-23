/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd.stationFleet;

import rzd.stationFleet.Figure;
import rzd.test.ColorHelper;
import rzd.test.ControllerStation;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import rzd.model.objects.Car;

/**
 * @author ЧерныхЕА
 */
public class GCar extends Figure {
    private Car car;
    private int number = 12345678;

    public GCar(Car car) {
        this.car = car;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(54, 19));
        setShape(new Rectangle2D.Double(1, 1, 52, 15));
        addMouseListener(ControllerStation.get());
    }

    public Car getCar() {
        return car;
    }

    public void paint(Graphics2D g) {
        g.setColor(ColorHelper.COLOR_VAGON_BORDER);
        g.fill(new Rectangle2D.Double(shape.getBounds().getX() - 1, shape.getBounds().getY() - 1, shape.getBounds().getWidth() + 2, shape.getBounds().getHeight() + 2));
        if (!selected)
            g.setColor(ColorHelper.COLOR_VAGON);
        else
            g.setColor(Color.RED);
        g.fill(shape);
        g.setFont(new Font("Tahoma", Font.PLAIN, 11));
        g.setColor(ColorHelper.COLOR_VAGON_NAMBER);
        g.drawString(new Integer(car.getNumber()).toString(), (int) shape.getBounds().getX() + 2, (int) shape.getBounds().getY() + 13);
    }
}
