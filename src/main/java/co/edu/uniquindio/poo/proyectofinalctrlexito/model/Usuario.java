package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona implements Observer{

    private String metodoPago;
    private List<Compra> compras;

    public Usuario(String id, String nombreCompleto, String correo, String telefono,String metodoPago) {
        super(id,nombreCompleto,correo,telefono);
        this.metodoPago = metodoPago;
        this.compras= new ArrayList<>();

    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    @Override
    public void actualizar(String mensaje) {

        System.out.println("Notificacion para: " + getNombreCompleto());

        System.out.println(mensaje);
    }

    public void agregarCompra(Compra compra) {

        compras.add(compra);
    }
}
