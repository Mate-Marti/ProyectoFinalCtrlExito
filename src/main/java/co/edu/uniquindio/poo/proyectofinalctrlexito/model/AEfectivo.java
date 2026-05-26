package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class AEfectivo implements MedioPago {
    private PagoEfectivo pagoEfectivo;

    public AEfectivo(PagoEfectivo pagoEfectivo) {
        this.pagoEfectivo = pagoEfectivo;
    }

    /**
     * Procesa el pago de la compra utilizando efectivo.
     * Delega la operacion al adaptado pagarEnEfectivo.
     *
     * @param monto Valor total a cobrar. Debe ser mayor que cero.
     */
    @Override
    public void procesarPago(double monto) {
        pagoEfectivo.pagarEnEfectivo(monto);
    }

    /**
     * Retorna el tipo de pago asociado a este adaptador.
     *
     * @return TipoPago.EFECTIVO siempre.
     */
    @Override
    public TipoPago getTipoPago() {
        return TipoPago.EFECTIVO;
    }
}
