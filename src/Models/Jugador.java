package Models;

import Exceptions.ContrasenaNoCoincideExeption;
import Interfaces.IRecolectable;

import java.util.Objects;

public class Jugador implements IRecolectable {
    //---------- ATRIBUTOS ----------
    private String nombre;
    private String usuario;
    private String contrasena;
    private Inventario inventario;
    private double progreso;

    //---------- CONSTRUCTORES ----------
    public Jugador(String nombre, String usuario, String contrasena) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.progreso = 0.0;
    }

    public Jugador() {
        this.nombre = "";
        this.usuario = "";
        this.contrasena = "";
        this.inventario = null;
        this.progreso = 0.0;
    }

    //---------- GETTERS y SETTERS ----------
    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
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

    public double getProgreso() {
        return progreso;
    }

    //---------- EQUALS, HASHCODE y TOSTRING ----------
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Jugador jugador)) return false;
        return Objects.equals(contrasena, jugador.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contrasena);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador:\n");
        sb.append("  Nombre: ").append(nombre).append("\n");
        sb.append("  Usuario: ").append(usuario).append("\n");
        sb.append("  Contraseña: ").append(contrasena).append("\n");
        sb.append("  Progreso: ").append(progreso).append("%\n");
        sb.append("  Inventario:\n").append(inventario != null ? inventario.toString() : "    (Vacío)").append("\n");
        return sb.toString();
    }

    @Override
    public String agarrar(Pista pista) {
        return "Agarraste " + pista.getNombre();
    }

    //---------- MÉTODOS CON EXCEPCIONES PERSONALIZADAS ----------
    public boolean cambiarContrasena(String contrasenaActual, String nuevaContrasena) throws ContrasenaNoCoincideExeption {
        if (this.contrasena.equals(contrasenaActual)) {
            this.contrasena = nuevaContrasena;
            return true;
        }
        throw new ContrasenaNoCoincideExeption("La contraseña no coincide");
    }
}
