package Model;

import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

public abstract class Pista {
    //---------- ATRIBUTOS ----------
    protected String nombre;
    protected String descripcion;
    protected String id = UUID.randomUUID().toString();

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //---------- EQUALS y HASHCODE ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pista pista = (Pista) o;
        return Objects.equals(id, pista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //---------- METODOS ----------
    public abstract JSONObject toJson();
}