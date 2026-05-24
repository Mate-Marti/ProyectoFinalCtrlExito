package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class AEfectivo implements MedioPago {
    private PagoEfectivo pagoEfectivo;

    public AEfectivo(PagoEfectivo pagoEfectivo) {
        this.pagoEfectivo = pagoEfectivo;
    }

    @Override
    public void procesarPago(double monto) {
        pagoEfectivo.pagarEnEfectivo(monto);
    }

    @Override
    public TipoPago getTipoPago() {
        return TipoPago.EFECTIVO;
    }
}
