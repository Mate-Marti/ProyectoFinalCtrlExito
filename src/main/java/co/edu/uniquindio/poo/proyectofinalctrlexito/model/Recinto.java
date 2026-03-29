package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Recinto {

    private int idRecinto;
    private String nombre;
    private String direccion;
    private String ciudad;

    public Recinto(int idRecinto, String nombre, String direccion, String ciudad) {

        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;

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
