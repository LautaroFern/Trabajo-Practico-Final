package Models;

import org.json.JSONException;
import org.json.JSONObject;

public class PistaTexto extends Pista {
    //---------- CONSTRUCTORES ----------
    public PistaTexto(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    public PistaTexto() {
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Datos de Pista Texto:\n" +
                "  ID: " + this.id + "\n" +
                "  Nombre: " + this.nombre + "\n" +
                "  Descripción: " + this.descripcion + "\n";
    }

    //---------- METODOS ----------
    public static PistaTexto toObject(JSONObject jsonObject) {
        PistaTexto pistaTexto = new PistaTexto();
        try {
            pistaTexto.setNombre(jsonObject.getString("Nombre"));
            pistaTexto.setDescripcion(jsonObject.getString("Descripcion"));
            pistaTexto.setId(jsonObject.getInt("Id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pistaTexto;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Tipo", "Pista Texto");
            jsonObject.put("Id", id);
            jsonObject.put("Nombre", nombre);
            jsonObject.put("Descripcion", descripcion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}