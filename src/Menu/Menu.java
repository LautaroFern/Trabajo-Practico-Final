package Menu;

import Enums.RolPersonaje;
import Enums.TipoGenero;
import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;
import Gestor.Juego;
import Models.*;

import java.util.Scanner;

public class Menu {
    //---------- ATRIBUTOS ----------
    private final Scanner teclado;
    private Juego<Personaje> personajes;
    private Juego<Habitacion> habitaciones;
    private Juego<Jugador> jugadores;
    private Jugador jugadorActivo;

    //---------- CONFESIONES ----------
    String confesionRicardo = "Fue despertado por un grito de terror por parte del mayordomo, él fue la persona que alertó a la policía." +
            "\nLa relación con su padre era estable, ni amistosa ni fría. Confesó temer que su padre cambiara el testamento y que cuando llegara el momento no fuera el responsable de la empresa familiar";
    String confesionJuana = "No tenía una buena relación con su suegro, simplemente se toleraban pero él no la consideraba una buena pareja para su hijo debido a que la veía como una interesada," +
            "\nque solo lo quería para estar a su lado al momento de que heredará la empresa.";
    String confesionMariana = "Tuvo una discusion con su abuelo el dia anterior a su muerte, confeso que la pelea fue por su compromiso. " +
            "\nTobias le dejo en claro que si se casaba, vendería la parte de la herencia que le correspondía para evitar que su futuro esposo se quedara con dinero fácil." +
            "\n Confiesa que estaba lista para huir con su prometido y una suma de dinero que había ahorrado.";
    String confesionMatias = "Un joven con problemas de apuestas y drogas, estuvo enredado en discusiones con su abuelo al enterarse que lo dejaría fuera del testamento por su comportamiento erratico." +
            "\nLa victima no soportaria seguir manteniendo a un vago inoperante que era como lo llamaba Tobias";
    String confesionAlfred = "Reitera su lealtad, pero confiesa el desgaste emocional y la humillación de los últimos meses (discusiones, amenazas de despido). " +
            "\nInsiste: El maestro pidio whisky, y luego se fue a dormir. Despues de servirle la bebida nadie mas entro a la habitacion";
    String confesionSalomon = "Confiesa que entro a la casa en la tarde cuando la victima no se encontraba y fue a su estudio a buscar un documento que supuestamente le habia hurtado." +
            "\nTambien confeso que a esa hora el no estaba en su casa porque estaba terminando unos (asuntos) con su secretaria, llego a su casa a las 03:20 AM\n";



    //---------- CONSTRUCTOR ----------
    public Menu() {
        this.teclado = new Scanner(System.in);
        this.personajes = new Juego<>();
        this.habitaciones = new Juego<>();
        this.jugadores = new Juego<>();

    }

    public void iniciar() {
        cargarHabitacionesYPistas();
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

        System.out.println("Ingrese su nombre:");
        String nombre = teclado.nextLine();

        try {
            jugadorActivo = jugadores.buscarElemento(nombre);
        } catch (ListaVaciaException | ElementoNuloException | ElementoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(jugadorActivo.toString());

        iniciarJuego(jugadorActivo.getProgreso());

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

    public void cargarHabitacionesYPistas() {
        PistaTexto testamentoNuevo = new PistaTexto("Testamento Nuevo",
                "Documento en el cual se detalla la exclusion de Matias del testamento" +
                        "\ny deja como unica beneficiaria a su nieta Mariana");

        PistaTexto boletosAvion = new PistaTexto("Boletos de Avion",
                "Pasajes previstos para dentro de dos dias," +
                "\nDestino: Madrid - España");

        PistaTexto folletosGeriatrico = new PistaTexto("Folletos de Geriatricos",
                "Volantes y folletos de diferentes geriatricos de la ciudad");

        PistaTexto documentoInternacion = new PistaTexto("Documento de Internacion",
                "Papeles que indican la internacion pronta de la Victima." +
                "\nEl documento esta firmado por Ricardo y su esposa, falta la firma de la Victima");


        ObjetoCasa frascoMedicamentosVacio = new ObjetoCasa("Frasco de Medicamentos",
                "");

        ObjetoCasa llaveDeHabitaciones = new ObjetoCasa("Llaves de las Habitaciones",
                "");

        ObjetoCasa frascoConVeneno = new ObjetoCasa("Frasco Extraño",
                "");

        ObjetoCasa guantesMayordomo = new ObjetoCasa("Guantes de Mayordomo",
                "");

        ObjetoCasa valijaPreparada = new ObjetoCasa("Valija Preparada",
                "");


        Habitacion habitacionRicardoYJuana = new Habitacion("Habitación de Ricardo y Juana.");
        Habitacion habitacionMariana = new Habitacion("Habitación de Mariana Sanchez.");
        Habitacion habitacionMatias = new Habitacion("Habitación de Matias Sanchez.");
        Habitacion habitacionTobias = new Habitacion("Habitación de Tobías Sanchez.");
        Habitacion habitacionAlfred = new Habitacion("Habitación de Alfred.");

        try {
            //----- CARGA PISTAS -----
            habitacionRicardoYJuana.agregarElemento(folletosGeriatrico);
            habitacionMariana.agregarElemento(boletosAvion);
            habitacionMariana.agregarElemento(valijaPreparada);
            habitacionMatias.agregarElemento(frascoConVeneno);
            habitacionMatias.agregarElemento(guantesMayordomo);
            habitacionTobias.agregarElemento(testamentoNuevo);
            habitacionTobias.agregarElemento(frascoMedicamentosVacio);
            habitacionAlfred.agregarElemento(llaveDeHabitaciones);

            //----- CARGA HABITACIONES -----
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
        ricardoSanchez.setConfesion(confesionRicardo);
        Personaje juanaRodriguez = new Personaje("Juana Rodriguez", 53, TipoGenero.Femenino, RolPersonaje.Nuera);
        juanaRodriguez.setConfesion(confesionJuana);
        Personaje marianaSanchez = new Personaje("Mariana Sanchez", 24, TipoGenero.Femenino, RolPersonaje.Nieta);
        marianaSanchez.setConfesion(confesionMariana);
        Personaje matiasSanchez = new Personaje("Matias Sanchez", 24, TipoGenero.Masculino, RolPersonaje.Nieto);
        matiasSanchez.setConfesion(confesionMatias);
        Personaje alfred = new Personaje("Alfred", 71, TipoGenero.Masculino, RolPersonaje.Mayordomo);
        alfred.setConfesion(confesionAlfred);
        Personaje salomonGuzman = new Personaje("Salomon Guzmán", 67, TipoGenero.Masculino, RolPersonaje.Vecino);
        salomonGuzman.setConfesion(confesionSalomon);

        Personaje tobiasSanchez = new Personaje("Tobías Sanchez", 83, TipoGenero.Masculino, RolPersonaje.Padre);


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

    public void iniciarJuego(int progreso){
        boolean continuar = true;
        while (continuar){
            switch (progreso){
                case 0:

                    jugadorActivo.setProgreso(20);
                    progreso = 20;
                    break;
                case 20:

                    jugadorActivo.setProgreso(40);
                    progreso = 40;
                    break;
                case 40:

                    jugadorActivo.setProgreso(60);
                    progreso = 60;
                    break;
                case 60:

                    jugadorActivo.setProgreso(80);
                    progreso = 80;
                    break;
                case 80:

                    jugadorActivo.setProgreso(100);
                    progreso = 100;
                    break;
                case 100:

                    continuar = false;
                    break;
                default:
                    continuar = false;
                    break;
            }
        }
    }
}