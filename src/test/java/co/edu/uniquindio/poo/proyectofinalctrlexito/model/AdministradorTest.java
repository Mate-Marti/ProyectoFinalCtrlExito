package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    private Administrador admin;
    private Evento eventoPrueba;

    @BeforeEach
    void setUp() {
        // Inicializamos el administrador para cada prueba
        admin = new Administrador("admin_exito", "admin123");
        // Nota: Ajusta los parámetros del constructor de Evento según tu implementación exacta
        eventoPrueba = new Evento("E1", "Festival de Rock", "Música", "Concierto al aire libre");
    }


    @Test
    void testAccionesSobreEventos() {
        // Probamos que los métodos delegados no rompan el flujo (no lancen excepciones inesperadas)
        assertDoesNotThrow(() -> admin.publicarEvento(eventoPrueba));
        assertDoesNotThrow(() -> admin.pausarEvento(eventoPrueba));
        assertDoesNotThrow(() -> admin.cancelarEvento(eventoPrueba));
        assertDoesNotThrow(() -> admin.finalizaEvento(eventoPrueba));
    }

    @Test
    void testCancelarCompra() {
        // Nota: Asumiendo que Compra posee un Builder o constructor accesible de acuerdo a tus pruebas previas
        Compra compraPrueba = new Compra.Builder()
                .setIdCompra(99)
                .setTotal(50000.0)
                .build();

        assertDoesNotThrow(() -> admin.cancelarCompra(compraPrueba));
    }
}