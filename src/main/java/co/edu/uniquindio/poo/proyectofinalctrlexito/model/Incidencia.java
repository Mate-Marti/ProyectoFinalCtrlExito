package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class Incidencia {

    private int idIncidencia;
    private String tipo;
    private String descripcion;
    private Date fechaIncidencia;

    public Incidencia (int idIncidencia, String tipo, String descripcion, Date fechaIncidencia) {

        this.idIncidencia = idIncidencia;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaIncidencia = fechaIncidencia;

    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(Date fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }
}
