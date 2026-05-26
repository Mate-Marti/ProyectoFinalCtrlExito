package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Zona implements ComponenteRecinto, Visitor {

    private int idZona;
    private String nombre;
    private int capacidad;
    private double preciobase;
    private List<Asiento> listaAsientos;

    /**
     * Constructor de la clase Zona.
     * Inicializa los atributos principales de la zona
     * y crea una lista vacía de asientos.
     *
     * @param idZona identificador único de la zona.
     * @param nombre nombre de la zona.
     * @param capacidad capacidad máxima de la zona.
     * @param preciobase precio base de la zona.
     */
    public Zona(int idZona,
                String nombre,
                int capacidad,
                double preciobase) {

        this.idZona = idZona;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.preciobase = preciobase;
        this.listaAsientos = new ArrayList<>();
    }

    /**
     * Crea una nueva instancia de una zona.
     *
     * @param idZona identificador único de la zona.
     * @param nombre nombre de la zona.
     * @param capacidad capacidad máxima de la zona.
     * @param preciobase precio base de la zona.
     * @return nueva instancia de Zona.
     */
    public static Zona crearZona(int idZona,
                                 String nombre,
                                 int capacidad,
                                 double preciobase) {

        return new Zona(
                idZona,
                nombre,
                capacidad,
                preciobase
        );
    }

    /**
     * Actualiza la información básica de la zona.
     *
     * @param nombre nuevo nombre de la zona.
     * @param capacidad nueva capacidad de la zona.
     * @param preciobase nuevo precio base de la zona.
     */
    public void actualizarZona(String nombre,
                               int capacidad,
                               double preciobase) {

        this.nombre = nombre;
        this.capacidad = capacidad;
        this.preciobase = preciobase;
    }

    /**
     * Elimina la información de la zona y limpia
     * la lista de asientos asociados.
     */
    public void eliminarZona() {

        this.listaAsientos.remove(this.idZona);

        this.idZona = 0;
        this.nombre = null;
        this.capacidad = 0;
        this.preciobase = 0;
    }

    /**
     * Retorna la información básica de la zona en formato texto.
     *
     * @return información de la zona.
     */
    public String listarZona() {

        return "ID de la zona:" + idZona +
                "Nombre: " + nombre +
                "Capacidad: " + capacidad +
                "Preciobase: " + preciobase;
    }

    /**
     * Consulta la cantidad de asientos disponibles
     * en la zona.
     *
     * @return cantidad de asientos disponibles.
     */
    public int consultarCapacidadZona() {

        int capacidad = 0;

        for (Asiento asiento : listaAsientos) {

            if (asiento.getEstado() == EstadoAsiento.DISPONIBLE) {

                capacidad++;
            }
        }

        return capacidad;
    }

    /**
     * Calcula la disponibilidad total de la zona
     * sumando la disponibilidad de todos sus asientos.
     *
     * @return cantidad total de asientos disponibles.
     */
    @Override
    public int obtenerDisponibilidad() {

        int totalDisponibles = 0;

        for (int i = 0; i < listaAsientos.size(); i++) {

            totalDisponibles =
                    totalDisponibles +
                            listaAsientos.get(i).obtenerDisponibilidad();
        }

        return totalDisponibles;
    }

    /**
     * Permite que un visitante procese la información
     * de la zona utilizando el patrón Visitor.
     *
     * @param visitor visitante encargado de procesar la zona.
     */
    @Override
    public void aceptarVisitante(ReporteVisitor visitor) {

        visitor.visitarZona(this);
    }

    /**
     * Obtiene el identificador de la zona.
     *
     * @return identificador único de la zona.
     */
    public int getIdZona() {
        return idZona;
    }

    /**
     * Modifica el identificador de la zona.
     *
     * @param idZona nuevo identificador de la zona.
     */
    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    /**
     * Obtiene el nombre de la zona.
     *
     * @return nombre de la zona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la zona.
     *
     * @param nombre nuevo nombre de la zona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la capacidad máxima de la zona.
     *
     * @return capacidad de la zona.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Modifica la capacidad máxima de la zona.
     *
     * @param capacidad nueva capacidad de la zona.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el precio base de la zona.
     *
     * @return precio base de la zona.
     */
    public double getPreciobase() {
        return preciobase;
    }

    /**
     * Modifica el precio base de la zona.
     *
     * @param preciobase nuevo precio base de la zona.
     */
    public void setPreciobase(double preciobase) {
        this.preciobase = preciobase;
    }

    /**
     * Obtiene la lista de asientos asociados a la zona.
     *
     * @return lista de asientos de la zona.
     */
    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }
}
