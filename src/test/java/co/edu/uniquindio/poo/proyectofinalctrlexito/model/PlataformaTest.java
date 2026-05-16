package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//Esta es la clase donde se realizaran las pruebas de los metodos de la clase Plataforma
public class PlataformaTest {

    private Plataforma plataforma;
//Creamos la plataforma de prueba
    @BeforeEach
    void setUp() {
        plataforma = new Plataforma(1, "Virtual", "Plataforma de pruebas");
    }

//Ahora, este test registra un usuario en la plataforma y verifica que esta no sea nula
// ademas, confirma que que el usuario sea correctamente el que fue agregado
    @Test
    void testRegistrarUsuario() {
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta");
        assertNotNull(plataforma);
        assertTrue(plataforma.buscarUsuario("1"));
    }

//Este test, toma un Usuario ya existente y actualiza sus datos, el test verifica que esto se haga correctamente

    @Test
    void testActualizarUsuario() {
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta");
        boolean actualizado = plataforma.actualizarUsuario("1", "Ana López", "ana2@gmail.com", "1234", "Tarjeta");
        assertTrue(actualizado);
        assertEquals(true, actualizado);
    }

//Este test elimina el usuario y lo que hace la prueba es buscar al usuario eliminado en la lista de usuarios activos o aun registrados
//y usa el metodo buscarUsuario para confirmar que ya no este en la lista

    @Test
    void testEliminarUsuario() {
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta");
        boolean eliminado = plataforma.eliminarUsuario("1");
        assertFalse(plataforma.buscarUsuario("1"));
    }

//Este test confirma la accion de buscar usuario en el sistema, verifica que sea correcta y efectivamente encuentre el usuario

    @Test
    void testBuscarUsuario() {
        plataforma.registrarUsuario("1", "Ana López", "ana2@gmail.com", "1234", "Tarjeta");
        boolean encontrado = plataforma.buscarUsuario("1");
        assertEquals(true, encontrado);
    }

 //Este test registra un evento dentro de la plataforma ya creada y confirma que el evento este en la plataforma

    @Test
    void testRegistrarEvento() {
        plataforma.registrarEvento("10", "Concierto", "Música", "Evento visible", "Activo");
        assertTrue(plataforma.buscarEvento("10"));
    }
}