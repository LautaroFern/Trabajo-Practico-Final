import Enums.RolPersonaje;
import Enums.TipoGenero;
import Exceptions.ElementoExistenteException;
import Exceptions.ElementoNuloException;
import Models.*;
import Collections.Casa;

void main() {
    Scanner teclado = new Scanner(System.in);
    String nombre, usuario, contrasena;

    //CREACION DE PERSONAJES:
    Personaje matias = new Personaje("Matias Sánchez", 22, TipoGenero.Masculino, RolPersonaje.Nieto);
    Personaje ricardo = new Personaje("Ricardo Sánchez", 57, TipoGenero.Masculino, RolPersonaje.Hijo);
    Personaje juana = new Personaje("Juana Rodriguez", 54, TipoGenero.Femenino, RolPersonaje.Esposa);
    Personaje alfred = new Personaje("Alfred", 65, TipoGenero.Masculino, RolPersonaje.Mayordomo);
    Personaje vecino = new Personaje("Sr. Guzman", 46, TipoGenero.Masculino, RolPersonaje.Vecino);
    Personaje mariana = new Personaje("Mariana Sánchez", 20, TipoGenero.Femenino, RolPersonaje.Nieta);
    Personaje tobias = new Personaje("Tobias Sánchez", 85, TipoGenero.Masculino, RolPersonaje.Padre);

    //CREACION DE LAS HABITACIONES:
    Habitacion habitacionTobias = new Habitacion("Habitación de Tobias Sánchez");
    Habitacion habitacionMatias = new Habitacion("Habitación de Matias Sánchez");
    Habitacion habitacionAlfed = new Habitacion("Habitación del mayordomo Alfred");
    Habitacion habitacionMariana = new Habitacion("Habitación de Mariana Sánchez");
    Habitacion habitacionPadres = new Habitacion("Habitación de Juana y Ricardo");

    //CREACION DE LA CASA
    Casa casa = new Casa<>();
    try {
        casa.agregarElemento(mariana);
        casa.agregarElemento(matias);
        casa.agregarElemento(juana);
        casa.agregarElemento(vecino);
        casa.agregarElemento(ricardo);
        casa.agregarElemento(alfred);
        casa.agregarElemento(tobias);
        casa.agregarElemento(habitacionAlfed);
        casa.agregarElemento(habitacionMariana);
        casa.agregarElemento(habitacionMatias);
        casa.agregarElemento(habitacionPadres);
        casa.agregarElemento(habitacionTobias);

    }catch (ElementoExistenteException | ElementoNuloException e){
        System.out.println(e.getMessage());
    }

    //CREANDO LAS PISTAS y OBJETOS:
    PistaTexto pistaHabitacionTobias1 = new PistaTexto("Testamento en la habitación de Tobias", "El testamento modificado en la habitación de Tobias que indicaba que su herencia no iba a pertenecer a Mariana");
    ObjetoCasa llaveHabitacionAlfred = new ObjetoCasa("Llave de la Habitación de Alfred", "Esta llave permite el acceso al dormitorio del mayordomo, sin ella no se puede acceder");
    PistaTexto pistaHabitacionMatias1 = new PistaTexto("Carta en la habitación de Matias", "Una carta en la habitación de Matias escrita a su pareja que demuestra desesperación: 'Si mi abuelo arruina esto todo termina para nosotros'");
    ObjetoCasa repdoructorMayordomo = new ObjetoCasa("Reproductor MP3 del maytordomo", "Un reproductor del mayordomo que grabo una discucion entre Matias y Tobias");
    PistaTexto pistaHabitacionTobias2 = new PistaTexto("Frasco vacío de medicina", "Un frasco en la mesita de luz de Tobias, que correspondia a la medicina que debia tomar, estaba vacío");

    //CREANDO EL INVENTARIO;



    System.out.println("Ingrese su nombre completo:");
    nombre = teclado.nextLine();
    System.out.println("Ingrese el nombre de usuario:");
    usuario = teclado.nextLine();
    System.out.println("Ingrese la contraseña:");
    contrasena = teclado.nextLine();

}