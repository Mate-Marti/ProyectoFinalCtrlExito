package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Persona {

    private String usuario;
    private String contrasenia;
    private final Plataforma plataforma = Plataforma.getInstancia();

    public Administrador(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public void publicarEvento(Evento evento) {
        evento.publicarEvento();
        notificarTodosLosUsuarios("📢 El evento \"" + evento.getNombre() + "\" ha sido PUBLICADO.");
        System.out.println("Evento publicado");
    }

    public void pausarEvento(Evento evento) {
        evento.pausarEvento();
        notificarTodosLosUsuarios("⏸ El evento \"" + evento.getNombre() + "\" ha sido PAUSADO.");
        System.out.println("Evento pausado");
    }

    public void cancelarEvento(Evento evento) {
        evento.cancelarEvento();
        notificarTodosLosUsuarios("❌ El evento \"" + evento.getNombre() + "\" ha sido CANCELADO.");
        System.out.println("Evento cancelado");
    }

    public void finalizaEvento(Evento evento) {
        evento.finalizarEvento();
        notificarTodosLosUsuarios("✅ El evento \"" + evento.getNombre() + "\" ha FINALIZADO.");
        System.out.println("Evento finalizado");
    }

    public void cancelarCompra(Compra compra) {
        compra.cancelarCompra();
        System.out.println("Compra cancelada");
    }

    private void notificarTodosLosUsuarios(String mensaje) {
        List<Persona> personas = plataforma.getListaPersonas();
        if (personas == null) return;
        for (Persona p : personas) {
            if (p instanceof Usuario) {
                GestorNotificaciones.getInstancia()
                        .agregarNotificacion(((Usuario) p).getId(), mensaje);
            }
        }
    }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
}
