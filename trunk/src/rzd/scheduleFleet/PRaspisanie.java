package rzd.scheduleFleet;

import rzd.scheduleFleet.Controller;
import rzd.scheduleFleet.GTrainV2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 03.09.2010
 * Time: 12:02:22
 * To change this template use File | Settings | File Templates.
 */
public class PRaspisanie extends JComponent {
    private int width = 1000;
    private int height = 1000;
    private Controller c;
    private String[] months = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    private Date dBeg;
    private Date dEnd;

    public boolean addPoezd(int namberPuti, GTrainV2 poezd) {
        return true;
    }

    public boolean removePoezd(GTrainV2 poezd) {
        return true;
    }

    public PRaspisanie() {
        super();
        setPreferredSize(new Dimension(2000, 800));
        GTrainV2 pv2 = new GTrainV2();
        pv2.setLocation(200, 100, 50);
        add(pv2);
        pv2 = new GTrainV2();
        pv2.setLocation(310, 100, 50);
        add(pv2);
        pv2 = new GTrainV2();
        pv2.setLocation(600, 100, 50);
        add(pv2);
        pv2 = new GTrainV2();
        pv2.setLocation(800, 100, 50);
        add(pv2);
        pv2 = new GTrainV2();
        pv2.setLocation(50, 200, 100);
        add(pv2);
        pv2 = new GTrainV2();
        pv2.setLocation(200, 200, 100);
        add(pv2);
//        this.c = new Controller(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawTable(g2);
    }


    private void drawTable(Graphics2D g2) {
        int width = this.getWidth();
        int height = this.getHeight();
        int dxManth = 1000;
        g2.setColor(Color.BLACK);
        int dy = height / 10;
        Line2D line;
        g2.setColor(Color.BLACK);
        line = new Line2D.Double(0, 1, width, 1);
        g2.draw(line);
        line = new Line2D.Double(0, 15, width, 15);
        g2.draw(line);

        int mB, mE;
        StringTokenizer st;
        Calendar cl = Calendar.getInstance();
        cl.setTime(dBeg);
        int ddB = cl.get(Calendar.DAY_OF_MONTH);
        int mmB = cl.get(Calendar.MONTH);
        int yyB = cl.get(Calendar.YEAR);
        cl.setTime(dEnd);
        int ddE = cl.get(Calendar.DAY_OF_MONTH);
        int mmE = cl.get(Calendar.MONTH);
        int yyE = cl.get(Calendar.YEAR);
        for (int yy = yyB; yy <= yyE; yy++) {
            if (yyB == yyE)
                mB = mmB;
            else mB = 0;
            if (yy == yyE)
                mE = mmE;
            else
                mE = 11;
            for (int mm = mB; mm <= mE; mm++) {
                //     new GregorianCalendar(yy, mm, 10).
            }
        }
        for (int m = 0; m < months.length; m++) {
            line = new Line2D.Double(m * dxManth, 0, m * dxManth, height);
            Color c = g2.getColor();
            g2.setColor(Color.RED);
            g2.draw(line);
            g2.setColor(c);
            g2.drawString(months[m], (int) (m * dxManth + 10), 13);
            int days = 30;
            double dxDay = dxManth / days;
            for (int i = 1; i <= days; i++) {
                g2.drawString(new Integer(i).toString(), (int) m * dxManth + (int) dxDay * i - 3, 28);
                line = new Line2D.Double(m * dxManth + dxDay * i, 30, m * dxManth + dxDay * i, width);
                g2.draw(line);
            }
        }
        line = new Line2D.Double(0, height - 1, width, height - 1);
        g2.draw(line);
    }
}
