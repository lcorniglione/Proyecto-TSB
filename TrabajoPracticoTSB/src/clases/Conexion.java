
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexion 
{
    Connection conexion;
    
    public Conexion()
    {
        
    }
    
    public void conectar()
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
           // Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Luciano\\Documents\\NetBeansProjects\\TrabajoPracticoTSB\\BDTSB.s3db");
            conexion = DriverManager.getConnection("jdbc:sqlite:..\\TrabajoPracticoTSB\\BDTSB.s3db");
            } catch (SQLException ex) {
                System.out.println("Error " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("Class Error " + ex.getMessage());
            }
    }
    
    public boolean ejecutarQuery(String query)
    {
        boolean seEfectuo = false;
        try
        {
            conexion.setAutoCommit(false);
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.executeUpdate();
            conexion.commit();
            conexion.setAutoCommit(true);
            ps.close();
            seEfectuo = true;
        }
        catch(SQLException e)
        {
            try
            {
                conexion.rollback();
            }
            catch(SQLException e1)
            {
                System.out.println("Error");
            }
            System.out.println("Error" + e.getMessage());
        }
        return seEfectuo;
    }
    
    public void cerrarConexion()
    {
        try
        {
            conexion.close();
        }
        catch(SQLException e)
        {
            
        }
    }
    
    

    
}
