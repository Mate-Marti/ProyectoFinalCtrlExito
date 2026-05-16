package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface MedioPago {
    void procesarPago(double monto);
    TipoPago getTipoPago();
}
