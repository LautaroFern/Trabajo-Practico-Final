package Collections;

import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Interfaces.IReconocerNombre;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventario<T> {
    private ArrayList<T> listaElemento;

    public Inventario() {
        this.listaElemento = new ArrayList<>();
    }

    public boolean agregarElemento(T elemento) throws ElementoNuloException {
        if (elemento == null) {
            throw new ElementoNuloException("Elemento nulo");
        }
        listaElemento.add(elemento);
        return true;
    }

    public boolean eliminarElemento(T elemento) throws ElementoNuloException, ElementoNoEncontradoException {
        if (elemento == null) {
            throw new ElementoNuloException("Elemento nulo");
        }
        Iterator<T> it = listaElemento.iterator();
        while (it.hasNext()) {
            T actual = it.next();
            if (actual.equals(elemento)) {
                it.remove();
                return true;
            }
        }

        throw new ElementoNoEncontradoException("Elemento no encontrado en la lista");
    }

    public T buscarElemento(String elementoNombre) throws ElementoNuloException, ElementoNoEncontradoException {
        if (elementoNombre == null) {
            throw new ElementoNuloException("Elemento nulo");
        }
        for (T e : listaElemento) {
            if(e instanceof IReconocerNombre aux){
                if(((IReconocerNombre) e).getNombre().equals(elementoNombre)){
                    return e;
                }
            }
        }
        throw new ElementoNoEncontradoException("Elemento no encontrado en la lista");
    }
}
