
package ejecutor;

import clases.*;
import interfaces.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Principal
{

   
    public static void main(String[] args) throws SQLException 
    {
    
        Documento d = new Documento(3,"18166-8","C:\\Users\\Luciano\\Documents\\Luciano Universidad\\TSB\\Libros [TP Unico TSB 2014]\\18166-8.txt");
        Procesar p = new Procesar();
        p.procesarDocumento(d);
        p.procesarConexion(d);

    }
        
    
    
}
