package co.edu.uniquindio.poo.proyectofinalctrlexito.model;


public class ConciertoEvento extends Evento {
    /**
     * Constructor de ConciertoEvento.
     * Crea un evento de tipo concierto con los datos basicos heredados de Evento.
     *
     * @param idEvento    Identificador unico del evento. No puede ser nulo.
     * @param nombre      Nombre del concierto. No puede ser nulo ni vacio.
     * @param categoria   Categoria del evento. No puede ser nula ni vacia.
     * @param descripcion Descripcion del concierto. No puede ser nula ni vacia.
     */
    public ConciertoEvento(String idEvento, String nombre,
                           String categoria, String descripcion) {
        super(idEvento, nombre, categoria, descripcion);
    }
}
