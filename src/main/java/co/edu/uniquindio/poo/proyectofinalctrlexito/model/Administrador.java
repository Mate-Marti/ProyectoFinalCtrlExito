package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Persona{

    private String usuario;
    private String contrasenia;

    public Administrador(String usuario, String contrasenia) {

        this.usuario = usuario;
        this.contrasenia = contrasenia;

    }

    /**
     * Muestra por consola todas las incidencias registradas.
     * Si no hay incidencias, imprime un mensaje.
     */
    public void publicarEvento(Evento evento) {

        evento.publicarEvento();

        System.out.println("Evento publicado");
    }
    /**
     * Pausa un evento activo en el sistema.
     * Cambia el estado del evento a PAUSADO y notifica a los observers.
     *
     * @param evento Evento a pausar. No puede ser nulo.
     */
    public void pausarEvento(Evento evento) {

        evento.pausarEvento();

        System.out.println("Evento pausado");
    }
    /**
     * Cancela un evento en el sistema.
     * Cambia el estado del evento a CANCELADO y notifica a los observers.
     *
     * @param evento Evento a cancelar. No puede ser nulo.
     */
    public void cancelarEvento(Evento evento) {

        evento.cancelarEvento();

        System.out.println("Evento cancelado");
    }
    /**
     * Finaliza un evento en el sistema.
     * Cambia el estado del evento a FINALIZADO y notifica a los observers.
     *
     * @param evento Evento a finalizar. No puede ser nulo.
     */
    public void finalizaEvento(Evento evento) {

        evento.finalizarEvento();

        System.out.println("Evento finalizado");
    }
    /**
     * Cancela una compra en el sistema.
     * Cambia el estado de la compra a CANCELADA segun las politicas definidas.
     *
     * @param compra Compra a cancelar. No puede ser nula.
     */
    public void cancelarCompra(Compra compra) {

        compra.cancelarCompra();

        System.out.println("Compra cancelada");
    }
    /**
     * Retorna el nombre de usuario de la cuenta.
     *
     * @return usuario como String.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Asigna el nombre de usuario de la cuenta.
     *
     * @param usuario Nuevo nombre de usuario. No puede ser nulo ni vacio.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna la contrasenia de la cuenta.
     *
     * @return contrasenia como String.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Asigna la contrasenia de la cuenta.
     *
     * @param contrasenia Nueva contrasenia. No puede ser nula ni vacia.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
