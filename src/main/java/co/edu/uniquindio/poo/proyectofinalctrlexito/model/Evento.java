package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Evento implements Subject, Visitor {

    private String idEvento;
    private String nombre;
    private String categoria;
    private String descripcion;
    private Date fecha;
    private EstadoEvento estado;
    private List<Usuario> usuarios;
    private List<Observer> observers;
    // NUEVO: Atributo de relación con un único Recinto
    private Recinto recinto;

    /**
     * Crea un nuevo evento con su información básica,
     * asigna la fecha actual, inicializa las listas de usuarios
     * y observadores, y establece el estado inicial en BORRADOR.
     *
     * @param idEvento identificador único del evento.
     * @param nombre nombre del evento.
     * @param categoria categoría a la que pertenece el evento.
     * @param descripcion descripción del evento.
     */
    public Evento(String idEvento, String nombre, String categoria, String descripcion) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = new Date();
        this.usuarios = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.estado = EstadoEvento.BORRADOR;
        // El constructor permanece intacto, no se añade el parámetro ni se inicializa aquí.
    }

    /**
     * Actualiza la información principal del evento,
     * incluyendo nombre, categoría, descripción y fecha.
     *
     * @param nombre nuevo nombre del evento.
     * @param categoria nueva categoría del evento.
     * @param descripcion nueva descripción del evento.
     * @param fecha nueva fecha del evento.
     */
    public void actualizarEvento(String nombre, String categoria, String descripcion, Date fecha) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    /**
     * Cambia el estado del evento a FINALIZADO
     * y notifica a los observadores sobre este cambio.
     */
    public void finalizarEvento() {
        this.estado = EstadoEvento.FINALIZADO;
        notificarObservers("El evento " + nombre + " ha finalizado");
    }

    /**
     * Cambia el estado del evento a PUBLICADO
     * y notifica a los observadores sobre este cambio.
     */
    public void publicarEvento() {
        this.estado = EstadoEvento.PUBLICADO;
        notificarObservers("El evento " + nombre + " fue publicado");
    }

    /**
     * Cambia el estado del evento a PAUSADO
     * y notifica a los observadores sobre este cambio.
     */
    public void pausarEvento() {
        this.estado = EstadoEvento.PAUSADO;
        notificarObservers("El evento " + nombre + " ha sido pausado");
    }

    /**
     * Cambia el estado del evento a CANCELADO
     * y notifica a los observadores sobre este cambio.
     */
    public void cancelarEvento() {
        this.estado = EstadoEvento.CANCELADO;
        notificarObservers("El evento " + nombre + " ha sido cancelado");
    }

    /**
     * Verifica si el evento se encuentra disponible
     * para los usuarios.
     *
     * @return true si el evento está publicado, false en caso contrario.
     */
    public boolean validarEventoDisponible() {
        return this.estado == EstadoEvento.PUBLICADO;
    }

    /**
     * Obtiene la información detallada del evento,
     * incluyendo nombre, categoría, descripción,
     * fecha y estado actual.
     *
     * @return cadena con los detalles del evento.
     */
    public String obtenerDetalleEvento() {
        return "Evento: " + nombre + " [" + categoria + "]\n" +
                "Descripción: " + descripcion + "\n" +
                "Fecha: " + fecha + "\n" +
                "Estado actual: " + estado;
    }

    /**
     * Agrega un usuario al evento y lo registra
     * como observador para recibir notificaciones.
     *
     * @param usuario usuario que será agregado al evento.
     */
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        agregarObserver(usuario);
    }

    /**
     * Elimina un usuario del evento y lo remueve
     * de la lista de observadores.
     *
     * @param usuario usuario que será eliminado del evento.
     */
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        eliminarObserver(usuario);
    }
    /**
     * Agrega un observador a la lista de observadores
     * para que reciba notificaciones sobre cambios en el evento.
     *
     * @param observer observador que será agregado.
     */
    @Override
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Elimina un observador de la lista de observadores
     * para que deje de recibir notificaciones del evento.
     *
     * @param observer observador que será eliminado.
     */
    @Override
    public void eliminarObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Envía un mensaje de notificación a todos
     * los observadores registrados en el evento.
     *
     * @param mensaje mensaje que será enviado a los observadores.
     */
    @Override
    public void notificarObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.actualizar(mensaje);
        }
    }

    /**
     * Permite que un visitante acceda a la información
     * del evento aplicando el patrón Visitor.
     *
     * @param visitor visitante que realizará una operación sobre el evento.
     */
    @Override
    public void aceptarVisitante(ReporteVisitor visitor){
        visitor.visitarEvento(this);
    }
    /**
     * Obtiene el identificador del evento.
     *
     * @return identificador del evento.
     */
    public String getIdEvento() {
        return idEvento;
    }

    /**
     * Asigna un nuevo identificador al evento.
     *
     * @param idEvento nuevo identificador del evento.
     */
    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @return nombre del evento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nuevo nombre al evento.
     *
     * @param nombre nuevo nombre del evento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la categoría del evento.
     *
     * @return categoría del evento.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Asigna una nueva categoría al evento.
     *
     * @param categoria nueva categoría del evento.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la descripción del evento.
     *
     * @return descripción del evento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna una nueva descripción al evento.
     *
     * @param descripcion nueva descripción del evento.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha del evento.
     *
     * @return fecha del evento.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Asigna una nueva fecha al evento.
     *
     * @param fecha nueva fecha del evento.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado actual del evento.
     *
     * @return estado actual del evento.
     */
    public EstadoEvento getEstado() {
        return estado;
    }

    /**
     * Asigna un nuevo estado al evento.
     *
     * @param estado nuevo estado del evento.
     */
    public void setEstado(EstadoEvento estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la lista de observadores registrados.
     *
     * @return lista de observadores del evento.
     */
    public List<Observer> getObservers() {
        return observers;
    }

    /**
     * Asigna una nueva lista de observadores al evento.
     *
     * @param observers nueva lista de observadores.
     */
    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    /**
     * Obtiene la lista de usuarios asociados al evento.
     *
     * @return lista de usuarios del evento.
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Asigna una nueva lista de usuarios al evento.
     *
     * @param usuarios nueva lista de usuarios.
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    // NUEVO: Getter y Setter para el recinto del evento
    public Recinto getRecinto() {
        return recinto;
    }

    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }
}