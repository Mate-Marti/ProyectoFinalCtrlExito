package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoPagada implements EstadoCompra{
    @Override
    public void pagar(Compra compra) {

        System.out.println("La compra ya fue pagada");
    }

    @Override
    public void confirmar(Compra compra) {

        System.out.println("Compra confirmada");

        compra.setEstadoCompra(new EstadoConfirmada());
    }

    @Override
    public void cancelar(Compra compra) {

        System.out.println("Compra cancelada y reembolsada");

        compra.setEstadoCompra(new EstadoReembolsada());
    }

    @Override
    public String mostrarEstado() {
        return "PAGADA";
    }
}
