/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PStationFleet.java
 *
 * Created on 11.09.2010, 17:34:53
 */
package rzd.tablo;

/**
 * @author ЧерныхЕА
 */
public class PStationFleet extends javax.swing.JPanel {

    private ControllerStation c;
    private String strSearch;

    /**
     * Creates new form PStationFleet
     */
    public PStationFleet() {
        initComponents();
         this.c = new ControllerStation(this);
    }

     public ControllerStation getController(){
         return c;
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

        jToggleButton1 = new javax.swing.JToggleButton();
        tabbedStation = new javax.swing.JTabbedPane();

        jToggleButton1.setText("jToggleButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedStation, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedStation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jToggleButton1;
    public javax.swing.JTabbedPane tabbedStation;
    // End of variables declaration//GEN-END:variables
}
