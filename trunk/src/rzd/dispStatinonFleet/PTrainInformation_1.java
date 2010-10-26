/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PTrainInformation.java
 *
 * Created on 12.10.2010, 23:39:37
 */
package rzd.dispStatinonFleet;

import java.util.ArrayList;
import javax.swing.JList;

import rzd.ControllerMain;
import rzd.model.objects.Car;

import javax.swing.*;

import rzd.model.Model;
import rzd.model.objects.Train;
import rzd.utils.Utils;

/**
 * @author Евгений
 */
public class PTrainInformation_1 extends javax.swing.JPanel {

    /**
     * Creates new form PTrainInformation
     */
    public PTrainInformation_1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fRoute = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lDT = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fChief = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lCarInTrain = new javax.swing.JList(new DefaultListModel());
        jLabel5 = new javax.swing.JLabel();
        fRoadType = new javax.swing.JTextField();
        fRoad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fDate = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        fRoute.setBackground(new java.awt.Color(255, 255, 255));
        fRoute.setEditable(false);
        fRoute.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        fRoute.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 13));
        jLabel7.setForeground(new java.awt.Color(82, 82, 82));
        jLabel7.setText("Маршрут");

        lDT.setBackground(new java.awt.Color(204, 204, 204));
        lDT.setFont(new java.awt.Font("Times New Roman", 2, 13));
        lDT.setForeground(new java.awt.Color(82, 82, 82));
        lDT.setText("Дата и время ");

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 13));
        jLabel4.setForeground(new java.awt.Color(82, 82, 82));
        jLabel4.setText("Начальник поезда");

        fChief.setBackground(new java.awt.Color(255, 255, 255));
        fChief.setEditable(false);
        fChief.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        fChief.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel3.setForeground(new java.awt.Color(82, 82, 82));
        jLabel3.setText("Отправление/прибытие");

        lCarInTrain.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        lCarInTrain.setFont(new java.awt.Font("Times New Roman", 0, 14));
        lCarInTrain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lCarInTrainshowInfByCar(evt);
            }
        });
        jScrollPane1.setViewportView(lCarInTrain);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 15));
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Состав вагонов");

        fRoadType.setBackground(new java.awt.Color(255, 255, 255));
        fRoadType.setEditable(false);
        fRoadType.setFont(new java.awt.Font("Times New Roman", 0, 14));
        fRoadType.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        fRoad.setBackground(new java.awt.Color(255, 255, 255));
        fRoad.setEditable(false);
        fRoad.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        fRoad.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 13));
        jLabel1.setForeground(new java.awt.Color(82, 82, 82));
        jLabel1.setText("Тип пути");

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 13));
        jLabel2.setForeground(new java.awt.Color(82, 82, 82));
        jLabel2.setText("Путь");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel6.setForeground(new java.awt.Color(82, 82, 82));
        jLabel6.setText("Информация");

        fDate.setBackground(new java.awt.Color(255, 255, 255));
        fDate.setEditable(false);
        fDate.setFont(new java.awt.Font("Times New Roman", 0, 14));
        fDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jPanel1.setBackground(new java.awt.Color(100, 149, 237));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13));
        jButton1.setForeground(new java.awt.Color(255, 0, 51));
        jButton1.setText("X");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Информация о поезде");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(jLabel2))
                                                                .addGap(41, 41, 41))
                                                        .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel7))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fDate)
                                        .addComponent(fRoute, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                        .addComponent(fChief)
                                        .addComponent(fRoad)
                                        .addComponent(fRoadType, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(fRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(fChief, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lDT, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(fRoadType, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(fRoad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lCarInTrainshowInfByCar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lCarInTrainshowInfByCar
        if (evt.getSource() instanceof JList && evt.getButton() == 1 && evt.getClickCount() == 2) {
            JList list = (JList) evt.getSource();
            Car car = ((Car) list.getSelectedValue());
            ControllerMain.getInstans().showCarInf(list, evt.getX() + 5, evt.getY() + 5, car);
        }
    }//GEN-LAST:event_lCarInTrainshowInfByCar

//    public void setData(int idTrain) {
//        Train train = Model.getModel().getTrainById(idTrain);
//        setData(train);
//    }

    public void setData(Train train) {
        String route = "";
        String date = "";
        if (train.getRoute().getSheduleForward().equals(train.getShedule())) {
            route = train.getRoute().getNumberForward() + "  " + train.getRoute().getCityFrom() + " - " + train.getRoute().getCityTo();
            date = Utils.convertDateTimeToStr(train.getDtDeparture());
        }
        if (train.getRoute().getSheduleBack().equals(train.getShedule())) {
            route = train.getRoute().getNumberBack() + "  " + train.getRoute().getCityTo() + " - " + train.getRoute().getCityFrom();
            date = Utils.convertDateTimeToStr(train.getDtDestination());
        }
        fRoute.setText(route);
        fDate.setText(date);
        fChief.setText(train.getChief());
        if (train.getRoad() != null) {
            fRoadType.setText(train.getRoad().getRoadType().getName());
            fRoad.setText(train.getRoad().getName());
        } else {
            fRoadType.setText("");
            fRoad.setText("");
        }
        ArrayList<Car> cars = train.getCarsIn();
        ((DefaultListModel) lCarInTrain.getModel()).removeAllElements();
        if (cars != null && cars.size() > 0) {
            for (Car car : cars) {
                ((DefaultListModel) lCarInTrain.getModel()).addElement(car);
            }
        }

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.getParent().setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fChief;
    private javax.swing.JTextField fDate;
    private javax.swing.JTextField fRoad;
    private javax.swing.JTextField fRoadType;
    private javax.swing.JTextField fRoute;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lCarInTrain;
    private javax.swing.JLabel lDT;
    // End of variables declaration//GEN-END:variables
}
