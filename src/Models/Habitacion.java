package Models;

import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;

import java.util.ArrayList;

public class Habitacion {
    private String nombre;
    private ArrayList<PistaTexto> pistas;
    private ArrayList<ObjetoCasa> objetos;
    private Integer idHabitacion;

    public Habitacion(String nombre, Integer idHabitacion) {
        this.nombre = nombre;
        this.idHabitacion = idHabitacion;
        this.pistas = new ArrayList<>();
        this.objetos = new ArrayList<>();
    }

    public boolean agregarPista(PistaTexto pista) throws ElementoNuloException {
        if (pista == null){
            throw new ElementoNuloException("La pista ingresada no puede ser nula");
        } else {
            pistas.add(pista);
            return true;
        }
    }

    public boolean agregarObjeto(ObjetoCasa objetoCasa) throws ElementoNuloException {
        if (objetoCasa == null){
            throw new ElementoNuloException("El objeto ingresado no puede ser nulo");
        } else {
            objetos.add(objetoCasa);
            return true;
        }
    }

    public boolean eliminarPista(String nombrePista) throws ElementoNuloException, ElementoNoEncontradoException {
        if (nombrePista == null){
            throw new ElementoNuloException("");
        }
        return true;
    }
}
