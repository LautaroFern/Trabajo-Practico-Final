package Models;

import Exceptions.EElementoNulo;

import java.util.ArrayList;
import java.util.Objects;

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

    public boolean agregarPista(PistaTexto pista) throws EElementoNulo {
        if (pista == null){
            throw new EElementoNulo("La pista ingresada no puede ser nula");
        } else {
            pistas.add(pista);
            return true;
        }
    }

    public boolean agregarObjeto(ObjetoCasa objetoCasa) throws EElementoNulo{
        if (objetoCasa == null){
            throw new EElementoNulo("El objeto ingresado no puede ser nulo");
        } else {
            objetos.add(objetoCasa);
            return true;
        }
    }


}
