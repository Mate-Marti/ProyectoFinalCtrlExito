package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Recinto implements ComponenteRecinto, Visitor {

    private int idRecinto;
    private String nombre;
    private String direccion;
    private String ciudad;
    private List<Zona> listaZonas;

    /**
     * Constructor de la clase Recinto.
     * Inicializa los atributos principales del recinto y
     * crea una lista vacía de zonas.
     *
     * @param idRecinto identificador único del recinto.
     * @param nombre nombre del recinto.
     * @param direccion dirección del recinto.
     * @param ciudad ciudad donde se encuentra el recinto.
     */
    public Recinto(int idRecinto,
                   String nombre,
                   String direccion,
                   String ciudad) {

        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.listaZonas = new ArrayList<Zona>();
    }

    /**
     * Crea una nueva instancia de un recinto.
     *
     * @param idRecinto identificador único del recinto.
     * @param nombre nombre del recinto.
     * @param direccion dirección del recinto.
     * @param ciudad ciudad donde se encuentra el recinto.
     * @return nuevo objeto Recinto creado.
     */
    public static Recinto crearRecinto(int idRecinto,
                                       String nombre,
                                       String direccion,
                                       String ciudad) {

        return new Recinto(
                idRecinto,
                nombre,
                direccion,
                ciudad
        );
    }

    /**
     * Actualiza la información básica del recinto.
     *
     * @param nombre nuevo nombre del recinto.
     * @param direccion nueva dirección del recinto.
     * @param ciudad nueva ciudad del recinto.
     */
    public void actualizarRecinto(String nombre,
                                  String direccion,
                                  String ciudad) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    /**
     * Agrega una nueva zona al recinto.
     *
     * @param nuevaZona zona que será agregada al recinto.
     */
    public void agregarZona(Zona nuevaZona) {
        this.listaZonas.add(nuevaZona);
    }

    /**
     * Elimina la información del recinto y limpia
     * la lista de zonas registradas.
     */
    public void eliminarRecinto() {

        this.idRecinto = 0;
        this.nombre = null;
        this.direccion = null;
        this.ciudad = null;

        this.listaZonas.clear();
    }

    /**
     * Retorna en formato texto la información básica del recinto.
     *
     * @return información del recinto en formato String.
     */
    public String listarRecinto() {

        return "ID:" + idRecinto +
                " Nombre:" + nombre +
                " Direccion:" + direccion +
                " Ciudad:" + ciudad;
    }

    /**
     * Calcula la disponibilidad total del recinto
     * sumando la disponibilidad de todas sus zonas.
     *
     * @return cantidad total de espacios disponibles.
     */
    @Override
    public int obtenerDisponibilidad() {

        int totalDisponibles = 0;

        for (int i = 0; i < listaZonas.size(); i++) {

            totalDisponibles = totalDisponibles +
                    listaZonas.get(i).obtenerDisponibilidad();
        }

        return totalDisponibles;
    }

    /**
     * Permite que un visitante genere reportes
     * utilizando el patrón Visitor.
     *
     * @param visitor visitante encargado de procesar el recinto.
     */
    @Override
    public void aceptarVisitante(ReporteVisitor visitor){
        visitor.visitarRecinto(this);
    }

    /**
     * Obtiene el identificador del recinto.
     *
     * @return identificador único del recinto.
     */
    public int getIdRecinto() {
        return idRecinto;
    }

    /**
     * Establece el identificador del recinto.
     *
     * @param idRecinto nuevo identificador del recinto.
     */
    public void setIdRecinto(int idRecinto) {
        this.idRecinto = idRecinto;
    }

    /**
     * Obtiene el nombre del recinto.
     *
     * @return nombre del recinto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del recinto.
     *
     * @param nombre nuevo nombre del recinto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del recinto.
     *
     * @return dirección del recinto.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la dirección del recinto.
     *
     * @param direccion nueva dirección del recinto.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la ciudad donde se encuentra el recinto.
     *
     * @return ciudad del recinto.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modifica la ciudad del recinto.
     *
     * @param ciudad nueva ciudad del recinto.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
