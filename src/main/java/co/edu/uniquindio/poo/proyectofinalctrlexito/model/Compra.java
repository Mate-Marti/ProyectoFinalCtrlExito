package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra implements Visitor{

    private int idCompra;
    private Date fechaCreacion;
    private double total;
    private EstadoCompra estadoCompra;
    private Usuario usuario;
    private Evento evento;
    private List<Entrada> entradas;
    private List<ServicioAdicional> serviciosAdicionales;
    //private List<>
    private TipoPago tipoPago;

    private Compra(Builder builder) {
        this.idCompra = builder.idCompra;
        this.fechaCreacion = new Date();
        this.total = builder.total;
        this.estadoCompra = new EstadoCreada();
        this.tipoPago = builder.tipoPago;
        this.usuario= builder.usuario;
        this.evento= builder.evento;
        this.serviciosAdicionales = new ArrayList<>();
        this.entradas = new ArrayList<>();
    }

    /**
     * Procesa el pago de la compra.
     * Delega la operacion al estado actual de la compra.
     */
    public void pagarCompra() {
        estadoCompra.pagar(this);
    }

    /**
     * Confirma la compra una vez el pago ha sido procesado.
     * Delega la operacion al estado actual de la compra.
     */
    public void confirmarCompra() {
        estadoCompra.confirmar(this);
    }

    /**
     * Cancela la compra segun las politicas definidas.
     * Delega la operacion al estado actual de la compra.
     */
    public void cancelarCompra() {
        estadoCompra.cancelar(this);
    }

    /**
     * Retorna la informacion detallada de la compra en formato texto.
     * Incluye usuario, evento, total, entradas, servicios adicionales,
     * estado actual y tipo de pago.
     *
     * @return String con el detalle completo de la compra.
     */
    public String consultarCompra() {
        return "Numero compra: " + idCompra +
                "\nUsuario: " + usuario.getNombreCompleto() +
                "\nEvento: " + evento.getNombre() +
                "\nTotal: " + total +
                "\nCantidad entradas: " + entradas.size() +
                "\nServicios adicionales: " + serviciosAdicionales.size() +
                "\nEstado: " + estadoCompra.mostrarEstado() +
                "\nTipo pago: " + tipoPago;
    }



    //
    // Builder
    //

    public static class Builder {

        private int idCompra;
        private double total;
        private TipoPago tipoPago;
        private Usuario usuario;
        private Evento evento;

        public Builder() {
        }

        public Builder setIdCompra(int idCompra) {
            this.idCompra = idCompra;
            return this;
        }


        public Builder setTotal(double total) {
            this.total = total;
            return this;
        }


        public Builder setTipoPago(TipoPago tipoPago) {
            this.tipoPago = tipoPago;
            return this;
        }
        public Builder setEvento(Evento evento) {
            this.evento= evento;
            return this;
        }
        public Builder setUsuario(Usuario usuario) {
            this.usuario= usuario;
            return this;
        }

        public Compra build() {
            return new Compra(this);
        }
    }

    /**
     * Acepta un visitante del tipo ReporteVisitor.
     * Metodo complementario al patron Visitor que permite generar
     * reportes sobre la compra sin modificar su estructura.
     *
     * @param visitor Visitante que procesara la informacion de la compra.
     */
    @Override
    public void aceptarVisitante(ReporteVisitor visitor) {
        visitor.visitarCompra(this);
    }
    /**
     * Agrega un servicio adicional a la compra y recalcula el total.
     *
     * @param servicio Servicio adicional a agregar. No puede ser nulo.
     */
    public void agregarServicio(ServicioAdicional servicio) {
        serviciosAdicionales.add(servicio);
        calcularTotal();
    }

    /**
     * Muestra por consola todos los servicios adicionales de la compra.
     * Si no hay servicios, no imprime nada.
     */
    public void mostrarServicios() {
        for (ServicioAdicional servicio : serviciosAdicionales) {
            System.out.println(servicio.mostrarServicio());
            System.out.println("----------------");
        }
    }

    /**
     * Agrega una entrada a la compra si el asiento esta disponible.
     * Reserva el asiento y recalcula el total automaticamente.
     *
     * @param entrada Entrada a agregar con su asiento asociado.
     */
    public void agregarEntrada(Entrada entrada) {
        Asiento asiento = entrada.getAsiento();
        if (asiento.validarDisponibilidad()) {
            entradas.add(entrada);
            asiento.reservarAsiento();
            calcularTotal();
            System.out.println("Entrada agregada correctamente");
        } else {
            System.out.println("El asiento no esta disponible");
        }
    }

    /**
     * Recalcula el total de la compra sumando el precio
     * de todas las entradas y servicios adicionales.
     */
    public void calcularTotal() {
        total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.getPrecioFinal();
        }
        for (ServicioAdicional servicio : serviciosAdicionales) {
            total += servicio.getPrecio();
        }
    }

    /**
     * Modifica el tipo de pago de la compra.
     * Solo se permite antes de que la compra sea pagada.
     *
     * @param nuevoTipoPago Nuevo tipo de pago a asignar. No puede ser nulo.
     */
    public void modificarCompra(TipoPago nuevoTipoPago) {
        if (estadoCompra instanceof EstadoCreada) {
            this.tipoPago = nuevoTipoPago;
            System.out.println("Compra modificada correctamente");
        } else {
            System.out.println("Solo se puede modificar antes de pagar");
        }
    }

    /**
     * Muestra por consola todas las entradas asociadas a la compra.
     * Si no hay entradas, no imprime nada.
     */
    public void mostrarEntradas() {
        for (Entrada entrada : entradas) {
            System.out.println(entrada.consultarEntrada());
            System.out.println("----------------");
        }
    }

    /**
     * Elimina una entrada de la compra, libera su asiento
     * y recalcula el total automaticamente.
     *
     * @param entrada Entrada a eliminar. No puede ser nula.
     */
    public void eliminarEntrada(Entrada entrada) {
        entradas.remove(entrada);
        entrada.getAsiento().liberarAsiento();
        calcularTotal();
    }

    /**
     * Elimina un servicio adicional de la compra
     * y recalcula el total automaticamente.
     *
     * @param servicio Servicio adicional a eliminar. No puede ser nulo.
     */
    public void eliminarServicio(ServicioAdicional servicio) {
        serviciosAdicionales.remove(servicio);
        calcularTotal();
    }

    /**
     * Realiza el pago de la compra utilizando el medio de pago indicado.
     * Actualiza el tipo de pago, procesa el cobro y cambia el estado a PAGADA.
     *
     * @param medioPago Medio de pago a utilizar. No puede ser nulo.
     * @throws IllegalArgumentException si el medio de pago es nulo.
     */
    public void realizarPago(MedioPago medioPago) {
        if (medioPago == null) {
            throw new IllegalArgumentException("El medio de pago no puede ser nulo.");
        }
        this.tipoPago = medioPago.getTipoPago();
        medioPago.procesarPago(this.total);
        pagarCompra();
        System.out.println("Pago realizado. Total cobrado: " + this.total);
    }

    /**
     * Modifica el medio de pago de la compra.
     * Solo se permite antes de que la compra sea pagada o confirmada.
     *
     * @param nuevoMedioPago Nuevo medio de pago a asignar. No puede ser nulo.
     * @throws IllegalArgumentException si el nuevo medio de pago es nulo.
     */
    public void modificarPago(MedioPago nuevoMedioPago) {
        if (!(estadoCompra instanceof EstadoCreada)) {
            System.out.println("Solo se puede modificar el pago antes de confirmar.");
            return;
        }
        if (nuevoMedioPago == null) {
            throw new IllegalArgumentException("El medio de pago no puede ser nulo.");
        }
        this.tipoPago = nuevoMedioPago.getTipoPago();
        System.out.println("Medio de pago actualizado a: " + this.tipoPago);
    }

    /**
     * Genera un ticket de entrada para el asiento y zona indicados.
     * Valida la disponibilidad del asiento antes de crear la entrada.
     *
     * @param asiento Asiento seleccionado para la entrada. No puede ser nulo.
     * @param zona Zona a la que pertenece el asiento. No puede ser nula.
     * @return Entrada generada, o null si el asiento no esta disponible.
     * @throws IllegalArgumentException si el asiento o la zona son nulos.
     */
    public Entrada generarEntrada(Asiento asiento, Zona zona) {
        if (asiento == null || zona == null) {
            throw new IllegalArgumentException("El asiento y la zona no pueden ser nulos.");
        }
        if (!asiento.validarDisponibilidad()) {
            System.out.println("El asiento no está disponible.");
            return null;
        }
        Entrada entrada = new Entrada(zona.getPreciobase(), EstadoEntrada.ACTIVA, asiento, zona);
        agregarEntrada(entrada);
        System.out.println("------ TICKET GENERADO ------");
        System.out.println("Compra N°:   " + idCompra);
        System.out.println("Usuario:     " + usuario.getNombreCompleto());
        System.out.println("Evento:      " + evento.getNombre());
        System.out.println("Zona:        " + zona.getNombre());
        System.out.println("Asiento:     " + asiento);
        System.out.println("Precio:      $" + zona.getPreciobase());
        System.out.println("Estado:      " + EstadoEntrada.ACTIVA);
        System.out.println("-----------------------------");
        return entrada;
    }

    /**
     * Procesa el reembolso de la compra.
     * Anula todas las entradas, libera los asientos asociados
     * y cambia el estado de la compra a REEMBOLSADA.
     */
    public void reembolsarCompra() {
        if (estadoCompra instanceof EstadoCreada) {
            System.out.println("La compra aún no ha sido pagada, use cancelarCompra().");
            return;
        }
        for (Entrada entrada : entradas) {
            entrada.anularEntrada();
            entrada.getAsiento().liberarAsiento();
        }
        this.estadoCompra = new EstadoReembolsada();
        System.out.println("------ REEMBOLSO PROCESADO ------");
        System.out.println("Compra N°:  " + idCompra);
        System.out.println("Usuario:    " + usuario.getNombreCompleto());
        System.out.println("Evento:     " + evento.getNombre());
        System.out.println("Total reembolsado: $" + total);
        System.out.println("---------------------------------");
    }
    /**
     * Retorna el usuario asociado a la compra.
     *
     * @return usuario como Usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Asigna el usuario asociado a la compra.
     *
     * @param usuario Nuevo usuario. No puede ser nulo.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna el evento asociado a la compra.
     *
     * @return evento como Evento.
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Asigna el evento asociado a la compra.
     *
     * @param evento Nuevo evento. No puede ser nulo.
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * Retorna la lista de entradas asociadas a la compra.
     *
     * @return Lista de entradas. No puede ser nula.
     */
    public List<Entrada> getEntradas() {
        return entradas;
    }

    /**
     * Retorna la lista de servicios adicionales asociados a la compra.
     *
     * @return Lista de servicios adicionales. No puede ser nula.
     */
    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    /**
     * Retorna el identificador unico de la compra.
     *
     * @return idCompra como entero.
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * Asigna el identificador unico de la compra.
     *
     * @param idCompra Nuevo identificador. Debe ser mayor que cero.
     */
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * Retorna la fecha de creacion de la compra.
     *
     * @return fechaCreacion como Date.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Asigna la fecha de creacion de la compra.
     *
     * @param fechaCreacion Nueva fecha. No puede ser nula.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Retorna el total de la compra incluyendo entradas y servicios adicionales.
     *
     * @return total como double.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Asigna el total de la compra.
     *
     * @param total Nuevo total. Debe ser mayor o igual a cero.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Retorna el estado actual de la compra.
     *
     * @return estadoCompra como EstadoCompra.
     */
    public EstadoCompra getEstadoCompra() {
        return estadoCompra;
    }

    /**
     * Asigna el estado actual de la compra.
     *
     * @param estadoCompra Nuevo estado. No puede ser nulo.
     */
    public void setEstadoCompra(EstadoCompra estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    /**
     * Retorna el tipo de pago utilizado en la compra.
     *
     * @return tipoPago como TipoPago.
     */
    public TipoPago getTipoPago() {
        return tipoPago;
    }

    /**
     * Asigna el tipo de pago de la compra.
     *
     * @param tipoPago Nuevo tipo de pago. No puede ser nulo.
     */
    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }
}
