package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface Subject {
    void agregarObserver(Observer observer);

    void eliminarObserver(Observer observer);

    void notificarObservers(String mensaje);
}
