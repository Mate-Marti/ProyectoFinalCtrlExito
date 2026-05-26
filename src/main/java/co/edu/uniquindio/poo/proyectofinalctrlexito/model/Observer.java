package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface Observer {
    /**
     * Recibe y procesa una notificación enviada
     * por el objeto observado.
     *
     * @param mensaje mensaje enviado al observador.
     */
    void actualizar(String mensaje);
}
