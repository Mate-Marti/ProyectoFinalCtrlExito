package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface EstadoCompra {
    void pagar(Compra compra);

    void confirmar(Compra compra);

    void cancelar(Compra compra);

    String mostrarEstado();
}
