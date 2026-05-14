package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlataformaTest {

    private Plataforma plataforma;

    @BeforeEach
    void setUp() {
        plataforma = new Plataforma(1, "Virtual", "Plataforma de pruebas");
    }

    @Test
    void testRegistrarUsuarioAssertNotNull() {
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta");
        assertNotNull(plataforma);
        assertTrue(plataforma.buscarUsuario("1"));
    }

    @Test
    void testActualizarUsuarioAssertEquals() {
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta");
        boolean actualizado = plataforma.actualizarUsuario("1", "Ana López", "ana2@gmail.com", "1234", "Tarjeta");
        assertTrue(actualizado);
        assertEquals(true, actualizado);
    }

    @Test
    void testEliminarUsuarioAssertFalse() {
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta");
        boolean eliminado = plataforma.eliminarUsuario("1");
        assertFalse(plataforma.buscarUsuario("1"));
    }

    @Test
    void testBuscarUsuarioAssertEquals() {
        plataforma.registrarUsuario("1", "Ana López", "ana2@gmail.com", "1234", "Tarjeta");
        boolean encontrado = plataforma.buscarUsuario("1");
        assertEquals(true, encontrado);
    }

    @Test
    void testRegistrarEventoAssertTrue() {
        plataforma.registrarEvento("10", "Concierto", "Música", "Evento visible", "Activo");
        assertTrue(plataforma.buscarEvento("10"));
    }
}
