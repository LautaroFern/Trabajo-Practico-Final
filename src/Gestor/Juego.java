package Gestor;

import Exceptions.*;
import Interfaces.IDevolverString;
import Interfaces.IGestora;
import Interfaces.IReconocerId;
import Models.Jugador;

import java.util.ArrayList;

public class Juego<T extends IReconocerId & IDevolverString> implements IGestora<T> {
    //---------- ATRIBUTOS ----------
    private ArrayList<T> jugadores;

    //---------- CONSTRUCTORES ----------

    public Juego() {
        this.jugadores = new ArrayList<>();
    }

    //---------- GETTERS y SETTERS ----------


    public ArrayList<T> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<T> jugadores) {
        this.jugadores = jugadores;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Juego:\n" +
                "Jugadores: " + this.jugadores + "\n";
    }

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    @Override
    public boolean agregarElemento(T elemnto) throws ElementoNuloException, ElementoExistenteException {
        if (elemnto == null) {
            throw new ElementoNuloException("El jugador no puede ser nulo");
        } else if (jugadores.contains(elemnto)) {
            throw new ElementoExistenteException("El jugador ya existe en el juego");
        } else return jugadores.add(elemnto);
    }

    @Override
    public boolean eliminarElemento(T elemento) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (elemento == null) {
            throw new ElementoNuloException("El jugador no puede ser nulo");
        } else if (!jugadores.isEmpty()) {
            for (T item: jugadores) {
                if (item.getId() == elemento.getId()) {
                    return jugadores.remove(elemento);
                }
            }
            throw new ElementoNoEncontradoException("El jugador no se encuentra registrado en el juego");
        } else throw new ListaVaciaException("El juego no tiene jugadores registrados");
    }

    @Override
    public T buscarElemento(String elemento) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (elemento == null) {
            throw new ElementoNuloException("Debe ingresar un nombre de usuario valido");
        } else if (!jugadores.isEmpty()) {
            for  (T item : jugadores) {
                if (item instanceof IDevolverString aux) {
                    if (aux.devolverString().equalsIgnoreCase(elemento)) {
                        return item;
                    }
                }
            }
            throw new ElementoNoEncontradoException("El jugador no se encuentra registrado en el juego");
        } else throw new ListaVaciaException("No hay jugadores registrados en el juego");
    }


}
