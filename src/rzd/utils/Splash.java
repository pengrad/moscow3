package rzd.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

public class Splash extends JPanel implements MouseListener {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Splash s = new Splash();
        s.addMouseListener(s);
        f.add(s);
        f.setVisible(true);
    }

    public Splash() {
        setBounds(0, 0, 2000, 2000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new GradientPaint(0, 300, Color.PINK, 400, 0, Color.BLUE));
        g2.fill(this.getVisibleRect());
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font f = new Font(null, Font.ITALIC, 32);
        g2.setFont(f);
        g2.setColor(Color.RED);
        g2.drawString("ДИСПЕТЧЕР СТАНЦИИ", 20, 140);
        g2.setColor(Color.white);
        g2.setFont(new Font(null, Font.ITALIC, 18));
        g2.drawString("Версия от 09.11.2010", 200, 240);

    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
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
