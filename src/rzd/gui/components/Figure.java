/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 15.08.2010
 * Time: 16:49:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class Figure extends JComponent {
    private int key;
    protected Shape shape;
    protected boolean selected = false;
    protected boolean entered = false;


    public Figure() {
        super();
        key = KeyGenerator.getKey();
        setBounds(0, 0, 5000, 5000);
//        addMouseListener(ControllerTree.get());
//        addMouseMotionListener(ControllerTree.get());
//        addMouseListener(MoverNodes.get());
//        addMouseMotionListener(MoverNodes.get());
//        addMouseListener(ConnectorNodes.get());
//        addMouseMotionListener(ConnectorNodes.get());
//        addMouseListener(Remover.get());

//        addMouseListener(actionNode);

    }

    public int getKey() {
        return key;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }


    public abstract void paint(Graphics2D g);


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paint((Graphics2D) g);
    }


//    public boolean contains(int x, int y) {
//        if (shape != null) {
//            return shape.contains(x, y);
//        } else return false;
//    }

    public void transform(AffineTransform af) {
        shape = af.createTransformedShape(shape);
        revalidate();
        repaint();
    }

    public void move(double x, double y) {
        AffineTransform af = new AffineTransform();
        af.translate(x - shape.getBounds().getX(), y - shape.getBounds().getY());
        shape = af.createTransformedShape(shape);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }


//    public boolean equals(Object o) {
//        if (o == null) return false;
//        if (o instanceof Figure)
//            return this.key == ((Node) o).getKey();
//        else if (o instanceof ConnectLine)
//            return this.key == ((ConnectLine) o).getChild().getKey();
//        else return false;
//    }

    public String toString() {
        return "Node - " + key;
    }

}
