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

    /**
     * Anula la entrada cambiando su estado a ANULADA.
     * Se usa cuando la compra asociada es cancelada o reembolsada.
     */
    public void anularEntrada() {
        this.estadoEntrada = EstadoEntrada.ANULADA;
    }

    /**
     * Marca la entrada como usada cambiando su estado a USADA.
     * Se usa cuando el usuario ingresa al evento.
     */
    public void usarEntrada() {
        this.estadoEntrada = EstadoEntrada.USADA;
    }

    /**
     * Verifica si la entrada se encuentra activa.
     *
     * @return true si el estado es ACTIVA, false en caso contrario.
     */
    public boolean estaActiva() {
        return this.estadoEntrada == EstadoEntrada.ACTIVA;
    }
    /**
     * Permite que un visitante acceda a la información de la entrada
     * aplicando el patrón Visitor.
     *
     * @param visitor visitante que realizará una operación sobre la entrada.
     */
    @Override
    public void aceptarVisitante(ReporteVisitor visitor) {
        visitor.visitarEntrada(this);
    }
    /**
     * Consulta y retorna la información actual de la entrada,
     * incluyendo su identificador, precio, estado y asiento asignado.
     *
     * @return cadena con los datos actuales de la entrada.
     */
    public String consultarEntrada() {
        return "Entrada{" +
                "idEntrada=" + idEntrada +
                ", precioFinal=" + precioFinal +
                ", estadoEntrada=" + estadoEntrada +
                ", asiento=" + asiento +
                '}';
    }
    /**
     * Obtiene la zona asignada a la entrada.
     *
     * @return zona asociada a la entrada.
     */
    public Zona getZona() {
        return zona;
    }

    /**
     * Asigna una zona a la entrada.
     *
     * @param zona nueva zona de la entrada.
     */
    public void setZona(Zona zona) {
        this.zona = zona;
    }

    /**
     * Obtiene el asiento asignado a la entrada.
     *
     * @return asiento asociado a la entrada.
     */
    public Asiento getAsiento() {
        return asiento;
    }

    /**
     * Asigna un asiento a la entrada.
     *
     * @param asiento nuevo asiento de la entrada.
     */
    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    /**
     * Obtiene el identificador de la entrada.
     *
     * @return identificador de la entrada.
     */
    public int getIdEntrada() {
        return idEntrada;
    }

    /**
     * Asigna el identificador de la entrada.
     *
     * @param idEntrada nuevo identificador de la entrada.
     */
    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    /**
     * Obtiene el precio final de la entrada.
     *
     * @return precio final de la entrada.
     */
    public double getPrecioFinal() {
        return precioFinal;
    }

    /**
     * Asigna el precio final de la entrada.
     *
     * @param precioFinal nuevo precio final de la entrada.
     */
    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    /**
     * Obtiene el estado actual de la entrada.
     *
     * @return estado actual de la entrada.
     */
    public EstadoEntrada getEstadoEntrada() {
        return estadoEntrada;
    }

    /**
     * Asigna el estado actual de la entrada.
     *
     * @param estadoEntrada nuevo estado de la entrada.
     */
    public void setEstadoEntrada(EstadoEntrada estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }
}
