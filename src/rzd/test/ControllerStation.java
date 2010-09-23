/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.test;

import rzd.stationFleet.GCar;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author ЧерныхЕА
 */
public class ControllerStation implements MouseListener {

    private final static ControllerStation con = new ControllerStation();
    private JPopupMenu p;

    public static ControllerStation get() {
        return con;
    }

    private ControllerStation() {
        p = new JPopupMenu();
        p.add(new PInfVagon(p));
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof GCar) {
            GCar v = (GCar) e.getSource();
            p.show(v, e.getX() + 20, e.getY() + 20);
        }
         if (e.getSource() instanceof JLabel) {
            p.show((JLabel)e.getSource(), e.getX() + 20, e.getY() + 20);
        }

    }

    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
