package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class TeatroFactory extends EventoFactory {

    /**
     * Crea un nuevo evento de tipo teatro.
     *
     * @param id identificador del evento.
     * @param nombre nombre del evento.
     * @param descripcion descripción del evento.
     * @param fecha fecha del evento.
     * @return nuevo objeto TeatroEvento.
     */
    @Override
    public Evento crearEvento(int id,
                              String nombre,
                              String descripcion,
                              Date fecha) {

        return new TeatroEvento(
                String.valueOf(id),
                nombre,
                "Teatro",
                descripcion
        );
    }

}