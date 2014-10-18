
package clases;

import java.io.Serializable;


public class Documento implements Serializable{
    
    private int id;
    private String nombre;
    private String path;

    public Documento() {
    }


    public Documento(int id, String nombre, String path) {
        this.id = id;
        this.nombre = nombre;
        this.path = path;
    }

    public Documento(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
