package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoReembolsada implements EstadoCompra{

    /**
     * Indica que no es posible realizar el pago
     * porque la compra ya fue reembolsada.
     *
     * @param compra compra sobre la que se intenta realizar el pago.
     */
    @Override
    public void pagar(Compra compra) {
        System.out.println("La compra ya fue reembolsada");
    }

    /**
     * Indica que no es posible confirmar
     * una compra que ya fue reembolsada.
     *
     * @param compra compra que se intenta confirmar.
     */
    @Override
    public void confirmar(Compra compra) {
        System.out.println("No se puede confirmar");
    }

    /**
     * Indica que no es posible cancelar
     * una compra que ya fue reembolsada.
     *
     * @param compra compra que se intenta cancelar nuevamente.
     */
    @Override
    public void cancelar(Compra compra) {
        System.out.println("La compra ya fue reembolsada");
    }

    /**
     * Retorna el estado actual de la compra.
     *
     * @return estado "REEMBOLSADA".
     */
    @Override
    public String mostrarEstado() {
        return "REEMBOLSADA";
    }
}
