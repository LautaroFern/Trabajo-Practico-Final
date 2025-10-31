package Models;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;

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

    public boolean eliminarPersonaje(Personaje p) throws ListaVaciaException, ElementoNoEncontradoException {
        if (!listaPersonajes.isEmpty()) {
            for (Personaje personaje : listaPersonajes) {
                if (personaje.equals(p)) {
                    return listaPersonajes.remove(p);
                }
            }
            throw new ElementoNoEncontradoException("El personaje que desea eliminar no se encuentra en la casa");
        }
        throw new ListaVaciaException("No se puede eliminar un personaje de la lista vacía");
    }

    public Personaje buscarPersonaje(Integer id) throws ListaVaciaException, ElementoNoEncontradoException {
        if (!listaPersonajes.isEmpty()) {
            for (Personaje p : listaPersonajes) {
                if (p.getIdPersonaje().equals(id)) {
                    return p;
                }
            }
            throw new ElementoNoEncontradoException("El personaje que desea buscar no se encuentra en la casa");
        }
        throw new ListaVaciaException("No se puede buscar un personaje de la lista vacía");
    }

    public boolean agregarHabitacion(Habitacion h) throws ElementoNuloException, ElementoExistenteException {
        if (listaHabitaciones.contains(h)) {
            throw new ElementoExistenteException("La habitación que quiere ingresar a la casa ya existe");
        } else if (h == null) {
            throw new ElementoNuloException("No puede ingresar una habitación nula a la casa");
        } else return listaHabitaciones.add(h);
    }

    public boolean eliminarHabitacion(Habitacion h) throws ListaVaciaException, ElementoNoEncontradoException {
        if (!listaHabitaciones.isEmpty()) {
            for (Habitacion habitacion : listaHabitaciones) {
                if (habitacion.equals(h)) {
                    return listaHabitaciones.remove(h);
                }
            }
            throw new ElementoNoEncontradoException("La habitación que desea eliminar no se encuentra en la casa");
        }
        throw new ListaVaciaException("No se puede buscar una habitación de la lista vacía");
    }

    public Habitacion buscarHabitacion(Integer id) throws ListaVaciaException, ElementoNoEncontradoException {
        if (!listaHabitaciones.isEmpty()) {
            for (Habitacion h : listaHabitaciones) {
                if (h.getIdHabitacion().equals(id)) {
                    return h;
                }
            }
            throw new ElementoNoEncontradoException("La habitación que desea buscar no se encuentra en la casa");
        }
        throw new ListaVaciaException("No se puede buscar una habitación de la lista vacía");
    }
}
