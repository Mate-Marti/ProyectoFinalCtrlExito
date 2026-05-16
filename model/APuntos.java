package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class APuntos implements MedioPago {
    private PagoPuntos pagoPuntos;

    public APuntos(PagoPuntos pagoPuntos) {
        this.pagoPuntos = pagoPuntos;
    }

    @Override
    public void procesarPago(double monto) {
        int puntos = (int) monto;
        pagoPuntos.pagarConPuntos(puntos);
    }

    @Override
    public TipoPago getTipoPago() {
        return TipoPago.PUNTOS;
    }
}
