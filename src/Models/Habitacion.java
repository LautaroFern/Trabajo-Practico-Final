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
            throw new ElementoNuloException("La pista ingresada no puede ser nula");
        }
        for (PistaTexto p : pistas){
            if (p.equals(nombrePista)){
                pistas.remove(p);
                return true;
            }
        }
        throw new ElementoNoEncontradoException("La pista no existe en la lista");
    }

    public boolean eliminarObjeto(String nombreObjeto) throws ElementoNuloException, ElementoNoEncontradoException{
        if (nombreObjeto == null){
            throw new ElementoNuloException("El objeto ingresado no puede ser nulo");
        }
        for (ObjetoCasa o : objetos){
            if (o.equals(nombreObjeto)){
                objetos.remove(o);
                return true;
            }
        }
        throw new ElementoNoEncontradoException("El objeto no existe en la lista");
    }
}
