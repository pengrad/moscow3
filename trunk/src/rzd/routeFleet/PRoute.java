/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PTrain.java
 *
 * Created on 12.09.2010, 17:20:34
 */
package rzd.routeFleet;

/**
 *
 * @author ЧерныхЕА
 */
import rzd.ModelTable;
public class PRoute extends javax.swing.JPanel {

    private Controller c;

    /** Creates new form PTrain */
    public PRoute() {
        initComponents();
        this.c = new Controller(this);
        bCreateRout.addActionListener(c);
        tRoute.addMouseListener(c);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tRoute = new javax.swing.JTable();
        bCreateRout = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tSchedule = new javax.swing.JTable();
        bCreateSchedule = new javax.swing.JButton();

        tRoute.setModel(new ModelTable() );
        tRoute.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tRoute);

        bCreateRout.setText("Создать маршрут");

        tSchedule.setModel(new ModelTable() );
        jScrollPane2.setViewportView(tSchedule);

        bCreateSchedule.setText("Создать расписание");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(401, Short.MAX_VALUE)
                .addComponent(bCreateSchedule)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(407, Short.MAX_VALUE)
                .addComponent(bCreateRout, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(bCreateRout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(bCreateSchedule)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bCreateRout;
    public javax.swing.JButton bCreateSchedule;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tRoute;
    public javax.swing.JTable tSchedule;
    // End of variables declaration//GEN-END:variables
}
