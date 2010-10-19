package rzd.scheduleFleet;

import rzd.model.TestModel;
import rzd.model.objects.Route;
import rzd.model.objects.Train;
import rzd.scheduleFleet.Controller;
import rzd.scheduleFleet.GTrainV2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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

    public PRaspisanie(Controller c) {
        super();
        this.controller=c;
    }


    private void addTrain(Train train) {
        if (train == null) return;
        Calendar cl = Calendar.getInstance();
        cl.setTime(dBeg);
        int ddB = cl.get(Calendar.DAY_OF_MONTH);
        int mmB = cl.get(Calendar.MONTH);
        int yyB = cl.get(Calendar.YEAR);
        cl.setTime(train.getDtDeparture());
        int ddPB = cl.get(Calendar.DAY_OF_MONTH);
        int mmPB = cl.get(Calendar.MONTH);
        int yyPB = cl.get(Calendar.YEAR);
        int minPB = cl.get(Calendar.HOUR_OF_DAY) * 60 + cl.get(Calendar.MINUTE);
        cl.setTime(train.getDtDestination());
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
            for (int mm = mB; mm <= mE; mm++) {
                countDay = countDay + new GregorianCalendar(yy, mm, 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            }
        }
        ddPB = countDay + ddPB;
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
        //   pv2.setLocation(ddPB * sizeDay + ((int) ((((double) minPB) / (24 * 60)) * sizeDay)), 100, (ddPE - ddPB) * sizeDay + ((int) ((((double) minPE) / (24 * 60)) * sizeDay)));
        int numberRoute = routes.indexOf(train.getRoute());
        if (numberRoute < 0) {
            updateRoute();
        }
        numberRoute = routes.indexOf(train.getRoute());
        if (numberRoute > 0) {
            int x = ddPB * sizeDay + ((int) ((((double) minPB) / (24 * 60)) * sizeDay));
            int y = numberRoute * sizeRoute + smTop;
            int w = (ddPE - ddPB) * sizeDay + ((int) ((((double) minPE) / (24 * 60)) * sizeDay));
            int h = sizeRoute;

            int route = 0;
            if (train.getRoute().getSheduleForward().equals(train.getShedule())) {
                route = GTrainV2.FORWARD;
            }
            if (train.getRoute().getSheduleBack().equals(train.getShedule())) {
                route = GTrainV2.BACK;
            }
            GTrainV2 pv2 = new GTrainV2(controller,train, route, x, y, w, h);
            add(pv2);
        }
    }

    private void updateRoute() {
        routes = TestModel.get().getRoutes();
    }


//    private int countDaysBetweenDate(Date d1, Date d2) {
//        int mB, mE;
//        Calendar cl = Calendar.getInstance();
//        cl.setTime(d1);
//        int ddB = cl.get(Calendar.DAY_OF_MONTH);
//        int mmB = cl.get(Calendar.MONTH);
//        int yyB = cl.get(Calendar.YEAR);
//        cl.setTime(d2);
//        int ddE = cl.get(Calendar.DAY_OF_MONTH);
//        int mmE = cl.get(Calendar.MONTH);
//        int yyE = cl.get(Calendar.YEAR);
//        int countDay = 0;
//        //Перебераем года
//        for (int yy = yyB; yy <= yyE; yy++) {
//            if (yyB == yyE)
//                mB = mmB;
//            else mB = 0;
//            if (yy == yyE)
//                mE = mmE;
//            else
//                mE = 11;
//            //Перебераем месяца
//            for (int mm = mB; mm <= mE; mm++) {
//                countDay = countDay + new GregorianCalendar(yy, mm, 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
//                if (yy == yyB && mm == mB && yyB == yyE && mB == mE)
//                    countDay = ddE - ddB;
//                else if (yy == yyB && mm == mB)
//                    countDay = countDay - ddB-1;
//                else if (yy == yyE && mm == mE)
//                    countDay = countDay - ddE;
//            }
//        }
//        return countDay;
//    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawTable(g2);
    }

    public void update(Date dBegin, Date dEnd) {
        updateRoute();
        int mB, mE;
        Calendar cl = Calendar.getInstance();
        cl.setTime(dBegin);
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
     //todo Надо получить у модели поезда

    }

    private void drawTable(Graphics2D g2) {
        if(dBeg==null||dEnd==null){
             Dimension d=getSize();
             g2.setFont(new Font("Arial",Font.BOLD,12));
             g2.drawString("Задайте период и нажмите кнопку смотреть",(int)d.getWidth()/2-100,(int)d.getHeight()/2);
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
            //Перебераем года-------------------------------------
            for (int yy = yyB; yy <= yyE; yy++) {
                //Рисуем год
                g2.setColor(Color.RED);
                g2.drawString(new Integer(yy).toString(), left + 10, 13);
                g2.setColor(Color.BLACK);
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
                    g2.setColor(Color.BLACK);
                    g2.draw(new Line2D.Double(left, 15, left + maxDD * sizeDay, 15));
                    g2.setColor(Color.RED);
                    g2.drawString(months[mm], left + 10 + sizeDay, 28);
                    g2.setColor(Color.BLACK);
                    g2.draw(new Line2D.Double(left, 30, left + maxDD * sizeDay, 30));
                    g2.draw(new Line2D.Double(left + maxDD * sizeDay + sizeDay, 15, left + maxDD * sizeDay + sizeDay, 30));
                    //Горизонтальная линия после дней
                    g2.draw(new Line2D.Double(left, 45, left + maxDD * sizeDay, 45));
                    g2.setColor(Color.BLACK);
                    //---------
                    //Перебераем дни
                    for (int dd = minDD; dd <= maxDD; dd++) {
                        left = left + sizeDay;
                        //Рисуем дни
                        g2.setColor(Color.BLUE);
                        g2.drawString(new Integer(dd).toString(), left - 3, 43);
                        if (dd == minDD) g2.setColor(Color.RED);
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
            for (int i = 1; i < routes.size() + 1; i++) {
                g2.setColor(Color.BLUE);
                int tx = ((JScrollPane) ((JViewport) getParent().getParent()).getParent()).getHorizontalScrollBar().getValue();
                Font f = new Font("Arial", Font.ITALIC, 12);
                g2.setFont(f);
//            JLabel tmp=new JLabel(routes.get(i-1).getCityFrom());
//            tmp.setFont(f);
//            System.out.println(tmp.getBounds().getX());
                g2.setColor(Color.white);
                //  g2.fill(new Rectangle2D.Double(tx + 20, (i-1) * sizeRoute +smTop,50,10));
                g2.setColor(Color.BLUE);
                g2.drawString(routes.get(i - 1).getCityFrom(), tx + 20, (i - 1) * sizeRoute + 15 + smTop);
                g2.drawString(routes.get(i - 1).getCityTo(), tx + 20, i * sizeRoute - 5 + smTop);
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                g2.setColor(Color.gray);
                g2.draw(new Line2D.Double(0, i * sizeRoute + smTop, widthPanel, i * sizeRoute + smTop));
            }
    }


}
