package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Asiento {

    private int idAsiento;
    private String fila;
    private String numero;
    private String estado;

    public Asiento(int idAsiento, String fila, String numero, String estado) {

        this.idAsiento = idAsiento;
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;

    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
