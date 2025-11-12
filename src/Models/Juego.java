package Models;

import Collections.Casa;

import java.util.ArrayList;

public class Juego {
    //---------- ATRIBUTOS ----------
    private Casa casa;
    private ArrayList<Jugador> jugadores;

    //---------- CONSTRUCTOR ----------
    public Juego(Casa casa) {
        this.casa = casa;
        this.jugadores = new ArrayList<>();
    }

    //---------- GETTERS y SETTERS ----------
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    //---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Juego{" +
                "casa=" + casa +
                ", jugadores=" + jugadores +
                '}';
    }

    //---------- METODOS ----------



}
