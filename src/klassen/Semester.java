
package klassen;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Semester {
    public String semester_id;
    public int totalECTS = 0;
    public JPanel panelSemester = new JPanel();
    public JButton buttonSemesterAdd = new JButton();
    public JButton buttonSemesterRemove = new JButton();
    public JLabel ectsCounter = new JLabel();
    public Vector<Modul> modulContainer = new Vector<Modul>();
    
    private int heightSemester = 115;
    private int widthSemester = 1220;
    private int heightButton = heightSemester/2;
    private int widthButton = 35;
    private int heightECTSCounter = 30;
    private int widthECTSCounter = 100;
    
    public Semester(int index){
        createSemesterPanel(index);
        createAddButton(index);
        createRemoveButton(index);
        createECTSCounter(index);
    }
    public Semester(){
        
    }
    
    private void createSemesterPanel(int index){
        
        Point positionSemester = new Point(10, index*heightSemester + 10*(index + 1));
        
        panelSemester.setBackground(Color.yellow);
        panelSemester.setForeground(Color.black);
        panelSemester.setOpaque(true);
        panelSemester.setBorder(BorderFactory.createLineBorder(Color.black));
        panelSemester.setBounds(positionSemester.x, positionSemester.y, widthSemester, heightSemester);
    }
    
    private void createAddButton(int index){
        Point positionButtonAdd = new Point(widthSemester+10, index*heightSemester + 10*(index + 1));
        
        buttonSemesterAdd.setText("+");
        buttonSemesterAdd.setOpaque(true);
        buttonSemesterAdd.setBounds(positionButtonAdd.x, positionButtonAdd.y, widthButton, heightButton);
    }
    
    private void createRemoveButton(int index){
        Point positionButtonRemove = new Point(widthSemester+10, index*heightSemester + 10*(index + 1) + heightSemester/2);
        
        buttonSemesterRemove.setText("-");
        buttonSemesterRemove.setOpaque(true);
        buttonSemesterRemove.setBounds(positionButtonRemove.x, positionButtonRemove.y, widthButton, heightButton);
    }
    
    private void createECTSCounter(int index){
        Point positionECTSCounter = new Point(widthSemester + 45, (heightSemester-30)/2 + index*(heightSemester) + 10*(index + 1));
        ectsCounter.setText("Total ECTS: 0");
        ectsCounter.setOpaque(true);
        ectsCounter.setBorder(BorderFactory.createLineBorder(Color.black));
        ectsCounter.setBounds(positionECTSCounter.x, positionECTSCounter.y, widthECTSCounter, heightECTSCounter);
    }  
}
