package Gestor;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;
import Interfaces.IDevolverString;
import Interfaces.IGestora;
import Interfaces.IReconocerId;

import java.util.HashSet;

public class Juego<T extends IReconocerId & IDevolverString> implements IGestora<T> {
    //---------- ATRIBUTOS ----------
    private HashSet<T> elementos;

    //---------- CONSTRUCTORES ----------
    public Juego() {
        this.elementos = new HashSet<>();
    }

    //---------- GETTERS y SETTERS ----------
    public HashSet<T> getJugadores() {
        return elementos;
    }

    public void setJugadores(HashSet<T> jugadores) {
        this.elementos = jugadores;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Juego:\n" +
                "Jugadores: " + this.elementos + "\n";
    }

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    @Override
    public boolean agregarElemento(T elemento) throws ElementoNuloException, ElementoExistenteException {
        if (elemento == null) {
            throw new ElementoNuloException("El elemento no puede ser nulo");
        } else if (elementos.contains(elemento)) {
            throw new ElementoExistenteException("El elemento ya existe en el juego");
        } else return elementos.add(elemento);
    }

    @Override
    public boolean eliminarElemento(T elemento) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (elemento == null) {
            throw new ElementoNuloException("El elemento no puede ser nulo");
        } else if (!elementos.isEmpty()) {
            for (T item : elementos) {
                if (item.getId().equals(elemento.getId())) {
                    return elementos.remove(item);
                }
            }
            throw new ElementoNoEncontradoException("El elemento no se encuentra registrado en el juego");
        } else throw new ListaVaciaException("No hay elementos registrados en el juego");
    }

    @Override
    public T buscarElemento(String elemento) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (elemento == null) {
            throw new ElementoNuloException("Debe ingresar un nombre válido");
        } else if (!elementos.isEmpty()) {
            for (T item : elementos) {
                if (item.devolverString().equalsIgnoreCase(elemento)) {
                    return item;
                }
            }
            throw new ElementoNoEncontradoException("El elemento no se encuentra registrado en el juego");
        } else throw new

                ListaVaciaException("No hay elementos registrados en el juego");
    }
}