package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Entrada {

    private int idEntrada;
    private double precioFinal;
    private EstadoEntrada estadoEntrada;

    public Entrada(double precioFinal, EstadoEntrada estadoEntrada) {

        this.precioFinal = precioFinal;
        this.estadoEntrada = estadoEntrada;

    }

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public EstadoEntrada getEstadoEntrada() {
        return estadoEntrada;
    }

    public void setEstadoEntrada(EstadoEntrada estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }
}
