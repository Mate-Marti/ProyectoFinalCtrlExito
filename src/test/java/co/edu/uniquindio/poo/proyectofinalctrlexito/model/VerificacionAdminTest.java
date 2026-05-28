package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VerificacionAdminTest {

    // =========================================================================
    // TESTS PARA VERIFICACIONADMIN (PATRÓN PROXY / SEGURIDAD)
    // =========================================================================

    @Test
    void testLoginExitosoYFallido() {
        // Configuramos las credenciales correctas en el Proxy
        VerificacionAdmin proxySeguridad = new VerificacionAdmin("admin123", "claveSegura");

        // Caso 1: Credenciales correctas -> Debe permitir el acceso (true)
        assertTrue(proxySeguridad.login("admin123", "claveSegura"));

        // Caso 2: Usuario incorrecto -> Debe denegar el acceso (false)
        assertFalse(proxySeguridad.login("usuarioInvalido", "claveSegura"));

        // Caso 3: Contraseña incorrecta -> Debe denegar el acceso (false)
        assertFalse(proxySeguridad.login("admin123", "claveErronea"));
    }

    @Test
    void testGestionComponentesSinExcepciones() {
        VerificacionAdmin proxySeguridad = new VerificacionAdmin("root", "12345");

        // Verificamos que los métodos de gestión de flujos se ejecuten correctamente sin arrojar errores
        assertDoesNotThrow(() -> proxySeguridad.gestionarUsuarios());
        assertDoesNotThrow(() -> proxySeguridad.gestionarEventos());
    }

    // =========================================================================
    // TESTS PARA SERVICIOADICIONAL
    // =========================================================================

    @Test
    void testServicioAdicionalGettersYFormato() {
        ServicioAdicional servicio = new ServicioAdicional(1, "Parqueadero VIP", "Estacionamiento techado", 25000.0);

        // Validamos la consistencia de los datos en el objeto
        assertEquals(1, servicio.getIdServicio());
        assertEquals("Parqueadero VIP", servicio.getNombre());
        assertEquals(25000.0, servicio.getPrecio());

        // Validamos que el formato de texto contenga los campos esenciales configurados
        String detalle = servicio.mostrarServicio();
        assertTrue(detalle.contains("Servicio: Parqueadero VIP"));
        assertTrue(detalle.contains("Precio: $25000.0"));
    }
}
