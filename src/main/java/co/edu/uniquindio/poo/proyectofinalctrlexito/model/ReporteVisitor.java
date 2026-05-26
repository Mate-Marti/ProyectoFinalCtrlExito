package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface ReporteVisitor {

    /**
     * Genera el reporte correspondiente a un recinto.
     *
     * @param recinto recinto visitado.
     */
    public void visitarRecinto(Recinto recinto);

    /**
     * Genera el reporte correspondiente a una zona.
     *
     * @param zona zona visitada.
     */
    public void visitarZona(Zona zona);

    /**
     * Genera el reporte correspondiente a una compra.
     *
     * @param compra compra visitada.
     */
    public void visitarCompra(Compra compra);

    /**
     * Genera el reporte correspondiente a una entrada.
     *
     * @param entrada entrada visitada.
     */
    public void visitarEntrada(Entrada entrada);

    /**
     * Genera el reporte correspondiente a un evento.
     *
     * @param evento evento visitado.
     */
    public void visitarEvento(Evento evento);

    /**
     * Genera el reporte correspondiente a un usuario.
     *
     * @param usuario usuario visitado.
     */
    public void visitarUsuario(Usuario usuario);

}
