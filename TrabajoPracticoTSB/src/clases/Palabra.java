
package clases;

import java.io.Serializable;

public class Palabra implements Serializable
{
    private String info;
    private int frecuencia;

    public Palabra() {
    }

    public Palabra(String info) {
        this.info = info;
        this.frecuencia = 0;
    }

    public Palabra(String info, int frecuencia) {
        this.info = info;
        this.frecuencia = frecuencia;
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

    @Override
    public String toString() {
        return "Palabra{" + "info=" + info + ", frecuencia=" + frecuencia + '}';
    }
    
    
    
    
    
}
