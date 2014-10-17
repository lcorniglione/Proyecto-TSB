
package clases;

import java.io.Serializable;


public class Documento implements Serializable{
    
    private String nombre;
    private String path;

    public Documento() {
    }

    public Documento(String nombre, String path) {
        this.nombre = nombre;
        this.path = path;
    }

    public Documento(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
