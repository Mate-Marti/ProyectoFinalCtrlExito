package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface EstadoCompra {
    /**
     * Procesa el pago de una compra según su estado actual.
     *
     * @param compra compra sobre la que se realizará el pago.
     */
    void pagar(Compra compra);

    /**
     * Confirma una compra según su estado actual.
     *
     * @param compra compra que se desea confirmar.
     */
    void confirmar(Compra compra);

    /**
     * Cancela una compra según su estado actual.
     *
     * @param compra compra que se desea cancelar.
     */
    void cancelar(Compra compra);

    /**
     * Retorna el estado actual de la compra.
     *
     * @return nombre del estado actual.
     */
    String mostrarEstado();
}
