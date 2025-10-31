package Models;

import Enums.RolPersonaje;
import Enums.TipoGenero;
import Exceptions.ListaVaciaException;
import Exceptions.RasgoInvalidoException;
import Exceptions.RasgoNoEncontadoException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Personaje {
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
    public Integer getIdPersonaje() {
        return idPersonaje;
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
        if (rasgo == null || rasgo.isEmpty()) {
            throw new RasgoInvalidoException("El rasgo no puede estar vacío.");
        }
        if (rasgos.contains(rasgo)) {
            throw new RasgoInvalidoException("El rasgo '" + rasgo + "' ya existe.");
        }
        rasgos.add(rasgo);
        return true;
    }

    public boolean eliminarRasgo(String rasgo) throws RasgoNoEncontadoException {
        if (!rasgos.contains(rasgo)) {
            throw new RasgoNoEncontadoException("No se encontró el rasgo '" + rasgo + "'.");
        }
        rasgos.remove(rasgo);
        return true;
    }

    public String modificarRasgo(String viejo, String nuevo) throws RasgoNoEncontadoException, RasgoInvalidoException {

        if (nuevo == null || nuevo.isEmpty()) {
            throw new RasgoInvalidoException("El nuevo rasgo no puede estar vacío.");
        }
        int index = rasgos.indexOf(viejo);
        if (index == -1) {
            throw new RasgoNoEncontadoException("El rasgo '" + viejo + "' no existe y no puede modificarse.");
        }
        rasgos.set(index, nuevo);
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

//
//    public JSONObject toJSON(){
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("Nombre",nombre);
//            jsonObject.put("Edad",edad);
//            jsonObject.put("Genero",genero);
//            jsonObject.put("IdPersonaje",idPersonaje);
//            jsonObject.put("RolPersonaje",rolPersonaje);
//            JSONArray array = new JSONArray();
//            for (String s : rasgos){
//                array.put(s.)
//            }
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }

}
