package co.edu.uniquindio.poo.proyectofinalctrlexito.model;
import java.util.Date;

public class ConferenciaFactory extends EventoFactory {
    /**
     * Crea un nuevo evento de tipo conferencia.
     * Asigna automaticamente la categoria "Conferencia".
     *
     * @param id          Identificador numerico del evento.
     * @param nombre      Nombre de la conferencia. No puede ser nulo ni vacio.
     * @param descripcion Descripcion de la conferencia. No puede ser nula ni vacia.
     * @param fecha       Fecha de realizacion del evento. No puede ser nula.
     * @return Nueva instancia de ConferenciaEvento.
     */
    @Override
    public Evento crearEvento(int id,
                              String nombre,
                              String descripcion,
                              Date fecha) {

        return new ConferenciaEvento(
                String.valueOf(id),
                nombre,
                "Conferencia",
                descripcion
        );
    }

}
