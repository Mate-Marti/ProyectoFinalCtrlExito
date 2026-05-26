package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class ConferenciaEvento extends Evento{
    /**
     * Constructor de ConferenciaEvento.
     * Crea un evento de tipo conferencia con los datos basicos heredados de Evento.
     *
     * @param idEvento    Identificador unico del evento. No puede ser nulo.
     * @param nombre      Nombre de la conferencia. No puede ser nulo ni vacio.
     * @param categoria   Categoria del evento. No puede ser nula ni vacia.
     * @param descripcion Descripcion de la conferencia. No puede ser nula ni vacia.
     */
    public ConferenciaEvento(String idEvento,
                             String nombre,
                             String categoria,
                             String descripcion) {

        super(idEvento, nombre, categoria, descripcion);
    }
}
