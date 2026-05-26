package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public abstract class EventoFactory {
    public abstract Evento crearEvento(int id, String nombre, String descripcion, Date fecha );
    public EventoFactory () {}
}
