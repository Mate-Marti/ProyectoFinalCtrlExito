package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class VerificacionAdmin implements Seguridad {
    private Administrador administrador;
    /**
     * Constructor de la clase VerificacionAdmin.
     * Inicializa las credenciales del administrador.
     *
     * @param usuario nombre de usuario del administrador.
     * @param contrasenia contraseña del administrador.
     */
    public VerificacionAdmin(String usuario, String contrasenia) {

        this.administrador = new Administrador(
                usuario,
                contrasenia
        );
    }

    /**
     * Verifica las credenciales del administrador
     * para permitir el acceso al sistema.
     *
     * @param usuario nombre de usuario ingresado.
     * @param contrasenia contraseña ingresada.
     * @return true si las credenciales son correctas,
     * false en caso contrario.
     */
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

    /**
     * Permite gestionar los usuarios del sistema
     * si el administrador inicia sesión correctamente.
     */
    @Override
    public void gestionarUsuarios() {

        if (login(administrador.getUsuario(),
                administrador.getContrasenia())) {

            System.out.println(
                    "Acceso permitido: gestionando usuarios..."
            );

        } else {

            System.out.println(
                    "Acceso denegado a gestionar usuarios."
            );
        }
    }

    /**
     * Permite gestionar los eventos del sistema
     * si el administrador inicia sesión correctamente.
     */
    @Override
    public void gestionarEventos() {

        if (login(administrador.getUsuario(),
                administrador.getContrasenia())) {

            System.out.println(
                    "Acceso permitido: gestionando eventos..."
            );

        } else {

            System.out.println(
                    "Acceso denegado a gestionar eventos."
            );
        }
    }
}
