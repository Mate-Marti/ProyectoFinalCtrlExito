package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Usuario extends Persona {

    private String metodoPago;

    public Usuario(String metodoPago) {

        this.metodoPago = metodoPago;

    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
