package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class ServicioAdicional {
    private int idServicio;
    private String nombre;
    private String descripcion;
    private double precio;

    /**
     * Constructor de la clase ServicioAdicional.
     * Inicializa los atributos principales del servicio.
     *
     * @param idServicio identificador único del servicio.
     * @param nombre nombre del servicio adicional.
     * @param descripcion descripción del servicio.
     * @param precio precio del servicio adicional.
     */
    public ServicioAdicional(int idServicio,
                             String nombre,
                             String descripcion,
                             double precio) {

        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Retorna la información del servicio adicional en formato texto.
     *
     * @return información del servicio adicional.
     */
    public String mostrarServicio() {

        return "Servicio: " + nombre +
                "\nDescripcion: " + descripcion +
                "\nPrecio: $" + precio;
    }

    /**
     * Obtiene el identificador del servicio adicional.
     *
     * @return identificador del servicio.
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * Modifica el identificador del servicio adicional.
     *
     * @param idServicio nuevo identificador del servicio.
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * Obtiene el nombre del servicio adicional.
     *
     * @return nombre del servicio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del servicio adicional.
     *
     * @param nombre nuevo nombre del servicio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del servicio adicional.
     *
     * @return descripción del servicio.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripción del servicio adicional.
     *
     * @param descripcion nueva descripción del servicio.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del servicio adicional.
     *
     * @return precio del servicio.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio del servicio adicional.
     *
     * @param precio nuevo precio del servicio.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
