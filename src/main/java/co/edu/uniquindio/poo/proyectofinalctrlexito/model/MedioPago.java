package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface MedioPago {
    /**
     * Procesa el pago de un monto determinado
     * utilizando el medio de pago correspondiente.
     *
     * @param monto valor que se desea pagar.
     */
    void procesarPago(double monto);

    /**
     * Retorna el tipo de pago asociado
     * al medio de pago implementado.
     *
     * @return tipo de pago utilizado.
     */
    TipoPago getTipoPago();
}
