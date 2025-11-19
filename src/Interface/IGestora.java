package Interface;

import Exception.*;

public interface IGestora <T>{
    boolean agregarElemento(T t) throws ElementoNuloException, ElementoExistenteException;
    boolean eliminarElemento(T t) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException;
    T buscarElemento(String s) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException;
}
