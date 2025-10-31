package Collections;

import Exceptions.*;
import Interfaces.IGestora;
import Interfaces.IReconocerId;

import java.util.HashSet;


public class Inventario<T> implements IGestora<T> {
    //---------- ATRIBUTOS ----------
    private HashSet<T> listaElemento;

    //---------- CONSTRUCTOR ----------
    public Inventario() {
        this.listaElemento = new HashSet<>();
    }

    //---------- GETTERS y SETTERS ----------
    public HashSet<T> getListaElemento() {
        return listaElemento;
    }

    public void setListaElemento(HashSet<T> listaElemento) {
        this.listaElemento = listaElemento;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario:\n");
        if (listaElemento != null && !listaElemento.isEmpty()) {
            for (T elemento : listaElemento) {
                sb.append(" - ").append(elemento).append("\n");
            }
        } else {
            sb.append("  (Vacío)\n");
        }
        return sb.toString();
    }

    //---------- METODOS CON EXCEPCIONES PERSONALIZADAS ----------
    @Override
    public boolean agregarElemento(T t) throws ElementoNuloException, ElementoExistenteException {
        if (t == null) {
            throw new ElementoNuloException("El elemento que se intenta agregar es nulo");
        } else if (listaElemento.contains(t)) {
            throw new ElementoExistenteException("El elemento ya existe en el inventario");
        }else return listaElemento.add(t);
    }

    @Override
    public boolean eliminarElemento(T t) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (!listaElemento.isEmpty()) {
            for (T t1 : listaElemento) {
                if (t1.equals(t)) {
                    return listaElemento.remove(t);
                }
            }
            throw new ElementoNoEncontradoException("El elemento que no se quiere eliminar no fue encontrado");
        } else if (t == null) {
            throw new ElementoNuloException("El elemento ingresado es nulo");
        }else throw new ListaVaciaException("La lista esta vacia");
    }

    @Override
    public T buscarElemento(Integer id) throws ListaVaciaException, ElementoNoEncontradoException, ParametroInvalidoException {
        if (id <= 0) {
            throw new ParametroInvalidoException("El ID no puede ser menor o igual a 0");
        } else if (!listaElemento.isEmpty()) {
            for (T e : listaElemento) {
                if (e instanceof IReconocerId aux) {
                    if (aux.getId().equals(id)) {
                        return e;
                    }
                }
            }
            throw new ElementoNoEncontradoException("Elemento no encontrado en la lista");
        }else throw new ListaVaciaException("La lista esta vacia");
    }

    /*public String mostrarInventario() {
        StringBuilder sb = new StringBuilder();
        for (T elemento : listaElemento) {
            sb.append(elemento.toString()).append("\n");
        }
        return sb.toString();
    }*/
}

