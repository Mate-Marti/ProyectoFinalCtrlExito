package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoCreada implements EstadoCompra{
    @Override
    public void pagar(Compra compra) {

        compra.setEstadoCompra(new EstadoPagada());

        for(Entrada entrada : compra.getEntradas()) {

            entrada.getAsiento().venderAsiento();
        }

        System.out.println("Compra pagada correctamente");
    }

    @Override
    public void confirmar(Compra compra) {

        System.out.println("No puedes confirmar una compra sin pagar");
    }

    @Override
    public void cancelar(Compra compra) {
        compra.setEstadoCompra(new EstadoCancelada());
        for(Entrada entrada : compra.getEntradas()) {
            entrada.getAsiento().liberarAsiento();
        }
        System.out.println("Compra cancelada");
    }

    @Override
    public String mostrarEstado() {
        return "CREADA";
    }
}
