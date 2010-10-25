/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd.stationFleet;

import logic.BusinessManager;
import rzd.model.objects.Train;
import rzd.stationFleet.Figure;
import rzd.test.ColorHelper;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import rzd.model.objects.Car;

import javax.imageio.ImageIO;

/**
 * @author ЧерныхЕА
 */
public class GCar extends Figure {
    private Car car;
    private Train train;
    private int number = 12345678;
    private ControllerStation c;

    public GCar(Car car, ControllerStation c) {
        this.car = car;
        this.c = c;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(69, 19));
        setShape(new Rectangle2D.Double(2, 2, 65, 14));
        addMouseListener(c);
    }

    public Car getCar() {
        return car;
    }

    public void paint(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(1.5f));
        g.setColor(ColorHelper.COLOR_VAGON_BORDER);
//        g.drawImage(c.getCarImage(), null, 0, 0);
        g.draw(new Rectangle2D.Double(shape.getBounds().getX() - 1, shape.getBounds().getY() - 1, shape.getBounds().getWidth() + 2, shape.getBounds().getHeight() + 2));
        if (!selected) {
            g.setColor(ColorHelper.COLOR_VAGON);
        } else {
            g.setColor(Color.RED);
            g.fill(shape);
        }
        g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        g.setColor(ColorHelper.COLOR_VAGON_NAMBER);
        if (car.getCarLocation().getIdLocation() == BusinessManager.IN_TRAIN) {
            g.setColor(Color.RED);
            g.drawString(new Integer(car.getCarNumberInTrain()).toString(), (int) shape.getBounds().getX() + 2, (int) shape.getBounds().getY() + 12);
            g.setColor(Color.BLACK);
            g.drawString(new Integer(car.getNumber()).toString(), (int) shape.getBounds().getX() + 15, (int) shape.getBounds().getY() + 12);
        } else {
            g.drawString(new Integer(car.getNumber()).toString(), (int) shape.getBounds().getX() + 8, (int) shape.getBounds().getY() + 12);
        }
    }
}
