package Models;

import Interfaces.IReconocerId;

import java.util.Objects;

public class ObjetoCasa implements IReconocerId   {
    private String nombre;
    private String descripcion;
    private Integer idIncremental = 0;
    private Integer id;

    public ObjetoCasa(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        idIncremental++;
        this.id = idIncremental;
    }

    public ObjetoCasa() {
        this.nombre = "";
        this.descripcion = "";
        idIncremental++;
        this.id = idIncremental;
    }

    public Integer getId() {
        return id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ObjetoCasa that = (ObjetoCasa) o;
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
