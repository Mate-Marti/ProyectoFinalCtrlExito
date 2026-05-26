package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface Seguridad {
    /**
     * Valida el inicio de sesión de un usuario en el sistema.
     *
     * @param usuario nombre de usuario ingresado.
     * @param contrasenia contraseña ingresada.
     * @return true si las credenciales son correctas,
     * false en caso contrario.
     */
    public boolean login(String usuario, String contrasenia);

    /**
     * Permite realizar la gestión de usuarios del sistema.
     */
    public void gestionarUsuarios();

    /**
     * Permite realizar la gestión de eventos del sistema.
     */
    public void gestionarEventos();
}
