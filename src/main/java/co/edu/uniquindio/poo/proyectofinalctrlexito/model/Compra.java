package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class Compra {

    private int idCompra;
    private Date fechaCreacion;
    private double total;
    private EstadoCompra estadoCompra;
    //private List<>
    private TipoPago tipoPago;

    private Compra(Builder builder) {
        this.idCompra = builder.idCompra;
        this.fechaCreacion = builder.fechaCreacion;
        this.total = builder.total;
        this.estadoCompra = new EstadoCreada();
        this.tipoPago = builder.tipoPago;
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
                "\nTotal: " + total +
                "\nEstado: " + estadoCompra.mostrarEstado() +
                "\nTipo pago: " + tipoPago;
    }


    //
    // Builder
    //

    public static class Builder {

        private int idCompra;
        private Date fechaCreacion;
        private double total;
        private TipoPago tipoPago;

        public Builder() {
        }

        public Builder setIdCompra(int idCompra) {
            this.idCompra = idCompra;
            return this;
        }

        public Builder setFechaCreacion(Date fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
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

        public Compra build() {
            return new Compra(this);
        }
    }

    //getters y setters

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
}
