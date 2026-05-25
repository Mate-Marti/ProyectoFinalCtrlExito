package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Entrada implements Visitor{

    private int idEntrada;
    private double precioFinal;
    private EstadoEntrada estadoEntrada;
    private Asiento asiento;
    private Zona zona;
    private static int contador = 1;

    public Entrada(double precioFinal, EstadoEntrada estadoEntrada,Asiento asiento,Zona zona) {
        this.idEntrada = contador++;
        this.precioFinal = precioFinal;
        this.estadoEntrada = estadoEntrada;
        this.asiento= asiento;
        this.zona=zona;

    }
    public void anularEntrada() {
        this.estadoEntrada = EstadoEntrada.ANULADA;
    }
    public void usarEntrada() {
        this.estadoEntrada = EstadoEntrada.USADA;
    }

    public boolean estaActiva() {
        return this.estadoEntrada == EstadoEntrada.ACTIVA;
    }

    //Metodo complementario al patron Visitor
    @Override
    public void aceptarVisitante(ReporteVisitor visitor) {
        visitor.visitarEntrada(this);
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
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
