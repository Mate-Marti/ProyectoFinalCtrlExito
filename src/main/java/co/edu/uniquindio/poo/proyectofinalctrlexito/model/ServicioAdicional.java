package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class ServicioAdicional {
    private int idServicio;
    private String nombre;
    private String descripcion;
    private double precio;

    public ServicioAdicional(int idServicio,
                             String nombre,
                             String descripcion,
                             double precio) {

        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String mostrarServicio() {

        return "Servicio: " + nombre +
                "\nDescripcion: " + descripcion +
                "\nPrecio: $" + precio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
