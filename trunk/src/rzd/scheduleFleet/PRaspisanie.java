package rzd.scheduleFleet;

import rzd.model.Model;
import rzd.model.objects.Route;
import rzd.model.objects.Train;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 03.09.2010
 * Time: 12:02:22
 * To change this template use File | Settings | File Templates.
 */
public class PRaspisanie extends JComponent {
    private Controller controller;
    private int sizeDay = 40;
    private int sizeRoute = 100;
    private int smTop = 45;
    private ArrayList<Route> routes;
    private int widthPanel;
    private int heightPanel;
    private Controller c;
    private String[] months = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    private Date dBeg = null;//new GregorianCalendar(2010, 9, 1).getTime();
    private Date dEnd = null;//new GregorianCalendar(2010, 11, 26).getTime();
    private JPanel heder;

    public PRaspisanie(Controller c) {
        super();
        this.controller = c;
        heder = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                drawHeder(g2);
            }
        };
        heder.setBackground(new Color(213, 213, 213));
        this.add(heder);
    }


    private void addTrain(Train train) {
        if (train == null) return;
        Calendar cl = Calendar.getInstance();
        cl.setTime(dBeg);
        int ddB = cl.get(Calendar.DAY_OF_MONTH);
        int mmB = cl.get(Calendar.MONTH);
        int yyB = cl.get(Calendar.YEAR);
        //    cl.setTime(train.getDtDestination());
        cl.setTime(train.getDtDeparture());
        int ddPB = cl.get(Calendar.DAY_OF_MONTH);
        int mmPB = cl.get(Calendar.MONTH);
        int yyPB = cl.get(Calendar.YEAR);
        int minPB = cl.get(Calendar.HOUR_OF_DAY) * 60 + cl.get(Calendar.MINUTE);
        cl.setTime(train.getDtDestination());
        //  cl.setTime(train.getDtDeparture());

        int ddPE = cl.get(Calendar.DAY_OF_MONTH);
        int mmPE = cl.get(Calendar.MONTH);
        int yyPE = cl.get(Calendar.YEAR);
        int minPE = cl.get(Calendar.HOUR_OF_DAY) * 60 + cl.get(Calendar.MINUTE);
        //Считаем день отправления
        int countDay = 0;
        int mB = 0, mE = 0;
        for (int yy = yyB; yy <= yyPB; yy++) {
            if (yy == yyB)
                mB = mmB;
            else mB = 0;
            if (yy == yyPB)
                mE = mmPB;
            else
                mE = 11;
            //Перебераем месяца
            for (int mm = mB; mm < mE; mm++) {
                countDay = countDay + new GregorianCalendar(yy, mm, 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            }
        }
        ddPB = countDay + ddPB;
        //Обнуляем счетчик
        countDay = 0;
        //Считаем день прибытия
        for (int yy = yyB; yy <= yyPE; yy++) {
            if (yy == yyB)
                mB = mmB;
            else mB = 0;
            if (yy == yyPE)
                mE = mmPE;
            else
                mE = 11;
            //Перебераем месяца
            for (int mm = mB; mm < mE; mm++) {
                countDay = countDay + new GregorianCalendar(yy, mm, 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            }
        }
        ddPE = countDay + ddPE;
        //-------------------------------------
        int numberRoute = routes.indexOf(train.getRoute());
        if (numberRoute < 0) {
            updateRoute();
        }
        numberRoute = routes.indexOf(train.getRoute());
        System.out.println(train.getRoute());

        if (numberRoute >= 0) {
            int x = ddPB * sizeDay + ((int) ((((double) minPB) / (24 * 60)) * sizeDay));
            int y = numberRoute * sizeRoute + smTop;
//            System.out.println("b=" + ddPB);
//            System.out.println("e=" + ddPE);
//            System.out.println("b-e=" + (ddPE - ddPB));
            int w = (ddPE - ddPB) * sizeDay + ((int) ((((double) minPE) / (24 * 60)) * sizeDay));
            int h = sizeRoute;

            int route = 0;
            if (train.getRoute().getSheduleForward().equals(train.getShedule())) {
                route = GTrainV2.FORWARD;
            }
            if (train.getRoute().getSheduleBack().equals(train.getShedule())) {
                route = GTrainV2.BACK;
            }
            GTrainV2 pv2 = new GTrainV2(controller, train, route, x, y, w, h);
            add(pv2);
        }
    }

    private void updateRoute() {
        routes = Model.getModel().getRoutes();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTable(g2);
    }

    public void update2(Date dB, Date dE) {
        this.dBeg = dB;
        this.dEnd = dE;
        updateRoute();
        int mB, mE;
        Calendar cl = Calendar.getInstance();
        cl.setTime(dBeg);
        int ddB = cl.get(Calendar.DAY_OF_MONTH);
        int mmB = cl.get(Calendar.MONTH);
        int yyB = cl.get(Calendar.YEAR);
        cl.setTime(dEnd);
        int ddE = cl.get(Calendar.DAY_OF_MONTH);
        int mmE = cl.get(Calendar.MONTH);
        int yyE = cl.get(Calendar.YEAR);
        //Считаем ширину экрана
        {
            int countDay = 0;
            for (int yy = yyB; yy <= yyE; yy++) {
                if (yy == yyB)
                    mB = mmB;
                else mB = 0;
                if (yy == yyE)
                    mE = mmE;
                else
                    mE = 11;
                //Перебераем месяца
                for (int mm = mB; mm <= mE; mm++) {
                    countDay = countDay + new GregorianCalendar(yy, mm, 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
                }
            }
            widthPanel = (int) (sizeDay * countDay);
        }
        //Считаем ширину экрана
        if (routes != null) {
            heightPanel = (int) (routes.size() * sizeRoute) + smTop;
        }
        setPreferredSize(new Dimension(widthPanel, heightPanel));
        ArrayList<Train> trains = Model.getModel().getTrainsForPeriod(dBeg, dEnd);
        if (trains != null) {
            System.out.println("size="+trains.size());
            for (Train train : trains) {
                addTrain(train);
            }
        }
        heder.setSize(widthPanel, smTop);
        repaint();
    }


    private void drawHeder(Graphics2D hg2) {
        if (dBeg == null || dEnd == null) {
            return;
        }
        int mB, mE;
        Calendar cl = Calendar.getInstance();
        cl.setTime(dBeg);
        int ddB = cl.get(Calendar.DAY_OF_MONTH);
        int mmB = cl.get(Calendar.MONTH);
        int yyB = cl.get(Calendar.YEAR);
        cl.setTime(dEnd);
        int ddE = cl.get(Calendar.DAY_OF_MONTH);
        int mmE = cl.get(Calendar.MONTH);
        int yyE = cl.get(Calendar.YEAR);
        Line2D line;
        int left = 0;
        //todo Рисуем шапку в панели
        //Перебераем года-------------------------------------
        for (int yy = yyB; yy <= yyE; yy++) {
            //Рисуем год
            hg2.setColor(Color.RED);
            hg2.drawString(new Integer(yy).toString(), left + 10, 13);
            hg2.setColor(Color.BLACK);
            //---------
            if (yy == yyB)
                mB = mmB;
            else mB = 0;
            if (yy == yyE)
                mE = mmE;
            else
                mE = 11;
            //Перебераем месяца
            for (int mm = mB; mm <= mE; mm++) {
                int minDD = 1;
                int maxDD = new GregorianCalendar(yy, mm, 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
                //Рисуем месяц
                hg2.setColor(Color.BLACK);
                hg2.draw(new Line2D.Double(left, 15, left + maxDD * sizeDay, 15));
                hg2.setColor(Color.RED);
                hg2.drawString(months[mm], left + 10 + sizeDay, 28);
                hg2.setColor(Color.BLACK);
                hg2.draw(new Line2D.Double(left, 30, left + maxDD * sizeDay, 30));
                hg2.draw(new Line2D.Double(left + maxDD * sizeDay + sizeDay, 15, left + maxDD * sizeDay + sizeDay, 30));

                //Горизонтальная линия после дней
                hg2.draw(new Line2D.Double(left, 45, left + maxDD * sizeDay, 45));
                hg2.setColor(Color.BLACK);
                //---------

                //Перебераем дни
                for (int dd = minDD; dd <= maxDD; dd++) {
                    left = left + sizeDay;
                    //Рисуем дни
                    hg2.setColor(Color.BLUE);
                    hg2.drawString(new Integer(dd).toString(), left - 3, 43);
                }
            }
        }

    }

    private void drawTable(Graphics2D g2) {
        Graphics2D hg2 = (Graphics2D) heder.getGraphics();
        if (dBeg == null || dEnd == null) {
            Dimension d = getSize();
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            g2.drawString("Задайте период и нажмите кнопку смотреть", (int) d.getWidth() / 2 - 100, (int) d.getHeight() / 2);
            return;
        }
        int mB, mE;
        Calendar cl = Calendar.getInstance();
        cl.setTime(dBeg);
        int ddB = cl.get(Calendar.DAY_OF_MONTH);
        int mmB = cl.get(Calendar.MONTH);
        int yyB = cl.get(Calendar.YEAR);
        cl.setTime(dEnd);
        int ddE = cl.get(Calendar.DAY_OF_MONTH);
        int mmE = cl.get(Calendar.MONTH);
        int yyE = cl.get(Calendar.YEAR);
        Line2D line;
        int left = 0;
        int ty = ((JScrollPane) ((JViewport) getParent().getParent()).getParent()).getVerticalScrollBar().getValue();
        //Перебераем года-------------------------------------
        for (int yy = yyB; yy <= yyE; yy++) {
            if (yy == yyB)
                mB = mmB;
            else mB = 0;
            if (yy == yyE)
                mE = mmE;
            else
                mE = 11;
            //Перебераем месяца
            for (int mm = mB; mm <= mE; mm++) {
                int minDD = 1;
                int maxDD = new GregorianCalendar(yy, mm, 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
                //Перебераем дни
                for (int dd = minDD; dd <= maxDD; dd++) {
                    left = left + sizeDay;
                    //Рисуем дни
                    if (dd == minDD)
                        g2.setColor(Color.RED);
                    else
                        g2.setColor(Color.BLACK);
                    g2.draw(new Line2D.Double(left, 45, left, heightPanel));
                    //---------
                    //Рисуем часы
                    int dh = (int) (((double) sizeDay) / 4.0);
                    int lh = left - sizeDay;
                    for (int hh = 6; hh < 24; hh = hh + 6) {
                        lh = lh + dh;
                        Color tc = g2.getColor();
                        g2.setColor(Color.lightGray);
                        line = new Line2D.Double(lh, 45, lh, heightPanel);
                        g2.draw(line);
                        g2.setColor(tc);
                    }
                }
            }
        }


        //----------------------------------------------------
        //Рисуем Маршруты
        for (int i = 1; i < routes.size() + 1; i++) {
            g2.setColor(Color.BLUE);
            int tx = ((JScrollPane) ((JViewport) getParent().getParent()).getParent()).getHorizontalScrollBar().getValue();
            Font f = new Font("Arial", Font.PLAIN, 12);
            g2.setFont(f);
//            JLabel tmp=new JLabel(routes.get(i-1).getCityFrom());
//            tmp.setFont(f);
//            System.out.println(tmp.getBounds().getX());
            g2.setColor(Color.WHITE);
            g2.fill(new Rectangle2D.Double(tx + 20, (i - 1) * sizeRoute + smTop + 3, 100, 15));
            g2.fill(new Rectangle2D.Double(tx + 20, (i) * sizeRoute + smTop -17, 100, 15));

            g2.setColor(Color.BLUE);

            g2.drawString(routes.get(i - 1).getNumberForward() + " - " + routes.get(i - 1).getCityFrom(), tx + 20, (i - 1) * sizeRoute + 15 + smTop);

            g2.drawString(routes.get(i - 1).getNumberBack() + " - " + routes.get(i - 1).getCityTo(), tx + 20, i * sizeRoute - 5 + smTop);

            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            g2.setColor(Color.gray);

            g2.draw(new Line2D.Double(0, i * sizeRoute + smTop, widthPanel, i * sizeRoute + smTop));
        }
        heder.setLocation(0, ty);
    }


}
