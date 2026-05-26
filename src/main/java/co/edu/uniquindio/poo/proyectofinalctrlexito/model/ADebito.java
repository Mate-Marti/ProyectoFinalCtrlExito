package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class ADebito implements MedioPago {
    private PagoDebito pagoDebito;

    public ADebito(PagoDebito pagoDebito) {
        this.pagoDebito = pagoDebito;
    }
    /**
     * Procesa el pago de la compra utilizando tarjeta débito.
     * Delega la operación al adaptado pagarConDebito.
     *
     * @param monto Valor total a cobrar. Debe ser mayor que cero.
     */
    @Override
    public void procesarPago(double monto) {
        pagoDebito.pagarConDebito(monto);
    }
    /**
     * Retorna el tipo de pago asociado a este adaptador.
     *
     * @return TipoPago.DEBITO siempre.
     */
    @Override
    public TipoPago getTipoPago() {
        return TipoPago.DEBITO;
    }
}
