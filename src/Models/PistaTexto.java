package Models;

import Interfaces.IReconocerId;

import java.util.Objects;

public class PistaTexto {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private String descripcion;

    //---------- CONSTRUCTORES ----------
    public PistaTexto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public PistaTexto() {
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

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PistaTexto that = (PistaTexto) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }



    @Override
    public String toString() {
        return  "\nNombre: " + this.nombre +
                "\nDescripcion: " + this.descripcion;
    }
}
