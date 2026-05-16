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
    public void pagarCompra() {
        estadoCompra.pagar(this);
    }

    public void confirmarCompra() {
        estadoCompra.confirmar(this);
    }

    public void cancelarCompra() {
        estadoCompra.cancelar(this);
    }
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

    //Metodo complementario al patron Visitor
    @Override
    public void aceptarVisitante(ReporteVisitor visitor) {
        visitor.visitarCompra(this);
    }

    //getters y setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }
    public List<Entrada> getEntradas() {
        return entradas;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoCompra getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(EstadoCompra estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }
    public void agregarServicio(ServicioAdicional servicio) {

        serviciosAdicionales.add(servicio);

        calcularTotal();
    }
    public void mostrarServicios() {

        for(ServicioAdicional servicio : serviciosAdicionales) {

            System.out.println(servicio.mostrarServicio());

            System.out.println("----------------");
        }
    }
    public void agregarEntrada(Entrada entrada) {
        Asiento asiento = entrada.getAsiento();
        if(asiento.validarDisponibilidad()) {
            entradas.add(entrada);
            asiento.reservarAsiento();
            calcularTotal();
            System.out.println("Entrada agregada correctamente");
        } else {
            System.out.println("El asiento no esta disponible");
        }
    }

    public void calcularTotal() {

        total = 0;

        for(Entrada entrada : entradas) {
            total += entrada.getPrecioFinal();
        }

        for(ServicioAdicional servicio : serviciosAdicionales) {
            total += servicio.getPrecio();
        }
    }
    public void modificarCompra(TipoPago nuevoTipoPago) {
        if(estadoCompra instanceof EstadoCreada) {

            this.tipoPago = nuevoTipoPago;

            System.out.println("Compra modificada correctamente");

        } else {

            System.out.println("Solo se puede modificar antes de pagar");
        }
    }
    public void mostrarEntradas() {

        for(Entrada entrada : entradas) {

            System.out.println(entrada.consultarEntrada());

            System.out.println("----------------");
        }
    }
    public void eliminarEntrada(Entrada entrada) {

        entradas.remove(entrada);

        entrada.getAsiento().liberarAsiento();

        calcularTotal();
    }
    public void eliminarServicio(ServicioAdicional servicio) {

        serviciosAdicionales.remove(servicio);

        calcularTotal();
    }
}
