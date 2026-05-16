package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class ConciertoFactory extends EventoFactory {

    @Override
    public Evento crearEvento(int id, String nombre, String descripcion, Date fecha) {
        return new ConciertoEvento(id, nombre, "Concierto", descripcion, fecha, EstadoEvento.BORRADOR);
    }

}
