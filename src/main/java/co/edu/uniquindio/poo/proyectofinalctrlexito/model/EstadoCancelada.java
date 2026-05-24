package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoCancelada implements EstadoCompra{
    @Override
    public void pagar(Compra compra) {

        System.out.println("No se puede pagar una compra cancelada");
    }

    @Override
    public void confirmar(Compra compra) {

        System.out.println("No se puede confirmar");
    }

    @Override
    public void cancelar(Compra compra) {

        System.out.println("La compra ya está cancelada");
    }

    @Override
    public String mostrarEstado() {
        return "CANCELADA";
    }
}
