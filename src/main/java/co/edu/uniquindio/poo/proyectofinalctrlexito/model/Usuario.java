package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona implements Observer, Visitor{

    private String metodoPago;
    private List<Compra> compras;
    /**
     * Constructor de la clase Usuario.
     * Inicializa los datos personales del usuario,
     * el método de pago y la lista de compras.
     *
     * @param id identificador único del usuario.
     * @param nombreCompleto nombre completo del usuario.
     * @param correo correo electrónico del usuario.
     * @param telefono número telefónico del usuario.
     * @param metodoPago método de pago asociado al usuario.
     */
    public Usuario(String id,
                   String nombreCompleto,
                   String correo,
                   String telefono,
                   String metodoPago) {

        super(id, nombreCompleto, correo, telefono);

        this.metodoPago = metodoPago;
        this.compras = new ArrayList<>();
    }


    /**
     * Actualiza al usuario enviándole una notificación
     * relacionada con un cambio o evento del sistema.
     *
     * @param mensaje mensaje enviado al usuario.
     */
    @Override
    public void actualizar(String mensaje) {

        System.out.println("Notificacion para: " + getNombreCompleto());

        System.out.println(mensaje);
    }

    /**
     * Permite que un visitante procese la información
     * del usuario utilizando el patrón Visitor.
     *
     * @param visitor visitante encargado de procesar el usuario.
     */
    @Override
    public void aceptarVisitante(ReporteVisitor visitor) {

        visitor.visitarUsuario(this);
    }

    /**
     * Agrega una compra a la lista de compras del usuario.
     *
     * @param compra compra que será agregada al usuario.
     */
    public void agregarCompra(Compra compra) {

        compras.add(compra);
    }

    /**
     * Permite seleccionar una entrada y asociarla
     * a una compra del usuario.
     *
     * @param compra compra a la que se agregará la entrada.
     * @param entrada entrada seleccionada por el usuario.
     * @throws IllegalArgumentException si la compra o la entrada son nulas.
     */
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

    /**
     * Solicita la cancelación de una compra realizada por el usuario.
     *
     * @param compra compra que será cancelada.
     * @throws IllegalArgumentException si la compra es nula.
     */
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

    /**
     * Muestra el comprobante de una compra realizada por el usuario.
     *
     * @param compra compra de la cual se descargará el comprobante.
     * @throws IllegalArgumentException si la compra es nula.
     */
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

    /**
     * Agrega un servicio adicional a una compra del usuario.
     *
     * @param compra compra a la que se agregará el servicio.
     * @param servicio servicio adicional que será agregado.
     * @throws IllegalArgumentException si la compra o el servicio son nulos.
     */
    public void agregarServicioACompra(Compra compra,
                                       ServicioAdicional servicio) {

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

    /**
     * Obtiene el método de pago asociado al usuario.
     *
     * @return método de pago del usuario.
     */
    public String getMetodoPago() {
        return metodoPago;
    }

    /**
     * Modifica el método de pago asociado al usuario.
     *
     * @param metodoPago nuevo método de pago del usuario.
     */
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene la lista de compras realizadas por el usuario.
     *
     * @return lista de compras del usuario.
     */
    public List<Compra> getCompras() {
        return compras;
    }
}
