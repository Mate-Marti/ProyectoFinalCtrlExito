package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoCancelada implements EstadoCompra{
    /**
     * Indica que no es posible realizar el pago
     * cuando la compra ya se encuentra cancelada.
     *
     * @param compra compra sobre la que se intenta realizar el pago.
     */
    @Override
    public void pagar(Compra compra) {

        System.out.println("No se puede pagar una compra cancelada");
    }
    /**
     * Indica que no es posible confirmar
     * una compra que ya fue cancelada.
     *
     * @param compra compra que se intenta confirmar.
     */
    @Override
    public void confirmar(Compra compra) {

        System.out.println("No se puede confirmar");
    }
    /**
     * Indica que la compra ya se encuentra
     * en estado cancelado.
     *
     * @param compra compra que se intenta cancelar nuevamente.
     */
    @Override
    public void cancelar(Compra compra) {

        System.out.println("La compra ya está cancelada");
    }
    /**
     * Retorna el estado actual de la compra.
     *
     * @return estado "CANCELADA".
     */
    @Override
    public String mostrarEstado() {
        return "CANCELADA";
    }
}
