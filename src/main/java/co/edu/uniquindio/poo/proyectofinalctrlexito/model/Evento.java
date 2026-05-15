package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class Evento {

    private String idEvento;
    private String nombre;
    private String categoria;
    private String descripcion;
    private Date fecha;
    private EstadoEvento estado;

    public Evento(String idEvento, String nombre, String categoria, String descripcion, Date fecha, EstadoEvento estado) {

        this.idEvento = idEvento;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = new Date();
        this.estado = estado;

    }

    public Evento(String idEvento, String nombre, String categoria, String descripcion, String estado) {
    }

    //Metodo para actualizar los datos del evento
    public void actualizareEvento(String nombre, String categoria, String descripcion, Date fecha) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    //Metodo para poner en estado de publicado
    public void publicarEvento() {
        this.estado = EstadoEvento.PUBLICADO;
    }

    //Metodo para poner en estado de pausado
    public void pausarEvento() {
        this.estado = EstadoEvento.PAUSADO;
    }

    //Metodo para poner en estado de cancelado
    public void cancelarEvento() {
        this.estado = EstadoEvento.CANCELADO;
    }

    //metodo para validar si el evento esta disponible
    public boolean validarEventoDisponible() {
        return this.estado == EstadoEvento.PUBLICADO;
    }

    //metodo para obtener losdetalles del evento
    public String obtenerDetalleEvento() {
        return "Evento: " + nombre + " [" + categoria + "]\n" +
                "Descripción: " + descripcion + "\n" +
                "Fecha: " + fecha + "\n" +
                "Estado actual: " + estado;
    }

    //getters y seters
    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoEvento getEstado() {
        return estado;
    }

    public void setEstado(EstadoEvento estado) {
        this.estado = estado;
    }
}
