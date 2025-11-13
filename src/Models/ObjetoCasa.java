package Models;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ObjetoCasa extends Pista {
    //---------- ATRIBUTOS ----------
    private static Integer idIncremental = 0;
    private Integer id;

    //---------- CONSTRUCTOR ----------
    public ObjetoCasa(String nombre, String descripcion) {
        super(nombre, descripcion);
        idIncremental++;
        this.id = idIncremental;
    }

    public ObjetoCasa() {
        idIncremental++;
        this.id = idIncremental;
    }

    //---------- GETTERS y SETTERS ----------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ObjetoCasa that = (ObjetoCasa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

   @Override
    public String toString() {
        return "Datos del Objeto:\n" +
                "  ID: " + this.id + "\n" +
                "  Nombre: " + this.nombre + "\n" +
                "  Descripción: " + this.descripcion + "\n";
    }
    //---------- METODOS ----------

    public static ObjetoCasa toObject(JSONObject jsonObject){
        ObjetoCasa objetoCasa = new ObjetoCasa();
        try {
            objetoCasa.setId(jsonObject.getInt("Id"));
            objetoCasa.setNombre(jsonObject.getString("Nombre"));
            objetoCasa.setDescripcion(jsonObject.getString("Descripcion"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        return objetoCasa;
    }
    @Override
    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id",id);
            jsonObject.put("Nombre",nombre);
            jsonObject.put("Descripcion",descripcion);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }
}
