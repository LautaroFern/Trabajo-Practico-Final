package Model;

import Enum.RolPersonaje;
import Enum.TipoGenero;
import Interface.IDevolverString;
import Interface.IReconocerId;

import java.util.Objects;

public class Personaje implements IReconocerId, IDevolverString {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private int edad;
    private TipoGenero genero;
    private Integer idPersonaje;
    private RolPersonaje rolPersonaje;
    private static Integer idIncremental = 0;
    private String confesion;

    //---------- CONSTRUCTORES ----------
    public Personaje(String nombre, int edad, TipoGenero genero, RolPersonaje rolPersonaje) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        idIncremental++;
        this.idPersonaje = idIncremental;
        this.rolPersonaje = rolPersonaje;
        this.confesion = "";
    }

    public Personaje() {
        this.nombre = "";
        this.edad = 0;
        this.genero = null;
        idIncremental++;
        this.idPersonaje = idIncremental;
        this.rolPersonaje = null;
        this.confesion = "";
    }

    //---------- GETTERS y SETTERS ----------
    @Override
    public Integer getId() {
        return this.idPersonaje;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public void setIdPersonaje(Integer idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public void setRolPersonaje(RolPersonaje rolPersonaje) {
        this.rolPersonaje = rolPersonaje;
    }

    public String getConfesion() {
        return confesion;
    }

    public void setConfesion(String confesion) {
        this.confesion = confesion;
    }

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personaje personaje = (Personaje) o;
        return Objects.equals(idPersonaje, personaje.idPersonaje);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPersonaje);
    }

    @Override
    public String toString() {
        return "Personaje [ID: " + idPersonaje + "\n" +
                "  Nombre: " + nombre + "\n" +
                "  Edad: " + edad + "\n" +
                "  Género: " + genero + "\n" +
                "  Rol: " + rolPersonaje + "\n" +
                "  Confesión: " + confesion + "\n";
    }

    @Override
    public String devolverString() {
        return this.nombre;
    }
}