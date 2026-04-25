package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private int idPlataforma;
    private String tipo;
    private String descripcion;
    private LocalDate fecha;
    private List<Persona> listaPersonas;

    public Plataforma(int idPlataforma, String tipo, String descripcion) {
        this.idPlataforma = idPlataforma;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
        this.listaPersonas= new ArrayList<>();
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

    public boolean buscarUsuario(String id){
       for(Persona persona:listaPersonas){
           if(persona instanceof Usuario){
               Usuario user = (Usuario) persona;
               if((user.getId().equals(id))){
                   return true;
           }
           }
       }

        return false;
    }
    public void registrarUsuario(String id,String nombre,String correo,String telefono,String metodoPago){
        if (buscarUsuario(id)) {
            System.out.println("El usuario ya existe");
            return;
        }

        Usuario nuevo = new Usuario(id, nombre,correo,telefono,metodoPago);
        listaPersonas.add(nuevo);

        System.out.println("Usuario registrado correctamente");
    }
    }

