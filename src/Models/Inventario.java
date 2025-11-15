package Models;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;
import Interfaces.IGestora;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;


public class Inventario implements IGestora<Pista> {
    //---------- ATRIBUTOS ----------
    private HashSet<Pista> listaElementos;

    //---------- CONSTRUCTOR ----------
    public Inventario() {
        this.listaElementos = new HashSet<>();
    }

    //---------- GETTERS y SETTERS ----------
    public HashSet<Pista> getListaElemento() {
        return listaElementos;
    }

    public void setListaElemento(HashSet<Pista> listaElemento) {
        this.listaElementos = listaElemento;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario:\n");
        if (listaElementos != null && !listaElementos.isEmpty()) {
            for (Pista elemento : listaElementos) {
                sb.append(" - ").append(elemento).append("\n");
            }
        } else {
            sb.append("  (Vacío)\n");
        }
        return sb.toString();
    }

    //---------- METODOS CON EXCEPCIONES PERSONALIZADAS ----------
    @Override
    public boolean agregarElemento(Pista t) throws ElementoNuloException, ElementoExistenteException {
        if (t == null) {
            throw new ElementoNuloException("El elemento que se intenta agregar es nulo");
        } else if (listaElementos.contains(t)) {
            throw new ElementoExistenteException("El elemento ya existe en el inventario");
        } else return listaElementos.add(t);
    }

    @Override
    public boolean eliminarElemento(Pista t) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (!listaElementos.isEmpty()) {
            for (Pista t1 : listaElementos) {
                if (t1.equals(t)) {
                    return listaElementos.remove(t1);
                }
            }
            throw new ElementoNoEncontradoException("El elemento que no se quiere eliminar no fue encontrado");
        } else if (t == null) {
            throw new ElementoNuloException("El elemento ingresado es nulo");
        } else throw new ListaVaciaException("La lista esta vacia");
    }

    @Override
    public Pista buscarElemento(String nombrePista) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (nombrePista == null || nombrePista.trim().isEmpty()) {
            throw new ElementoNuloException("El nombre de la pista no puede ser nulo o vacío");
        } else if (!listaElementos.isEmpty()) {
            for (Pista e : listaElementos) {
                if (e.getNombre().equals(nombrePista)) {
                    return e;
                }
            }

            throw new ElementoNoEncontradoException("Elemento no encontrado en la lista");
        } else throw new ListaVaciaException("La lista esta vacia");
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray array = new JSONArray();
            for (Pista pista : listaElementos) {
                if (pista instanceof PistaTexto p) {
                    array.put(p.toJson());
                } else if (pista instanceof ObjetoCasa o) {
                    array.put(o.toJson());
                }
            }
            jsonObject.put("Inventario", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Inventario toObject(JSONObject jsonObject) {
        Inventario inventario = new Inventario();
        try {
            JSONArray array = jsonObject.getJSONArray("Inventario");
            HashSet<Pista> lista = new HashSet<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject aux = array.getJSONObject(i);
                Pista pista = null;
                String tipo = aux.getString("Tipo");

                if (tipo.equalsIgnoreCase("Pista Texto")) {
                    pista = PistaTexto.toObject(aux);
                } else if (tipo.equalsIgnoreCase("Objeto Casa")) {
                    pista = ObjetoCasa.toObject(aux);
                }
                lista.add(pista);
            }
            inventario.setListaElemento(lista);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return inventario;
    }
}