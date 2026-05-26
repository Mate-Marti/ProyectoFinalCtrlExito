package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public abstract class EventoFactory {
    /**
     * Crea un nuevo evento con los datos proporcionados.
     *
     * @param id identificador del evento.
     * @param nombre nombre del evento.
     * @param descripcion descripción del evento.
     * @param fecha fecha programada del evento.
     * @return nuevo objeto de tipo Evento.
     */
    public abstract Evento crearEvento(int id, String nombre, String descripcion, Date fecha);

    /**
     * Constructor por defecto de la fábrica de eventos.
     */
    public EventoFactory() {}
}
