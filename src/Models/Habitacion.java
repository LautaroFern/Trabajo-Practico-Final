package Models;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;
import Interfaces.IReconocerId;

import java.util.ArrayList;
import java.util.Objects;

public class Habitacion implements IReconocerId {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private ArrayList<PistaTexto> pistas;
    private ArrayList<ObjetoCasa> objetos;
    private Integer idHabitacion;
    private static Integer idIncremental = 0;

    //---------- CONSTRUCTORES ----------
    public Habitacion(String nombre) {
        this.nombre = nombre;
        idIncremental++;
        this.idHabitacion = idIncremental;
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

    @Override
    public Integer getId() {
        return this.idHabitacion;
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

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    public boolean agregarPista(PistaTexto pista) throws ElementoNuloException, ElementoExistenteException {
        if (pista == null) {
            throw new ElementoNuloException("La pista ingresada no puede ser nula");
        } else if (pistas.contains(pista)) {
            throw new ElementoExistenteException("La pista ya existe en la habitación");
        } else return pistas.add(pista);
    }

    public boolean agregarObjeto(ObjetoCasa objetoCasa) throws ElementoNuloException, ElementoExistenteException {
        if (objetoCasa == null) {
            throw new ElementoNuloException("El objeto ingresado no puede ser nulo");
        } else if (objetos.contains(objetoCasa)) {
            throw new ElementoExistenteException("El objeto ya existe en la habitación");
        } else return objetos.add(objetoCasa);
    }

    public boolean eliminarPista(String nombrePista) throws ElementoNuloException, ElementoNoEncontradoException, ListaVaciaException {
        if (nombrePista == null) {
            throw new ElementoNuloException("La pista ingresada no puede ser nula");
        } else if (!pistas.isEmpty()) {
            for (PistaTexto p : pistas) {
                if (p.getNombre().equals(nombrePista)) {
                    return pistas.remove(p);
                }
            }
            throw new ElementoNoEncontradoException("La pista no se encuentra en la habitacón");
        } else throw new ListaVaciaException("No se puede eliminar la pista de la lista vacía");
    }

    public boolean eliminarObjeto(String nombreObjeto) throws ElementoNuloException, ElementoNoEncontradoException, ListaVaciaException {
        if (nombreObjeto == null) {
            throw new ElementoNuloException("El objeto ingresado no puede ser nulo");
        } else if (!objetos.isEmpty()) {
            for (ObjetoCasa o : objetos) {
                if (o.getNombre().equals(nombreObjeto)) {
                    return objetos.remove(o);
                }
            }
            throw new ElementoNoEncontradoException("El objeto no se encuentra en la habitación");
        }else throw new ListaVaciaException("El objeto no existe en la lista");
    }

    public ObjetoCasa buscarObjeto(String nombreObjeto) throws ElementoNuloException, ElementoNoEncontradoException, ListaVaciaException {
        if (nombreObjeto == null) {
            throw new ElementoNuloException("El nombre del objeto ingresado no puede ser nulo");
        } else if (!objetos.isEmpty()) {
            for (ObjetoCasa o : objetos) {
                if (o.getNombre().equals(nombreObjeto)) {
                    return o;
                }
            }
            throw new ElementoNoEncontradoException("El objeto no se encuntra en la habitación");
        }else throw new ListaVaciaException("El objeto no existe en la lista");
    }

    public PistaTexto buscarPista(String nombrePista) throws ElementoNuloException, ElementoNoEncontradoException, ListaVaciaException {
        if (nombrePista == null) {
            throw new ElementoNuloException("El nombre de la pista ingresada no puede ser nulo");
        } else if (!pistas.isEmpty()) {
            for (PistaTexto p : pistas) {
                if (p.getNombre().equals(nombrePista)) {
                    return p;
                }
            }
            throw new ElementoNoEncontradoException("La pista no se encuentra en la habitación");
        }else throw new ListaVaciaException("La pista no existe en la lista");
    }

    public String listarPistas() {
        StringBuilder sb = new StringBuilder();
        for (PistaTexto p : pistas) {
            sb.append(p.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public String listarObjetos() {
        StringBuilder sb = new StringBuilder();
        for (ObjetoCasa o : objetos) {
            sb.append(o.toString());
            sb.append("\n");
        }
        return sb.toString();
    }


}
