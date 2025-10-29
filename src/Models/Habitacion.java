package Models;

import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;

import java.util.ArrayList;
import java.util.Objects;

public class Habitacion {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private ArrayList<PistaTexto> pistas;
    private ArrayList<ObjetoCasa> objetos;
    private Integer idHabitacion;

    //---------- CONSTRUCTORES ----------
    public Habitacion(String nombre, Integer idHabitacion) {
        this.nombre = nombre;
        this.idHabitacion = idHabitacion;
        this.pistas = new ArrayList<>();
        this.objetos = new ArrayList<>();
    }

    public Habitacion() {
        this.nombre = "";
        this.idHabitacion = 0;
        this.pistas = new ArrayList<>();
        this.objetos = new ArrayList<>();
    }

    //---------- GETTERS y SETTERS ----------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<PistaTexto> getPistas() {
        return pistas;
    }

    public void setPistas(ArrayList<PistaTexto> pistas) {
        this.pistas = pistas;
    }

    public ArrayList<ObjetoCasa> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<ObjetoCasa> objetos) {
        this.objetos = objetos;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return Objects.equals(idHabitacion, that.idHabitacion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idHabitacion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Habitación [ID: ").append(idHabitacion).append("]\n");
        sb.append("  Nombre: ").append(nombre).append("\n");

        sb.append("  Pistas:\n");
        if (pistas != null && !pistas.isEmpty()) {
            for (PistaTexto p : pistas) {
                sb.append("    - ").append(p).append("\n");
            }
        } else {
            sb.append("    (Sin pistas)\n");
        }

        sb.append("  Objetos:\n");
        if (objetos != null && !objetos.isEmpty()) {
            for (ObjetoCasa o : objetos) {
                sb.append("    - ").append(o).append("\n");
            }
        } else {
            sb.append("    (Sin objetos)\n");
        }

        return sb.toString();
    }

    //---------- METODOS ----------
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

    public ObjetoCasa buscarObjeto(String nombreObjeto) throws ElementoNuloException, ElementoNoEncontradoException{
        if (nombreObjeto == null){
            throw new ElementoNuloException("El nombre del objeto ingresado no puede ser nulo");
        }
        for (ObjetoCasa o : objetos){
            if (o.equals(nombreObjeto)){
                return o;
            }
        }
        throw new ElementoNoEncontradoException("El objeto no existe en la lista");
    }

    public PistaTexto buscarPista(String nombrePista) throws ElementoNuloException, ElementoNoEncontradoException{
        if (nombrePista == null){
            throw new ElementoNuloException("El nombre de la pista ingresada no puede ser nulo");
        }
        for (PistaTexto p : pistas){
            if (p.equals(nombrePista)){
                return p;
            }
        }
        throw new ElementoNoEncontradoException("La pista no existe en la lista");
    }

    public String listarPistas(){
        StringBuilder sb = new StringBuilder();
        for (PistaTexto p : pistas){
            sb.append(p.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public String listarObjetos(){
        StringBuilder sb = new StringBuilder();
        for (ObjetoCasa o : objetos){
            sb.append(o.toString());
            sb.append("\n");
        }
        return sb.toString();
    }


}
