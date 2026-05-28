package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ReportePDF implements ReporteVisitor{

    private String rutaArchivo;
    private StringBuilder contenido;
    private double totalCompras;
    private double totalIngresosServicios;
    private int numeroCompras;
    private int contadorEntradasActivas;
    private int contadorEntradasAnuladas;
    private int contadorEventos;
    private int contadorUsuarios;

    public ReportePDF(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.contenido = new StringBuilder();
        this.totalCompras = 0;
        this.totalIngresosServicios = 0;
        this.numeroCompras = 0;
        this.contadorEntradasActivas = 0;
        this.contadorEntradasAnuladas = 0;
        this.contadorEventos = 0;
        this.contadorUsuarios = 0;

        contenido.append("--------------------------------------------------------\n");
        contenido.append("            REPORTE OPERATIVO - CTRL EXITO              \n");
        contenido.append("--------------------------------------------------------\n");
    }

    public void generarReporteDesdePlataforma(Plataforma plataforma) {

        int totalRecintos = plataforma.getListaRecintos().size();
        int totalEventos = plataforma.getListaEventos().size();
        int totalUsuariosRegistrados = 0;
        double ingresosTotales = 0.0;
        int totalVentas = 0;
        int totalIncidencias = (plataforma.getListaIncidencias() != null) ? plataforma.getListaIncidencias().size() : 0;

        contenido.append("\n--- RESUMEN GENERAL DEL SISTEMA ---\n");
        contenido.append("Fecha de Generación: ").append(java.time.LocalDate.now()).append("\n\n");

        contenido.append("1. INFRAESTRUCTURA Y EVENTOS:\n");
        contenido.append(" - Total Recintos Registrados: ").append(totalRecintos).append("\n");
        contenido.append(" - Total Eventos en Cartelera: ").append(totalEventos).append("\n\n");

        contenido.append("2. RENDIMIENTO FINANCIERO Y COMPRAS:\n");

        for (Persona p : plataforma.getListaPersonas()) {
            if (p instanceof Usuario) {
                totalUsuariosRegistrados++;
                Usuario u = (Usuario) p;
                for (Compra c : u.getCompras()) {
                    totalVentas++;
                    ingresosTotales += c.getTotal();
                }
            }
        }

        contenido.append(" - Total Usuarios Registrados: ").append(totalUsuariosRegistrados).append("\n");
        contenido.append(" - Cantidad de Compras Procesadas: ").append(totalVentas).append("\n");
        contenido.append(" - TOTAL INGRESOS EN CAJA: $").append(ingresosTotales).append("\n\n");

        contenido.append("3. REPORTE DE INCIDENCIAS:\n");
        contenido.append(" - Total Incidencias Reportadas: ").append(totalIncidencias).append("\n\n");

        contenido.append("--------------------------------------------------------\n");
        contenido.append("                 FIN DEL REPORTE                        \n");
        contenido.append("--------------------------------------------------------\n");

        guardarArchivoFisico();
    }

    private void guardarArchivoFisico() {
        try (FileWriter archivo = new FileWriter(this.rutaArchivo);
             PrintWriter escritor = new PrintWriter(archivo)) {

            escritor.print(this.contenido.toString());
            System.out.println("Reporte generado exitosamente en: " + this.rutaArchivo);

        } catch (IOException excepcion) {
            System.out.println("Error al generar el reporte: " + excepcion.getMessage());
        }
    }

    @Override
    public void visitarRecinto(Recinto recinto) {
        contenido.append("--- REPORTE DEL RECINTO ---\n");
        contenido.append("Nombre: ").append(recinto.getNombre())
                .append("Ciudad: ").append(recinto.getCiudad()).append("\n");
        contenido.append("Asientos Disponibles Totales: ").append(recinto.obtenerDisponibilidad()).append("\n");
    }

    @Override
    public void visitarZona(Zona zona) {
        int capacidadTotal = zona.getCapacidad();
        int disponibles = zona.obtenerDisponibilidad();
        int ocupados = capacidadTotal - disponibles;
        double porcentaje = 0.0;
        if (capacidadTotal > 0) {
            porcentaje = ((double) ocupados / capacidadTotal) * 100.0;
        }
        contenido.append("ZONA: ").append(zona.getNombre()).append("\n");
        contenido.append("Capacidad Total: ").append(capacidadTotal).append("\n");
        contenido.append("Ocupados: ").append(ocupados).append(" (").append(String.format("%.2f", porcentaje)).append("%)\n");
        contenido.append("Libres: ").append(disponibles).append("\n");
    }

    @Override
    public void visitarCompra(Compra compra) {
        numeroCompras++;
        totalCompras += compra.getTotal();
        if (compra.getServiciosAdicionales() != null) {
            for (ServicioAdicional servicio : compra.getServiciosAdicionales()) {
                totalIngresosServicios += servicio.getPrecio();
            }
        }
    }

    @Override
    public void visitarEntrada(Entrada entrada) {
        if (entrada.estaActiva()) {
            contadorEntradasActivas++;
        } else {
            contadorEntradasAnuladas++;
        }
    }

    @Override
    public void visitarEvento(Evento evento) {
        contadorEventos++;
        contenido.append("EVENTO: ").append(evento.getNombre()).append(" ---\n");
        contenido.append("Categoría: ").append(evento.getCategoria())
                .append("Estado: ").append(evento.getEstado()).append("\n");
    }

    @Override
    public void visitarUsuario(Usuario usuario) {
        contadorUsuarios++;
    }

    public void generarDocumentoReporte() {
        contenido.append("--------------------------------------------------------\n");
        contenido.append("                         RESUMEN                        \n");
        contenido.append("--------------------------------------------------------\n");
        contenido.append("1. USARIOS Y EVENTOS:\n");
        contenido.append(" - Total Usuarios Auditados: ").append(contadorUsuarios).append("\n");
        contenido.append(" - Total Eventos Gestionados: ").append(contadorEventos).append("\n\n");

        contenido.append("2. TASA DE CANCELACIÓN DE ENTRADAS:\n");
        int totalEntradas = contadorEntradasActivas + contadorEntradasAnuladas;
        double numCancelacion = 0.0;
        if(totalEntradas > 0) {
            numCancelacion = ((double) contadorEntradasAnuladas / totalEntradas) * 100.0;
        }
        contenido.append(" - Entradas Activas/Usadas: ").append(contadorEntradasActivas).append("\n");
        contenido.append(" - Entradas Anuladas: ").append(contadorEntradasAnuladas).append("\n");
        contenido.append(" - Tasa de Cancelación: ").append(String.format("%.2f", numCancelacion)).append("%\n\n");

        contenido.append("3. RENDIMIENTO FINANCIERO:\n");
        contenido.append(" - Cantidad de Comprs Procesadas: ").append(numeroCompras).append("\n");
        contenido.append(" - Ingresos por Servicios Adicionales: $").append(totalIngresosServicios).append("\n");
        contenido.append(" - TOTAL INGRESOS EN CAJA: $").append(totalCompras).append("\n");

        contenido.append("--------------------------------------------------------\n");
        contenido.append("                 FIN DEL REPORTE                        \n");
        contenido.append("--------------------------------------------------------\n");

        guardarArchivoFisico();
    }
}