package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class ConciertoFactory extends EventoFactory {
    /**
     * Crea un nuevo evento de tipo concierto.
     * Asigna automaticamente la categoria "Concierto".
     *
     * @param id          Identificador numerico del evento.
     * @param nombre      Nombre del concierto. No puede ser nulo ni vacio.
     * @param descripcion Descripcion del concierto. No puede ser nula ni vacia.
     * @param fecha       Fecha de realizacion del evento. No puede ser nula.
     * @return Nueva instancia de ConciertoEvento.
     */
    @Override
    public Evento crearEvento(int id, String nombre,
                              String descripcion, Date fecha) {
        return new ConciertoEvento(
                String.valueOf(id),
                nombre,
                "Concierto",
                descripcion
        );
    }

}
