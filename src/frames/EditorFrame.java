package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import klassen.*;

public class EditorFrame extends javax.swing.JFrame {
    
    private HSQL sql = new HSQL();
    private Vector<Semester> semesterContainer = new Vector<Semester>();
    public String studiengangID;
    public Modul selectedModul = new Modul();
    
    public EditorFrame(){
        
    }
    
    public EditorFrame(String ID) {
        studiengangID = ID;
        initComponents();
        sql.initSQL();
        
        semesterContainer = sql.refreshSemesterVector(studiengangID);
        initSemester(semesterContainer);
        
        refreshSemester();
        refreshJSPModulBar();
    }
    
    private void initSemester(Vector<Semester> semesterContainer){
        for (int i = 0; i < semesterContainer.size(); i++) {
            addActionListenerButton(semesterContainer.get(i));
            this.add(semesterContainer.get(i).panelSemester);
            this.add(semesterContainer.get(i).buttonSemesterAdd);
            this.add(semesterContainer.get(i).buttonSemesterRemove);
            this.add(semesterContainer.get(i).ectsCounter);
        }
    }
    
    public void refreshJSPModulBar(){
        jPModulBar.removeAll();
        Vector<Modul> modulContainerSP = new Vector<Modul>();
        modulContainerSP = sql.refreshModulVectorSP(studiengangID);
        for (int i = 0; i < modulContainerSP.size(); i++) {
            
            //jPModulBar.setLayout(new FlowLayout());
            
            if(jPModulBar.getHeight() <= 103*i){
                jPModulBar.setPreferredSize(new Dimension(266, 103*(i+2)));
            }
            
            JLabel label = modulContainerSP.get(i).labelModul;
            int ects = Integer.parseInt(modulContainerSP.get(i).ECTS);
            label.setBounds(3, i*103 + 3, ects*40, 100);
            addMouseListenerModul(modulContainerSP.get(i));

            jPModulBar.add(label);
        }
        jPModulBar.repaint();
    }
    
    private void addMouseListenerModul(Modul modul){
        
        JLabel labelModul = modul.labelModul;
        labelModul.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent e) {
                    labelModul.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                }

                public void mouseReleased(MouseEvent e){
                    if (e.isPopupTrigger()) {
                        labelModul.setBorder(BorderFactory.createLineBorder(Color.RED));
                        selectedModul = modul;
                    }
                }

                public void mouseExited(MouseEvent e) {
                    labelModul.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            });
    }
    
    private void addActionListenerButton(Semester semester){
        JButton buttonAdd = semester.buttonSemesterAdd;
        buttonAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt){
                    semesterButtonAddActionPerformed(evt, semester);
                }
            });
        
        JButton buttonRemove = semester.buttonSemesterRemove;
        buttonRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt){
                    semesterButtonRemoveActionPerformed(evt, semester);
                }
            });
    }
    
    private void semesterButtonAddActionPerformed(java.awt.event.ActionEvent evt, Semester semester){
        if(semester.totalECTS + Integer.parseInt(selectedModul.ECTS) > 30){
            JOptionPane.showMessageDialog(null, "Sie können nicht mehr als 30 ECTS Punkte einfügen!");
            return;
        }
        if(selectedModul.setToSemester == true){
            JOptionPane.showMessageDialog(null, "Dieses Modul wurde bereits hinzugefügt!");
            return;
        }
        if(selectedModul.name == null){
            JOptionPane.showMessageDialog(null, "Bitte wählen Sie ein Modul aus!");
            return;
        }
        
        sql.saveModulToSemester(semester.semester_id, selectedModul.id, studiengangID);
        selectedModul.setToSemester = true;
        refreshSemester();
        refreshJSPModulBar();
    }
    
    private void semesterButtonRemoveActionPerformed(java.awt.event.ActionEvent evt, Semester semester){
        if(semester.panelSemester.getComponentCount() == 0){
            JOptionPane.showMessageDialog(null, "Dieses Semester ist leer!");
            return;
        }
        if(selectedModul.setToSemester == false){
            JOptionPane.showMessageDialog(null, "Bitte wählen Sie ein Modul aus, das entfernt werden soll!");
            return;
        }
        
        sql.removeModulFromSemester(semester.semester_id, selectedModul, studiengangID);
        selectedModul.setToSemester = false;
        refreshSemester();
        refreshJSPModulBar();
    }
    
    private void refreshSemester(){
        
        for (int i = 0; i < semesterContainer.size(); i++) {
            
            Semester semester = semesterContainer.get(i);
            semester.panelSemester.removeAll();
            semester.totalECTS = 0;
            semester.panelSemester.setLayout(null);
            Vector<Modul> modulContainer = sql.refreshModulVectorSemester(semester.semester_id);

            int currentPosition = 0;
            for (int j = 0; j < modulContainer.size(); j++) {

                int ects = Integer.parseInt(modulContainer.get(j).ECTS);
                modulContainer.get(j).labelModul.setBounds(currentPosition + 3, 3, ects*40, 100);
                modulContainer.get(j).setToSemester = true;
                addMouseListenerModul(modulContainer.get(j));
                semester.panelSemester.add(modulContainer.get(j).labelModul);

                currentPosition = currentPosition + ects*40;
                semester.totalECTS = semester.totalECTS + Integer.parseInt(modulContainer.get(j).ECTS);
                
            }
            semester.ectsCounter.setText("Total ECTS: " + semester.totalECTS);
            semester.panelSemester.repaint();
        }
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSPModulBar = new javax.swing.JScrollPane();
        jPModulBar = new javax.swing.JPanel();
        jBModulHinzufügen = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPModulBar.setAutoscrolls(true);
        jPModulBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPModulBarLayout = new javax.swing.GroupLayout(jPModulBar);
        jPModulBar.setLayout(jPModulBarLayout);
        jPModulBarLayout.setHorizontalGroup(
            jPModulBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        jPModulBarLayout.setVerticalGroup(
            jPModulBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        jSPModulBar.setViewportView(jPModulBar);
        jPModulBar.getAccessibleContext().setAccessibleDescription("");

        jBModulHinzufügen.setText("Modul hinzufügen");
        jBModulHinzufügen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModulHinzufügenActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(674, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSPModulBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBModulHinzufügen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSPModulBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBModulHinzufügen)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBModulHinzufügenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModulHinzufügenActionPerformed
        ModulHinzufügenFrame modulHinzufügen = new ModulHinzufügenFrame(this);
        modulHinzufügen.setVisible(true);
        modulHinzufügen.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jBModulHinzufügenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("xxxx:" + jPModulBar.getWidth() + "---" + jPModulBar.getHeight());
            
            
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBModulHinzufügen;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPModulBar;
    private javax.swing.JScrollPane jSPModulBar;
    // End of variables declaration//GEN-END:variables
}
