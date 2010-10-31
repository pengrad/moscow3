package rzd.utils.calendar;

import sun.awt.WindowClosingListener;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 29.10.2010
 * Time: 13:46:33
 * To change this template use File | Settings | File Templates.
 */
public class Calendar extends JDialog {
//    private Calendarable  mf;
    private int yy, mm, dd;
    private String selectValue;


    public Calendar(Frame mf) {
        super(mf, true);
        setUndecorated(true);
        setTitle("Календарь");
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(new CalendarPanel(this));
        pack();
    }


    class CalendarPanel extends JPanel {

        private JDialog jdialog;
        protected JButton labs[];
        protected int leadGap = 0;
        java.util.Calendar calendar = new GregorianCalendar();
        protected final int thisYear = calendar.get(java.util.Calendar.YEAR);
        protected final int thisMonth = calendar.get(java.util.Calendar.MONTH);
        private JButton b0;
        private JComboBox monthChoice;
        private JComboBox yearChoice;

        CalendarPanel(JDialog jd) {
            super();
            //setPreferredSize(new Dimension(200,200));
            jdialog = jd;
            setYYMMDD(calendar.get(java.util.Calendar.YEAR), calendar.get(java.util.Calendar.MONTH),
                    calendar.get(java.util.Calendar.DAY_OF_MONTH));
            buildGUI();
            recompute();

        }

        CalendarPanel(int year, int month, int today) {
            super();
            setYYMMDD(year, month, today);
            buildGUI();
            recompute();
        }

        private void setYYMMDD(int year, int month, int today) {
            yy = year;
            mm = month;
            dd = today;
            //    System.out.println("yy=");
            //    System.out.println("yy=");
            //    System.out.println("yy=");

        }

        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};


        private void buildGUI() {
            getAccessibleContext().setAccessibleDescription(
                    "Calendar not accessible yet. Sorry!");
            setBorder(BorderFactory.createEtchedBorder());

            setLayout(new BorderLayout());

            JPanel tp = new JPanel();
            tp.add(monthChoice = new JComboBox());
            for (int i = 0; i < months.length; i++)
                monthChoice.addItem(months[i]);
            monthChoice.setSelectedItem(months[mm]);
            monthChoice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    int i = monthChoice.getSelectedIndex();
                    if (i >= 0) {
                        mm = i;
                        recompute();
                    }
                }
            });
            monthChoice.getAccessibleContext().setAccessibleName("Months");
            monthChoice.getAccessibleContext().setAccessibleDescription(
                    "Choose a month of the year");

            tp.add(yearChoice = new JComboBox());
            yearChoice.setEditable(true);
            for (int i = yy - 5; i < yy + 5; i++)
                yearChoice.addItem(Integer.toString(i));
            yearChoice.setSelectedItem(Integer.toString(yy));
            yearChoice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    int i = yearChoice.getSelectedIndex();
                    if (i >= 0) {
                        yy = Integer.parseInt(yearChoice.getSelectedItem()
                                .toString());
                        //   System.out.println("Year=" + yy+"mm="+mm+"day="+dd);
                        recompute();
                    }
                }
            });
            add(BorderLayout.CENTER, tp);

            JPanel bp = new JPanel();
            bp.setLayout(new GridLayout(7, 7));
            JButton b = new JButton("ПН");
            b.setBorder(BorderFactory.createEmptyBorder());
            bp.add(b0 = b);
            b = new JButton("ВТ");
            b.setBorder(BorderFactory.createEmptyBorder());
            bp.add(b);
            b = new JButton("СР");
            b.setBorder(BorderFactory.createEmptyBorder());
            bp.add(b);
            b = new JButton("ЧТ");
            b.setBorder(BorderFactory.createEmptyBorder());
            bp.add(b);
            b = new JButton("ПТ");
            b.setBorder(BorderFactory.createEmptyBorder());
            bp.add(b);
            b = new JButton("СБ");
            b.setBorder(BorderFactory.createEmptyBorder());
            bp.add(b);
            b = new JButton("ВС");
            b.setBorder(BorderFactory.createEmptyBorder());
            bp.add(b);

            ActionListener dateSetter = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String num = e.getActionCommand();
                    if (!num.equals("")) {
                        setDayActive(Integer.parseInt(num));
                        //Получаем дату       if (((Calendarable) obj).setDate(yy + "-" + (mm + 1) + "-" + dd, surse))
                        selectValue = dd + "." + (mm + 1) + "." + yy;

                        jdialog.setVisible(false);
                    }
                }
            };

            // Construct all the buttons, and add them.

            labs = new JButton[42]; // first row is days
            for (int j = 0; j < 42; j++) {
                JButton button = new JButton("");
                button.setPreferredSize(new Dimension(20, 20));
                button.setBorder(null);
                button.setBorderPainted(false);
                bp.add(labs[j] = button);
                labs[j].addActionListener(dateSetter);
            }

            add(BorderLayout.SOUTH, bp);
        }

        public final int dom[] = {31, 28, 31, 30, /* jan feb mar apr */
                31, 30, 31, 31, /* may jun jul aug */
                30, 31, 30, 31 /* sep oct nov dec */
        };

        /**
         * Compute which days to put where, in the Cal panel
         */
        protected void recompute() {
            // System.out.println("Cal::recompute: " + yy + ":" + mm + ":" + dd);
            if (mm < 0 || mm > 11)
                throw new IllegalArgumentException("Month " + mm
                        + " bad, must be 0-11");
            clearDayActive();
            calendar = new GregorianCalendar(yy, mm, dd);

            GregorianCalendar gCal = new GregorianCalendar(yy, mm, 1);
            gCal.setGregorianChange(new GregorianCalendar(1918, java.util.Calendar.FEBRUARY, 14).getTime());

            leadGap = gCal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
            if (leadGap == 0) leadGap = 7;
            int daysInMonth = dom[mm];
            if (isLeap(calendar.get(java.util.Calendar.YEAR)) && mm == 1)
                daysInMonth++;

            for (int i = 0; i <= leadGap; i++) {
                labs[i].setText("");
            }

            for (int i = 0; i < daysInMonth; i++) {
                JButton b = labs[leadGap - 1 + i];
                b.setText(Integer.toString(i + 1));
            }

            for (int i = leadGap - 1 + daysInMonth; i < 42; i++) {
                labs[i].setText("");
            }

            if (thisYear == yy && mm == thisMonth)
                setDayActive(dd); // shade the box for today

            repaint();
        }

        public boolean isLeap(int year) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                return true;
            return false;
        }

        public void setDate(int y, int m, int d) {
            yy = y;
            mm = m;
            dd = d;
            recompute();
        }

        private void clearDayActive() {
            JButton b;

            if (activeDay > 0) {
                b = labs[activeDay + leadGap - 2];
                b.setBackground(b0.getBackground());
                b.repaint();
                activeDay = -1;
            }
        }

        private int activeDay = -1;

        public void setDayActive(int newDay) {

            clearDayActive();
            if (newDay <= 0) {

                dd = new GregorianCalendar().get(java.util.Calendar.DAY_OF_MONTH);
            } else
                dd = newDay;

            Component square = labs[dd + leadGap - 2];
            square.setBackground(Color.red);
            square.repaint();
            activeDay = newDay;
        }
    }

    public String open() {
        selectValue = null;
        this.setVisible(true);
        return selectValue;
    }
}
