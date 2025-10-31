package Models;

import Collections.Inventario;
import Exceptions.ContrasenaNoCoincideExeption;

import java.util.Objects;

public class Jugador {
    private String nombre;
    private String usuario;
    private Integer contrasena;
    private Inventario inventario;
    private double progreso;

    public Jugador(String nombre, String usuario, Integer contrasena, Inventario inventario, double progreso) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.inventario = inventario;
        this.progreso = progreso;
    }

    public Jugador(){
        this.nombre = "";
        this.usuario = "";
        this.contrasena = 0;
        this.inventario = null;
        this.progreso = 0.0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public Integer getContrasena() {
        return contrasena;
    }

    public void setContrasena(Integer contrasena) {
        this.contrasena = contrasena;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public double getProgreso() {
        return progreso;
    }


    public boolean cambiarContrasena(Integer contrasenaActual, Integer nuevaContrasena) throws ContrasenaNoCoincideExeption {
        if (this.contrasena.equals(contrasenaActual)) {
            this.contrasena = nuevaContrasena;
            return true;
        }
        throw new ContrasenaNoCoincideExeption("La contraseña no coincide");
    }

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

}
