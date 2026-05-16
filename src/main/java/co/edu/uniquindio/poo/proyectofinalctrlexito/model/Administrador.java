package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Administrador extends Persona implements Seguridad {

    private String usuario;
    private String contrasenia;

    public Administrador(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean login(String usuario, String contrasenia) {
        return this.usuario.equals(usuario) && this.contrasenia.equals(contrasenia);
    }

    @Override
    public void gestionarUsuarios() {
        System.out.println("Administrador gestionando usuarios...");
    }

    @Override
    public void gestionarEventos() {
        System.out.println("Administrador gestionando eventos...");
    }
}
