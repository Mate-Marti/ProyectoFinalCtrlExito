package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoPagada implements EstadoCompra{
    /**
     * Indica que no es posible volver a pagar
     * una compra que ya fue pagada.
     *
     * @param compra compra sobre la que se intenta realizar el pago nuevamente.
     */
    @Override
    public void pagar(Compra compra) {
        System.out.println("La compra ya fue pagada");
    }

    /**
     * Confirma una compra pagada cambiando
     * su estado a confirmada.
     *
     * @param compra compra que se desea confirmar.
     */
    @Override
    public void confirmar(Compra compra) {
        System.out.println("Compra confirmada");

        compra.setEstadoCompra(new EstadoConfirmada());
    }

    /**
     * Cancela una compra pagada y cambia
     * su estado a reembolsada.
     *
     * @param compra compra que se desea cancelar.
     */
    @Override
    public void cancelar(Compra compra) {
        System.out.println("Compra cancelada y reembolsada");

        compra.setEstadoCompra(new EstadoReembolsada());
    }

    /**
     * Retorna el estado actual de la compra.
     *
     * @return estado "PAGADA".
     */
    @Override
    public String mostrarEstado() {
        return "PAGADA";
    }
}
