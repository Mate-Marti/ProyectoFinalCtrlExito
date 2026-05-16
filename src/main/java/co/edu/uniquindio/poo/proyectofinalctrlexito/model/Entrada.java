package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Entrada {

    private int idEntrada;
    private double precioFinal;
    private EstadoEntrada estadoEntrada;
    private Asiento asiento;

    public Entrada(double precioFinal, EstadoEntrada estadoEntrada,Asiento asiento) {

        this.precioFinal = precioFinal;
        this.estadoEntrada = estadoEntrada;
        this.asiento= asiento;

    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
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

    public String consultarEntrada() {
        return "Entrada{" +
                "idEntrada=" + idEntrada +
                ", precioFinal=" + precioFinal +
                ", estadoEntrada=" + estadoEntrada +
                ", asiento=" + asiento +
                '}';
    }
}
