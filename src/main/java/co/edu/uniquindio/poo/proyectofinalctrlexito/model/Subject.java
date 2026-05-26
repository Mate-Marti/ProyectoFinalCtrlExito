package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface Subject {
    /**
     * Agrega un observador a la lista de observers del sujeto.
     *
     * @param observer observador que será agregado.
     */
    void agregarObserver(Observer observer);

    /**
     * Elimina un observador de la lista de observers del sujeto.
     *
     * @param observer observador que será eliminado.
     */
    void eliminarObserver(Observer observer);

    /**
     * Notifica a todos los observadores registrados
     * enviando un mensaje determinado.
     *
     * @param mensaje mensaje enviado a los observers.
     */
    void notificarObservers(String mensaje);
}
