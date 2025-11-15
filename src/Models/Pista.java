package Models;

import org.json.JSONObject;

import java.util.Objects;

public abstract class Pista {
    //---------- ATRIBUTOS ----------
    protected String nombre;
    protected String descripcion;
    private static Integer idIncremental = 0;
    protected Integer id;

    //---------- CONSTRUCTORES ----------
    public Pista(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        idIncremental++;
        this.id = idIncremental;
    }

    public Pista() {
        this.nombre = "";
        this.descripcion = "";
        idIncremental++;
        this.id = idIncremental;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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