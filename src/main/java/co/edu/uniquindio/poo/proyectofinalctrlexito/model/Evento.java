package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Evento implements Subject, Visitor{

    private String idEvento;
    private String nombre;
    private String categoria;
    private String descripcion;
    private Date fecha;
    private EstadoEvento estado;
    private List<Usuario> usuarios;
    private List<Observer> observers;

    public Evento(String idEvento, String nombre, String categoria, String descripcion) {

        this.idEvento = idEvento;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = new Date();
        this.usuarios=new ArrayList<>();
        this.observers = new ArrayList<>();
        this.estado= EstadoEvento.BORRADOR;
    }

    //Metodo para actualizar los datos del evento
    public void actualizarEvento(String nombre, String categoria, String descripcion, Date fecha) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    //Metodo para poner en estado de finalizado
    public void finalizarEvento() {
        this.estado = EstadoEvento.FINALIZADO;
        notificarObservers("El evento " + nombre +" ha finalizado"        );
    }
    //Metodo para poner en estado de publicado
    public void publicarEvento() {
        this.estado = EstadoEvento.PUBLICADO;
        notificarObservers("El evento " + nombre +" fue publicado");
    }

    //Metodo para poner en estado de pausado
    public void pausarEvento() {
        this.estado = EstadoEvento.PAUSADO;
        notificarObservers("El evento " + nombre +" ha sido pausado");
    }

    //Metodo para poner en estado de cancelado
    public void cancelarEvento() {
        this.estado = EstadoEvento.CANCELADO;
        notificarObservers("El evento " + nombre +" ha sido cancelado");
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
    public void agregarUsuario(Usuario usuario) {

        usuarios.add(usuario);

        agregarObserver(usuario);
    }
    public void eliminarUsuario(Usuario usuario) {

        usuarios.remove(usuario);

        eliminarObserver(usuario);
    }
    @Override
    public void agregarObserver(Observer observer) {

        observers.add(observer);
    }

    @Override
    public void eliminarObserver(Observer observer) {

        observers.remove(observer);
    }

    @Override
    public void notificarObservers(String mensaje) {

        for (Observer observer : observers) {

            observer.actualizar(mensaje);
        }
    }

    //Metodo complementario al patron Visitor
    @Override
    public void aceptarVisitante(ReporteVisitor visitor){
        visitor.visitarEvento(this);
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
    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
