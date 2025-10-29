package Models;

import Enums.RolPersonaje;
import Enums.TipoGenero;

import java.util.ArrayList;
import java.util.Objects;

public class Personaje {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private int edad;
    private TipoGenero genero;
    private ArrayList <String>rasgos;
    private int idPersonaje;
    private RolPersonaje rolPersonaje;

    //---------- CONSTRUCTORES ----------
    public Personaje(String nombre, int edad, TipoGenero genero, int idPersonaje, RolPersonaje rolPersonaje) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.idPersonaje = idPersonaje;
        this.rolPersonaje = rolPersonaje;
        this.rasgos = new ArrayList<>();
    }

    public Personaje() {
        this.nombre = "";
        this.edad = 0;
        this.genero = null;
        this.idPersonaje = 0;
        this.rolPersonaje = null;
        this.rasgos = new ArrayList<>();
    }

    //---------- GETTERS y SETTERS ----------
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

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personaje personaje = (Personaje) o;
        return idPersonaje == personaje.idPersonaje;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPersonaje);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Personaje [ID: ").append(idPersonaje).append("]\n");
        sb.append("  Nombre: ").append(nombre).append("\n");
        sb.append("  Edad: ").append(edad).append("\n");
        sb.append("  Género: ").append(genero).append("\n");
        sb.append("  Rol: ").append(rolPersonaje).append("\n");
        sb.append("  Rasgos:\n");
        for (String r : rasgos) {
            sb.append("    - ").append(r).append("\n");
        }
        return sb.toString();
    }


    //---------- METODOS ----------
    public boolean agregarRasgo(String rasgo) {
        if (!rasgos.contains(rasgo)) {
            rasgos.add(rasgo);
            return true;
        }
        return false;
    }

    public boolean eliminarRasgo(String rasgo) {
        return rasgos.remove(rasgo);
    }

    public String modificarRasgo(String viejo, String nuevo) {
        if (rasgos.contains(viejo)) {
            rasgos.remove(viejo);
            rasgos.add(nuevo);
            return "Se cambió '" + viejo + "' por '" + nuevo + "'";
        } else {
            return "No se encontró el rasgo: " + viejo;
        }
    }

    public String detallesRasgos() {

        StringBuilder sb = new StringBuilder("Rasgos de " + nombre + ":\n");

        if (rasgos.isEmpty()) {
            return "El personaje no tiene rasgos asignados.";
        }
        for (String r : rasgos) {
            sb.append("- ").append(r).append("\n");
        }
        return sb.toString();
    }




}
