package Models;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;

import java.util.ArrayList;
import java.util.HashSet;

public class Casa {
    //---------- ATRIBUTOS ----------
    private HashSet<Personaje> listaPersonajes;
    private HashSet<Habitacion> listaHabitaciones;

    //---------- CONSTRUCTOR ----------
    public Casa() {
        this.listaPersonajes = new HashSet<>();
        this.listaHabitaciones = new HashSet<>();
    }

    //---------- GETTERS y SETTERS ----------
    public HashSet<Personaje> getListaPersonajes() {
        return listaPersonajes;
    }

    public void setListaPersonajes(HashSet<Personaje> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    public HashSet<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(HashSet<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Habitaciones:\n");
        for (Habitacion h : listaHabitaciones) {
            sb.append(" - ").append(h).append("\n");
        }

        sb.append("Personajes:\n");
        for (Personaje p : listaPersonajes) {
            sb.append(" - ").append(p).append("\n");
        }

        return sb.toString();
    }

    //---------- METODOS ----------
    public boolean agregarPersonaje(Personaje p) throws ElementoNuloException, ElementoExistenteException {
        if (listaPersonajes.contains(p)) {
            throw new ElementoExistenteException("El personaje que quiere ingresar a la casa ya existe");
        } else if (p == null) {
            throw new ElementoNuloException("No puede ingresar un personaje nulo a la casa");
        } else return listaPersonajes.add(p);
    }

    public boolean eliminarPersonaje(Personaje p) throws ListaVaciaException {
        if (!listaPersonajes.isEmpty()) {
            for (Personaje personaje : listaPersonajes) {
                if (personaje.equals(p)) {
                    return listaPersonajes.remove(p);
                }
            }
        }
        throw new ListaVaciaException("No se puede eliminar un personaje de la lista vacía");
    }
}
