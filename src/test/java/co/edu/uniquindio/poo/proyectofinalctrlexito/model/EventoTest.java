package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventoTest {
    //Test al metodo de pausar evento, crea un evento, implementa el metodo y confirma que su estado cambiase correctamente

    @Test
    void testPausarEvento() {
        Evento evento = new Evento("10", "Concierto", "Música", "Evento pausado");
        evento.pausarEvento();
        assertTrue(evento.getEstado() == EstadoEvento.PAUSADO);
    }

//Test que asi mismo como el anterior verifica que el estado ahora sea correctamente, cancelado
    @Test
    void testCancelarEvento() {
        Evento evento = new Evento("10", "Concierto", "Música", "Evento cancelado");
        evento.cancelarEvento();
        assertTrue(evento.getEstado() == EstadoEvento.CANCELADO);
    }

//Test que valida la publicacion y cancelacion de un evento
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

//Crea un evento y genera unos datos cual mensaje, y compara los mensajes entre si
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


}
