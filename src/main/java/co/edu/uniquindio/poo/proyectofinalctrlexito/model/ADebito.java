package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class ADebito implements MedioPago {
    private PagoDebito pagoDebito;

    public ADebito(PagoDebito pagoDebito) {
        this.pagoDebito = pagoDebito;
    }

    @Override
    public void procesarPago(double monto) {
        pagoDebito.pagarConDebito(monto);
    }

    @Override
    public TipoPago getTipoPago() {
        return TipoPago.DEBITO;
    }
}
