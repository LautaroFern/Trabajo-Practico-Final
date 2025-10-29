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
