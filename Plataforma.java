package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.time.LocalDate;

public class Plataforma {
    private int idPlataforma;
    private String tipo;
    private String descripcion;
    private LocalDate fecha;

    public Plataforma(int idPlataforma, String tipo, String descripcion) {
        this.idPlataforma = idPlataforma;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
