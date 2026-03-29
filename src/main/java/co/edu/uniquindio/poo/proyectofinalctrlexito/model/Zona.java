package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Zona {

    private int idZona;
    private String nombre;
    private String capacidad;
    private double preciobase;

    public Zona(int idZona, String nombre, String capacidad, double preciobase) {

        this.idZona = idZona;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.preciobase = preciobase;

    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public double getPreciobase() {
        return preciobase;
    }

    public void setPreciobase(double preciobase) {
        this.preciobase = preciobase;
    }
}
