package Model;

import Exception.ValidarLetrasException;
import Interface.IDevolverString;
import Interface.IReconocerId;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Jugador implements IReconocerId, IDevolverString {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private String usuario;
    private String contrasena;
    private Inventario inventario;
    private int progreso;
    private final Integer idJugador;
    private static Integer idIncremental = 0;

    //---------- CONSTRUCTORES ----------
    public Jugador(String nombre, String usuario, String contrasena) {
        try {
            this.nombre = validarLetras(nombre);
            this.usuario = validarLetras(usuario);
        } catch (ValidarLetrasException e) {
            e.printStackTrace();
        }
        this.contrasena = contrasena;
        this.progreso = 0;
        idIncremental++;
        this.idJugador = idIncremental;
    }

    public Jugador() {
        this.nombre = "";
        this.usuario = "";
        this.contrasena = "";
        this.inventario = new Inventario();
        this.progreso = 0;
        idIncremental++;
        this.idJugador = idIncremental;
    }

    //---------- GETTERS y SETTERS ----------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    @Override
    public Integer getId() {
        return this.idJugador;
    }

    @Override
    public String devolverString() {
        return this.usuario;
    }

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(idJugador, jugador.idJugador);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idJugador);
    }

    @Override
    public String toString() {
        return "Jugador:\n" +
                "  Nombre: " + nombre + "\n" +
                "  Usuario: " + usuario + "\n" +
                "  Contraseña: " + contrasena + "\n" +
                "  Progreso: " + progreso + "%\n" +
                "  Inventario:\n" +
                (inventario != null ? inventario.toString() : "    (Vacío)") +
                "\n";
    }

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Nombre", nombre);
            jsonObject.put("Usuario", usuario);
            jsonObject.put("Contraseña", contrasena);
            jsonObject.put("Progreso", progreso);
            if (inventario == null) {
                Inventario inventario1 = new Inventario();
                jsonObject.put("Inventario", inventario1.toJson());
            }else {
                jsonObject.put("Inventario", inventario.toJson());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Jugador toObject(JSONObject jsonObject) {
        Jugador jugador = new Jugador();
        try {
            jugador.setNombre(jsonObject.getString("Nombre"));
            jugador.setUsuario(jsonObject.getString("Usuario"));
            jugador.setContrasena(jsonObject.getString("Contraseña"));
            jugador.setProgreso(jsonObject.getInt("Progreso"));
            JSONObject inv = jsonObject.getJSONObject("Inventario");
            jugador.setInventario(Inventario.toObject(inv));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jugador;
    }

    public String validarLetras(String texto) throws ValidarLetrasException {

        if (!texto.matches("[a-zA-Z]+")) {
            throw new ValidarLetrasException("Los datos ingresados no pueden contener numeros o simbolos");
        } else {
            return texto;

        }
    }
}