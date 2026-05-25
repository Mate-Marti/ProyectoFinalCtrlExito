package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class Incidencia {

    private int idIncidencia;
    private String descripcion;
    private Date fechaIncidencia;
    private TipoIncidencia tipo;
    private EntidadAfectada entidad;
    private EstadoIncidencia estado;

    public Incidencia (int idIncidencia, String descripcion, Date fechaIncidencia,TipoIncidencia tipo,EntidadAfectada entidad   ) {

        this.idIncidencia = idIncidencia;
        this.descripcion = descripcion;
        this.fechaIncidencia = fechaIncidencia;
        this.tipo=tipo;
        this.entidad =entidad;
        this.estado = EstadoIncidencia.ABIERTA;
    }
    public void ponerEnRevision() {
        if (estado != EstadoIncidencia.ABIERTA) {
            System.out.println("Solo se puede revisar una incidencia ABIERTA.");
            return;
        }
        this.estado = EstadoIncidencia.EN_REVISION;
        System.out.println("Incidencia " + idIncidencia + " puesta EN REVISION.");
    }

    public void resolverIncidencia() {
        if (estado != EstadoIncidencia.EN_REVISION) {
            System.out.println("Solo se puede resolver una incidencia EN REVISION.");
            return;
        }
        this.estado = EstadoIncidencia.RESUELTA;
        System.out.println("Incidencia " + idIncidencia + " RESUELTA.");
    }

    public void cerrarIncidencia() {
        if (estado != EstadoIncidencia.RESUELTA) {
            System.out.println("Solo se puede cerrar una incidencia RESUELTA.");
            return;
        }
        this.estado = EstadoIncidencia.CERRADA;
        System.out.println("Incidencia " + idIncidencia + " CERRADA.");
    }
    public EstadoIncidencia getEstado() { return estado; }
    public void setEstado(EstadoIncidencia estado) { this.estado = estado; }
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
