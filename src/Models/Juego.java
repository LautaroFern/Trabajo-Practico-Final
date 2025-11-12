package Models;

import Collections.Casa;
import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;

import java.util.ArrayList;

public class Juego {
    //---------- ATRIBUTOS ----------
    private Casa casa;
    private ArrayList<Jugador> jugadores;

    //---------- CONSTRUCTORES ----------
    public Juego(Casa casa) {
        this.casa = casa;
        this.jugadores = new ArrayList<>();
    }

    public Juego() {
        this.casa = new Casa<>();
        this.jugadores = new ArrayList<>();
    }

    //---------- GETTERS y SETTERS ----------
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Juego:\n" +
                "Casa: " + this.casa + "\n" +
                "Jugadores: " + this.jugadores + "\n";
    }

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    public boolean agregarJugador(Jugador jugador) throws ElementoNuloException, ElementoExistenteException {
        if (jugador == null) {
            throw new ElementoNuloException("El jugador no puede ser nulo");
        } else if (jugadores.contains(jugador)) {
            throw new ElementoExistenteException("El jugador ya existe en el juego");
        } else return jugadores.add(jugador);
    }

    public boolean eliminarJugador(Jugador jugador) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (jugador == null) {
            throw new ElementoNuloException("El jugador no puede ser nulo");
        } else if (!jugadores.isEmpty()) {
            for (Jugador j : jugadores) {
                if (j.equals(jugador)) {
                    return jugadores.remove(jugador);
                }
            }
            throw new ElementoNoEncontradoException("El jugador no se encuentra registrado en el juego");
        } else throw new ListaVaciaException("El juego no tiene jugadores registrados");
    }

    public Jugador buscarJugador(String usuario) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (usuario.isEmpty()) {
            throw new ElementoNuloException("Debe ingresar un nombre de usuario valido");
        } else if (!jugadores.isEmpty()) {
            for (Jugador j : jugadores) {
                if (j.getUsuario().equals(usuario)) {
                    return j;
                }
            }
            throw new ElementoNoEncontradoException("El jugador no se encuentra registrado en el juego");
        } else throw new ListaVaciaException("No hay jugadores registrados en el juego");
    }
}
