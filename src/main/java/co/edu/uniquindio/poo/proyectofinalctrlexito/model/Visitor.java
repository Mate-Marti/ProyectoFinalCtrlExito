package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

public interface Visitor {
    /**
     * Permite que un visitante procese el objeto
     * utilizando el patrón Visitor.
     *
     * @param visitor visitante encargado de procesar el objeto.
     */
    public void aceptarVisitante(ReporteVisitor visitor);
}