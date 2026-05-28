package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class EventoTest {

    // =========================================================================
    // TESTS ORIGINALES (MANTENIDOS INTACTOS)
    // =========================================================================

    @Test
    void testPausarEvento() {
        Evento evento = new Evento("10", "Concierto", "Música", "Evento pausado");
        evento.pausarEvento();
        assertTrue(evento.getEstado() == EstadoEvento.PAUSADO);
    }

    @Test
    void testCancelarEvento() {
        Evento evento = new Evento("10", "Concierto", "Música", "Evento cancelado");
        evento.cancelarEvento();
        assertTrue(evento.getEstado() == EstadoEvento.CANCELADO);
    }

    @Test
    void testValidarEventoDisponible() {
        //Disponible
        Evento eventoDisponible = new Evento("10", "Concierto", "Música", "Evento disponible");
        eventoDisponible.publicarEvento();
        assertEquals(true, eventoDisponible.validarEventoDisponible());

        //No disponible
        Evento eventoNoDisponible = new Evento("10", "Concierto", "Música", "Evento no disponible");
        eventoNoDisponible.cancelarEvento();
        assertEquals(false, eventoNoDisponible.validarEventoDisponible());
    }

    @Test
    void testObtenerDetalleEvento() {
        Date fecha = new Date();
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");
        evento.setFecha(fecha);
        String esperado = "Evento: Concierto [Música]\n" +
                "Descripción: Evento de prueba\n" +
                "Fecha: " + fecha + "\n" +
                "Estado actual: BORRADOR";

        assertEquals(esperado, evento.obtenerDetalleEvento());
    }

    // =========================================================================
    // NUEVOS TESTS: IMPLEMENTACIÓN FACTORY METHOD
    // =========================================================================

    @Test
    void testConciertoFactory() {
        EventoFactory factory = new ConciertoFactory();
        Date fecha = new Date();

        Evento evento = factory.crearEvento(1, "Rock al Parque", "Festival de rock", fecha);

        assertNotNull(evento);
        assertTrue(evento instanceof ConciertoEvento, "Debería ser una instancia de ConciertoEvento");
        assertEquals("1", evento.getIdEvento());
        assertEquals("Rock al Parque", evento.getNombre());
        assertEquals("Concierto", evento.getCategoria(), "La fábrica debe asignar automáticamente la categoría 'Concierto'");
    }

    @Test
    void testConferenciaFactory() {
        EventoFactory factory = new ConferenciaFactory();
        Date fecha = new Date();

        Evento evento = factory.crearEvento(2, "Tech Talk 2026", "Conferencia de tecnología", fecha);

        assertNotNull(evento);
        assertTrue(evento instanceof ConferenciaEvento, "Debería ser una instancia de ConferenciaEvento");
        assertEquals("2", evento.getIdEvento());
        assertEquals("Tech Talk 2026", evento.getNombre());
        assertEquals("Conferencia", evento.getCategoria(), "La fábrica debe asignar automáticamente la categoría 'Conferencia'");
    }

    @Test
    void testTeatroFactory() {
        EventoFactory factory = new TeatroFactory();
        Date fecha = new Date();

        Evento evento = factory.crearEvento(3, "Romeo y Julieta", "Obra clásica", fecha);

        assertNotNull(evento);
        assertTrue(evento instanceof TeatroEvento, "Debería ser una instancia de TeatroEvento");
        assertEquals("3", evento.getIdEvento());
        assertEquals("Romeo y Julieta", evento.getNombre());
        assertEquals("Teatro", evento.getCategoria(), "La fábrica debe asignar automáticamente la categoría 'Teatro'");
    }

    // =========================================================================
    // NUEVOS TESTS: ADICIONALES PARA PATRONES OBSERVER Y VISITOR
    // =========================================================================

    @Test
    void testPatronObserverNotificaciones() {
        Evento evento = new Evento("10", "Concierto Rock", "Música", "Prueba de observer");

        // Creamos una bandera para registrar si el observador es notificado
        final String[] mensajeRecibido = {null};

        // Creamos un Observer simulado usando una interfaz anónima
        Observer observadorPrueba = new Observer() {
            @Override
            public void actualizar(String mensaje) {
                mensajeRecibido[0] = mensaje;
            }
        };

        // Registramos al observador en el ciclo del Evento (Subject)
        evento.agregarObserver(observadorPrueba);
        assertEquals(1, evento.getObservers().size());

        // Al cambiar el estado a publicado, debe detonar automáticamente la notificación
        evento.publicarEvento();
        assertNotNull(mensajeRecibido[0]);
        assertTrue(mensajeRecibido[0].contains("fue publicado"));

        // Verificamos la eliminación del observador
        evento.eliminarObserver(observadorPrueba);
        assertTrue(evento.getObservers().isEmpty());
    }

    @Test
    void testAceptarVisitantePatronVisitor() {
        Evento evento = new Evento("20", "Stand Up Comedy", "Teatro", "Prueba de visitor");
        final boolean[] seVisitoEvento = {false};

        // Creamos un Mock del ReporteVisitor para verificar que el evento delegue correctamente
        ReporteVisitor visitantePrueba = new ReporteVisitor() {
            @Override
            public void visitarEvento(Evento e) {
                assertEquals(evento, e);
                seVisitoEvento[0] = true;
            }

            @Override public void visitarRecinto(Recinto recinto) {}
            @Override public void visitarZona(Zona zona) {}
            @Override public void visitarCompra(Compra compra) {}
            @Override public void visitarEntrada(Entrada entrada) {}
            @Override public void visitarUsuario(Usuario usuario) {}
        };

        assertDoesNotThrow(() -> evento.aceptarVisitante(visitantePrueba));
        assertTrue(seVisitoEvento[0], "El método aceptarVisitante debió redirigir de forma correcta el flujo hacia visitarEvento.");
    }
}