package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoConfirmada implements EstadoCompra{
    /**
     * Indica que no es posible realizar el pago
     * porque la compra ya se encuentra confirmada.
     *
     * @param compra compra sobre la que se intenta realizar el pago.
     */
    @Override
    public void pagar(Compra compra) {
        System.out.println("La compra ya está confirmada");
    }

    /**
     * Indica que la compra ya había sido confirmada
     * previamente.
     *
     * @param compra compra que se intenta confirmar nuevamente.
     */
    @Override
    public void confirmar(Compra compra) {
        System.out.println("La compra ya estaba confirmada");
    }

    /**
     * Indica que no es posible cancelar
     * una compra que ya fue confirmada.
     *
     * @param compra compra que se intenta cancelar.
     */
    @Override
    public void cancelar(Compra compra) {
        System.out.println("No se puede cancelar una compra confirmada");
    }

    /**
     * Retorna el estado actual de la compra.
     *
     * @return estado "CONFIRMADA".
     */
    @Override
    public String mostrarEstado() {
        return "CONFIRMADA";
    }
}
