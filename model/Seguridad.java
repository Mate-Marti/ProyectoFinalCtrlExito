package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface Seguridad {
    public boolean login(String usuario, String contrasenia);
    public void gestionarUsuarios();
    public void gestionarEventos();
}
