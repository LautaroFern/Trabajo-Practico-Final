package Menu;

import Enums.RolPersonaje;
import Enums.TipoGenero;
import Exceptions.*;
import Gestor.Juego;
import Models.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public void jugar() {
        cargarHabitacionesYPistas();
        cargaPersonaje();
        for (Habitacion h : habitaciones.getElementos()) {
            System.out.println(h.toString());
        }
        int opcion = mostrarMenu();
        String usuario;
        boolean continuar = true;
        while (continuar) {
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
                    System.out.println("\tIngrese su nombre de usuario");
                    teclado.nextLine();
                    usuario = teclado.nextLine().trim();
                    Jugador jugadorCargado = cargarPartida(usuario);
                    jugadorActivo = jugadorCargado;
                    iniciarJuego(jugadorActivo.getProgreso());
                    System.out.println(jugadorCargado.toString());
                    continuar = false;
                    break;
                case 3:
                    System.out.println("\tIngrese su nombre de usuario");
                    teclado.nextLine();
                    usuario = teclado.nextLine();
                    cargarPartida(usuario);
                    System.out.println(jugadorActivo.getProgreso());
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("\tOpción incorrecta, seleccione una opcion valida\n");
                    opcion = mostrarMenu();
                    break;
            }
        }
        teclado.close();
    }

    public Jugador loginJugador() {
        boolean n1 = true;
        boolean n2 = true;
        Jugador aux = new Jugador();
        teclado.nextLine();
        System.out.println("Ingrese su nombre completo (sin símbolos ni números):");
        String nombre = teclado.nextLine();
        while (n1) {
            try {
                aux.setNombre(aux.validarLetras(nombre));
                n1 = false;
            } catch (ValidarLetrasException e) {
                System.out.println(e.getMessage());
                System.out.println("Ingrese su nombre completo (sin símbolos ni números):");
                nombre = teclado.nextLine();

            }
        }
        System.out.println("Ingrese su nombre de usuario (sin símbolos ni números):");
        String usuario = teclado.nextLine();
        while (n2) {
            try {
                aux.setUsuario(aux.validarLetras(usuario));
                n2 = false;
            } catch (ValidarLetrasException e) {
                System.out.println(e.getMessage());
                System.out.println("Ingrese su nombre de usuario (sin símbolos ni números):");
                usuario = teclado.nextLine();

            }
        }
        System.out.println("Ingrese la contraseña nueva:");
        String contrasena = teclado.nextLine();

        aux.setContrasena(contrasena);

        return aux;
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
                "Frasco de medicamentos con prescripcion medica para la presion arterial y " +
                        "\nproblemas del corazon");

        ObjetoCasa llaveDeHabitaciones = new ObjetoCasa("Llaves de las Habitaciones",
                "Juego de llaves de las diferentes habitaciones de la mansion");

        ObjetoCasa frascoConVeneno = new ObjetoCasa("Frasco Extraño",
                "Frasco pequeño de color negro que contiene un liquido transparente");

        ObjetoCasa guantesMayordomo = new ObjetoCasa("Guantes de Mayordomo",
                "Par de guantes de Alfred humedos, probablemente no deberian estar ahi");

        ObjetoCasa valijaPreparada = new ObjetoCasa("Valija Preparada",
                "Valija llena de pertenencias, parece cargada como para un viaje muy largo");

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
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  |  1  |  I N I C I A R  J U E G O          |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  |  2  |  C A R G A R  P A R T I D A        |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  |  3  |  V E R  P R O G R E S O            |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  |  4  |  S A L I R                         |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  +-----+------------------------------------+\n"

        );
        return teclado.nextInt();
    }

    public void iniciarJuego(int progreso) {
        boolean continuar = true;
        char eleccion;
        Inventario inventarioJugador = jugadorActivo.getInventario();
        if (inventarioJugador == null){
            inventarioJugador = new Inventario();
            jugadorActivo.setInventario(inventarioJugador);
        }
        ObjetoCasa oAux1 = new ObjetoCasa();
        ObjetoCasa oAux2 = new ObjetoCasa();
        ObjetoCasa oAux3 = new ObjetoCasa();
        ObjetoCasa oAux4 = new ObjetoCasa();
        ObjetoCasa oAux5 = new ObjetoCasa();

        PistaTexto pAux1 = new PistaTexto();
        PistaTexto pAux2 = new PistaTexto();
        PistaTexto pAux3 = new PistaTexto();
        PistaTexto pAux4 = new PistaTexto();

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
                            Arma homicida: Desconocida
                            
                            Anotaciones:
                            La víctima fue encontrada sin vida colgando en su dormitorio; debajo de él se halló una 
                            silla tumbada, y todo apunta inicialmente a un suicidio. El Dr. Sanchez, de unos 75 años, 
                            era un hombre de complexión delgada y tenía antecedentes médicos relacionados con problemas 
                            de presión arterial y afecciones cardíacas.
                            
                            ───────────────────────────────────────────────────────────────────────────────
                            """);

                    System.out.println("Ingresas a la habitacion de la victima: ");
                    System.out.println("""
                            Te encuentras frente a una habitacion iluminada, la escena del crimen pertenece intacta esperando tu prescencia
                            sobre la parte derecha de se encuentra una cama con sus mesitas de luz, debajo de la luz que alumbra la habitacion
                            una silla caida descansa debajo de una soga colgada (De aqui descolgaron a la victima).
                            El resto de la habitacion esta adornada por un espejo de pie, un escritorio y un ropero bien mantenido
                            """);

                    while (!inventarioJugador.getListaElemento().contains(pAux1) || !inventarioJugador.getListaElemento().contains(oAux1)) {
                        int opcion;
                        System.out.println("1- Acercarse a observar la cama");
                        System.out.println("2- Tomar el testamento del escritorio");
                        System.out.println("3- Tomar el frasco de medicamentos de la mesita de luz");
                        System.out.println("4- Escuchar la confesion de Salomon Guzman (El rival eterno de tobias y vecino de la familia)");
                        opcion = teclado.nextInt();
                        switch (opcion) {
                            case 1:
                                System.out.println("""
                                        Al acercarte a la cama puedes notar como la alfombra esta movida y mal acomodada segun la sombra de polvo que la rodea,
                                        cuando la mueves revela una marca de arrastre de un tamaño grande, al seguirla notas que va desde la cama hasta el lugar donde
                                        la victima fue encontrada colgada
                                        """);
                                break;
                            case 2:
                                try {
                                    System.out.println("""
                                            Sobre el escritorio puedes notar un documento el cual dice en letras grandes "TESTAMENTO FAMILIA SANCHEZ"
                                            parece hecho hace relativamente poco
                                            """);
                                    pAux1 = (PistaTexto) habitaciones.buscarElemento("Habitación de Tobías Sanchez.").buscarElemento("Testamento Nuevo");
                                    inventarioJugador.agregarElemento(pAux1);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("Recogiste el testamento");
                                break;
                            case 3:
                                try {
                                    System.out.println("""
                                            Un frasco de medicamentos reposa tranquilamente al lado de una lampara sobre la mesita de luz.
                                            Posee el nombre: captopril (Recuerdas de un caso antiguo que son medicamentos para la presion arterial y el corazon)
                                            """);
                                    oAux1 = (ObjetoCasa) habitaciones.buscarElemento("Habitación de Tobías Sanchez.").buscarElemento("Frasco de Medicamentos");
                                    inventarioJugador.agregarElemento(oAux1);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("Recogiste el frasco");
                                break;
                            case 4:
                                System.out.println(confesionSalomon);
                                break;
                        }
                    }
                    System.out.println("A continuacion te diriges a la habitacion del mayordomo...");
                    jugadorActivo.setProgreso(20);
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    teclado.nextLine();
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        continuar = false;
                    } else {
                        progreso = 20;
                    }
                    break;
                case 20:
                    System.out.println("Habitacion del mayordomo");
                    System.out.println("""
                            Te adentras en una habitacion pequeña pero acogedora, esta muy bien organizada y ordenada
                            no posee cuadros ni pinturas, simplemente una cama y un pequeño mueble que funciona como vestidor y escritorio.
                            En una esquina de la habitacion se encuentra un zapatero con mocasines listos para ser usados, 
                            encima de este mismo una taquilla con una cerradura combinada guarda lo que parece ser un manojo de llaves
                            """);

                    while (!inventarioJugador.getListaElemento().contains(oAux2)) {
                        int opcion;
                        System.out.println("1- Acercarse a observar la cama");
                        System.out.println("2- Tomar las llaves de las habitaciones");
                        System.out.println("3- Escuchar la confesion del mayordomo");
                        opcion = teclado.nextInt();
                        switch (opcion) {
                            case 1:
                                System.out.println("""
                                        Una cama bien prolija y limpia descansa frente a ti esperando poder otorgar las respuestas que buscas pero que ella no las tiene
                                        """);
                                break;
                            case 2:
                                System.out.println("""
                                        Para obtener las llaves de las habitaciones que se encuentran en una taquilla, la cual se accede ingresando un numero de tres cifras en la cerradura combinada.
                                        El mayordomo no recuerda el codigo pero encima del zapatero ves un pequeño papel que dice: En el caso de olvidarse el codigo recordar que este mismo se obtiene
                                        calculando el cociente que resulta de elevar al cuadrado la cuarta parte de 100, para luego dividir el resultado en 5.
                                        """);
                                System.out.println("Ingrese el código");
                                int respuesta = teclado.nextInt();
                                while (respuesta != 125) {
                                    System.out.println("Acceso denegado, codigo incorrecto");
                                    System.out.println("Ingrese el código");
                                    respuesta = teclado.nextInt();
                                }
                                System.out.println("Se escucha un TAC de parte del candado");
                                try {
                                    oAux2 = (ObjetoCasa) habitaciones.buscarElemento("Habitación de Alfred.").buscarElemento("Llaves de las Habitaciones");
                                    inventarioJugador.agregarElemento(oAux2);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("Llaves de las habitaciones recogidas");
                                break;
                            case 3:
                                System.out.println(confesionAlfred);
                                break;
                        }
                    }
                    System.out.println("Continuas en la habitacion de matias...");
                    jugadorActivo.setProgreso(40);
                    guardarPartida();
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    teclado.nextLine();
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        continuar = false;
                    } else {
                        progreso = 40;
                    }
                    break;
                case 40:
                    System.out.println("Habitacion de matias");
                    System.out.println("""
                            Al entrar un fuerte olor a encierro y alcohol te golpea el olfato, estas viendo lo que supondrias 
                            que sucederia si tiraras una bomba en el lugar. Absolutamente todo en la habitacion esta tirado y desordenado
                            arriba de la mesa hay botellas y frascos vacios, sobre la cama ropa y cosas tiradas, la ventana esta rota
                            y el foco que cuelga sobre el panorama de vez en cuando falla haciendo que la habitacion caiga en una oscuridad momentanea 
                            """);

                    while (!inventarioJugador.getListaElemento().contains(oAux3) || !inventarioJugador.getListaElemento().contains(oAux4)) {
                        int opcion;
                        System.out.println("1- Revisar la cama");
                        System.out.println("2- Recoger frasco extraño");
                        System.out.println("3- ");
                        opcion = teclado.nextInt();
                        switch (opcion) {
                            case 1:
                                try {
                                    oAux3 = (ObjetoCasa) habitaciones.buscarElemento("Habitación de Matias Sanchez.").buscarElemento("Guantes de Mayordomo");
                                    inventarioJugador.agregarElemento(oAux3);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    oAux4 = (ObjetoCasa) habitaciones.buscarElemento("Habitación de Matias Sanchez.").buscarElemento("Frasco Extraño");
                                    inventarioJugador.agregarElemento(oAux4);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                break;
                        }
                    }
                    System.out.println("Seguiras en la habitacion de Mariana...");
                    jugadorActivo.setProgreso(60);
                    guardarPartida();
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    teclado.nextLine();
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        continuar = false;
                    } else {
                        progreso = 60;
                    }
                    break;
                case 60:
                    System.out.println("Habitacion de Mariana");
                    System.out.println("""
                            Te encuentras fentre a una habitación notablemente mas grande que la de su hermano. Al ingresar, se puede ver que todo esta 
                            organizado, el ambiente huele a esencia de vainilla, las paredes son de color rosa pastel como si de una princesa se tratara.
                            Lo unico que pareciera desentonar en el orden del lugar es una valija abierta encima de la cama, casi lista para partir. 
                            Arriba de la mesita de luz, a un costado de los perfumes importados, se pueden observan dos boletos de avión 
                            """);

                    while (!inventarioJugador.getListaElemento().contains(pAux2) || !inventarioJugador.getListaElemento().contains(oAux5)) {
                        int opcion;
                        System.out.println("1- Tomar boletos de avion");
                        System.out.println("2- Recoger la valija de Mariana");
                        opcion = teclado.nextInt();
                        switch (opcion) {
                            case 1:
                                try {
                                    pAux2 = (PistaTexto) habitaciones.buscarElemento("Habitación de Mariana Sanchez.").buscarElemento("Boletos de Avion");
                                    inventarioJugador.agregarElemento(pAux2);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    oAux5 = (ObjetoCasa) habitaciones.buscarElemento("Habitación de Mariana Sanchez.").buscarElemento("Valija Preparada");
                                    inventarioJugador.agregarElemento(oAux5);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                break;
                        }
                    }
                    System.out.println("Continuas en la habitacion de Ricardo y Juana...");
                    jugadorActivo.setProgreso(80);
                    guardarPartida();
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    teclado.nextLine();
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        continuar = false;
                    } else {
                        progreso = 80;
                    }
                    break;
                case 80:
                    System.out.println("Habitacion de Ricardo y Juana");
                    System.out.println("""
                            Por último, nos encontramos con la habitación matrimonial de Ricardo y Juana. Lo primero que se puede notar es una cama de dos plazas a medio hacer, 
                            lo que pareciera indicar que Juana pasa gran parte del día allí. En la mesita de luz de su lado, junto a una taza vacía y un vaso de agua, hay una pila de libros y revistas. 
                            Por otra parte, te llaman la atencion unos papeles en una silla junto al armario, 
                            """);

                    while (!inventarioJugador.getListaElemento().contains(pAux3) || !inventarioJugador.getListaElemento().contains(pAux4)) {
                        int opcion;
                        System.out.println("1- Mirar la pila de libros");
                        System.out.println("2- Tomar los papeles de la silla");
                        System.out.println("3- Observar la cama");
                        System.out.println("4- Escuchar las confesiones de Ricardo y Juana");
                        opcion = teclado.nextInt();
                        switch (opcion) {
                            case 1:
                                try {
                                    System.out.println("Al revisar los libros, encuentras un papel doblado y metido entre las páginas de una novela de misterio: " +
                                            "\nEs un Documento de Internación con fecha próxima a nombre de Tobías Sanchez en el geriatrico local “El Retoño”");
                                    pAux3 = (PistaTexto) habitaciones.buscarElemento("Habitación de Ricardo y Juana.").buscarElemento("Documento de Internacion");
                                    inventarioJugador.agregarElemento(pAux3);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    System.out.println("Al acercarte a la silla observas varios folletos de geriatricos de la zona, con diferentes nombres y direcciones." +
                                            "\nAlgunos estan marcados con diferentes mensajes como: Muy caro - Poco personal - Llamar para reservar visita");
                                    pAux4 = (PistaTexto) habitaciones.buscarElemento("Habitación de Ricardo y Juana.").buscarElemento("Folletos de Geriatricos");
                                    inventarioJugador.agregarElemento(pAux4);
                                    jugadorActivo.setInventario(inventarioJugador);
                                    guardarPartida();
                                } catch (ListaVaciaException | ElementoNoEncontradoException | ElementoNuloException |
                                         ElementoExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                System.out.println("");
                                break;
                            case 4:
                                System.out.println("Confesion de Ricardo: " + "\n" + confesionRicardo);
                                System.out.println("Confesion de Juana: " + "\n" + confesionJuana);
                                break;
                        }
                    }
                    System.out.println("Te queda el veredicto final, es hora de elegir un culpable");
                    int seleccion;
                    System.out.println("1- Culpar a Alfred (El Mayordomo)");
                    System.out.println("2- Culpar a Ricardo (El Hijo de Tobias)");
                    System.out.println("3- Culpar a Mariana (La Nieta de Tobias)");
                    System.out.println("4- Culpar a Matias (El Nieto de Tobias)");
                    System.out.println("5- Culpar a Juana (La Cuñada de Tobias)");
                    System.out.println("6- Culpar a Salomon (El vecino de la Familia)");
                    seleccion = teclado.nextInt();
                    switch (seleccion){
                        case 1:
                            System.out.println("""
                                    -Tu veredicto fue incorrecto-
                                    Alfred fue absuelto del asesinato, pero condenado por obstrucción a la justicia al encubrir al verdadero asesino. 
                                    Perdió su empleo y su hogar, muriendo solo meses después. Matías Sánchez quedó impune, heredó parte de la fortuna y vive con el tormento de su crimen. 
                                    El asesino real camina libre gracias a tu error.
                                    """);
                            break;
                        case 2:
                            System.out.println("""
                                    -Tu veredicto fue incorrecto-
                                    Ricardo Sánchez fue exonerado del asesinato, pero el escándalo de su detención y el plan de internar a su padre lo destruyó. 
                                    La junta directiva lo destituyó de la presidencia, y su esposa lo abandonó. Quedó como un accionista menor, arruinado y sin el poder que tanto deseaba. 
                                    El asesino real, Matías, se salió con la suya.
                                    """);
                            break;
                        case 3:
                            System.out.println("""
                                    -Tu veredicto fue incorrecto-
                                    Mariana Guzman fue declarada inocente. No obstante, su prometido la abandonó por el escándalo, y la familia la vio como una traidora. 
                                    Se quedó en la mansión, sola, sin amor y sin herencia completa. La favorita del abuelo pagó el precio de la sospecha, mientras el verdadero culpable sonreía.
                                    """);
                            break;
                        case 4:
                            System.out.println("""
                                    -Tu veredicto fue el correcto-
                                    Matias Rodriguez fue hallado culpable de homicidio en primer grado. Sentenciado a 25 años de prisión, Matías perdió la herencia. 
                                    Cumple su condena en una prisión estatal, sin visitas y atormentado por el fantasma de su abuelo. 
                                    Usted ha resuelto el Caso Nº 26062011.
                                    """);
                            break;
                        case 5:
                            System.out.println("""
                                    -Tu veredicto fue incorrecto-
                                    Juana Rodriguez fue absuelta. El juicio hizo pública su ambición y el desprecio de Tobias. 
                                    Fue repudiada socialmente, obligándola a vivir en un aislamiento total. Su matrimonio se volvió frío.
                                     Obtuvo la riqueza, pero perdió el estatus y la felicidad. El verdadero asesino no fue juzgado.
                                    """);
                            break;
                        case 6:
                            System.out.println("""
                                    -Tu veredicto fue incorrecto-
                                    Salomon Guzman fue declarado inocente por el asesinato. Sin embargo, el juicio expuso su infidelidad y el allanamiento. 
                                    Su esposa solicitó el divorcio y su empresa sufrió un colapso financiero por el escándalo. 
                                    Quedó arruinado y profesionalmente marginado. El asesinato de su rival nunca fue castigado.
                                    """);
                            break;
                    }
                    jugadorActivo.setProgreso(100);
                    guardarPartida();
                    System.out.println("Indique s si prefiere salir y guardar el progreso o n si desea continuar al siguiente nivel");
                    teclado.nextLine();
                    eleccion = teclado.nextLine().charAt(0);
                    if (eleccion == 's' || eleccion == 'S') {
                        continuar = false;
                    } else {
                        progreso = 100;
                    }
                    break;
                case 100:
                    System.out.println("Case 100");
                    guardarPartida();
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
        String archivo = jugadorActivo.getUsuario() + ".json";
        JsonUtiles.grabarUnJson(jsonArray, archivo);
    }

    public Jugador cargarPartida(String usuario) {
        try {

            HashSet<Jugador> jugadorAux = new HashSet<>();
            JSONArray array = new JSONArray(JsonUtiles.leerUnJson(usuario + ".json"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                jugadorAux.add(Jugador.toObject(jsonObject));
            }
            jugadores.setElementos(jugadorAux);
        } catch (JSONException e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (Jugador j : jugadores.getElementos()) {
            if (j.getUsuario().equalsIgnoreCase(usuario)) {
                return j;
            }
            System.out.println("Chau");
        }
        return null;
    }
}