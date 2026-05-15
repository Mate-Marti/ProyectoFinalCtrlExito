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

    //Metodo para cancalar la compra
    public void cancelarCompra() {
        this.estadoCompra = EstadoCompra.CANCELADA;
    }

    //Metodo para consultar la compra
    public String consultarCompra() {
        return "Numero de compra:" + idCompra + "Total:" + total + "Estado de la Compra:" + estadoCompra + "TipoPago:" + tipoPago;
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
