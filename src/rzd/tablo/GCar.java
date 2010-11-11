/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd.tablo;

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
import javax.swing.*;

/**
 * @author ЧерныхЕА
 */
public class GCar extends JComponent {
    private Car car;
    private Train train;
    private int number = 12345678;
    private ControllerStation c;

    public GCar(Car car, ControllerStation c) {
        this.car = car;
        this.c = c;
        setLayout(new BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(new JLabel(" "));
        if (car.getCarLocation().getIdLocation() == BusinessManager.IN_TRAIN) {
            JLabel pn = new JLabel(new Integer(car.getCarNumberInTrain()).toString());
            pn.setFont(new Font("Times New Roman", 0, 12));
            pn.setForeground(Color.RED);
            add(pn);
            add(new JLabel(" "));
        }
        JLabel nv = new JLabel(new Integer(car.getNumber()).toString());
        nv.setFont(new Font("Times New Roman", 0, 12));
        add(nv);
        add(new JLabel(" "));
    }

    public Car getCar() {
        return car;
    }
}
