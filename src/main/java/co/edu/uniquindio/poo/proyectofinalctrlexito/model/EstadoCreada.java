package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoCreada implements EstadoCompra{
    /**
     * Procesa el pago de una compra creada, cambiando su estado a pagada
     * y marcando los asientos asociados como vendidos.
     *
     * @param compra compra sobre la que se realizará el pago.
     */
    @Override
    public void pagar(Compra compra) {
        compra.setEstadoCompra(new EstadoPagada());

        for(Entrada entrada : compra.getEntradas()) {
            entrada.getAsiento().venderAsiento();
        }

        System.out.println("Compra pagada correctamente");
    }

    /**
     * Indica que no es posible confirmar una compra
     * que aún no ha sido pagada.
     *
     * @param compra compra que se intenta confirmar.
     */
    @Override
    public void confirmar(Compra compra) {
        System.out.println("No puedes confirmar una compra sin pagar");
    }

    /**
     * Cancela una compra creada, cambiando su estado a cancelada
     * y liberando los asientos asociados.
     *
     * @param compra compra que se desea cancelar.
     */
    @Override
    public void cancelar(Compra compra) {
        compra.setEstadoCompra(new EstadoCancelada());

        for(Entrada entrada : compra.getEntradas()) {
            entrada.getAsiento().liberarAsiento();
        }

        System.out.println("Compra cancelada");
    }

    /**
     * Retorna el estado actual de la compra.
     *
     * @return estado "CREADA".
     */
    @Override
    public String mostrarEstado() {
        return "CREADA";
    }
}
