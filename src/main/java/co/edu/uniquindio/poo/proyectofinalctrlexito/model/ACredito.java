package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class ACredito implements MedioPago {
    private PagoCredito pagoCredito;

    public ACredito(PagoCredito pagoCredito) {
        this.pagoCredito = pagoCredito;
    }

    @Override
    public void procesarPago(double monto) {
        pagoCredito.pagarConCredito(monto);
    }

    @Override
    public TipoPago getTipoPago() {
        return TipoPago.CREDITO;
    }
}
