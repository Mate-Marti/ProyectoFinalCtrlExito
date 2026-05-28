package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para los métodos de la clase Plataforma.
 */
public class PlataformaTest {

    private Plataforma plataforma;

    @BeforeEach
    void setUp() {
        plataforma = new Plataforma(1, "Virtual", "Plataforma de pruebas");
    }

    @Test
    void testRegistrarUsuario() {
        // Corrección: Método de instancia y adición del parámetro 'contrasena'
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta", "password123");
        assertTrue(plataforma.buscarUsuario("1"));
    }

    @Test
    void testActualizarUsuario() {
        // Corrección: Método de instancia y adición del parámetro 'contrasena' en registro
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta", "password123");

        // Corrección: El método real no recibe contraseña al actualizar y se ejecuta sobre la instancia
        boolean actualizado = plataforma.actualizarUsuario("1", "Ana López", "ana2@gmail.com", "1234", "Tarjeta");
        assertTrue(actualizado);
    }

    @Test
    void testEliminarUsuario() {
        // Corrección: Método de instancia y adición del parámetro 'contrasena'
        plataforma.registrarUsuario("1", "Ana López", "ana@gmail.com", "1234", "Tarjeta", "password123");

        boolean eliminado = plataforma.eliminarUsuario("1");
        assertTrue(eliminado);
        assertFalse(plataforma.buscarUsuario("1"));
    }

    @Test
    void testBuscarUsuario() {
        // Corrección: Método de instancia y adición del parámetro 'contrasena'
        plataforma.registrarUsuario("1", "Ana López", "ana2@gmail.com", "1234", "Tarjeta", "password123");

        boolean encontrado = plataforma.buscarUsuario("1");
        assertTrue(encontrado);
    }

    @Test
    void testRegistrarEvento() {
        plataforma.registrarEvento("10", "Concierto", "Música", "Evento visible");
        assertTrue(plataforma.buscarEvento("10"));
    }

    @Test
    void testReasignarCompra() {
        // Usamos la variable 'plataforma' del setUp para mantener la coherencia
        // Corrección: Registro con firma e instancia correctas
        plataforma.registrarUsuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta", "securePass");

        // Recuperar el usuario desde la lista de la plataforma (método de instancia getListaPersonas)
        Usuario usuario = null;
        for (Persona persona : plataforma.getListaPersonas()) {
            if (persona instanceof Usuario && persona.getId().equals("U1")) {
                usuario = (Usuario) persona;
                break;
            }
        }
        assertNotNull(usuario);

        // Registrar evento
        plataforma.registrarEvento("10", "Concierto", "Música", "Evento de prueba");
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");

        // Crear compra y asociarla al usuario
        Compra compra = new Compra.Builder()
                .setIdCompra(25)
                .setTotal(200.0)
                .setTipoPago(TipoPago.CREDITO)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        usuario.agregarCompra(compra);

        // Crear entrada
        Zona zonaVIP = new Zona(1, "VIP", 100, 200.0);
        Asiento asientoA5 = new Asiento(1, "A", "5");
        Entrada entradaVieja = new Entrada(zonaVIP.getPreciobase(), EstadoEntrada.ACTIVA, asientoA5, zonaVIP);
        compra.agregarEntrada(entradaVieja);

        // Nuevo asiento
        Asiento asientoB3 = new Asiento(2, "B", "3");

        // Reasignar compra
        boolean resultado = plataforma.reasignarCompra(25, entradaVieja, asientoB3);

        assertTrue(resultado);
        assertTrue(compra.getEntradas().stream().anyMatch(e -> e.getAsiento().getNumero().equals("3")));
    }
}