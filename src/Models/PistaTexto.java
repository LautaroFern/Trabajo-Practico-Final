package Models;

import Interfaces.IReconocerId;

import java.util.Objects;

public class PistaTexto implements IReconocerId {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private String descripcion;
    private Integer idIncremental = 0;
    private Integer id;

    //---------- CONSTRUCTORES ----------
    public PistaTexto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        idIncremental++;
        this.id = idIncremental;
    }

    public PistaTexto() {
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

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PistaTexto that = (PistaTexto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pista [ID: ").append(id).append("]\n");
        sb.append("  Nombre: ").append(nombre).append("\n");
        sb.append("  Descripción: ").append(descripcion);
        return sb.toString();
    }
}
