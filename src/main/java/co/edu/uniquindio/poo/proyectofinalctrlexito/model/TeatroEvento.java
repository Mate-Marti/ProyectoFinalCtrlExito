package co.edu.uniquindio.poo.proyectofinalctrlexito.model;


public class TeatroEvento extends Evento{

    /**
     * Constructor de la clase TeatroEvento.
     * Inicializa los datos principales de un evento de tipo teatro.
     *
     * @param idEvento identificador único del evento.
     * @param nombre nombre del evento.
     * @param categoria categoría del evento.
     * @param descripcion descripción del evento.
     */
    public TeatroEvento(String idEvento,
                        String nombre,
                        String categoria,
                        String descripcion) {

        super(idEvento, nombre, categoria, descripcion);
    }
}
