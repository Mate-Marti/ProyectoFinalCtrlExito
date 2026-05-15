package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoConfirmada implements EstadoCompra{
    @Override
    public void pagar(Compra compra) {

        System.out.println("La compra ya está confirmada");
    }

    @Override
    public void confirmar(Compra compra) {

        System.out.println("La compra ya estaba confirmada");
    }

    @Override
    public void cancelar(Compra compra) {

        System.out.println("No se puede cancelar una compra confirmada");
    }

    @Override
    public String mostrarEstado() {
        return "CONFIRMADA";
    }
}
