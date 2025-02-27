package classes;

public class Events {
    // Variables privadas
    private String nombre;
    private String fecha;
    private String ubicacion;
    private boolean online;
    private int maxUsers;
    private boolean cancelado;

    private Inscription[] inscriptions;
    private Users[] users;
    private Categoria categoria;
    private int numUsers;

    // Constructores
    public Events(String nombre) {
        this.nombre = nombre;
        this.users = new Users[10];
        this.inscriptions = new Inscription[10];
    }

    public Events(String nombre, String fecha, Categoria categoria) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.cancelado = false;
        this.numUsers = 0;
        this.maxUsers = 10;
        this.users = new Users[maxUsers];
        this.inscriptions = new Inscription[maxUsers];
        this.categoria = categoria;
        this.online = false;
    }

    public Events(String nombre, String fecha, Categoria categoria, int maxUsers) {
        this(nombre, fecha, categoria);
        this.users = new Users[maxUsers];
        this.inscriptions = new Inscription[maxUsers];
    }

    public Events(String nombre, String fecha, Categoria categoria, int maxUsers, String ubicacion) {
        this(nombre, fecha, categoria, maxUsers);
        this.ubicacion = ubicacion;
    }

    public Events(String nombre, String fecha, Categoria categoria, int maxUsers, String ubicacion, boolean online) {
        this(nombre, fecha, categoria, maxUsers, ubicacion);
            this.online = online;
    }

    // Métodos públicos
    public void RegistrarParticipante(Users participante) {
        // System.out.println("Intentando registrar al participante: " + participante.getNombre() + " en el evento: " + nombre);

        if (!cancelado) {
            if (numUsers < users.length) {
                for (int i = 0; i < users.length; i++) {
                    if (users[i] == null) {
                        users[i] = participante;
                        numUsers++;

                        Inscription inscription = new Inscription(participante, this);
                        // Puedes agregar la inscripción al array de inscriptions si es necesario
                        for (int j = 0; j < inscriptions.length; j++) {
                            if (inscriptions[j] == null) {
                                inscriptions[j] = inscription;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void CancelarRegistroParticipante(Users participante) {
        if (!cancelado) {
            for (int i = 0; i < users.length; i++) {
                if (users[i] != null && users[i].equals(participante)) {
                    users[i] = null;
                    numUsers--;

                    inscriptions[i] = null;

                    return;
                }
            }
        }
    }

    public void CancelarEvento() {
        System.out.println("Cancelando el evento: " + nombre);
        this.cancelado = true;

        // Cancelar inscripciones si es necesario
        for (Inscription inscription : inscriptions) {
            if (inscription != null) {
                inscription.Cancelar();
            }
        }

        System.out.println("El evento: " + nombre + " ha sido cancelado.");
    }

    public void MostrarEvento(Events this){
        System.out.println("---------------------------");
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Lugar: " + ubicacion);
        System.out.println("Capacidad: " + users.length + " personas");
        System.out.println("Online: " + online);
        System.out.println("---------------------------");
    }

    // Getters
    public boolean getCancelado() {
        return cancelado;
    }

    public String getNombre() {
        return nombre;
    }

    public Inscription[] getInscriptions() {
        return inscriptions;
    }

    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
