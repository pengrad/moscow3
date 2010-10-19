/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 06.09.2010
 * Time: 15:49:03
 * To change this template use File | Settings | File Templates.
 */
package rzd.scheduleFleet;

import rzd.Updateble;
import rzd.model.TestModel;
import rzd.model.objects.Train;
import rzd.scheduleFleet.PRaspisanie;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller implements MouseListener, Updateble {
    private PSchedule pSchedule;
    private PRaspisanie pRaspisanie;

    public Controller(PSchedule pSchedule) {
        this.pSchedule = pSchedule;
        this.pRaspisanie = pSchedule.getPRaspisanie();
    }

    public void mouseClicked(MouseEvent e) {

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
      
        //  pRaspisanie.update();
///    pRaspisanie.addTrain(new Train(0, new GregorianCalendar(2010, 9, 2).getTime(),new GregorianCalendar(2010, 9, 5).getTime()));
    }
}
