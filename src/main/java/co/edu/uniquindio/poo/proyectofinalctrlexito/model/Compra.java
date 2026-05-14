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
        this.estadoCompra = builder.estadoCompra;
        this.tipoPago = builder.tipoPago;
    }
    public void cancelarCompra() {
        this.estadoCompra = EstadoCompra.CANCELADA;
    }

    //Metodo para consultar la compra
    public String consultarCompra() {
        return "Numero de compra: " + idCompra +
                " Total: " + total +
                " Estado de la Compra: " + estadoCompra +
                " TipoPago: " + tipoPago;
    }

    //
    // Builder
    //

    public static class Builder {

        private int idCompra;
        private Date fechaCreacion;
        private double total;
        private EstadoCompra estadoCompra;
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

        public Builder setEstadoCompra(EstadoCompra estadoCompra) {
            this.estadoCompra = estadoCompra;
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
