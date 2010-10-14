/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 06.09.2010
 * Time: 15:49:03
 * To change this template use File | Settings | File Templates.
 */
package rzd.scheduleFleet;

import rzd.model.objects.Train;
import rzd.scheduleFleet.PRaspisanie;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Controller implements MouseListener {
    private PSchedule pSchedule;
    private PRaspisanie pRaspisanie;

    public Controller(PSchedule pSchedule) {
        this.pSchedule = pSchedule;
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

    private void update(){
//        ArrayList<Train> trains
//      pSchedule.getPRaspisanie().addTrain();
    }
}
