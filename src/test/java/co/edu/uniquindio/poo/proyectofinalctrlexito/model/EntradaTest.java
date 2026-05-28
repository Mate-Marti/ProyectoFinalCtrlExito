package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntradaTest {

    private Entrada entrada;
    private Asiento asiento;
    private Zona zona;

    @BeforeEach
    void setUp() {
        asiento = new Asiento(1, "A", "10");
        zona = new Zona(1, "VIP", 50, 150000.0);
        // Creamos una entrada inicialmente ACTIVA
        entrada = new Entrada(150000.0, EstadoEntrada.ACTIVA, asiento, zona);
    }

    @Test
    void testConstructorYValoresIniciales() {
        assertNotNull(entrada);
        assertTrue(entrada.getIdEntrada() > 0); // Valida el incremento secuencial del contador estático
        assertEquals(150000.0, entrada.getPrecioFinal());
        assertEquals(EstadoEntrada.ACTIVA, entrada.getEstadoEntrada());
        assertTrue(entrada.estaActiva());
        assertEquals(asiento, entrada.getAsiento());
        assertEquals(zona, entrada.getZona());
    }

    @Test
    void testUsarEntrada() {
        entrada.usarEntrada();
        assertEquals(EstadoEntrada.USADA, entrada.getEstadoEntrada());
        assertFalse(entrada.estaActiva());
    }

    @Test
    void testAnularEntrada() {
        entrada.anularEntrada();
        assertEquals(EstadoEntrada.ANULADA, entrada.getEstadoEntrada());
        assertFalse(entrada.estaActiva());
    }

    @Test
    void testConsultarEntrada() {
        String consulta = entrada.consultarEntrada();
        assertTrue(consulta.contains("precioFinal=150000.0"));
        assertTrue(consulta.contains("estadoEntrada=ACTIVA"));
    }
}