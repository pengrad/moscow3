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

    public GTrainV2(Controller c,Train train,int going,int x, int y, int w, int h) {
        super();
        this.controller=c;
        this.train=train;
        if(going==FORWARD)
           shape = new Line2D.Double(x, y, x + w, y + h);
        if(going==BACK)
            shape = new Line2D.Double(x, y+h, x + w, y);

//        this.shape = new Line2D.Double(100, 100, 200, 100);
//        Path2D path = new Path2D.Double();
//        path.append(new Line2D.Double(shape.getBounds().getX() + shape.getBounds().getWidth() - 7, shape.getBounds().getX() - 4, shape.getBounds().getX() + shape.getBounds().getWidth(), shape.getBounds().getY()), true);
//        path.append(new Line2D.Double(shape.getBounds().getX() + shape.getBounds().getWidth() - 7, shape.getBounds().getX() + 4, shape.getBounds().getX() + shape.getBounds().getWidth(), shape.getBounds().getY()), true);
//        path.append(shape, false);
//        path.closePath();
//        this.shape = path;//
        setBounds(0, 0, 5000, 5000);
        addMouseListener(controller);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

//    public void setLocation(int x, int y, int width) {
//        AffineTransform af = new AffineTransform();
//        af.scale(width / shape.getBounds().getWidth(), 1);
//        shape = af.createTransformedShape(shape);
//        af = new AffineTransform();
//        af.translate(x - shape.getBounds().getX(), y - shape.getBounds().getY());
//        shape = af.createTransformedShape(shape);
//    }
//
//    public void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
//        g2.draw(shape);
//        g2.setColor(Color.WHITE);
//        g2.fill(new Rectangle2D.Double((int) shape.getBounds().getX() + 5, (int) shape.getBounds().getY() - 10, 35, 10));
//        g2.setColor(Color.RED);
//
//        g2.drawString("№748", (int) shape.getBounds().getX() + 5, (int) shape.getBounds().getY());
//    }

//    public void setLocation(int x, int y, int w, int h) {
//        shape = new Line2D.Double(x, y, x + w, y + h);
//    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

        //todo Разобраться сос татусами
        // if(train.getTrainStatus().getId()== BusinessLogic.)
        g2.setColor(Color.BLACK);
        g2.draw(shape);
//        g2.setColor(Color.WHITE);
//        g2.fill(new Rectangle2D.Double((int) shape.getBounds().getX() + 5, (int) shape.getBounds().getY() - 10, 35, 10));
//        g2.setColor(Color.RED);

        //      g2.drawString("№748", (int) shape.getBounds().getX() + 5, (int) shape.getBounds().getY());
    }

    public Train getTrain(){
        return train;
    }

    public boolean contains(int x, int y) {
        return shape.intersects(x, y, 5, 5);
    }

}
