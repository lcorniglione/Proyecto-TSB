
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
    
    public void abrirConexion()
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
    
    private boolean procesarConexion(PreparedStatement ps)
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
    

//    public PreparedStatement prepararStatementVocabulario(String palabra, int frec)
//    {
//        PreparedStatement ps = null;
//        try{
//        ps = conexion.prepareStatement("INSERT INTO Vocabulario(id, palabra, frecuencia) VALUES (,?,?)");
//        ps.setString(2, palabra);
//        ps.setInt(3, frec);
//        }
//        catch(SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        return ps;
//    }
//        public PreparedStatement prepararStatementDocumento(String palabra, int frec)
//    {
//        PreparedStatement ps = null;
//        try{
//        ps = conexion.prepareStatement("INSERT INTO Vocabulario(id, palabra, frecuencia) VALUES (,?,?)");
//        ps.setString(2, palabra);
//        ps.setInt(3, frec);
//        }
//        catch(SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        return ps;
//    }
    
      public boolean estaPalabra(Palabra p) 
      {
          boolean esta = false;
          String pal;
        try 
        {
//            this.abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT palabra FROM Vocabulario WHERE palabra ='"+p.getInfo()+"'");
          
            while(rs.next())
            {
                pal = rs.getString("palabra");
                if(pal.equals(p.getInfo()))
                {
                    esta = true;
                    break;
                }
            }
            rs.close();
            st.close();
//            this.cerrarConexion();
        } 
        catch (SQLException e) 
        {
            System.out.println("" + e.getMessage());
        }
        return esta;
    }
      
      public boolean esDeDocumento(Palabra p)
      {
          boolean es= false;
          try
          {
//              this.abrirConexion();
              Statement st = conexion.createStatement();
              ResultSet rs = st.executeQuery("SELECT v.palabra FROM PalxDoc p JOIN Vocabulario v ON (p.idPal = v.id) WHERE v.palabra = '" + p.getInfo()+"'");
              if(rs.next())
              {
                  es = true;
              }
              rs.close();
               st.close();
               
//               this.cerrarConexion();
          }
          catch(SQLException e)
          {
               System.out.println("" + e.getMessage());
          }
          return es;
      }
      
      public boolean updateFrec(Palabra p)
      {
          boolean seUpdateo = false;
          int frec = 0;
          try
          {
//              this.abrirConexion();
              Statement st = conexion.createStatement();
              ResultSet rs = st.executeQuery("SELECT frecuencia FROM Vocabulario WHERE palabra ='" + p.getInfo()+ "'");
              while(rs.next())
              {
                  frec = rs.getInt("frecuencia");
              }
              PreparedStatement ps = conexion.prepareStatement("UPDATE Vocabulario SET frecuencia =" + (frec + p.getFrecuencia()));
              ps.executeUpdate();
              seUpdateo = true;
               ps.close();
              rs.close();
              st.close();
              
             
//              this.cerrarConexion();
          }
          catch(SQLException e)
          {
              System.out.println("" + e.getMessage());
          }
          return seUpdateo;
      }
      
      public boolean insertDoc(Documento d)
      {
          boolean seInserto = false;
          try
          {
//              this.abrirConexion();
              if(!this.verificarDocumento(d))
              {PreparedStatement ps = conexion.prepareStatement("INSERT INTO Documento (nombre) VALUES ('"+d.getNombre()+"')");
              ps.executeUpdate();
              seInserto = true;
              ps.close();
              }
             
//              this.cerrarConexion();
          }
          catch(SQLException e)
          {
               System.out.println("" + e.getMessage());
          }
          return seInserto;
      }
      public boolean verificarDocumento(Documento d)
      {
         boolean esta=false;
            try
          {
//              this.abrirConexion();
              Statement st = conexion.createStatement();
              ResultSet rs = st.executeQuery("SELECT nombre FROM Documento WHERE nombre='"+d.getNombre()+"'");
          while(rs.next())
              {
                 esta=true;
                 break;
              }
              rs.close();
               st.close();
          }
            catch(SQLException e)
            {
                
            }
          
          return esta;
      }
      public boolean insertVocabulario(Palabra p)
      {
          boolean seInserto = false;
          try 
          {
//              this.abrirConexion();
              PreparedStatement ps = conexion.prepareStatement("INSERT INTO Vocabulario (palabra) VALUES ('" + p.getInfo() + "')");
              ps.executeUpdate();
              seInserto=true;
              ps.close();
//              this.cerrarConexion();
          }
          catch(SQLException e)
          {
                System.out.println("" + e.getMessage());
          }
          return seInserto;
      }
      
      public boolean insertPalxDoc(Documento doc, Palabra p)
      {
          boolean seInserto = false;
          int idPal=0, idDoc = 0;
          try
          {
//              this.abrirConexion();
              Statement st = conexion.createStatement();
              ResultSet rs = st.executeQuery("SELECT id FROM Vocabulario WHERE palabra ='" + p.getInfo()+"'");
              while(rs.next())
              {
                  idPal = rs.getInt("id");
              }
              rs.close();
               st.close();
              Statement st1 = conexion.createStatement();
              ResultSet rs1 = st1.executeQuery("SELECT idDoc FROM Documento WHERE nombre ='" + doc.getNombre()+"'");
              while(rs1.next())
              {
                  idDoc = rs1.getInt("idDoc");
              }
              rs1.close();
             
              st1.close();
              PreparedStatement ps = conexion.prepareStatement("INSERT INTO PalxDoc(idPal,idDoc) VALUES (" + idPal + "," + idDoc +")");
              ps.executeUpdate();
              
              
              
              ps.close();
              
//              this.cerrarConexion();
              seInserto=true;
          }
          catch(SQLException e)
          {
              System.out.println("" + e.getMessage());

          }
          return seInserto;
      }
      
      public void setAutoCommit(boolean d)
      {
          try
          {
               conexion.setAutoCommit(d);
          }
         catch(SQLException e)
         {
             
         }
      }
      
      public void realizarCommit()
      {
          try
          {
              conexion.commit();
          }
          catch(SQLException e)
          {
              
          }
          
      }
      
      public void realizarRollBack()
      {
          try
          {
              conexion.rollback();
          }
          catch(SQLException e)
          {
              
          }
      }


}
