package classes;

public class Users {

    private String nombre;
    private String correo;
    private Events eventos[];

    private boolean libre = true;

    // Constructores
    public Users(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        eventos = new Events[5];
    }

    public void Inscripcion(Events evento) {
        // System.out.println("Intentando inscribir al usuario: " + nombre + " en el evento: " + evento.getNombre());

        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] == null) {
                eventos[i] = evento;
                // System.out.println("Inscripción exitosa para el usuario: " + nombre + " en el evento: " + evento.getNombre());
                return;
            }
        }

        // System.out.println("No se pudo inscribir al usuario: " + nombre + ", ya está inscrito en el máximo de eventos permitidos.");
    }

    public void CancelarInscripcion(Events evento) {
        // System.out.println("Intentando cancelar la inscripción del usuario: " + nombre + " en el evento: " + evento.getNombre());

        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null && eventos[i].equals(evento)) {
                eventos[i] = null;
                // System.out.println("Inscripción cancelada para el usuario: " + nombre + " en el evento: " + evento.getNombre());
                return;
            }
        }

        // System.out.println("No se encontró la inscripción del usuario: " + nombre + " en el evento: " + evento.getNombre());
    }
    public void MostrarEventos(){
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null) {
                System.out.println("Evento " + (i + 1) + ":");
                eventos[i].MostrarEvento();
            }
        }
    }

    public boolean verificarCredenciales(String nombre, String correo) {
        return this.nombre.equals(nombre) && this.correo.equals(correo);
    }

    public String getNombre() {
        return nombre;
    }
    public String getCorreo() {
        return correo;
    }
}
