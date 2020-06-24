
package frames;

import klassen.*;
import javax.swing.*;
import java.awt.Color;
import java.util.Vector;
import klassen.Fakultaet;

public class ModulHinzufügenFrame extends javax.swing.JFrame {

    HSQL sql = new HSQL();
    public EditorFrame editor = new EditorFrame();
    EditorFrame parent;
    private Color col = Color.GRAY;
    
    public ModulHinzufügenFrame(EditorFrame jf) {
        initComponents();
        
        lblColorShow.setBackground(Color.GRAY);
        sql.initSQL();
        refreshJCBFakultaet();
        parent = jf;      
    }
    
    private void refreshJCBFakultaet(){
        jCBFakultaet.removeAllItems();
        Vector<Fakultaet> fakultaetContainer = new Vector<Fakultaet>();
        fakultaetContainer = sql.refreshFakultaetVector();
        
        for (int i = 0; i < fakultaetContainer.size(); i++) {
            jCBFakultaet.addItem(fakultaetContainer.get(i).name);
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCBFakultaet = new javax.swing.JComboBox<>();
        Speichern = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        AuswahlenButton = new javax.swing.JButton();
        lblColorShow = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCBFakultaet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Speichern.setText("OK");
        Speichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpeichernActionPerformed(evt);
            }
        });

        jLabel6.setText("ID");

        jLabel7.setText("Name");

        jLabel8.setText("Fakultät");

        jLabel9.setText("ECTS ");

        jLabel10.setText("Farbe");

        AuswahlenButton.setText("Auswahlen");
        AuswahlenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AuswahlenButtonActionPerformed(evt);
            }
        });

        lblColorShow.setBackground(new java.awt.Color(100, 40, 240));
        lblColorShow.setOpaque(true);

        jButton1.setText("Übernehmen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5)
                            .addComponent(jTextField4)
                            .addComponent(jCBFakultaet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblColorShow, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AuswahlenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(187, 187, 187))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Speichern, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBFakultaet, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AuswahlenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColorShow, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Speichern, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpeichernActionPerformed
                
        Modul newModul = new Modul();
        newModul.id = jTextField4.getText();
        newModul.name = jTextField5.getText();
        newModul.ECTS = jTextField2.getText();
        newModul.studiengang = parent.studiengangID;
        newModul.fakultaet = (String) jCBFakultaet.getSelectedItem();
        newModul.farbe = Integer.toString(col.getRGB());
        
        if ("".equals(newModul.id) && "".equals(newModul.name) && "".equals(newModul.ECTS)) {
            this.dispose();
        }
        
        else if("".equals(newModul.id) || "".equals(newModul.name) || "".equals(newModul.ECTS)){
            JOptionPane.showMessageDialog(null, "Diese Felder können Sie nicht leer lassen");
            return;
        }
        
        sql.saveModul(newModul);
        
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField2.setText("");
        parent.refreshJSPModulBar();
        this.dispose();
      
    }//GEN-LAST:event_SpeichernActionPerformed

    private void AuswahlenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AuswahlenButtonActionPerformed
        JColorChooser jcc = new JColorChooser();
        col = JColorChooser.showDialog(this, "Farbe auswählen!", Color.GRAY);
        lblColorShow.setBackground(col);
        this.repaint();
    }//GEN-LAST:event_AuswahlenButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Modul newModul = new Modul();
        newModul.id = jTextField4.getText();
        newModul.name = jTextField5.getText();
        newModul.ECTS = jTextField2.getText();
        newModul.studiengang = parent.studiengangID;
        newModul.fakultaet = (String) jCBFakultaet.getSelectedItem();
        newModul.farbe = Integer.toString(col.getRGB());
        
        if("".equals(newModul.id) || "".equals(newModul.name) || "".equals(newModul.ECTS)){
            JOptionPane.showMessageDialog(null, "Diese Felder können Sie nicht leer lassen");
            return;
        }
        sql.saveModul(newModul);
        
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField2.setText("");
        parent.refreshJSPModulBar();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AuswahlenButton;
    private javax.swing.JButton Speichern;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBFakultaet;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lblColorShow;
    // End of variables declaration//GEN-END:variables
}
