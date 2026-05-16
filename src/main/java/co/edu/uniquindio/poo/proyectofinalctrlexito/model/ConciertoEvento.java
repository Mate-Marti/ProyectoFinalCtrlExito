package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class ConciertoEvento extends Evento {

    public ConciertoEvento(int idEvento, String nombre, String categoria, String descripcion, Date fecha, EstadoEvento estado) {
        super(idEvento, nombre, categoria, descripcion, fecha, estado);
    }
}
