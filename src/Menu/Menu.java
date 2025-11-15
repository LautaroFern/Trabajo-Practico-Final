package Menu;

import Enums.RolPersonaje;
import Enums.TipoGenero;
import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNuloException;
import Gestor.Juego;
import Models.Habitacion;
import Models.Jugador;
import Models.Personaje;

import java.util.Scanner;

public class Menu {
    //---------- ATRIBUTOS ----------
    private final Scanner teclado;
    private Juego<Personaje> personajes;
    private Juego<Habitacion> habitaciones;
    private Juego<Jugador> jugadores;

    //---------- CONSTRUCTOR ----------
    public Menu() {
        this.teclado = new Scanner(System.in);
        this.personajes = new Juego<>();
        this.habitaciones = new Juego<>();
        this.jugadores = new Juego<>();

    }

    public void iniciar() {
        cargarHabitaciones();
        cargaPersonaje();
        System.out.println(
                        "\t███╗   ███╗ " + "██╗ " + "███████╗ " + "████████╗ " + "███████╗ " + "██████╗  " + "██╗   ██╗ " + "   ██╗ " + "███╗   ██╗ " + "   ████████╗ " + "██╗  ██╗ " + "███████╗ " + "   ███╗   ███╗ " + " █████╗  " + "███╗   ██╗ " + "███████╗ " + "██╗ " + " ██████╗  " + "███╗   ██╗ " + "\n" +
                        "\t████╗ ████║ " + "██║ " + "██╔════╝ " + "╚══██╔══╝ " + "██╔════╝ " + "██╔══██╗ " + "╚██╗ ██╔╝ " + "   ██║ " + "████╗  ██║ " + "   ╚══██╔══╝ " + "██║  ██║ " + "██╔════╝ " + "   ████╗ ████║ " + "██╔══██╗ " + "████╗  ██║ " + "██╔════╝ " + "██║ " + "██╔═══██╗ " + "████╗  ██║ " + "\n" +
                        "\t██╔████╔██║ " + "██║ " + "███████╗ " + "   ██║    " + "█████╗   " + "██████╔╝ " + " ╚████╔╝  " + "   ██║ " + "██╔██╗ ██║ " + "      ██║    " + "███████║ " + "█████╗   " + "   ██╔████╔██║ " + "███████║ " + "██╔██╗ ██║ " + "███████╗ " + "██║ " + "██║   ██║ " + "██╔██╗ ██║ " + "\n" +
                        "\t██║╚██╔╝██║ " + "██║ " + "╚════██║ " + "   ██║    " + "██╔══╝   " + "██╔══██╗ " + "  ╚██╔╝   " + "   ██║ " + "██║╚██╗██║ " + "      ██║    " + "██╔══██║ " + "██╔══╝   " + "   ██║╚██╔╝██║ " + "██╔══██║ " + "██║╚██╗██║ " + "╚════██║ " + "██║ " + "██║   ██║ " + "██║╚██╗██║ " + "\n" +
                        "\t██║ ╚═╝ ██║ " + "██║ " + "███████║ " + "   ██║    " + "███████╗ " + "██║  ██║ " + "   ██║    " + "   ██║ " + "██║ ╚████║ " + "      ██║    " + "██║  ██║ " + "███████╗ " + "   ██║ ╚═╝ ██║ " + "██║  ██║ " + "██║ ╚████║ " + "███████║ " + "██║ " + "╚██████╔╝ " + "██║ ╚████║ " + "\n" +
                        "\t╚═╝     ╚═╝ " + "╚═╝ " + "╚══════╝ " + "   ╚═╝    " + "╚══════╝ " + "╚═╝  ╚═╝ " + "   ╚═╝    " + "   ╚═╝ " + "╚═╝  ╚═══╝ " + "      ╚═╝    " + "╚═╝  ╚═╝ " + "╚══════╝ " + "   ╚═╝     ╚═╝ " + "╚═╝  ╚═╝ " + "╚═╝  ╚═══╝ " + "╚══════╝ " + "╚═╝ " + " ╚═════╝  " + "╚═╝  ╚═══╝ " + "\n"

        );

        System.out.println("Introducción:");
        System.out.println("La familia Sanchez siempre fue conocida por su fortuna, construida gracias al esfuerzo del patriarca: El Dr Tobias Sanchez, un hombre estricto, pero generoso con quien lo merecía. \n" +
                "Querido por pocos y odiado por muchos, no era un hombre el cual tuviera enemigos pero su mano dura con la gente y la manera de llevar sus negocios no le caía bien a todo el mundo.");
        System.out.println("Era una noche fría pero calma, la noche transcurría como cualquier otra, hasta que una llamada sacudió la calma de la familia y de la mansión. " + "\n" +
                "El Dr Tobias Sanchez estaba sin vida en su dormitorio, la familia consternada y triste te está esperando para que comiences tu investigación");

        System.out.println(mostrarMenu());
        Jugador jugador = loginJugador();

        try {
            jugadores.agregarElemento(jugador);
        } catch (ElementoNuloException | ElementoExistenteException e) {
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

    public void cargarHabitaciones() {
        Habitacion habitacionRicardoYJuana = new Habitacion("Habitación de Ricardo y Juana.");
        Habitacion habitacionMariana = new Habitacion("Habitación de Mariana Sanchez.");
        Habitacion habitacionMatias = new Habitacion("Habitación de Matias Sanchez.");
        Habitacion habitacionTobias = new Habitacion("Habitación de Tobías Sanchez.");
        Habitacion habitacionAlfred = new Habitacion("Habitación de Alfred.");

        try {
            habitaciones.agregarElemento(habitacionAlfred);
            habitaciones.agregarElemento(habitacionMariana);
            habitaciones.agregarElemento(habitacionMatias);
            habitaciones.agregarElemento(habitacionTobias);
            habitaciones.agregarElemento(habitacionRicardoYJuana);
        } catch (ElementoExistenteException | ElementoNuloException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargaPersonaje() {
        Personaje ricardoSanchez = new Personaje("Ricardo Sanchez", 55, TipoGenero.Masculino, RolPersonaje.Hijo);
        Personaje juanaRodriguez = new Personaje("Juana Rodriguez", 53, TipoGenero.Femenino, RolPersonaje.Nuera);
        Personaje marianaSanchez = new Personaje("Mariana Sanchez", 24, TipoGenero.Femenino, RolPersonaje.Nieta);
        Personaje matiasSanchez = new Personaje("Matias Sanchez", 24, TipoGenero.Masculino, RolPersonaje.Nieto);
        Personaje alfred = new Personaje("Alfred", 71, TipoGenero.Masculino, RolPersonaje.Mayordomo);
        Personaje tobiasSanchez = new Personaje("Tobías Sanchez", 83, TipoGenero.Masculino, RolPersonaje.Padre);
        Personaje salomonGuzman = new Personaje("Salomon Guzmán", 67, TipoGenero.Masculino, RolPersonaje.Vecino);

        try {
            personajes.agregarElemento(ricardoSanchez);
            personajes.agregarElemento(juanaRodriguez);
            personajes.agregarElemento(marianaSanchez);
            personajes.agregarElemento(matiasSanchez);
            personajes.agregarElemento(alfred);
            personajes.agregarElemento(tobiasSanchez);
            personajes.agregarElemento(salomonGuzman);

        } catch (ElementoExistenteException | ElementoNuloException e) {
            System.out.println(e.getMessage());
        }
    }

    public String mostrarMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("-----Menú-----").append("\n");
        stringBuilder.append("1. Ingresar usuario.").append("\n");
        stringBuilder.append("2. Registrar usurio.").append("\n");
        stringBuilder.append("3. Ver progreso.").append("\n");
        stringBuilder.append("4. Cargar partida.").append("\n");
        stringBuilder.append("5. Salir.").append("\n");

        return stringBuilder.toString();
    }


}