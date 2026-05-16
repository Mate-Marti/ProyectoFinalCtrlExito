package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;

public class Administrador extends Persona{

    private String usuario;
    private String contrasenia;
    private ArrayList<Incidencia> incidencias = new ArrayList<>();

    public Administrador(String usuario, String contrasenia) {

        this.usuario = usuario;
        this.contrasenia = contrasenia;

    }
    public void registrarIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
    }

    public ArrayList<Incidencia> consultarIncidencias() {
        return incidencias;
    }
    public void mostrarIncidencias() {
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
