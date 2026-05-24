package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class Incidencia {

    private int idIncidencia;
    private String descripcion;
    private Date fechaIncidencia;
    private TipoIncidencia tipo;
    private EntidadAfectada entidad;

    public Incidencia (int idIncidencia, String descripcion, Date fechaIncidencia,TipoIncidencia tipo,EntidadAfectada entidad) {

        this.idIncidencia = idIncidencia;
        this.descripcion = descripcion;
        this.fechaIncidencia = fechaIncidencia;
        this.tipo=tipo;
        this.entidad =entidad;
    }

    public EntidadAfectada getEntidad() {
        return entidad;
    }
    @Override
    public String toString() {
        return "Incidencia{" +
                "id=" + idIncidencia +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fechaIncidencia +
                ", tipo=" + tipo +
                '}';
    }

    public void setEntidad(EntidadAfectada entidad) {
        this.entidad = entidad;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public TipoIncidencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoIncidencia tipo) {
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
