package Interfaces;

import Exceptions.*;

public interface IGestora <T>{
    boolean agregarElemento(T t) throws ElementoNuloException, ElementoExistenteException;
    boolean eliminarElemento(T t) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException;
    T buscarElemento(Integer id) throws ListaVaciaException, ElementoNoEncontradoException, ParametroInvalidoException;
}
