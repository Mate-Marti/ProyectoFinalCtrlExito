package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AsientoTest {

    private Asiento asiento;

    @BeforeEach
    void setUp() {
        // Inicializamos un asiento en el estado por defecto (DISPONIBLE)
        asiento = new Asiento(1, "A", "15");
    }

    @Test
    void testEstadoInicial() {
        assertNotNull(asiento);
        assertEquals(1, asiento.getIdAsiento());
        assertEquals("A", asiento.getFila());
        assertEquals("15", asiento.getNumero());
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstado());
        assertTrue(asiento.validarDisponibilidad());
        assertEquals(1, asiento.obtenerDisponibilidad());
    }

    @Test
    void testCicloDeVidaEstados() {
        // Reservar el asiento
        asiento.reservarAsiento();
        assertEquals(EstadoAsiento.RESERVADO, asiento.getEstado());
        assertFalse(asiento.validarDisponibilidad());
        assertEquals(0, asiento.obtenerDisponibilidad());

        // Vender el asiento
        asiento.venderAsiento();
        assertEquals(EstadoAsiento.VENDIDO, asiento.getEstado());

        // Liberar el asiento (vuelve a estar disponible)
        asiento.liberarAsiento();
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstado());
        assertTrue(asiento.validarDisponibilidad());

        // Bloquear el asiento
        asiento.bloquearAsiento();
        assertEquals(EstadoAsiento.BLOQUEADO, asiento.getEstado());
        assertEquals(0, asiento.obtenerDisponibilidad());

        // Habilitar el asiento bloqueado
        asiento.habilitarAsiento();
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstado());
    }

    @Test
    void testConsultarAsiento() {
        String informacion = asiento.consultarAsiento();
        assertTrue(informacion.contains("ID asiento: 1"));
        assertTrue(informacion.contains("Fila: A"));
        assertTrue(informacion.contains("Numero: 15"));
        assertTrue(informacion.contains("Estado: DISPONIBLE"));
    }
}