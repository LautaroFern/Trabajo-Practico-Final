package Models;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;
import Interfaces.IDevolverString;
import Interfaces.IGestora;
import Interfaces.IReconocerId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Habitacion implements IReconocerId, IGestora<Pista>, IDevolverString {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private ArrayList<Pista> pistas;
    private final Integer idHabitacion;
    private static Integer idIncremental = 0;

    //---------- CONSTRUCTORES ----------
    public Habitacion(String nombre) {
        this.nombre = nombre;
        idIncremental++;
        this.idHabitacion = idIncremental;
        this.pistas = new ArrayList<>();
    }

    public Habitacion() {
        this.nombre = "";
        idIncremental++;
        this.idHabitacion = idIncremental;
        this.pistas = new ArrayList<>();
    }

    //---------- GETTERS y SETTERS ----------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    public void setPistas(ArrayList<Pista> pistas) {
        this.pistas = pistas;
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
        if (pistas != null && !pistas.isEmpty()) {
            sb.append("  Pistas:\n");
            for (Pista p : pistas) {
                if (p instanceof PistaTexto) {
                    sb.append("    - ").append(p).append("\n");
                } else sb.append("    (Sin pistas)\n");
            }
            sb.append("  Objetos:\n");
            for (Pista p : pistas) {
                if (p instanceof ObjetoCasa) {
                    sb.append("    - ").append(p).append("\n");
                } else sb.append("    (Sin objetos)\n");
            }
        }
        return sb.toString();
    }

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    @Override
    public String devolverString() {
        return this.nombre;
    }

    @Override
    public boolean agregarElemento(Pista p) throws ElementoNuloException, ElementoExistenteException {
        if (p == null) {
            throw new ElementoNuloException("La pista ingresada no puede ser nula");
        } else if (pistas.contains(p)) {
            throw new ElementoExistenteException("La pista ya existe en la habitación");
        } else return pistas.add(p);
    }

    @Override
    public boolean eliminarElemento(Pista p) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (p == null) {
            throw new ElementoNuloException("La pista ingresada no puede ser nula");
        } else if (!pistas.isEmpty()) {
            for (Pista pista : pistas) {
                if (pista.getId().equals(p.getId())) {
                    return pistas.remove(pista);
                }
            }
            throw new ElementoNoEncontradoException("La pista no se encuentra en la habitacón");
        } else throw new ListaVaciaException("No se puede eliminar la pista de la lista vacía");
    }

    @Override
    public Pista buscarElemento(String nombrePista) throws ListaVaciaException, ElementoNoEncontradoException, ElementoNuloException {
        if (nombrePista == null) {
            throw new ElementoNuloException("El nombre de la pista ingresada no puede ser nulo");
        } else if (!pistas.isEmpty()) {
            for (Pista p : pistas) {
                if (p.getNombre().equals(nombrePista)) {
                    return p;
                }
            }
            throw new ElementoNoEncontradoException("La pista no se encuentra en la habitación");
        } else throw new ListaVaciaException("La pista no existe en la lista");
    }

    /*public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ID", this.idHabitacion);
            jsonObject.put("Nombre habitacin", this.nombre);
            JSONArray array = new JSONArray();
            for (Pista pista : pistas) {
                if (pista instanceof PistaTexto p) {
                    array.put(p.toJson());
                } else if (pista instanceof ObjetoCasa o) {
                    array.put(o.toJson());
                }
            }
            jsonObject.put("Pistas", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Habitacion toObject(JSONObject jsonObject) {
        Habitacion nueva = new Habitacion();
        try {
            nueva.setNombre(jsonObject.getString("Nombre habitacion"));
            JSONArray array = jsonObject.getJSONArray("Pistas");
            ArrayList<Pista> p = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject aux = array.getJSONObject(i);
                Pista pista = null;
                String tipo = aux.getString("Tipo");

                if (tipo.equalsIgnoreCase("Pista Texto")) {
                    pista = PistaTexto.toObject(aux);
                } else if (tipo.equalsIgnoreCase("Objeto Casa")) {
                    pista = ObjetoCasa.toObject(aux);
                }
                p.add(pista);
            }
            nueva.setPistas(p);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nueva;
    }*/
}