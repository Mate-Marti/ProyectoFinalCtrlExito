package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class APuntos implements MedioPago {
    private PagoPuntos pagoPuntos;

    public APuntos(PagoPuntos pagoPuntos) {
        this.pagoPuntos = pagoPuntos;
    }

    /**
     * Procesa el pago de la compra utilizando puntos acumulados.
     * Convierte el monto a entero y delega la operacion a pagarConPuntos.
     *
     * @param monto Cantidad de puntos a utilizar. Se convierte a entero internamente.
     */
    @Override
    public void procesarPago(double monto) {
        int puntos = (int) monto;
        pagoPuntos.pagarConPuntos(puntos);
    }

    /**
     * Retorna el tipo de pago asociado a este adaptador.
     *
     * @return TipoPago.PUNTOS siempre.
     */
    @Override
    public TipoPago getTipoPago() {
        return TipoPago.PUNTOS;
    }
}
