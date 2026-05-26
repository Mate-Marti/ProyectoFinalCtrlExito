package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Asiento{

    private int idAsiento;
    private String fila;
    private String numero;
    private EstadoAsiento estado;

    public Asiento(int idAsiento, String fila, String numero) {

        this.idAsiento = idAsiento;
        this.fila = fila;
        this.numero = numero;
        this.estado = EstadoAsiento.DISPONIBLE;

    }

    /**
     * Habilita el asiento cambiando su estado a DISPONIBLE.
     * Se usa cuando un asiento bloqueado vuelve a estar en uso.
     */
    public void habilitarAsiento() {
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    /**
     * Bloquea el asiento impidiendo que sea reservado o vendido.
     * Se usa para mantenimiento o restricciones administrativas.
     */
    public void bloquearAsiento() {
        this.estado = EstadoAsiento.BLOQUEADO;
    }

    /**
     * Reserva el asiento cambiando su estado a RESERVADO.
     * Se usa cuando un usuario selecciona el asiento en una compra pendiente.
     */
    public void reservarAsiento() {
        this.estado = EstadoAsiento.RESERVADO;
    }

    /**
     * Marca el asiento como vendido cambiando su estado a VENDIDO.
     * Se usa cuando la compra asociada al asiento es confirmada y pagada.
     */
    public void venderAsiento() {
        this.estado = EstadoAsiento.VENDIDO;
    }

    /**
     * Libera el asiento cambiando su estado a DISPONIBLE.
     * Se usa cuando una compra es cancelada o reembolsada.
     */
    public void liberarAsiento() {
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    /**
     * Retorna la informacion detallada del asiento en formato texto.
     *
     * @return String con el id, fila, numero y estado actual del asiento.
     */
    public String consultarAsiento() {
        return "ID asiento: " + idAsiento +
                "\nFila: " + fila +
                "\nNumero: " + numero +
                "\nEstado: " + estado;
    }
    /**
     * Valida si el asiento se encuentra disponible para ser reservado.
     *
     * @return true si el estado es DISPONIBLE, false en caso contrario.
     */
    public boolean validarDisponibilidad() {
        return estado == EstadoAsiento.DISPONIBLE;
    }

    /**
     * Retorna la disponibilidad del asiento como valor numerico.
     * Utilizado para calcular la ocupacion total de una zona.
     *
     * @return 1 si el asiento esta disponible, 0 si esta reservado, vendido o bloqueado.
     */
    public int obtenerDisponibilidad() {
        if (validarDisponibilidad()) {
            return 1;
        }
        return 0;
    }
    /**
     * Retorna el identificador unico del asiento.
     *
     * @return idAsiento como entero.
     */
    public int getIdAsiento() {
        return idAsiento;
    }

    /**
     * Asigna el identificador unico del asiento.
     *
     * @param idAsiento Nuevo identificador. Debe ser mayor que cero.
     */
    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    /**
     * Retorna la fila en la que se encuentra el asiento.
     *
     * @return fila como String.
     */
    public String getFila() {
        return fila;
    }

    /**
     * Asigna la fila del asiento.
     *
     * @param fila Nueva fila. No puede ser nula ni vacia.
     */
    public void setFila(String fila) {
        this.fila = fila;
    }

    /**
     * Retorna el numero del asiento dentro de su fila.
     *
     * @return numero como String.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Asigna el numero del asiento dentro de su fila.
     *
     * @param numero Nuevo numero. No puede ser nulo ni vacio.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Retorna el estado actual del asiento.
     *
     * @return estado como EstadoAsiento.
     */
    public EstadoAsiento getEstado() {
        return estado;
    }

    /**
     * Asigna el estado del asiento.
     *
     * @param estado Nuevo estado. No puede ser nulo.
     */
    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }
}
