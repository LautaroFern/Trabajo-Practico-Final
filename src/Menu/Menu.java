package Menu;

import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNuloException;
import Models.Juego;
import Models.Jugador;

import java.util.Scanner;

public class Menu {
    //---------- ATRIBUTOS ----------
    private Scanner teclado;
    private Juego juego;

    //---------- CONSTRUCTOR ----------
    public Menu() {
        this.teclado = new Scanner(System.in);
        this.juego = new Juego();
    }

    public void iniciar() {
        System.out.println("Introducción:\n");
        System.out.println("La familia Sanchez siempre fue conocida por su fortuna, construida gracias al esfuerzo del patriarca: El Dr Tobias Sanchez, un hombre estricto, pero generoso con quien lo merecía. \n" +
                "Querido por pocos y odiado por muchos, no era un hombre el cual tuviera enemigos pero su mano dura con la gente y la manera de llevar sus negocios no le caía bien a todo el mundo.\n");
        System.out.println("Era una noche fría pero calma, la noche transcurría como cualquier otra, hasta que una llamada sacudió la calma de la familia y de la mansión. El Dr Tobias Sanchez estaba sin vida en su dormitorio, la familia consternada y triste te está esperando para que comiences tu investigación");


        Jugador jugador = loginJugador();
        try {
            juego.agregarJugador(jugador);
        }catch (ElementoNuloException | ElementoExistenteException e){
            System.out.println(e.getMessage());
        }
    }

    public Jugador loginJugador() {
        System.out.println("Ingrese su nombre completo:");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese su nombre de usuario:");
        String usuario = teclado.nextLine();
        System.out.println("Ingrese la contraseña nueva:");
        String contrasena = teclado.nextLine();

        return new Jugador(nombre, usuario, contrasena);
    }
}