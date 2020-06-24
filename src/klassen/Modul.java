package klassen;

import frames.EditorFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Modul {
    public String id;
    public String name;
    public String fakultaet;
    public String ECTS;
    public String studiengang;
    public String farbe;
    public JLabel labelModul = new JLabel();
    public boolean setToSemester = false;
    
    public Modul(){
        createModul();
    }
    
    
    private void createModul(){
        
        labelModul.setVerticalAlignment(JLabel.TOP);
        labelModul.setHorizontalAlignment(JLabel.CENTER);
        labelModul.setOpaque(true);
        labelModul.setForeground(Color.black);
        labelModul.setBorder(BorderFactory.createLineBorder(Color.black));
        
    }
    
    public void initJLabelModul(){
        labelModul.setText(
                "<html>" 
                + "<p>" + name+ "</p> "
                + "<br>"
                + "<p>" + id + "</p>"
                + "<p>" + fakultaet + "</p>"
                + "<p>" + ECTS + "</p>"
                + "<p>" + studiengang + "</p>"
                + "</html>");
    }
    
    public void setFarbe(String col){
        
        labelModul.setBackground(new Color(Integer.parseInt(col)));
    }
    
}
