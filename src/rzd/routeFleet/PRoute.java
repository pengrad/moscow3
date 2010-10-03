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
        bCreateRoute.addActionListener(c);
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
        bCreateRoute = new javax.swing.JButton();
        bSearch = new javax.swing.JButton();
        fSearch = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(416, 304));

        tRoute.setModel(new ModelTable() );
        tRoute.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tRoute);

        bCreateRoute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rzd/resurce/add16px.png"))); // NOI18N
        bCreateRoute.setText("Создать маршрут");

        bSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rzd/resurce/search.gif"))); // NOI18N
        bSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bSearch.setBorderPainted(false);
        bSearch.setContentAreaFilled(false);
        bSearch.setFocusPainted(false);

        fSearch.setFont(new java.awt.Font("Tahoma", 2, 11));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(bCreateRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bCreateRoute)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bCreateRoute;
    public javax.swing.JButton bSearch;
    public javax.swing.JTextField fSearch;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tRoute;
    // End of variables declaration//GEN-END:variables
}
