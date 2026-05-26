package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class ACredito implements MedioPago {
    private PagoCredito pagoCredito;

    public ACredito(PagoCredito pagoCredito) {
        this.pagoCredito = pagoCredito;
    }
    /**
     * Procesa el pago de la compra utilizando tarjeta de crédito.
     * Delega la operación al adaptado
     *
     * @param monto Valor total a cobrar. Debe ser mayor que cero.
     */
    @Override
    public void procesarPago(double monto) {
        pagoCredito.pagarConCredito(monto);
    }
    /**
     * Retorna el tipo de pago asociado a este adaptador.
     *
     * @return  TipoPago#CREDITO siempre.
     */
    @Override
    public TipoPago getTipoPago() {
        return TipoPago.CREDITO;
    }
}
