/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 06.09.2010
 * Time: 15:49:03
 * To change this template use File | Settings | File Templates.
 */
package rzd.scheduleFleet;

import rzd.ControllerMain;
import rzd.Updateble;

import rzd.model.objects.Train;
import rzd.scheduleFleet.PRaspisanie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller implements MouseListener, Updateble {
    private PSchedule pSchedule;

    public Controller(PSchedule pSchedule) {
        this.pSchedule = pSchedule;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1 && e.getClickCount() == 2 && e.getSource() instanceof GTrainV2) {
            Train train = ((GTrainV2) e.getSource()).getTrain();
            ControllerMain.getInstans().showTrainInf((GTrainV2) e.getSource(), e.getX(), e.getY(), train);
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

    public void update() {
        pSchedule.getPRaspisanie().update2(new GregorianCalendar(2010, 8, 1).getTime(), new GregorianCalendar(2010, 11, 25).getTime());
    }

    public Component getPanel() {
        return pSchedule;
    }
}
