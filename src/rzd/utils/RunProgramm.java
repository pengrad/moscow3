package rzd.utils;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RunProgramm extends JFrame implements ActionListener {
    Loading load;
    Timer t1;
    Timer t2;
    Timer t3;
    Timer t4;
    Timer t5;
    Random random;

    public RunProgramm() {
        initComponents();
        add(new Splash(), BorderLayout.CENTER);
        load = new Loading();
        add(load, BorderLayout.SOUTH);
        setVisible(true);
        random = new Random();
        t1 = new Timer(1000, this);
        t2 = new Timer(1500, this);
        t3 = new Timer(2000, this);
        t4 = new Timer(2000, this);
        t5 = new Timer(500, this);
        new Thread(new Runnable() {
            public void run() {
             }
        }).start();
        t1.start();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 400) / 2, (screenSize.height - 300) / 2, 400, 300);
//        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/resources/icon.gif")).getImage());
        this.setTitle("NetPaint");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t1) {
            t1.stop();
            int[] proc = new int[]{15, 16, 17, 18, 20};
            int r = random.nextInt(5);
            load.setProcent(proc[r]);
            repaint();
            t2.start();
        } else if (e.getSource() == t2) {
            t2.stop();
            int[] proc = new int[]{27, 39, 42, 44, 45};
            int r = random.nextInt(5);
            load.setProcent(proc[r]);
            repaint();
            t3.start();
        } else if (e.getSource() == t3) {
            t3.stop();
            int[] proc = new int[]{50, 58, 61, 69, 74};
            int r = random.nextInt(5);
            load.setProcent(proc[r]);
            repaint();
            t4.start();
        } else if (e.getSource() == t4) {
            t4.stop();
            load.setProcent(100);
            repaint();
            t5.start();
        } else if (e.getSource() == t5) {
            t5.stop();
            this.setVisible(false);
        }
    }

    private class Loading extends JComponent {
        private JLabel text;
        private int procent;

        public void setProcent(int p) {
            procent = p;
            text.setText(p + " %");
        }

        private Loading() {
            setBounds(0, 0, 400, 200);
            setLayout(new BorderLayout());
            text = new JLabel("0 %");
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setForeground(Color.black);
            text.setBackground(Color.darkGray);
            setBackground(Color.darkGray);
            procent = 0;
            add(text);
            setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            g.fillRect(0, 0, procent * 4, 20);
        }
    }
    
    public static void main(String args[]) {
        new RunProgramm();
    }
}
