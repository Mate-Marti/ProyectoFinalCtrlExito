package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

public class Recinto implements ComponenteRecinto {

    private int idRecinto;
    private String nombre;
    private String direccion;
    private String ciudad;
    private List<Zona> listaZonas;

    public Recinto(int idRecinto, String nombre, String direccion, String ciudad) {

        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.listaZonas = new ArrayList<Zona>();

    }

    //Metodo para crear un nuevo recinto
    public static Recinto crearRecinto(int idRecinto, String nombre, String direccion, String ciudad) {
        return new Recinto(idRecinto, nombre, direccion, ciudad );
    }

    //metodo para actualizar datos del recinto
    public void actualizarRecinto(String nombre, String direccion, String ciudad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    //Metodo para agregar una zona al recinto
    public void agregarZona(Zona nuevaZona) {
        this.listaZonas.add(nuevaZona);
    }

    //Metodo para elinimar el recinto
    public void eliminarRecinto() {
        this.idRecinto = 0;
        this.nombre = null;
        this.direccion = null;
        this.ciudad = null;
        this.listaZonas.clear();
    }

    //Metodo para retornar en texto la info del recinto
    public String listarRecinto() {
        return "ID:" + idRecinto + "Nombre:" + nombre + "Direccion:" + direccion + "Ciudad:" + ciudad;
    }

    @Override
    public int obtenerDisponibilidad() {
        int totalDisponibles = 0;
        for (int i = 0; i < listaZonas.size(); i++) {
            totalDisponibles = totalDisponibles + listaZonas.get(i).obtenerDisponibilidad();
        }
        return totalDisponibles;
    }

    public int getIdRecinto() {
        return idRecinto;
    }

    public void setIdRecinto(int idRecinto) {
        this.idRecinto = idRecinto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
