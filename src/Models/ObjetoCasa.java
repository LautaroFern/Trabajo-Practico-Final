package Models;


import org.json.JSONException;
import org.json.JSONObject;

public class ObjetoCasa extends Pista {
    //---------- CONSTRUCTORES ----------
    public ObjetoCasa(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    public ObjetoCasa() {
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Datos del Objeto:\n" +
                "  ID: " + this.id + "\n" +
                "  Nombre: " + this.nombre + "\n" +
                "  Descripción: " + this.descripcion + "\n";
    }

    //---------- METODOS ----------
    public static ObjetoCasa toObject(JSONObject jsonObject) {
        ObjetoCasa objetoCasa = new ObjetoCasa();
        try {
            objetoCasa.setNombre(jsonObject.getString("Nombre"));
            objetoCasa.setDescripcion(jsonObject.getString("Descripcion"));
            objetoCasa.setId(jsonObject.getInt("Id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objetoCasa;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Tipo", "Objeto");
            jsonObject.put("Id", id);
            jsonObject.put("Nombre", nombre);
            jsonObject.put("Descripcion", descripcion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
