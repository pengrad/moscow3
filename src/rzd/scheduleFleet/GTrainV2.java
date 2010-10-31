package rzd.scheduleFleet;

import logic.BusinessLogic;
import rzd.model.objects.Train;
import rzd.utils.KeyGenerator;
import rzd.scheduleFleet.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 05.09.2010
 * Time: 1:26:52
 * To change this template use File | Settings | File Templates.
 */
public class GTrainV2 extends JComponent {
    public final static int FORWARD = 0;
    public final static int BACK = 1;
    private Controller controller;
    private Train train;
    private Shape shape;
    private boolean mouseEntered = false;

    public GTrainV2(Controller c, Train train, int going, int x, int y, int w, int h) {
        super();
        this.controller = c;
        this.train = train;
        if (going == FORWARD)
            shape = new Line2D.Double(x, y, x + w, y + h);
        if (going == BACK)
            shape = new Line2D.Double(x, y + h, x + w, y);
        setBounds(0, 0, 5000, 5000);
        addMouseListener(controller);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEntered = true;
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseEntered = false;
                repaint();
            }
        });
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // if(mouseEntered)
        g2.setStroke(new BasicStroke((mouseEntered ? 4.0f : 2.0f), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

        //todo Разобраться сос татусами
        if (train.getTrainStatus().getId() == BusinessLogic.PLANNED || train.getTrainStatus().getId() == BusinessLogic.MAKED)
            g2.setColor(Color.GRAY);
        if (train.getTrainStatus().getId() == BusinessLogic.IN_WAY)
            g2.setColor(Color.RED);
        if (train.getTrainStatus().getId() == BusinessLogic.DESTROYED || train.getTrainStatus().getId() == BusinessLogic.ARRIVED)
            g2.setColor(Color.BLACK);
        g2.draw(shape);


        //-------------
//        AffineTransform fontAT = new AffineTransform();
//        fontAT.rotate(Math.toRadians(160));
//        Font fx = new Font("serif", Font.BOLD, 14).deriveFont(fontAT);
//        g.setFont(fx);
//        g.drawString("New String ", (int) shape.getBounds().getX(), (int) shape.getBounds().getY());
    }

    public Train getTrain() {
        return train;
    }

    public boolean contains(int x, int y) {
        return shape.intersects(x, y, 10, 10);
    }

}
