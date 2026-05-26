package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class Incidencia {

    private int idIncidencia;
    private String descripcion;
    private Date fechaIncidencia;
    private TipoIncidencia tipo;
    private EntidadAfectada entidad;
    private EstadoIncidencia estado;

    /**
     * Crea una nueva incidencia con su información básica
     * y asigna el estado inicial como ABIERTA.
     *
     * @param idIncidencia identificador único de la incidencia.
     * @param descripcion descripción de la incidencia.
     * @param fechaIncidencia fecha en que se registró la incidencia.
     * @param tipo tipo de incidencia reportada.
     * @param entidad entidad afectada por la incidencia.
     */
    public Incidencia(int idIncidencia, String descripcion, Date fechaIncidencia,
                      TipoIncidencia tipo, EntidadAfectada entidad) {

        this.idIncidencia = idIncidencia;
        this.descripcion = descripcion;
        this.fechaIncidencia = fechaIncidencia;
        this.tipo = tipo;
        this.entidad = entidad;
        this.estado = EstadoIncidencia.ABIERTA;
    }

    /**
     * Cambia el estado de la incidencia a EN_REVISION,
     * siempre que actualmente esté en estado ABIERTA.
     */
    public void ponerEnRevision() {
        if (estado != EstadoIncidencia.ABIERTA) {
            System.out.println("Solo se puede revisar una incidencia ABIERTA.");
            return;
        }
        this.estado = EstadoIncidencia.EN_REVISION;
        System.out.println("Incidencia " + idIncidencia + " puesta EN REVISION.");
    }

    /**
     * Cambia el estado de la incidencia a RESUELTA,
     * siempre que actualmente esté en estado EN_REVISION.
     */
    public void resolverIncidencia() {
        if (estado != EstadoIncidencia.EN_REVISION) {
            System.out.println("Solo se puede resolver una incidencia EN REVISION.");
            return;
        }
        this.estado = EstadoIncidencia.RESUELTA;
        System.out.println("Incidencia " + idIncidencia + " RESUELTA.");
    }

    /**
     * Cambia el estado de la incidencia a CERRADA,
     * siempre que actualmente esté en estado RESUELTA.
     */
    public void cerrarIncidencia() {
        if (estado != EstadoIncidencia.RESUELTA) {
            System.out.println("Solo se puede cerrar una incidencia RESUELTA.");
            return;
        }
        this.estado = EstadoIncidencia.CERRADA;
        System.out.println("Incidencia " + idIncidencia + " CERRADA.");
    }

    /**
     * Retorna una representación en texto de la incidencia,
     * incluyendo su identificador, descripción, fecha y tipo.
     *
     * @return cadena con la información de la incidencia.
     */
    @Override
    public String toString() {
        return "Incidencia{" +
                "id=" + idIncidencia +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fechaIncidencia +
                ", tipo=" + tipo +
                '}';
    }

    /**
     * Obtiene el estado actual de la incidencia.
     *
     * @return estado actual de la incidencia.
     */
    public EstadoIncidencia getEstado() {
        return estado;
    }

    /**
     * Asigna un nuevo estado a la incidencia.
     *
     * @param estado nuevo estado de la incidencia.
     */
    public void setEstado(EstadoIncidencia estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la entidad afectada por la incidencia.
     *
     * @return entidad afectada.
     */
    public EntidadAfectada getEntidad() {
        return entidad;
    }

    /**
     * Asigna una nueva entidad afectada a la incidencia.
     *
     * @param entidad nueva entidad afectada.
     */
    public void setEntidad(EntidadAfectada entidad) {
        this.entidad = entidad;
    }

    /**
     * Obtiene el identificador de la incidencia.
     *
     * @return identificador de la incidencia.
     */
    public int getIdIncidencia() {
        return idIncidencia;
    }

    /**
     * Asigna un nuevo identificador a la incidencia.
     *
     * @param idIncidencia nuevo identificador de la incidencia.
     */
    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    /**
     * Obtiene el tipo de incidencia.
     *
     * @return tipo de la incidencia.
     */
    public TipoIncidencia getTipo() {
        return tipo;
    }

    /**
     * Asigna un nuevo tipo a la incidencia.
     *
     * @param tipo nuevo tipo de incidencia.
     */
    public void setTipo(TipoIncidencia tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la descripción de la incidencia.
     *
     * @return descripción de la incidencia.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna una nueva descripción a la incidencia.
     *
     * @param descripcion nueva descripción de la incidencia.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha en la que fue registrada la incidencia.
     *
     * @return fecha de la incidencia.
     */
    public Date getFechaIncidencia() {
        return fechaIncidencia;
    }

    /**
     * Asigna una nueva fecha a la incidencia.
     *
     * @param fechaIncidencia nueva fecha de registro de la incidencia.
     */
    public void setFechaIncidencia(Date fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }
}
