
package ejecutor;

import clases.*;
import interfaces.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Principal
{

   
    public static void main(String[] args) 
    {
       
        try {
            Class.forName("org.sqlite.JDBC");
           // Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Luciano\\Documents\\NetBeansProjects\\TrabajoPracticoTSB\\BDTSB.s3db");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:..\\TrabajoPracticoTSB\\BDTSB.s3db");
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery("select palabra from Vocabulario;");
            System.out.println("Items de la tabla Prueba");
            while (rs.next()) {
                System.out.println("NOMBRE.....: " + rs.getString("palabra"));
                System.out.println("-----------------------------------");
            }
            rs.close();
            stat.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Error " + ex.getMessage());
        }

    }
        
    
    
}
