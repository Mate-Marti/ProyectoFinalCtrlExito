package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Plataforma {
    private int idPlataforma;
    private String tipo;
    private String descripcion;
    private LocalDate fecha;
    private List<Persona> listaPersonas;
    private List<Evento> listaEventos;
    private List<Recinto> listaRecintos;
    private List<Asiento> listaAsientos;
    private List<Incidencia> listaIncidencias;

    // ==========================================
    // SINGLETON
    // ==========================================
    private static Plataforma instancia;

    /**
     * Retorna la única instancia de la plataforma.
     * Si no existe, la crea con valores por defecto.
     *
     * @return instancia única de Plataforma.
     */
    public static Plataforma getInstancia() {
        if (instancia == null) {
            instancia = new Plataforma(1, "General", "Plataforma principal");
        }
        return instancia;
    }

    /**
     * Constructor de la clase Plataforma.
     * Inicializa los atributos principales de la plataforma y
     * crea las listas de personas y eventos vacías.
     *
     * @param idPlataforma identificador único de la plataforma.
     * @param tipo tipo de eventos manejados por la plataforma.
     * @param descripcion descripción general de la plataforma.
     */
    public Plataforma(int idPlataforma, String tipo, String descripcion) {
        this.idPlataforma = idPlataforma;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
        this.listaPersonas = new ArrayList<>();
        this.listaEventos = new ArrayList<>();
        this.listaRecintos = new ArrayList<>();
        this.listaAsientos = new ArrayList<>();
        this.listaIncidencias = new ArrayList<>();
    }

    /**
     * Obtiene el identificador de la plataforma.
     *
     * @return id de la plataforma.
     */
    public int getIdPlataforma() {
        return idPlataforma;
    }

    /**
     * Establece el identificador de la plataforma.
     *
     * @param idPlataforma nuevo identificador de la plataforma.
     */
    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    /**
     * Obtiene el tipo de la plataforma.
     *
     * @return tipo de eventos de la plataforma.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo de la plataforma.
     *
     * @param tipo nuevo tipo de eventos.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la descripción de la plataforma.
     *
     * @return descripción de la plataforma.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripción de la plataforma.
     *
     * @param descripcion nueva descripción de la plataforma.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha de creación o registro de la plataforma.
     *
     * @return fecha de la plataforma.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha de la plataforma.
     *
     * @param fecha nueva fecha de la plataforma.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Busca un usuario específico dentro de la lista de personas registradas
     * en la plataforma utilizando su identificador.
     *
     * @param id identificador del usuario a buscar.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean buscarUsuario(String id){
        for(Persona persona : listaPersonas){
            if(persona instanceof Usuario){
                Usuario user = (Usuario) persona;
                if(user.getId().equals(id)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Registra un nuevo usuario en la plataforma verificando previamente
     * que no exista otro usuario con el mismo identificador.
     *
     * @param id identificador único del usuario.
     * @param nombre nombre completo del usuario.
     * @param correo correo electrónico del usuario.
     * @param telefono número telefónico del usuario.
     * @param metodoPago método de pago asociado al usuario.
     */
    public void registrarUsuario(String id, String nombre,
                                 String correo, String telefono,
                                 String metodoPago, String contrasena) {
        if (buscarUsuario(id)) {
            System.out.println("El usuario ya existe");
            return;
        }
        Usuario nuevo = new Usuario(id, nombre, correo, telefono, metodoPago, contrasena);
        listaPersonas.add(nuevo);
        System.out.println("Usuario registrado correctamente");
    }

    /**
     * Actualiza la información de un usuario registrado en la plataforma.
     * Solo se modifican los datos que no sean nulos ni vacíos.
     *
     * @param id identificador del usuario a actualizar.
     * @param nuevoNombre nuevo nombre del usuario.
     * @param nuevoCorreo nuevo correo electrónico.
     * @param nuevoTelefono nuevo número telefónico.
     * @param nuevoMetodoPago nuevo método de pago.
     * @return true si el usuario fue actualizado correctamente,
     * false si no se encontró.
     */
    public boolean actualizarUsuario(String id,
                                     String nuevoNombre,
                                     String nuevoCorreo,
                                     String nuevoTelefono,
                                     String nuevoMetodoPago) {
        for (Persona persona : listaPersonas) {
            if (persona instanceof Usuario) {
                Usuario usuario = (Usuario) persona;
                if (usuario.getId().equals(id)) {
                    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                        usuario.setNombreCompleto(nuevoNombre);
                    }
                    if (nuevoCorreo != null && !nuevoCorreo.isEmpty()) {
                        usuario.setCorreo(nuevoCorreo);
                    }
                    if (nuevoMetodoPago != null && !nuevoMetodoPago.isEmpty()) {
                        usuario.setMetodoPago(nuevoMetodoPago);
                    }
                    if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
                        usuario.setTelefono(nuevoTelefono);
                    }
                    System.out.println("Usuario actualizado exitosamente.");
                    return true;
                }
            }
        }
        System.out.println("Usuario con ID " + id + " no encontrado.");
        return false;
    }

    /**
     * Elimina un usuario de la plataforma utilizando su identificador.
     *
     * @param id identificador del usuario a eliminar.
     * @return true si el usuario fue eliminado correctamente,
     * false si no se encontró.
     */
    public boolean eliminarUsuario(String id) {
        Iterator<Persona> iterator = listaPersonas.iterator();
        while (iterator.hasNext()) {
            Persona persona = iterator.next();
            if (persona instanceof Usuario) {
                Usuario usuario = (Usuario) persona;
                if (usuario.getId().equals(id)) {
                    iterator.remove();
                    System.out.println("Usuario eliminado exitosamente.");
                    return true;
                }
            }
        }
        System.out.println("Usuario con ID " + id + " no encontrado.");
        return false;
    }

    /**
     * Busca un evento específico dentro de la lista de eventos registrados
     * en la plataforma.
     *
     * @param id identificador del evento a buscar.
     * @return true si el evento existe, false en caso contrario.
     */
    public boolean buscarEvento(String id){
        for(Evento ev : listaEventos){
            if(ev.getIdEvento().equals(id)){
                return true;
            }
        }
        return false;
    }

    /**
     * Registra un nuevo evento en la plataforma verificando previamente
     * que no exista otro evento con el mismo identificador.
     *
     * @param idEvento identificador único del evento.
     * @param nombre nombre del evento.
     * @param categoria categoría del evento.
     * @param descripcion descripción del evento.
     */
    public void registrarEvento(String idEvento,
                                String nombre,
                                String categoria,
                                String descripcion) {
        for (Evento e : listaEventos) {
            if (e.getIdEvento().equals(idEvento)) {
                System.out.println("Ya existe un evento con el ID: " + idEvento);
                return;
            }
        }
        Evento nuevo = new Evento(idEvento, nombre, categoria, descripcion);
        listaEventos.add(nuevo);
        System.out.println("Evento registrado correctamente: " + nombre);
    }

    /**
     * Consulta el estado de una compra realizada por un usuario.
     *
     * @param idCompra identificador de la compra.
     * @return mensaje con el estado de la compra o null
     * si no se encuentra.
     */
    public String estadoCompra(int idCompra) {
        for (Persona persona : listaPersonas) {
            if (persona instanceof Usuario) {
                Usuario usuario = (Usuario) persona;
                for (Compra compra : usuario.getCompras()) {
                    if (compra.getIdCompra() == idCompra) {
                        return "Estado de compra " + idCompra + ": "
                                + compra.getEstadoCompra().mostrarEstado();
                    }
                }
            }
        }
        System.out.println("No se encontró la compra con ID: " + idCompra);
        return null;
    }

    /**
     * Muestra en consola todos los eventos registrados
     * en la plataforma.
     */
    public void listarEventos() {
        if (listaEventos.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }
        for (Evento evento : listaEventos) {
            System.out.println(evento.obtenerDetalleEvento());
            System.out.println("----------------");
        }
    }

    /**
     * Muestra en consola todos los usuarios registrados
     * en la plataforma.
     * Si no existen usuarios registrados, se informa mediante un mensaje.
     */
    public void listarUsuarios() {
        boolean hayUsuarios = false;
        for (Persona persona : listaPersonas) {
            if (persona instanceof Usuario) {
                Usuario usuario = (Usuario) persona;
                System.out.println(usuario);
                hayUsuarios = true;
            }
        }
        if (!hayUsuarios) {
            System.out.println("No hay usuarios registrados.");
        }
    }

    /**
     * Reasigna una entrada de una compra a un nuevo asiento.
     *
     * @param idCompra identificador de la compra.
     * @param entradaVieja entrada que será reasignada.
     * @param nuevoAsiento nuevo asiento asignado a la entrada.
     * @return true si la reasignación fue exitosa,
     * false si no se encontró la compra.
     * @throws IllegalArgumentException si la entrada o el asiento son nulos.
     */
    public boolean reasignarCompra(int idCompra,
                                   Entrada entradaVieja,
                                   Asiento nuevoAsiento) {
        if (nuevoAsiento == null || entradaVieja == null) {
            throw new IllegalArgumentException(
                    "La entrada y el asiento no pueden ser nulos."
            );
        }
        for (Persona persona : listaPersonas) {
            if (persona instanceof Usuario) {
                Usuario usuario = (Usuario) persona;
                for (Compra compra : usuario.getCompras()) {
                    if (compra.getIdCompra() == idCompra) {
                        compra.eliminarEntrada(entradaVieja);
                        entradaVieja.setAsiento(nuevoAsiento);
                        compra.agregarEntrada(entradaVieja);
                        System.out.println("Compra " + idCompra + " reasignada correctamente.");
                        return true;
                    }
                }
            }
        }
        System.out.println("No se encontró la compra con ID: " + idCompra);
        return false;
    }

    /**
     * Elimina un evento registrado en la plataforma utilizando su identificador.
     *
     * @param idEvento identificador del evento a eliminar.
     * @return true si el evento fue eliminado correctamente,
     * false si no se encontró el evento.
     */
    public boolean eliminarEvento(String idEvento) {
        boolean eliminado = listaEventos.removeIf(
                e -> e.getIdEvento().equals(idEvento)
        );
        if (eliminado) {
            System.out.println("Evento " + idEvento + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró el evento con ID: " + idEvento);
        }
        return eliminado;
    }

    // ==========================================
    // NUEVOS MÉTODOS ADAPTADOS PARA RECINTOS
    // ==========================================

    /**
     * Crea una nueva instancia de un recinto y lo almacena en la lista de la plataforma.
     *
     * @param idRecinto identificador único del recinto.
     * @param nombre nombre del recinto.
     * @param direccion dirección del recinto.
     * @param ciudad ciudad donde se encuentra el recinto.
     * @return nuevo objeto Recinto creado.
     */
    public Recinto crearRecinto(int idRecinto, String nombre,
                                String direccion, String ciudad,
                                int capacidadMaxima) {
        Recinto nuevo = new Recinto(idRecinto, nombre, direccion, ciudad, capacidadMaxima);
        listaRecintos.add(nuevo);
        return nuevo;
    }

    /**
     * Actualiza la información básica del recinto buscando por su identificador.
     *
     * @param idRecinto identificador del recinto a actualizar.
     * @param nombre nuevo nombre del recinto.
     * @param direccion nueva dirección del recinto.
     * @param ciudad nueva ciudad del recinto.
     */
    public void actualizarRecinto(int idRecinto,
                                  String nombre,
                                  String direccion,
                                  String ciudad) {
        for (Recinto recinto : listaRecintos) {
            if (recinto.getIdRecinto() == idRecinto) {
                recinto.setNombre(nombre);
                recinto.setDireccion(direccion);
                recinto.setCiudad(ciudad);
                return;
            }
        }
    }

    /**
     * Elimina un recinto de la lista global de la plataforma utilizando su ID.
     *
     * @param idRecinto identificador del recinto a eliminar.
     */
    public void eliminarRecinto(int idRecinto) {
        listaRecintos.removeIf(recinto -> recinto.getIdRecinto() == idRecinto);
    }

    /**
     * Retorna en formato texto la información básica de todos los recintos registrados.
     *
     * @return información agregada de los recintos en formato String.
     */
    public String listarRecinto() {
        StringBuilder sb = new StringBuilder();
        for (Recinto recinto : listaRecintos) {
            sb.append("ID:").append(recinto.getIdRecinto())
                    .append(" Nombre:").append(recinto.getNombre())
                    .append(" Direccion:").append(recinto.getDireccion())
                    .append(" Ciudad:").append(recinto.getCiudad())
                    .append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Busca y retorna un recinto registrado en la plataforma por su identificador.
     *
     * @param idRecinto identificador del recinto a buscar.
     * @return el objeto Recinto si se encuentra, null en caso contrario.
     */
    public Recinto buscarRecintoPorId(int idRecinto) {
        for (Recinto recinto : listaRecintos) {
            if (recinto.getIdRecinto() == idRecinto) {
                return recinto;
            }
        }
        return null;
    }

    /**
     * Registra una nueva incidencia en el sistema.
     * Valida que la incidencia no sea nula antes de agregarla.
     *
     * @param incidencia Incidencia a registrar. No puede ser nula.
     * @throws IllegalArgumentException si la incidencia es nula.
     */
    public void registrarIncidencia(Incidencia incidencia) {
        if (incidencia == null) {
            throw new IllegalArgumentException("La incidencia no puede ser nula.");
        }
        listaIncidencias.add(incidencia);
        System.out.println("Incidencia registrada: " + incidencia);
    }
    /**
     * Retorna la lista de todas las incidencias registradas.
     *
     * @return Lista de incidencias. No puede ser nula.
     */
    public List<Incidencia> consultarIncidencias() {
        return listaIncidencias;
    }
    public void mostrarIncidencias() {
        if (listaIncidencias.isEmpty()) {
            System.out.println("No hay incidencias registradas.");
            return;
        }
        for (Incidencia i : listaIncidencias) {
            System.out.println(i);
        }
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public List<Recinto> getListaRecintos() {
        return listaRecintos;
    }

    public void setListaRecintos(List<Recinto> listaRecintos) {
        this.listaRecintos = listaRecintos;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<Asiento> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }

    public List<Incidencia> getListaIncidencias() {return listaIncidencias;}

    public void setListaIncidencias(List<Incidencia> listaIncidencias) {this.listaIncidencias = listaIncidencias;}
}