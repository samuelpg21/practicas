package classes;

import java.util.Scanner;

public class Organizadores {
    private String nombre;
    private String correo;
    private int telefono;

    private Categoria[] categorias = new Categoria[20];
    private int contCategorias = 0;
    private Events[] eventosCreados;
    private int contadorEventos = 0;

    Events evento;

    public Organizadores(String nombre){
        this.nombre = nombre;
        this.correo = null;
        this.telefono = 0;
        eventosCreados = new Events[10];

    }
    public Organizadores(String nombre, String correo){
        this(nombre);
        this.correo = correo;
    }
    public Organizadores(String nombre, String correo, int telefono){
        this(nombre, correo);
        this.telefono = telefono;
    }

    // Eventos

    // Metodo encargado de crear eventos teniendo en cuenta todos los casos posibles
    public void CrearEvento(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Iniciando la creacion del Evento");
        System.out.println("---");

        System.out.println("Indique el nombre del evento: ");
        String nombreEvento = sc.nextLine();

        System.out.println("Indique la fecha del evento (Formato recomendado DD-MM-AAAA): ");
        String fechaEvento = sc.nextLine();

        System.out.println("A que categoria pertenece el evento: ");
        mostrarCategorias();
        int categoria = sc.nextInt() - 1;
        sc.nextLine();

        System.out.println("Indique el número de usuarios maximos permitidos. (Valor por defecto 10)");
        String maxUsers = sc.nextLine();

        System.out.println("Indique la ubicacion del evento: ");
        String ubicacionEvento = sc.nextLine();

        System.out.println("Indique si el evento sera online o no. (Valor por defecto: False)");
        String online = sc.nextLine();


        System.out.println("Creando evento...");

        if (maxUsers.isEmpty()){
            evento = new Events(nombreEvento, fechaEvento, categorias[categoria]);
        } else if (ubicacionEvento.isEmpty()){
            int maxUsersInt = Integer.parseInt(maxUsers);
            evento = new Events(nombreEvento, fechaEvento, categorias[categoria], maxUsersInt);
        } else if (online.isEmpty()) {
            int maxUsersInt = Integer.parseInt(maxUsers);
            evento = new Events(nombreEvento, fechaEvento, categorias[categoria], maxUsersInt, ubicacionEvento);
        } else {
            int maxUsersInt = Integer.parseInt(maxUsers);
            boolean isOnline = Boolean.parseBoolean(online);
            evento = new Events(nombreEvento, fechaEvento, categorias[categoria], maxUsersInt, ubicacionEvento, isOnline);
        }

        eventosCreados[contadorEventos++] = evento;
        evento.MostrarEvento();
    }
    // Metodo encargado de cancelar el evento
    public void CancelarEvento(){
        evento.CancelarEvento();
        System.out.println("El evento ha sido cancelado");

        for (int i = 0; i < eventosCreados.length; i++) {
            if (eventosCreados[i].equals(evento)){
                eventosCreados[i] = null;
                contadorEventos--;
                return;
            }
        }
    }
    // Metodo que permite la modificacion de cada atributo del evento
    public void ModificarEvento(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona el evento que quieres modificar");
        MostrarEventosCreados();
        int option = sc.nextInt() - 1;
        sc.nextLine();

        System.out.println("Modificar Nombre");
        String nombreEvento = sc.nextLine();

        System.out.println("Modificar Fecha");
        String fechaEvento = sc.nextLine();

        System.out.println("Modificar usuarios maximos");
        String maxUsers = sc.nextLine();

        System.out.println("Modificar ubicacion");
        String ubicacionEvento = sc.nextLine();

        System.out.println("Modificar si es online");
        String online = sc.nextLine();


        if (!nombreEvento.isEmpty()){
            eventosCreados[option].setNombre(nombreEvento);
        }
        if (!fechaEvento.isEmpty()) {
            eventosCreados[option].setFecha(fechaEvento);
        }
        if (!maxUsers.isEmpty()){
            int maxUsersInt = Integer.parseInt(maxUsers);
            eventosCreados[option].setMaxUsers(maxUsersInt);
        }
        if (!ubicacionEvento.isEmpty()){
            eventosCreados[option].setUbicacion(ubicacionEvento);
        }
        if (!online.isEmpty()){
            boolean isOnline = Boolean.parseBoolean(online);
            eventosCreados[option].setOnline(isOnline);
        }
    }
    // Metodo que imprime todos los eventos creados
    public void MostrarEventosCreados(){
        System.out.println("Los eventos creados por " + nombre + ": ");
        for (int i = 0; i < eventosCreados.length; i++) {
            if (eventosCreados[i] != null) {
                System.out.println("Evento " + (i + 1) + ": ");
                eventosCreados[i].MostrarEvento();
                System.out.println();
            }
        }
    }


    // Categorias

    // Metodo de creacion de categorias
    public void crearCategoria(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Iniciadno la creacion de la categoria");
        System.out.println("---");

        System.out.println("Introduzca el nombre de la categoria");
        String nombreCategoria = sc.nextLine();

        System.out.println("Introduzca la descripcion de la categoria");
        String descripcionCategoria = sc.nextLine();

        Categoria categoria = new Categoria(nombreCategoria, descripcionCategoria);
        contCategorias++;

        for (int i = 0; i < contCategorias; i++) {
            if (categorias[i] == null) {
                System.out.println("Agregando categoria");
                categorias[i] = categoria;
                System.out.println("Categoria añadida");
                break;
            }
        }
    }
    // Metodo de eliminacion de categorias
    public void eliminarCategoria(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona con los numeros la categoria a eliminar");
        mostrarCategorias();
        int option = sc.nextInt() - 1;

        categorias[option] = null;
        contCategorias--;

    }
    // Metodo que se encarga de mostrar las categorias
    public void mostrarCategorias(){
        for (int i = 0; i < contCategorias; i++) {
            System.out.println(i + 1 + ". " + categorias[i].getNombre());
        }
    }


    // Este metodo comprueba los datos para acceder al organizador correspondiente
    public boolean verificarCredenciales(String nombre, String correo) {
        return this.nombre.equals(nombre) && this.correo.equals(correo);
    }

    public String getNombre() {
        return nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public int getTelefono() {
        return telefono;
    }
}
