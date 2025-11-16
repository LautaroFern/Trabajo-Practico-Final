package Menu;

import Enums.RolPersonaje;
import Enums.TipoGenero;
import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoNuloException;
import Exceptions.ListaVaciaException;
import Gestor.Juego;
import Models.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Menu {
    //---------- ATRIBUTOS ----------
    private final Scanner teclado;
    private final Juego<Personaje> personajes;
    private final Juego<Habitacion> habitaciones;
    private final Juego<Jugador> jugadores;
    private Jugador jugadorActivo;

    //---------- CONFESIONES ----------
    String confesionRicardo = """
            Fue despertado por un grito de terror por parte del mayordomo, él fue la persona que alertó a la policía.
            La relación con su padre era estable, ni amistosa ni fría. Confesó temer que su padre cambiara el testamento y que, cuando llegara el momento, no fuera el responsable de la empresa familiar.
            """;

    String confesionJuana = """
            No tenía una buena relación con su suegro, simplemente se toleraban, pero él no la consideraba una buena pareja para su hijo debido a que la veía como una interesada,
            que solo lo quería para estar a su lado al momento de que heredara la empresa.
            """;

    String confesionMariana = """
            Tuvo una discusión con su abuelo el día anterior a su muerte, confesó que la pelea fue por su compromiso.
            Tobías le dejó en claro que, si se casaba, vendería la parte de la herencia que le correspondía para evitar que su futuro esposo se quedara con dinero fácil.
            Confiesa que estaba lista para huir con su prometido y una suma de dinero que había ahorrado.
            """;

    String confesionMatias = """
            Un joven con problemas de apuestas y drogas, estuvo enredado en discusiones con su abuelo al enterarse que lo dejaría fuera del testamento por su comportamiento errático.
            La víctima no soportaría seguir manteniendo a un vago inoperante, que era como lo llamaba Tobías.
            """;

    String confesionAlfred = """
            Reitera su lealtad, pero confiesa el desgaste emocional y la humillación de los últimos meses (discusiones, amenazas de despido).
            Insiste: El maestro pidió whisky, y luego se fue a dormir. Después de servirle la bebida nadie más entró a la habitación
            """;

    String confesionSalomon = """
            Confiesa que entro a la casa en la tarde cuando la victima no se encontraba y fue a su estudio a buscar un documento que supuestamente le habia hurtado.
            Tambien confeso que a esa hora el no estaba en su casa porque estaba terminando unos (asuntos) con su secretaria, llego a su casa a las 03:20 AM
            """;

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
        int opcion = mostrarMenu();
        String usuario;
        boolean continuar = true;
        while (continuar){
            switch (opcion) {
                case 1:
                    jugadorActivo = loginJugador();
                    try {
                        jugadores.agregarElemento(jugadorActivo);
                    } catch (ElementoNuloException | ElementoExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    iniciarJuego(jugadorActivo.getProgreso());
                    continuar = false;
                    break;
                case 2:
                    System.out.println("Ingrese su nombre de usuario");
                    teclado.nextLine();
                    usuario = teclado.nextLine();
                    cargarPartida(usuario);
                    try {
                        jugadorActivo = jugadores.buscarElemento(usuario);
                    } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException e){
                        System.out.println(e.getMessage());
                    }
                    iniciarJuego(jugadorActivo.getProgreso());
                    System.out.println(jugadorActivo.toString());
                    break;
                case 3:
                    System.out.println("Ingrese su nombre de usuario");
                    teclado.nextLine();
                    usuario = teclado.nextLine();
                    cargarPartida(usuario);
                    System.out.println(jugadorActivo.getProgreso());
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    break;
            }
        }

        teclado.close();

    }

    public Jugador loginJugador() {
        teclado.nextLine();
        System.out.println("Ingrese su nombre completo (sin símbolos ni números):");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese su nombre de usuario (sin símbolos ni números):");
        String usuario = teclado.nextLine();
        System.out.println("Ingrese la contraseña nueva:");
        String contrasena = teclado.nextLine();

        return new Jugador(nombre, usuario, contrasena);
    }

    public void cargarHabitacionesYPistas() {
        //----- CREANDO LAS PISTAS -----
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

        //----- CREANDO LAS HABITACIONES -----
        Habitacion habitacionRicardoYJuana = new Habitacion("Habitación de Ricardo y Juana.");
        Habitacion habitacionMariana = new Habitacion("Habitación de Mariana Sanchez.");
        Habitacion habitacionMatias = new Habitacion("Habitación de Matias Sanchez.");
        Habitacion habitacionTobias = new Habitacion("Habitación de Tobías Sanchez.");
        Habitacion habitacionAlfred = new Habitacion("Habitación de Alfred.");

        try {
            //----- CARGA PISTAS -----
            habitacionRicardoYJuana.agregarElemento(folletosGeriatrico);
            habitacionRicardoYJuana.agregarElemento(documentoInternacion);
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

    public int mostrarMenu() {
        System.out.println(
                        "\t███╗   ███╗ " + "██╗ " + "███████╗ " + "████████╗ " + "███████╗ " + "██████╗  " + "██╗   ██╗ " + "   ██╗ " + "███╗   ██╗ " + "   ████████╗ " + "██╗  ██╗ " + "███████╗ " + "   ███╗   ███╗ " + " █████╗  " + "███╗   ██╗ " + "███████╗ " + "██╗ " + " ██████╗  " + "███╗   ██╗ " + "\n" +
                        "\t████╗ ████║ " + "██║ " + "██╔════╝ " + "╚══██╔══╝ " + "██╔════╝ " + "██╔══██╗ " + "╚██╗ ██╔╝ " + "   ██║ " + "████╗  ██║ " + "   ╚══██╔══╝ " + "██║  ██║ " + "██╔════╝ " + "   ████╗ ████║ " + "██╔══██╗ " + "████╗  ██║ " + "██╔════╝ " + "██║ " + "██╔═══██╗ " + "████╗  ██║ " + "\n" +
                        "\t██╔████╔██║ " + "██║ " + "███████╗ " + "   ██║    " + "█████╗   " + "██████╔╝ " + " ╚████╔╝  " + "   ██║ " + "██╔██╗ ██║ " + "      ██║    " + "███████║ " + "█████╗   " + "   ██╔████╔██║ " + "███████║ " + "██╔██╗ ██║ " + "███████╗ " + "██║ " + "██║   ██║ " + "██╔██╗ ██║ " + "\n" +
                        "\t██║╚██╔╝██║ " + "██║ " + "╚════██║ " + "   ██║    " + "██╔══╝   " + "██╔══██╗ " + "  ╚██╔╝   " + "   ██║ " + "██║╚██╗██║ " + "      ██║    " + "██╔══██║ " + "██╔══╝   " + "   ██║╚██╔╝██║ " + "██╔══██║ " + "██║╚██╗██║ " + "╚════██║ " + "██║ " + "██║   ██║ " + "██║╚██╗██║ " + "\n" +
                        "\t██║ ╚═╝ ██║ " + "██║ " + "███████║ " + "   ██║    " + "███████╗ " + "██║  ██║ " + "   ██║    " + "   ██║ " + "██║ ╚████║ " + "      ██║    " + "██║  ██║ " + "███████╗ " + "   ██║ ╚═╝ ██║ " + "██║  ██║ " + "██║ ╚████║ " + "███████║ " + "██║ " + "╚██████╔╝ " + "██║ ╚████║ " + "\n" +
                        "\t╚═╝     ╚═╝ " + "╚═╝ " + "╚══════╝ " + "   ╚═╝    " + "╚══════╝ " + "╚═╝  ╚═╝ " + "   ╚═╝    " + "   ╚═╝ " + "╚═╝  ╚═══╝ " + "      ╚═╝    " + "╚═╝  ╚═╝ " + "╚══════╝ " + "   ╚═╝     ╚═╝ " + "╚═╝  ╚═╝ " + "╚═╝  ╚═══╝ " + "╚══════╝ " + "╚═╝ " + " ╚═════╝  " + "╚═╝  ╚═══╝ " + "\n"

        );
        System.out.println(
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  |  1  |  I N I C I A R  J U E G O          |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  |  2  |  C A R G A R  P A R T I D A        |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  |  3  |  V E R  P R O G R E S O            |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  |  4  |  S A L I R                         |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n"

        );
        return teclado.nextInt();
    }

    public void iniciarJuego(int progreso) {
        boolean continuar = true;
        char eleccion;

        while (continuar) {
            switch (progreso) {
                case 0:
                    System.out.println("""
                            ────────────────────────────────────────────────────────────
                                                    INTRODUCCIÓN
                            ────────────────────────────────────────────────────────────
                            
                            La familia Sanchez siempre fue conocida por su fortuna, construida gracias al esfuerzo
                            del patriarca: el Dr. Tobias Sanchez, un hombre estricto y firme, pero generoso con quienes
                            él consideraba dignos. Querido por pocos y temido por muchos, su forma inflexible de manejar
                            los negocios generó tensiones y resentimientos a lo largo de los años.
                            
                            Aquella noche fría y silenciosa parecía transcurrir con total normalidad, hasta que una llamada
                            rompió la calma de la mansión: el Dr. Tobias Sanchez había sido encontrado sin vida en su
                            dormitorio. Con la familia conmocionada y la policía sin respuestas claras, tú eres la única
                            persona capaz de descubrir qué ocurrió realmente.
                            
                            Tu investigación comienza ahora...
                            
                            ────────────────────────────────────────────────────────────
                            """);
                    System.out.println("""
                            ───────────────────────────────────────────────────────────────────────────────
                                                        INFORME PRELIMINAR DEL CASO
                            ───────────────────────────────────────────────────────────────────────────────
                            
                            Bienvenido detective:""" + jugadorActivo.getUsuario() + """
                            
                            Caso Nº 26062011        |        Legajo Nº 15062013
                            Víctima: Tobias Sanchez
                            Causa inicial de muerte: Ahorcamiento
                            Hora estimada: 03:15 AM
                            Arma homicida: —
                            
                            Anotaciones:
                            La víctima fue encontrada sin vida colgando en su dormitorio; debajo de él se halló una 
                            silla tumbada, y todo apunta inicialmente a un suicidio. El Dr. Sanchez, de unos 75 años, 
                            era un hombre de complexión delgada y tenía antecedentes médicos relacionados con problemas 
                            de presión arterial y afecciones cardíacas.
                            
                            ───────────────────────────────────────────────────────────────────────────────
                            """);

                    jugadorActivo.setProgreso(20);
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        guardarPartida();
                        continuar = false;
                    } else {
                        progreso = 20;
                    }
                    break;
                case 20:
                    System.out.println("Case 20");
                    jugadorActivo.setProgreso(40);
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        guardarPartida();
                    } else {
                        progreso = 40;
                    }
                    break;
                case 40:
                    System.out.println("Case 40");
                    jugadorActivo.setProgreso(60);
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        guardarPartida();
                    } else {
                        progreso = 60;
                    }
                    break;
                case 60:
                    System.out.println("Case 60");
                    jugadorActivo.setProgreso(80);
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        guardarPartida();
                    } else {
                        progreso = 80;
                    }
                    break;
                case 80:
                    System.out.println("Case 80");
                    jugadorActivo.setProgreso(100);
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        guardarPartida();
                    } else {
                        progreso = 100;
                    }
                    break;
                case 100:
                    System.out.println("Case 100");
                    continuar = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void guardarPartida() {
        JSONArray jsonArray = new JSONArray();
        for (Jugador j : jugadores.getElementos()) {
            jsonArray.put(j.toJson());
        }
        JsonUtiles.grabarUnJson(jsonArray, "Jugador.json");
    }

    public void cargarPartida(String usuario) {
        try {
            JSONObject jsonObject = new JSONObject();
            HashSet<Jugador> jugadorAux = new HashSet<>();
            JSONArray array = new JSONArray(JsonUtiles.leerUnJson("Jugador.json"));
            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                jugadorAux.add(Jugador.toObject(jsonObject));
            }
            jugadores.setElementos(jugadorAux);
        } catch (JSONException e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (Jugador j : jugadores.getElementos()) {
            if (j.getUsuario().equalsIgnoreCase(usuario)) {
                jugadorActivo = j;
            }
        }
    }
}