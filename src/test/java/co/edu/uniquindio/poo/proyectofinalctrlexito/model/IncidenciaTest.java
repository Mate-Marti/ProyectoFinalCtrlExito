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

        incidencia = new Incidencia(
                101,
                "Fallo de conexión en pasarela",
                fechaActual,
                TipoIncidencia.ERROR_PAGO,
                EntidadAfectada.COMPRA,
                EstadoIncidencia.ABIERTA,
                "Error en compra"
        );
    }

    @Test
    void testConstructorYEstadoInicial() {

        assertNotNull(incidencia);

        assertEquals(101, incidencia.getIdIncidencia());

        assertEquals(
                "Fallo de conexión en pasarela",
                incidencia.getDescripcion()
        );

        assertEquals(
                fechaActual,
                incidencia.getFechaIncidencia()
        );

        assertEquals(
                EstadoIncidencia.ABIERTA,
                incidencia.getEstado()
        );

        assertEquals(
                "Error en compra",
                incidencia.getAsunto()
        );
    }

    @Test
    void testFlujoEstadoExitoso() {

        // ABIERTA -> EN_REVISION
        incidencia.ponerEnRevision();

        assertEquals(
                EstadoIncidencia.EN_REVISION,
                incidencia.getEstado()
        );

        // EN_REVISION -> RESUELTA
        incidencia.resolverIncidencia();

        assertEquals(
                EstadoIncidencia.RESUELTA,
                incidencia.getEstado()
        );

        // RESUELTA -> CERRADA
        incidencia.cerrarIncidencia();

        assertEquals(
                EstadoIncidencia.CERRADA,
                incidencia.getEstado()
        );
    }

    @Test
    void testTransicionesDeEstadoInvalidas() {

        // Resolver desde ABIERTA
        incidencia.resolverIncidencia();

        assertEquals(
                EstadoIncidencia.ABIERTA,
                incidencia.getEstado()
        );

        // Cerrar desde ABIERTA
        incidencia.cerrarIncidencia();

        assertEquals(
                EstadoIncidencia.ABIERTA,
                incidencia.getEstado()
        );

        // ABIERTA -> EN_REVISION
        incidencia.ponerEnRevision();

        // Cerrar desde EN_REVISION
        incidencia.cerrarIncidencia();

        assertEquals(
                EstadoIncidencia.EN_REVISION,
                incidencia.getEstado()
        );
    }
}