
package ejecutor;

import clases.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Procesar extends HashMap
{
    private HashMap <String,Palabra> hm;
    private Conexion c = new Conexion();
    public Procesar()
    {
        hm = new HashMap();
    }
    
   
    public void procesarDocumento(Documento doc)
    {
        String palabra;
        String entrada;
        File f = new File(doc.getPath());
        try
        {
            Scanner sc = new Scanner (f);
            while(sc.hasNextLine())
            {
                entrada = sc.nextLine();
                StringTokenizer st = new StringTokenizer(entrada," ,-0123456789-{}*[].:()#");
                while(st.hasMoreTokens())
                {
                    palabra = st.nextToken();
                    Palabra p = new Palabra(palabra, doc);
                    if(hm.containsKey(p.getInfo()))
                    {
                        p.incrementar();
                      //  hm.put(p.getInfo(),p);
                    }
                    else
                    {
                        hm.put(p.getInfo(), p);
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No se encontro el archivo a procesar");
        }
    }
    
        private void agregarDocBD(Palabra p,ArrayList<PreparedStatement> arr)
    {
        
        for (int i = 0; i < p.getDoc().size(); i++) {                
               PreparedStatement ps = c.prepararStatementDocumento(palabra, freq);  
            }
    }
    public void impactarBD()
    {
        
        ArrayList<PreparedStatement> arr = new ArrayList<>();
        Iterator it = hm.entrySet().iterator();
        
        while(it.hasNext())
        {
            Palabra aux = (Palabra) it.next();
            String palabra = aux.getInfo();
            int freq = aux.getFrecuencia();
            PreparedStatement ps = c.prepararStatementVocabulario(palabra, freq);
            arr.add(ps);
            for (int i = 0; i < aux.getDoc().size(); i++) {                
                
            }
   
           
        }

    }
    
    public String toString()
    {
        return hm.toString();
    }
    
}
