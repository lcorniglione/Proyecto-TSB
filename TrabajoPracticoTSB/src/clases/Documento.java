
package clases;

import java.io.Serializable;


public class Documento implements Serializable{
    
    private String nombre;

    public Documento() {
    }

    public Documento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
