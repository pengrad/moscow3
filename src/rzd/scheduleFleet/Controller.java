/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 06.09.2010
 * Time: 15:49:03
 * To change this template use File | Settings | File Templates.
 */
package rzd.scheduleFleet;

import rzd.scheduleFleet.PRaspisanie;
import rzd.test.PInfVagon;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {
    private JPopupMenu p;
    private PSchedule pSchedule;

    public Controller(PSchedule pSchedule) {
        this.pSchedule = pSchedule;
        p = new JPopupMenu();
        p.add(new PInfVagon(p));
    }

    public void mouseClicked(MouseEvent e) {
        p.show((JComponent) e.getSource(), e.getX() + 5, e.getY() + 5);
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
