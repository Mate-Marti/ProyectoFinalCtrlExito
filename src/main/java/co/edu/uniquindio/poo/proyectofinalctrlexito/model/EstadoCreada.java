package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoCreada implements EstadoCompra{
    @Override
    public void pagar(Compra compra) {

        System.out.println("Compra pagada correctamente");

        compra.setEstadoCompra(new EstadoPagada());
    }

    @Override
    public void confirmar(Compra compra) {

        System.out.println("No puedes confirmar una compra sin pagar");
    }

    @Override
    public void cancelar(Compra compra) {

        System.out.println("Compra cancelada");

        compra.setEstadoCompra(new EstadoCancelada());
    }

    @Override
    public String mostrarEstado() {
        return "CREADA";
    }
}
