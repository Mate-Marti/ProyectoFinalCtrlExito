package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface ReporteVisitor {

    public void visitarRecinto(Recinto recinto);

    public void visitarZona(Zona zona);

    public void visitarCompra(Compra compra);

    public void visitarEntrada(Entrada entrada);

    public void visitarEvento(Evento evento);

    public void visitarUsuario(Usuario usuario);

}
