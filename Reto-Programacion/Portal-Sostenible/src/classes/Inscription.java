package classes;

public class Inscription {
    Users user;
    Events event;

    public Inscription(Users user, Events event) {
        this.user = user;
        this.event = event;
    }

    public void Inscribir() {
        System.out.println("Intentando inscribir al usuario: " + user.getNombre() + " al evento: " + event.getNombre());

        if (!event.getCancelado()) {
            System.out.println("El evento no está cancelado. Procediendo con la inscripción...");
            user.Inscripcion(event);
            event.RegistrarParticipante(user);
            System.out.println("Inscripción exitosa para el usuario: " + user.getNombre());
        } else {
            System.out.println("No se puede inscribir al usuario: " + user.getNombre() + " porque el evento está cancelado.");
        }
    }

    public void Cancelar() {
        System.out.println("Intentando cancelar la inscripción del usuario: " + user.getNombre() + " del evento: " + event.getNombre());
        user.CancelarInscripcion(event);
        event.CancelarRegistroParticipante(user);
        System.out.println("Inscripción cancelada para el usuario: " + user.getNombre());
    }
}
