package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.Date;

public class Compra {

    private int idCompra;
    private Date fechaCreacion;
    private double total;
    private EstadoCompra estadoCompra;
    //private List<>
    private TipoPago tipoPago;

    public Compra(int idCompra,Date fechaCreacion,double total,EstadoCompra estadoCompra,TipoPago tipoPago) {

        this.idCompra = idCompra;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.estadoCompra = estadoCompra;
        this.tipoPago = tipoPago;

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
}
