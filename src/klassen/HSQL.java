package klassen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class HSQL {
    
    private Connection con;
    private String url;
    
    public void initSQL(){
        try{
            System.out.println("Connecting database...");
            Class.forName("org.hsqldb.jdbcDriver");
            url = "jdbc:hsqldb:file:C:\\Users\\Ensar\\Desktop\\DB\\";
            con = DriverManager.getConnection(url, "SA", "");
            System.out.println("Database connected!");
            
        }
        catch (ClassNotFoundException e) {
            System.out.println("Database connection error. ClassNotFoundException: " + e);
        } 
        catch (SQLException e) {
            System.out.println("Database connection error. SQLException: " + e);
        }
    }
        
    public void createTables(){

        try {
            Statement stmt = con.createStatement();

            stmt.executeUpdate("CREATE TABLE Studiengang_name (name VARCHAR(50) NOT NULL,PRIMARY KEY (name))");
            String[] namen = {"TMS", "WING", "HUK", "BWL", "GEN", "INF", "Siyaset", "Elektronik", "falan filan"};
            for (int i = 0; i < namen.length; i++) {
                stmt.executeUpdate("INSERT INTO \"PUBLIC\".\"STUDIENGANG_NAME\" ( \"NAME\" ) VALUES ( '"+ namen[i] +"')");
            }

        } 

        catch (SQLException ex) {
            System.out.println("Database error. CREATE TABLE Studiengang_name" + ex);
        }

        try {
            Statement stmt = con.createStatement();

            stmt.executeUpdate("CREATE TABLE fakultaet_name (name VARCHAR(50) NOT NULL,PRIMARY KEY (name))");
            String[] namen_fak = {"Ingenieurswissenschaften", "Naturwissenschaften", "Rechtswissenschaften", "Wirtschaftswissenschaften", "Kultur- und Sozialwissenschaften"};
            for (int i = 0; i < namen_fak.length; i++) {
                stmt.executeUpdate("INSERT INTO \"PUBLIC\".\"FAKULTAET_NAME\" ( \"NAME\" ) VALUES ( '"+namen_fak[i]+"')");
            }
        } 

        catch (SQLException ex) {
            System.out.println("Database error. CREATE TABLE fakultaet_name" + ex);
        }

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE Studiengang_Jahr (studiengang_id VARCHAR(50) NOT NULL, jahr int not null, name varchar(50) not null, anzahlSemester int not null,  PRIMARY KEY (studiengang_id))");
        } 
        catch (SQLException ex) {
            System.out.println("Database error. SQLException. Studiengang_Jahr" + ex);
        }
        
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE MODUL_SCROLLPANE (SCROLLPANE_ID VARCHAR(50) NOT NULL, PRIMARY KEY (SCROLLPANE_ID))");
        } 
        catch (SQLException ex) {
            System.out.println("Database error. SQLException. MODUL_SCROLLPANE" + ex);
        }

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE Studiengang_Semester (semester_id VARCHAR(50) NOT NULL, studiengang_id varchar(50) not null, PRIMARY KEY (semester_id))");
            } 
        catch (SQLException ex) {
            System.out.println("Database error. SQLException");
        }

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE Semester_Modul (semester_id VARCHAR(50) NOT NULL, modul_id varchar(50) not null)");
            } 
        catch (SQLException ex) {
            System.out.println("Database error. SQLException");
        }

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE Modul (modul_id VARCHAR(50) NOT NULL, name varchar(50) not null, studiengang varchar(50) not null, fakultaet varchar(50) not null, ects int  not null, farbe varchar(50) not null, PRIMARY KEY (modul_id))");
        } 
        catch (SQLException ex) {
            System.out.println("Database error. SQLException");
        }

    }
    
    public Vector<Studiengang> refreshStudiengangVector(){
        
        Vector<Studiengang> studiengangContainer = new Vector<Studiengang>();
        
        ResultSet rs;
            try { 
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM studiengang_jahr");
                con.commit();
                
                while (rs.next()) {
                    Studiengang studiengang = new Studiengang();
                    studiengang.studiengangID = rs.getString("STUDIENGANG_ID");
                    studiengang.anzahlSemester = rs.getString("ANZAHLSEMESTER");
                    studiengangContainer.add(studiengang);
                }
            }
            
            catch (SQLException ex) {
                Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return studiengangContainer;
    }
    
    public Vector<String> refreshStudiengangNameVector(){
        Vector<String> studiengangNameContainer = new Vector<String>();
        
        ResultSet rs;
            try { 
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM STUDIENGANG_NAME");
                con.commit();
                
                while (rs.next()) {
                    studiengangNameContainer.add(rs.getString("NAME"));
                }
            }
            
            catch (SQLException ex) {
                Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return studiengangNameContainer;
    }
    
    public Vector<Semester> refreshSemesterVector(String studiengangID){
        Vector<Semester> semesterContainer = new Vector<Semester>();
        
        ResultSet rs;
            try { 
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM STUDIENGANG_SEMESTER WHERE STUDIENGANG_ID = '"+ studiengangID +"' ");
                con.commit();
                
                int indexSemester = 0;
                while (rs.next()) {
                    Semester semester = new Semester(indexSemester);
                    semester.semester_id = rs.getString("SEMESTER_ID");
                    semesterContainer.add(semester);
                    indexSemester++;
                }
            }
            
            catch (SQLException ex) {
                Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return semesterContainer;
    }
    
    public Vector<Modul> refreshModulVectorSP(String studiengangID){
        Vector<Modul> modulContainer = new Vector<Modul>();
        
        ResultSet rs;

        try { 
            Statement stmt = con.createStatement();
            ResultSet rsStudiengangModul = stmt.executeQuery("SELECT * FROM MODUL_" + studiengangID);
            con.commit();

            while (rsStudiengangModul.next()) {


                rs = stmt.executeQuery("SELECT * FROM MODUL WHERE MODUL_ID = '"+rsStudiengangModul.getString("MODUL_ID")+"'");
                con.commit();

                rs.next();

                Modul modul = new Modul();
                modul.id = rs.getString("MODUL_ID");
                modul.name = rs.getString("NAME");
                modul.studiengang = rs.getString("STUDIENGANG");
                modul.fakultaet = rs.getString("FAKULTAET");
                modul.ECTS = rs.getString("ECTS");
                modul.setFarbe(rs.getString("FARBE"));
                modul.initJLabelModul();

                modulContainer.add(modul);
            }
        }

        catch (SQLException ex) {
            Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return modulContainer;
    }
    
    public Vector<Modul> refreshModulVectorSemester(String semesterID){
        Vector<Modul> modulContainer = new Vector<Modul>();
        
        ResultSet rs;
            try { 
                Statement stmt = con.createStatement();
                ResultSet rsSemesterModul = stmt.executeQuery("SELECT * FROM SEMESTER_MODUL WHERE SEMESTER_ID = '"+semesterID+"'");
                con.commit();
                
                while (rsSemesterModul.next()) {
                    
                    
                    rs = stmt.executeQuery("SELECT * FROM MODUL WHERE MODUL_ID = '"+rsSemesterModul.getString("MODUL_ID")+"'");
                    con.commit();
                    
                    rs.next();
                    
                    Modul modul = new Modul();
                    modul.id = rs.getString("MODUL_ID");
                    modul.name = rs.getString("NAME");
                    modul.studiengang = rs.getString("STUDIENGANG");
                    modul.fakultaet = rs.getString("FAKULTAET");
                    modul.ECTS = rs.getString("ECTS");
                    modul.setFarbe(rs.getString("FARBE"));
                    modul.initJLabelModul();
                    
                    modulContainer.add(modul);
                }
            }
            
            catch (SQLException ex) {
                Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return modulContainer;
    }
    
    public Vector<Fakultaet> refreshFakultaetVector(){
        Vector<Fakultaet> fakultaetContainer = new Vector<Fakultaet>();
        
        ResultSet rs;
            try { 
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM FAKULTAET_NAME");
                con.commit();
                
                while (rs.next()) {
                    Fakultaet fakultaet = new Fakultaet();
                    fakultaet.name = rs.getString("NAME");
                    
                    fakultaetContainer.add(fakultaet);
                }
            }
            
            catch (SQLException ex) {
                Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return fakultaetContainer;
    }
    
    public void saveStudiengang(Studiengang studiengang){
        
        try {
            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate("INSERT INTO \"PUBLIC\".\"STUDIENGANG_JAHR\" ( \"STUDIENGANG_ID\", \"JAHR\", \"NAME\", \"ANZAHLSEMESTER\" ) VALUES ( '"+studiengang.studiengangID+"', "+studiengang.jahr+", '"+studiengang.name+"', "+studiengang.anzahlSemester+")");
            
            if (result == 1) {
                System.out.println("Successfully added to table!");
            }
            
            int anzahlSemester = Integer.parseInt(studiengang.anzahlSemester);
            for (int i = 1; i <= anzahlSemester; i++) {
                stmt.executeUpdate("INSERT INTO \"PUBLIC\".\"STUDIENGANG_SEMESTER\" ( \"SEMESTER_ID\", \"STUDIENGANG_ID\" ) VALUES ( '"+studiengang.studiengangID + "semester_" + i + "', '"+studiengang.studiengangID+"')");
            }
            
            stmt.executeUpdate("CREATE TABLE MODUL_"+ studiengang.studiengangID +" (modul_id) AS (SELECT MODUL_ID FROM MODUL) WITH DATA");
            stmt.executeUpdate("INSERT INTO \"PUBLIC\".\"MODUL_SCROLLPANE\" ( \"SCROLLPANE_ID\" ) VALUES ('MODUL_"+studiengang.studiengangID+"')");
            
            con.commit();
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveModul(Modul modul){
        try {
            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate("INSERT INTO \"PUBLIC\".\"MODUL\" ( \"MODUL_ID\", \"NAME\", \"STUDIENGANG\", \"FAKULTAET\", \"ECTS\", \"FARBE\" ) VALUES ( '"+modul.id+"', '"+modul.name+"', '"+modul.studiengang+"', '"+modul.fakultaet+"', '"+modul.ECTS+"', '"+modul.farbe+"')");
            con.commit();
            if (result == 1) {
                System.out.println("Successfully added to table!");
            }
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM MODUL_SCROLLPANE");
            while(rs.next()){
                stmt.executeUpdate("INSERT INTO \"PUBLIC\".\""+rs.getString("SCROLLPANE_ID")+"\" ( \"MODUL_ID\", \"NAME\", \"STUDIENGANG\", \"FAKULTAET\", \"ECTS\", \"FARBE\" ) VALUES ( '"+modul.id+"', '"+modul.name+"', '"+modul.studiengang+"', '"+modul.fakultaet+"', '"+modul.ECTS+"', '"+modul.farbe+"')");
            }
            
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Fehler Code: " + ex);
        }
    }
    
    public void saveModulToSemester(String semesterID, String modulID, String studiengangID){
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO \"PUBLIC\".\"SEMESTER_MODUL\" ( \"SEMESTER_ID\", \"MODUL_ID\" ) VALUES ( '"+semesterID+"', '"+modulID+"')");
            con.commit();
            stmt.executeUpdate("DELETE FROM MODUL_"+studiengangID+" WHERE MODUL_ID='" + modulID + "'");
            con.commit();
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeModulFromSemester(String semesterID, Modul modul, String studiengangID){
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM SEMESTER_MODUL WHERE MODUL_ID='" + modul.id + "'");
            con.commit();
            stmt.executeUpdate("INSERT INTO \"PUBLIC\".\"MODUL_"+studiengangID+"\" ( \"MODUL_ID\") VALUES ( '"+modul.id+"')");
            con.commit();
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteStudiengang(String studiengangID){
        
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM STUDIENGANG_JAHR WHERE STUDIENGANG_ID='" + studiengangID + "'");
            stmt.executeUpdate("DELETE FROM MODUL_SCROLLPANE WHERE SCROLLPANE_ID='MODUL_" + studiengangID + "'");
            stmt.executeUpdate("DROP TABLE MODUL_" + studiengangID);
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDIENGANG_SEMESTER WHERE STUDIENGANG_ID = '"+ studiengangID +"' ");
            
            while(rs.next()){
                stmt.executeUpdate("DELETE FROM SEMESTER_MODUL WHERE SEMESTER_ID='" + rs.getString("SEMESTER_ID") + "'");
            }
            stmt.executeUpdate("DELETE FROM STUDIENGANG_SEMESTER WHERE STUDIENGANG_ID='" + studiengangID + "'");
            
            stmt.executeUpdate("DELETE FROM STUDIENGANG_SEMESTER WHERE STUDIENGANG_ID='" + studiengangID + "'");
            con.commit();
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(HSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
