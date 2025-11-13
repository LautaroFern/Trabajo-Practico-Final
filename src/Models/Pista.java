package Models;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Pista {
    //---------- ATRIBUTOS ----------
    protected String nombre;
    protected String descripcion;

    //---------- CONSTRUCTORES ----------
    public Pista(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Pista() {
        this.nombre = "";
        this.descripcion = "";
    }

    //---------- GETTERS y SETTERS ----------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //---------- METODOS ----------
    public abstract JSONObject toJson();

}
