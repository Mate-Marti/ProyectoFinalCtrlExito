package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class VerificacionAdmin implements Seguridad {
    private Administrador administrador;

    public VerificacionAdmin(String usuario, String contrasenia) {
        this.administrador = new Administrador(usuario, contrasenia);
    }

    @Override
    public boolean login(String usuario, String contrasenia) {
        if (administrador.getUsuario().equals(usuario) &&
                administrador.getContrasenia().equals(contrasenia)) {
            System.out.println("Login exitoso.");
            return true;
        }
        System.out.println("Credenciales incorrectas.");
        return false;
    }

    @Override
    public void gestionarUsuarios() {
        if (login(administrador.getUsuario(), administrador.getContrasenia())) {
            System.out.println("Acceso permitido: gestionando usuarios...");
        } else {
            System.out.println("Acceso denegado a gestionar usuarios.");
        }
    }

    @Override
    public void gestionarEventos() {
        if (login(administrador.getUsuario(), administrador.getContrasenia())) {
            System.out.println("Acceso permitido: gestionando eventos...");
        } else {
            System.out.println("Acceso denegado a gestionar eventos.");
        }
    }
}
