package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Persona{

    private String usuario;
    private String contrasenia;
    private ArrayList<Incidencia> incidencias = new ArrayList<>();

    public Administrador(String usuario, String contrasenia) {

        this.usuario = usuario;
        this.contrasenia = contrasenia;

    }
    public void registrarIncidencia(Incidencia incidencia) {
        if (incidencia == null) {
            throw new IllegalArgumentException("La incidencia no puede ser nula.");
        }
        incidencias.add(incidencia);
        System.out.println("Incidencia registrada: " + incidencia);
    }

    public List<Incidencia> consultarIncidencias() {
        return incidencias;
    }
    public void mostrarIncidencias() {
        if (incidencias.isEmpty()) {
            System.out.println("No hay incidencias registradas.");
            return;
        }
        for (Incidencia i : incidencias) {
            System.out.println(i);
        }
    }
    public void publicarEvento(Evento evento) {

        evento.publicarEvento();

        System.out.println("Evento publicado");
    }

    public void pausarEvento(Evento evento) {

        evento.pausarEvento();

        System.out.println("Evento pausado");
    }

    public void cancelarEvento(Evento evento) {

        evento.cancelarEvento();

        System.out.println("Evento cancelado");
    }
    public void finalizaEvento(Evento evento) {

        evento.finalizarEvento();

        System.out.println("Evento finalizado");
    }
    public void cancelarCompra(Compra compra) {

        compra.cancelarCompra();

        System.out.println("Compra cancelada");
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
