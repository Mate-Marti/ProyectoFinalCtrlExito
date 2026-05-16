package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Zona implements ComponenteRecinto, Visitor {

    private int idZona;
    private String nombre;
    private int capacidad;
    private double preciobase;
    private List<Asiento> listaAsientos;

    public Zona(int idZona, String nombre, int capacidad, double preciobase) {

        this.idZona = idZona;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.preciobase = preciobase;
        this.listaAsientos = new ArrayList<>();

    }

    //Metodo para crearla zona
    public static Zona crearZona(int idZona, String nombre, int capacidad, double preciobase) {
        return new Zona(idZona, nombre, capacidad, preciobase);
    }

    //Metodo para actualizr la zona
    public void actualizarZona(String nombre, int capacidad, double preciobase) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.preciobase = preciobase;
    }

    //Metodo para eliminar la zona
    public void eliminarZona() {
        this.listaAsientos.remove(this.idZona);
        this.idZona = 0;
        this.nombre = null;
        this.capacidad = 0;
        this.preciobase = 0;
    }

    //Metodo para retornar en texto la info de la zona
    public String listarZona() {
        return "ID de la zona:" + idZona + "Nombre: " + nombre + "Capacidad: " + capacidad + "Preciobase: " + preciobase;
    }

    //Metodo para consultar la capacidad de la Zona
    public int consultarCapacidadZona() {
        int capacidad = 0;
        for (Asiento asiento : listaAsientos) {
            if (asiento.getEstado() == EstadoAsiento.DISPONIBLE) {
                capacidad++;
            }
        }

        return capacidad;
    }

    //metodo para obtener la disponibilidad
    @Override
    public int obtenerDisponibilidad() {
        int totalDisponibles = 0;
        for (int i = 0; i < listaAsientos.size(); i++) {
            totalDisponibles = totalDisponibles + listaAsientos.get(i).obtenerDisponibilidad();
        }
        return totalDisponibles;
    }

    //Metodo complementario all patron Visitor
    @Override
    public void aceptarVisitante(ReporteVisitor visitor) {
        visitor.visitarZona(this);
    }

    //getters y setters
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPreciobase() {
        return preciobase;
    }

    public void setPreciobase(double preciobase) {
        this.preciobase = preciobase;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }
}
