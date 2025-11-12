package Models;

import Interfaces.IReconocerId;
import org.json.JSONObject;

import java.util.Objects;

public class ObjetoCasa extends Pista implements IReconocerId {
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
    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }
}
