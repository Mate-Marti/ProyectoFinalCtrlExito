package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Asiento{

    private int idAsiento;
    private String fila;
    private String numero;
    private EstadoAsiento estado;

    public Asiento(int idAsiento, String fila, String numero) {

        this.idAsiento = idAsiento;
        this.fila = fila;
        this.numero = numero;
        this.estado = EstadoAsiento.DISPONIBLE;

    }

    //Metodo para habilitar el asiento
    public void habilitarAsiento() {
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    //Metodo para bloquear el asiento
    public void bloquearAsiento() {
        this.estado = EstadoAsiento.BLOQUEADO;
    }
    //Metodo para reservar el asiento
    public void reservarAsiento() {
        this.estado = EstadoAsiento.RESERVADO;
    }
    //Metodo para vender el asiento
    public void venderAsiento() {
        this.estado = EstadoAsiento.VENDIDO;
    }
    //Metodo para liberar el asiento
    public void liberarAsiento() {
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    //Metodo para consultar el estado del asiento
    public String consultarAsiento() {
        return "ID asiento: " + idAsiento +
                "\nFila: " + fila +
                "\nNumero: " + numero +
                "\nEstado: " + estado;
    }
    public boolean validarDisponibilidad() {

        return estado == EstadoAsiento.DISPONIBLE;
    }
    public int obtenerDisponibilidad() {

        if (validarDisponibilidad()) {
            return 1;
        }

        return 0;
    }
    //Getters y Seters
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

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }
}
