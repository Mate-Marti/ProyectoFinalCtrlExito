package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class IncidenciaTest {

    private Incidencia incidencia;
    private Date fechaActual;

    @BeforeEach
    void setUp() {
        fechaActual = new Date();
        // CORRECCIÓN: Usando tus enums reales: TipoIncidencia.ERROR_PAGO y EntidadAfectada.COMPRA
        incidencia = new Incidencia(101, "Fallo de conexión en pasarela", fechaActual, TipoIncidencia.ERROR_PAGO, EntidadAfectada.COMPRA);
    }

    @Test
    void testConstructorYEstadoInicial() {
        assertNotNull(incidencia);
        assertEquals(101, incidencia.getIdIncidencia());
        assertEquals("Fallo de conexión en pasarela", incidencia.getDescripcion());
        assertEquals(fechaActual, incidencia.getFechaIncidencia());
        assertEquals(EstadoIncidencia.ABIERTA, incidencia.getEstado());
    }

    @Test
    void testFlujoEstadoExitoso() {
        // ABIERTA -> EN_REVISION
        incidencia.ponerEnRevision();
        assertEquals(EstadoIncidencia.EN_REVISION, incidencia.getEstado());

        // EN_REVISION -> RESUELTA
        incidencia.resolverIncidencia();
        assertEquals(EstadoIncidencia.RESUELTA, incidencia.getEstado());

        // RESUELTA -> CERRADA
        incidencia.cerrarIncidencia();
        assertEquals(EstadoIncidencia.CERRADA, incidencia.getEstado());
    }

    @Test
    void testTransicionesDeEstadoInvalidas() {
        // Intentar resolver directamente desde ABIERTA debería fallar
        incidencia.resolverIncidencia();
        assertEquals(EstadoIncidencia.ABIERTA, incidencia.getEstado());

        // Intentar cerrar directamente desde ABIERTA debería fallar
        incidencia.cerrarIncidencia();
        assertEquals(EstadoIncidencia.ABIERTA, incidencia.getEstado());

        // Pasamos a EN_REVISION
        incidencia.ponerEnRevision();

        // Intentar cerrar desde EN_REVISION debería fallar
        incidencia.cerrarIncidencia();
        assertEquals(EstadoIncidencia.EN_REVISION, incidencia.getEstado());
    }
}