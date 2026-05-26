package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public class Persona {

    private String id;
    private String nombreCompleto;
    private String correo;
    private String telefono;

    /**
     * Constructor vacío de la clase Persona.
     */
    public Persona() {}

    /**
     * Constructor de la clase Persona.
     *
     * @param id identificador único de la persona.
     * @param nombreCompleto nombre completo de la persona.
     * @param correo correo electrónico de la persona.
     * @param telefono número telefónico de la persona.
     */
    public Persona(String id, String nombreCompleto,
                   String correo, String telefono) {

        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
    }

    /**
     * Obtiene el identificador de la persona.
     *
     * @return id de la persona.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la persona.
     *
     * @param id nuevo identificador de la persona.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre completo de la persona.
     *
     * @return nombre completo de la persona.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Modifica el nombre completo de la persona.
     *
     * @param nombreCompleto nuevo nombre completo.
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene el correo electrónico de la persona.
     *
     * @return correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo electrónico de la persona.
     *
     * @param correo nuevo correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el número telefónico de la persona.
     *
     * @return número telefónico.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el número telefónico de la persona.
     *
     * @param telefono nuevo número telefónico.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
