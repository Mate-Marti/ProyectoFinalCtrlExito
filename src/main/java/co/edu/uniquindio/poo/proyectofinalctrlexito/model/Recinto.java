package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Recinto {

    private int idRecinto;
    private String nombre;
    private String direccion;
    private String ciudad;
    private Zona zona;

    public Recinto(int idRecinto, String nombre, String direccion, String ciudad , Zona zona) {

        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.zona = zona;

    }

    //Metodo para crear un nuevo recinto
    public static Recinto crearRecinto(int idRecinto, String nombre, String direccion, String ciudad, Zona zona) {
        return new Recinto(idRecinto, nombre, direccion, ciudad, zona);
    }

    //metodo para actualizar datos del recinto
    public void actualizarRecinto(String nombre, String direccion, String ciudad, Zona zona) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.zona = zona;
    }

    //Metodo para elinimar el recinto
    public void eliminarRecinto() {
        this.idRecinto = 0;
        this.nombre = null;
        this.direccion = null;
        this.ciudad = null;
        this.zona = null;
    }

    //Metodo para retornar en texto la info del recinto
    public String listarRecinto() {
        return "ID:" + idRecinto + "Nombre:" + nombre + "Direccion:" + direccion + "Ciudad:" + ciudad + "Zona:" + zona;
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
