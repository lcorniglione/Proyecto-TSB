
package clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Palabra implements Serializable
{
    private String info;
    private int frecuencia;
    private ArrayList<Documento> doc;

    public Palabra() {
    }

    public Palabra(String info, Documento docu) {
        this.info = info;
        this.frecuencia = 1;
        this.doc = new ArrayList();
        this.doc.add(docu);
    }

    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    public void incrementar()
    {
        this.frecuencia = this.frecuencia +1;
    }

    @Override
    public String toString() {
        return "Palabra{" + "info=" + info + ", frecuencia=" + frecuencia + '}';
    }

    public int[] getDoc() {
        int vec[] = new int[doc.size()];
        for (int i = 0; i < doc.size(); i++) 
        {
            vec[i]=doc.get(i).getId();
        }
        return vec;
    }
    
    
    
    
    
    
    
}
