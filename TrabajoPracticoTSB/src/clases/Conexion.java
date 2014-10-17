
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Conexion 
{
    Connection conexion;
    
    
    public Conexion()
    {
        
    }

    public Connection getConexion() {
        return conexion;
    }
    
    private void abrirConexion()
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
    
    private boolean ejecutarQuery(PreparedStatement ps)
    {
        boolean seEfectuo = false;
        try
        {
            conexion.setAutoCommit(false);
          //  PreparedStatement ps = conexion.prepareStatement(query);
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
    
    private void cerrarConexion()
    {
        try
        {
            conexion.close();
        }
        catch(SQLException e)
        {
            
        }
    }
    

    public PreparedStatement prepararStatementVocabulario(String palabra, int frec)
    {
        PreparedStatement ps = null;
        try{
        ps = conexion.prepareStatement("INSERT INTO Vocabulario(id, palabra, frecuencia) VALUES (,?,?)");
        ps.setString(2, palabra);
        ps.setInt(3, frec);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return ps;
    }
        public PreparedStatement prepararStatementDocumento(String palabra, int frec)
    {
        PreparedStatement ps = null;
        try{
        ps = conexion.prepareStatement("INSERT INTO Vocabulario(id, palabra, frecuencia) VALUES (,?,?)");
        ps.setString(2, palabra);
        ps.setInt(3, frec);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return ps;
    }
}
