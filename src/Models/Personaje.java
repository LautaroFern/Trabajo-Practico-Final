package Models;

import Enums.RolPersonaje;
import Enums.TipoGenero;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ListaVaciaException;
import Exceptions.RasgoInvalidoException;
import Interfaces.IReconocerId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Personaje implements IReconocerId {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private int edad;
    private TipoGenero genero;
    private ArrayList<String> rasgos;
    private Integer idPersonaje;
    private RolPersonaje rolPersonaje;
    private static Integer idIncremental = 0;

    //---------- CONSTRUCTORES ----------
    public Personaje(String nombre, int edad, TipoGenero genero, RolPersonaje rolPersonaje) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        idIncremental++;
        this.idPersonaje = idIncremental;
        this.rolPersonaje = rolPersonaje;
        this.rasgos = new ArrayList<>();
    }

    public Personaje() {
        this.nombre = "";
        this.edad = 0;
        this.genero = null;
        idIncremental++;
        this.idPersonaje = idIncremental;
        this.rolPersonaje = null;
        this.rasgos = new ArrayList<>();
    }

    //---------- GETTERS y SETTERS ----------
    @Override
    public Integer getId() {
        return this.idPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public RolPersonaje getRolPersonaje() {
        return rolPersonaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public void setRasgos(ArrayList<String> rasgos) {
        this.rasgos = rasgos;
    }

    public void setIdPersonaje(Integer idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public void setRolPersonaje(RolPersonaje rolPersonaje) {
        this.rolPersonaje = rolPersonaje;
    }

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personaje personaje = (Personaje) o;
        return Objects.equals(idPersonaje, personaje.idPersonaje);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPersonaje);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Personaje [ID: ").append(idPersonaje).append("]\n");
        sb.append("  Nombre: ").append(nombre).append("\n");
        sb.append("  Edad: ").append(edad).append("\n");
        sb.append("  Género: ").append(genero).append("\n");
        sb.append("  Rol: ").append(rolPersonaje).append("\n");
        sb.append("  Rasgos:\n");
        for (String r : rasgos) {
            sb.append("    - ").append(r).append("\n");
        }
        return sb.toString();
    }

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    public boolean agregarRasgo(String rasgo) throws RasgoInvalidoException {
        if (rasgo == null) {
            throw new RasgoInvalidoException("El rasgo no puede estar vacío.");
        } else if (rasgos.contains(rasgo)) {
            throw new RasgoInvalidoException("El rasgo '" + rasgo + "' ya existe.");
        }else return rasgos.add(rasgo);
    }

    public boolean eliminarRasgo(String rasgo) throws ElementoNoEncontradoException, ListaVaciaException {
        if (!rasgos.isEmpty()){
            if (!rasgos.contains(rasgo)) {
                throw new ElementoNoEncontradoException("No se encontró el rasgo '" + rasgo + "'.");
            }
            return rasgos.remove(rasgo);
        }
        throw new ListaVaciaException("El personaje no tiene ningpún rasgo cargado");
    }

    public String modificarRasgo(String viejo, String nuevo) throws ElementoNoEncontradoException, RasgoInvalidoException, ListaVaciaException {
        int index = rasgos.indexOf(viejo);
        if (nuevo == null) {
            throw new RasgoInvalidoException("El nuevo rasgo no puede estar vacío");
        } else if (rasgos.isEmpty()) {
            throw new ListaVaciaException("El personaje no tiene ningpún rasgo cargado");
        } else if (index == -1) {
            throw new ElementoNoEncontradoException("El rasgo '" + viejo + "' no existe y no puede modificarse");
        }else rasgos.set(index, nuevo);
        return "Rasgo modificado: '" + viejo + "' → '" + nuevo + "'";
    }

    public String detallesRasgos() throws ListaVaciaException {
        if (rasgos.isEmpty()) {
            throw new ListaVaciaException("El personaje no tiene rasgos asignados.");
        }
        StringBuilder sb = new StringBuilder("Rasgos de " + nombre + ":\n");
        for (String r : rasgos) {
            sb.append("- ").append(r).append("\n");
        }
        return sb.toString();
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Nombre", nombre);
            jsonObject.put("Edad", edad);
            jsonObject.put("Genero", genero);
            jsonObject.put("Id Personaje", idPersonaje);
            jsonObject.put("Rol Personaje", rolPersonaje);
            jsonObject.put("Rasgos", new JSONArray(rasgos));
        } catch (JSONException e) {
            System.out.println("Error al cargar el JSON: " + e.getMessage());
        }
        return jsonObject;
    }

    public static Personaje toObject(JSONObject jsonObject) {
        Personaje personaje = new Personaje();
        try {
            personaje.setNombre(jsonObject.getString("Nombre"));
            personaje.setEdad(jsonObject.getInt("Edad"));
            personaje.setGenero(TipoGenero.valueOf(jsonObject.getString("Genero")));
            personaje.setIdPersonaje(jsonObject.getInt("Id Personaje"));
            personaje.setRolPersonaje(RolPersonaje.valueOf(jsonObject.getString("Rol Personaje")));
            JSONArray array = new JSONArray();
            ArrayList<String> aux = new ArrayList<>();
            for ( int i = 0; i<array.length(); i++){
                aux.add(array.getString(i));
            }
            personaje.setRasgos(aux);
        }catch (JSONException e){
            System.out.println("Error al cargar el objeto: " + e.getMessage());
        }
        return personaje;
    }


}
