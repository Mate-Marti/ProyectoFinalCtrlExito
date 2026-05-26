package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class EstadoReembolsada implements EstadoCompra{

    @Override
    public void pagar(Compra compra) {

        System.out.println("La compra ya fue reembolsada");
    }

    @Override
    public void confirmar(Compra compra) {

        System.out.println("No se puede confirmar");
    }

    @Override
    public void cancelar(Compra compra) {

        System.out.println("La compra ya fue reembolsada");
    }

    @Override
    public String mostrarEstado() {
        return "REEMBOLSADA";
    }
}
