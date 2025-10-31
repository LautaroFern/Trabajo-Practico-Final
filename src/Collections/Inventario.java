package Collections;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;
import Interfaces.IGestora;

import java.util.HashSet;


public class Inventario<T> implements IGestora<T> {
    private HashSet<T> listaElemento;

    public Inventario() {
        this.listaElemento = new HashSet<>();
    }

    @Override
    public boolean agregarElemento(T t) throws ElementoNuloException, ElementoExistenteException {
        if (t == null) {
            throw new ElementoNuloException("El elemento que se intenta agregar es nulo");
        }
        if (listaElemento.contains(t)) {
            throw new ElementoExistenteException("El elemento ya existe en el inventario");
        }
        listaElemento.add(t);
        return true;
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
        }

        throw new ListaVaciaException("La lista esta vacia");

    }

    @Override
    public T buscarElemento(Integer id) throws ListaVaciaException, ElementoNoEncontradoException {
        return null;
//        if (id <= 0) {
//            throw new ElementoNuloException("Elemento invalido");
//        }
//        for (T e : listaElemento) {
//            if (e instanceof IReconocerNombre aux) {
//                if (aux.getNombre().equals(elementoNombre)) {
//                    return e;
//                }
//            }
//        }
//        throw new ElementoNoEncontradoException("Elemento no encontrado en la lista");
//    }

//    public String mostrarInventario() {
//        StringBuilder sb = new StringBuilder();
//        for (T elemento : listaElemento) {
//            sb.append(elemento.toString()).append("\n");
//        }
//        return sb.toString();
//    }
    }
}
