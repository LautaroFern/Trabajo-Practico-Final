package Models;

import Enums.RolPersonaje;
import Enums.TipoGenero;

import java.util.ArrayList;

public class Personaje {

    private String nombre;
    private int edad;
    private TipoGenero genero;
    private ArrayList <String>rasgos;
    private int idPersonaje;
    private RolPersonaje rolPersonaje;


    public Personaje(String nombre, int edad, TipoGenero genero, int idPersonaje, RolPersonaje rolPersonaje) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.idPersonaje = idPersonaje;
        this.rolPersonaje = rolPersonaje;
        this.rasgos = new ArrayList<>();
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public RolPersonaje getRolPersonaje() {
        return rolPersonaje;
    }


    @Override
    public String toString() {
        return "Personaje:'\'" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", genero=" + genero +
                ", idPersonaje=" + idPersonaje +
                ", rolPersonaje=" + rolPersonaje;
    }
}
