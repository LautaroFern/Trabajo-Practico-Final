package Models;

import Collections.Casa;

public class Juego {
    //---------- ATRIBUTOS ----------
    private Casa casa;
    private Jugador jugador;

    //---------- CONSTRUCTOR ----------
    public Juego(Casa casa, Jugador jugador) {
        this.casa = casa;
        this.jugador = jugador;
    }

    //---------- GETTERS y SETTERS ----------
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Detalles de la Partida ===\n");
        sb.append("Casa:\n");
        sb.append(casa != null ? casa.toString() : "  (sin casa asignada)\n");
        sb.append("\nJugador:\n");
        sb.append(jugador != null ? jugador.toString() : "  (sin jugador asignado)\n");
        return sb.toString();
    }

    //---------- METODOS ----------
    public void iniciar(){

    }

    public void mostrarMenu(){

    }

    public void finalizar(){

    }

}
