package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecintoTest {

    private Recinto recinto;

    @BeforeEach
    void setUp() {
        // Inicializamos el contenedor Composite principal
        recinto = new Recinto(10, "Estadio Centenario", "Calle 10", "Armenia");
    }

    @Test
    void testConstructorYValoresIniciales() {
        assertEquals(10, recinto.getIdRecinto());
        assertEquals("Estadio Centenario", recinto.getNombre());
        assertEquals("Calle 10", recinto.getDireccion());
        assertEquals("Armenia", recinto.getCiudad());
        assertTrue(recinto.getListaZonas().isEmpty());
    }

    @Test
    void testGestionZonasCRUD() {
        // 1. Crear / Agregar Zona al Composite
        recinto.crearZona(1, "Platea", 100, 50000.0);
        assertEquals(1, recinto.getListaZonas().size());

        Zona zonaConsultada = recinto.consultarZona(1);
        assertNotNull(zonaConsultada);
        assertEquals("Platea", zonaConsultada.getNombre());

        // 2. Actualizar datos de la Zona
        boolean actualizado = recinto.actualizarZona(1, "Platea Preferencial", 120, 60000.0);
        assertTrue(actualizado);
        assertEquals("Platea Preferencial", zonaConsultada.getNombre());

        // 3. Eliminar la Zona del contenedor
        boolean eliminado = recinto.eliminarZona(1);
        assertTrue(eliminado);
        assertNull(recinto.consultarZona(1));
    }

    @Test
    void testObtenerDisponibilidadComposite() {
        // 1. Creamos la estructura dentro del Recinto
        recinto.crearZona(1, "VIP", 2, 150000.0);
        Zona zonaVip = recinto.consultarZona(1);

        // 2. Evaluamos la delegación del cálculo en cascada
        int disponibilidadInicial = recinto.obtenerDisponibilidad();

        // El test verifica que el contenedor (Recinto) sume correctamente
        // lo que devuelven sus componentes internos (Zonas/Asientos)
        assertDoesNotThrow(() -> recinto.obtenerDisponibilidad());
        assertEquals(zonaVip.obtenerDisponibilidad(), disponibilidadInicial);
    }

    @Test
    void testConsultarOcupacionPorZonaMensajes() {
        // Probar el formato de texto cuando el contenedor está vacío
        String vacio = recinto.consultarOcupacionPorZona();
        assertTrue(vacio.contains("no tiene zonas registradas"));

        // Probar con zonas integradas en la estructura
        recinto.crearZona(1, "VIP", 50, 100.0);
        String conZonas = recinto.consultarOcupacionPorZona();
        assertTrue(conZonas.contains("--- Reporte de Ocupación: Estadio Centenario ---"));
        assertTrue(conZonas.contains("Zona: VIP"));
    }

    // =========================================================================
    // NUEVO TEST: IMPLEMENTACIÓN PATRÓN VISITOR
    // =========================================================================

    @Test
    void testAceptarVisitantePatronVisitor() {
        // Creamos una bandera para verificar que el visitante realmente ejecute su acción
        final boolean[] seVisitoRecinto = {false};

        // Creamos un visitante de prueba (Mock anónimo) para evaluar el comportamiento
        ReporteVisitor visitantePrueba = new ReporteVisitor() {
            @Override
            public void visitarRecinto(Recinto r) {
                // Verificamos que el objeto recibido por el visitante sea el mismo recinto
                assertEquals(recinto, r);
                seVisitoRecinto[0] = true;
            }

            @Override public void visitarZona(Zona zona) {}
            @Override public void visitarCompra(Compra compra) {}
            @Override public void visitarEntrada(Entrada entrada) {}
            @Override public void visitarEvento(Evento evento) {}
            @Override public void visitarUsuario(Usuario usuario) {}
        };

        // Acto: El recinto acepta al visitante
        assertDoesNotThrow(() -> recinto.aceptarVisitante(visitantePrueba));

        // Afirmación: El método visitarRecinto debió llamarse y cambiar la bandera a true
        assertTrue(seVisitoRecinto[0], "El patrón Visitor debería redirigir el flujo correctamente al método visitarRecinto.");
    }
}