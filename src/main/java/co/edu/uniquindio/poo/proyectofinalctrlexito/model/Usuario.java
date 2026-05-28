package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona implements Observer, Visitor{

    private String metodoPago;
    private List<Compra> compras;
    // NUEVO
    private String contrasena;

    public Usuario(String id,
                   String nombreCompleto,
                   String correo,
                   String telefono,
                   String metodoPago) {
        super(id, nombreCompleto, correo, telefono);
        this.metodoPago = metodoPago;
        this.compras = new ArrayList<>();
    }

    // NUEVO
    public Usuario(String id,
                   String nombreCompleto,
                   String correo,
                   String telefono,
                   String metodoPago,
                   String contrasena) {
        this(id, nombreCompleto, correo, telefono, metodoPago);
        this.contrasena = contrasena;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificacion para: " + getNombreCompleto());
        System.out.println(mensaje);
    }

    @Override
    public void aceptarVisitante(ReporteVisitor visitor) {
        visitor.visitarUsuario(this);
    }

    public void agregarCompra(Compra compra) {
        compras.add(compra);
    }

    public void seleccionarEntrada(Compra compra, Entrada entrada) {
        if (compra == null || entrada == null) {
            throw new IllegalArgumentException(
                    "La compra y la entrada no pueden ser nulas."
            );
        }
        if (!compras.contains(compra)) {
            System.out.println("La compra no pertenece a este usuario.");
            return;
        }
        compra.agregarEntrada(entrada);
        System.out.println(
                "Entrada seleccionada correctamente para: "
                        + getNombreCompleto()
        );
    }

    public void solicitarCancelacion(Compra compra) {
        if (compra == null) {
            throw new IllegalArgumentException(
                    "La compra no puede ser nula."
            );
        }
        if (!compras.contains(compra)) {
            System.out.println("La compra no pertenece a este usuario.");
            return;
        }
        compra.cancelarCompra();
        System.out.println(
                "Cancelacion solicitada por: "
                        + getNombreCompleto()
        );
    }

    public void descargarComprobante(Compra compra) {
        if (compra == null) {
            throw new IllegalArgumentException(
                    "La compra no puede ser nula."
            );
        }
        if (!compras.contains(compra)) {
            System.out.println("La compra no pertenece a este usuario.");
            return;
        }
        System.out.println("===== COMPROBANTE DE COMPRA =====");
        System.out.println(compra.consultarCompra());
        System.out.println("=================================");
    }

    public void agregarServicioACompra(Compra compra, ServicioAdicional servicio) {
        if (compra == null || servicio == null) {
            throw new IllegalArgumentException(
                    "La compra y el servicio no pueden ser nulos."
            );
        }
        if (!compras.contains(compra)) {
            System.out.println("La compra no pertenece a este usuario.");
            return;
        }
        compra.agregarServicio(servicio);
        System.out.println(
                "Servicio agregado correctamente a la compra de: "
                        + getNombreCompleto()
        );
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    // NUEVO
    public String getContrasena() {
        return contrasena;
    }

    // NUEVO
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}