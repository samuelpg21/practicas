import classes.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Definimos los usuarios y organizadores maximos que puede tener el sistema
        final int MAX_USUARIOS = 100; // Usuarios maximos, Cambiar este valor segun las necesidades
        final int MAX_ORGANIZADORES = 20; // Organizadores maximos, Cambiar este valor segun las necesidades
        final int MAX_EVENTS = 100; // Eventos maximos, Cambiar este valor segun las necesidades

        // Declaramos los arrays necesarios para acceder a toda la informacion disponible de manera rapida sin recorrer cada objeto.
        // Array de usuarios
        Users[] users = new Users[MAX_USUARIOS];
        int contUsuarios = 0; // Contador para saber cuantos usuarios tenemos
        // Array de organizadores
        Organizadores[] organizadores = new Organizadores[MAX_ORGANIZADORES];
        int contOrganizadores = 0; // Contador para saber cuantos organizadores tenemos
        // Array de eventos
        Events[] eventos = new Events[MAX_EVENTS];
        int contEventos = 0; // Contador para saber cuantos eventos tenemos en total

        // Bucle que ejecutara siempre que el usuario no salga del programa
        while (true) {

            // Menu dentro de la consola
            System.out.println("Control: Portal de eventos sostenible");
            System.out.println("1: Registrar nuevo usuario");
            System.out.println("2: Iniciar sesion como usuario: ");
            System.out.println("3: Registrar nuevo organizador");
            System.out.println("4: iniciar sesion como organizador: ");
            System.out.println("5: Mostrar los usuarios creados");
            System.out.println("6: Mostrar los organizadores creados");
            System.out.println("7: Salir");
            System.out.print("Seleccione una opción: ");

            int option = sc.nextInt();
            sc.nextLine();

            // Opciones del menu
            switch (option) {
                case 1:

                    if (contUsuarios >= users.length){
                        System.out.println("No se pueden crear mas usuarios: Limite alcanzado");
                        break;
                    }

                    Users user;
                    System.out.println("Iniciando la creacion de usuario");
                    System.out.println("---");

                    System.out.println("Indique el nombre del usuario: ");
                    String nombreUsuario = sc.nextLine();

                    System.out.println("Indique el correo del usuario: ");
                    String correoUsuario = sc.nextLine();

                    System.out.println("Creando usuario...");
                    user = new Users(nombreUsuario, correoUsuario);
                    users[contUsuarios++] = user;

                    System.out.println("Usuario creado");
                    break;
                case 2:
                    System.out.println("Iniciando sesión como Usuario");
                    System.out.print("Ingrese su nombre: ");
                    String nombreLogin = sc.nextLine();
                    System.out.print("Ingrese su correo: ");
                    String correoLogin = sc.nextLine();

                    boolean loggedIn = false;
                    for (int i = 0; i < contUsuarios; i++) {
                        if (users[i].verificarCredenciales(nombreLogin, correoLogin)){
                            System.out.println("Inicio de sesion exitoso como usuario: " + nombreLogin);
                            loggedIn = true;


                            while (true) {

                                // Menu dentro de la consola
                                System.out.println("Control: Portal de eventos sostenible");
                                System.out.println("1: Inscribirse a un evento");
                                System.out.println("2: Cancelar inscripcion");
                                System.out.println("3: Mostrar eventos inscritos");
                                System.out.println("4: Salir");
                                System.out.print("Seleccione una opción: ");

                                option = sc.nextInt();
                                sc.nextLine();

                                Events evento;
                                Inscription inscription;

                                // Opciones del menu
                                switch (option) {
                                    case 1:
                                        if (hayEventos(eventos)){
                                            System.out.println("Estos son los eventos disponibles: ");
                                            MostrarEventos(eventos);

                                            System.out.print("Seleccione el número del evento para inscribirse: ");
                                            int eventoSeleccionado = sc.nextInt();
                                            sc.nextLine();

                                            if (eventoSeleccionado > 0 && eventoSeleccionado <= contEventos) {
                                                evento = eventos[eventoSeleccionado - 1];
                                                inscription = new Inscription(users[i], evento);
                                                inscription.Inscribir();
                                            }
                                        } else {
                                            System.out.println("No hay eventos disponibles");
                                        }

                                        break;
                                    case 2:
                                        System.out.println("Estos son los eventos a los que estas inscritos");
                                        users[i].MostrarEventos();
                                        System.out.print("Seleccione el número del evento para cancelar la inscripcion: ");
                                        int eventoSeleccionado = sc.nextInt();
                                        sc.nextLine();

                                        if (eventoSeleccionado > 0 && eventoSeleccionado <= contEventos) {
                                            evento = eventos[eventoSeleccionado - 1];
                                            inscription = new Inscription(users[i], evento);
                                            inscription.Cancelar();
                                        }
                                        break;
                                    case 3:
                                        users[i].MostrarEventos();
                                        break;
                                    case 4:
                                        System.out.println("Saliendo del programa");
                                        break;
                                    default:
                                        System.out.println("Opcion invalida, intente de nuevo");
                                        break;
                                }
                                if (option == 4){
                                    break;
                                }
                            }
                        }
                    }

                    break;
                case 3:
                    Organizadores organizador = null;

                    if (contOrganizadores >= organizadores.length){
                        System.out.println("No se pueden crear mas organizadores: Limite alcanzado");
                        break;
                    }

                    System.out.println("Iniciando la creacion del organizador");
                    System.out.println("---");

                    System.out.println("Indique el nombre del organizador: ");
                    String nombreOrganizador = sc.nextLine();

                    System.out.println("Indique el correo del organizador: ");
                    String correoOrganizador = sc.nextLine();

                    System.out.println("Indique el número de teléfono (o presione Enter para omitir): ");
                    String telefonoInput = sc.nextLine();
                    System.out.println("Creando organizador...");

                    if (telefonoInput.isEmpty()) {
                        organizador = new Organizadores(nombreOrganizador, correoOrganizador);
                    } else {
                        int telefonoOrganizador = Integer.parseInt(telefonoInput);
                        organizador = new Organizadores(nombreOrganizador, correoOrganizador, telefonoOrganizador);
                    }
                    organizadores[contOrganizadores++] = organizador;

                    System.out.println("Organizador creado");
                    break;

                case 4:
                    System.out.println("Iniciando sesión como Organizador");
                    System.out.print("Ingrese su nombre: ");
                    nombreLogin = sc.nextLine();
                    System.out.print("Ingrese su correo: ");
                    correoLogin = sc.nextLine();

                    loggedIn = false;
                    for (int i = 0; i < contOrganizadores; i++) {
                        if (organizadores[i].verificarCredenciales(nombreLogin, correoLogin)) {
                            organizador = organizadores[i];
                            System.out.println("Inicio de sesion exitoso como Organizador: " + nombreLogin);
                            loggedIn = true;

                            while (true) {

                                // Menu dentro de la consola
                                System.out.println("Control: Portal de eventos sostenible");
                                System.out.println("1: Crear un evento");
                                System.out.println("2: Cancelar evento");
                                System.out.println("3: Mostrar eventos");
                                System.out.println("4: Modificar eventos");
                                System.out.println("5: Crear nueva categoria");
                                System.out.println("6: Eliminar categoria");
                                System.out.println("7: Mostrar categorias");
                                System.out.println("0: Salir");
                                System.out.print("Seleccione una opción: ");

                                option = sc.nextInt();
                                sc.nextLine();

                                Inscription inscripcion = null;
                                // Opciones del menu
                                switch (option) {
                                    case 1:
                                        if (organizador != null){
                                            System.out.println("Creando evento...");
                                            organizador.CrearEvento();
                                        } else {
                                            System.out.println("Organizador es nulo, no se pueden crear eventos");
                                        }
                                        break;
                                    case 2:
                                        organizador.CancelarEvento();
                                        break;
                                    case 3:
                                        MostrarEventos(eventos);
                                        break;
                                    case 4:
                                        if (hayEventos(eventos)){
                                            organizador.ModificarEvento();
                                        } else {
                                            System.out.println("No hay eventos a modificar");
                                        }
                                        break;
                                    case 5:
                                        organizador.crearCategoria();
                                        break;
                                    case 6:
                                        organizador.eliminarCategoria();
                                        break;
                                    case 7:
                                        System.out.println("Las categorias actuales son: ");
                                        organizador.mostrarCategorias();
                                        break;
                                    case 0:
                                        System.out.println("Saliendo del programa");
                                        break;
                                    default:
                                        System.out.println("Opcion invalida, intente de nuevo");
                                        break;
                                }
                                if (option == 0){
                                    break;
                                }
                            }

                        }
                    }
                    break;
                case 5:
                    MostrarUsuarios(users);
                    break;

                case 6:
                    MostrarOrganizadores(organizadores);
                    break;

                case 7:
                    System.out.println("Saliendo del programa.");
                    sc.close(); // Cerrar el escáner
                    return;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }
    }

    public static void MostrarUsuarios(Users[] users) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                System.out.println("Usuario " + (i + 1) + ":");
                System.out.println("Nombre: " + users[i].getNombre());
                System.out.println("Correo: " + users[i].getCorreo());
            }
        }
    }

    public static void MostrarOrganizadores(Organizadores[] organizadores) {
        for (int i = 0; i < organizadores.length; i++) {
            if (organizadores[i] != null) {
                System.out.println("Organizador " + (i + 1) + ":");
                System.out.println("Nombre: " + organizadores[i].getNombre());
                System.out.println("Correo: " + organizadores[i].getCorreo());
                System.out.println("Telefono: " + organizadores[i].getTelefono());
            }
        }
    }

    public static void MostrarEventos(Events[] events){

        for (int i = 0; i < events.length; i++) {
            if (events[i] != null) {
                System.out.println("Evento " + (i + 1) + ":");
                events[i].MostrarEvento();
            }
        }
    }

    public static void MostrarCategorias(Categoria[] categorias){
        for (int i = 0; i < categorias.length; i++) {
            if (categorias[i] != null) {
                System.out.println( i + 1 + ". " + categorias[i].getNombre() + " - " + categorias[i].getDescripcion());
            }
        }
    }

    public static boolean hayEventos(Events[] events) {
        for (Events event : events) {
            if (event != null) {
                return true; // Hay al menos un evento
            }
        }
        return false; // No hay eventos
    }

}