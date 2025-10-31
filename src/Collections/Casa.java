package Collections;

import Exceptions.*;
import Interfaces.IGestora;
import Interfaces.IReconocerId;

import java.util.HashSet;

public class Casa<T> implements IGestora<T> {
    //---------- ATRIBUTOS ----------
    private HashSet<T> listaElementos;

    //---------- CONSTRUCTOR ----------
    public Casa() {
        this.listaElementos = new HashSet<>();
    }

    //---------- GETTERS y SETTERS ----------
    public HashSet<T> getListaElementos() {
        return listaElementos;
    }

    public void setListaElementos(HashSet<T> listaElementos) {
        this.listaElementos = listaElementos;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Casa con elementos:\n");
        if (listaElementos != null && !listaElementos.isEmpty()) {
            for (T elemento : listaElementos) {
                sb.append(" - ").append(elemento).append("\n");
            }
        } else {
            sb.append("  (Sin elementos)\n");
        }
        return sb.toString();
    }

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    @Override
    public boolean agregarElemento(T t) throws ElementoNuloException, ElementoExistenteException {
        if (listaElementos.contains(t)) {
            throw new ElementoExistenteException("El personaje que quiere ingresar a la casa ya existe");
        } else if (t == null) {
            throw new ElementoNuloException("No puede ingresar un personaje nulo a la casa");
        } else return listaElementos.add(t);
    }

    @Override
    public boolean eliminarElemento(T t) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (!listaElementos.isEmpty()) {
            for (T t1 : listaElementos) {
                if (t1.equals(t)) {
                    return listaElementos.remove(t);
                }
            }
            throw new ElementoNoEncontradoException("El elemento que desea eliminar no se encuentra en la casa");
        } else if (t == null) {
            throw new ElementoNuloException("El elemento que desea borrar no puede ser nulo");
        }else throw new ListaVaciaException("No se puede eliminar un elemento si la lista está vacía");
    }

    @Override
    public T buscarElemento(Integer id) throws ListaVaciaException, ElementoNoEncontradoException, ParametroInvalidoException {
        if (!listaElementos.isEmpty()) {
            for (T item : listaElementos) {
                if (item instanceof IReconocerId aux) {
                    if (aux.getId().equals(id)) {
                        return item;
                    }
                }
            }
            throw new ElementoNoEncontradoException("El personaje que desea buscar no se encuentra en la casa");
        } else if (id <= 0) {
            throw new ParametroInvalidoException("El ID no puede ser menor o igual a 0");
        }else throw new ListaVaciaException("No se puede buscar un personaje de la lista vacía");
    }
}
