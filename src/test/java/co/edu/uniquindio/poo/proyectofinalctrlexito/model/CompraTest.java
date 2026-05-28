package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompraTest {

    // =========================================================================
    // TESTS ORIGINALES (MANTENIDOS INTACTOS)
    // =========================================================================

    //Test para confirmar los datos de realizar pago
    @Test
    void testRealizarPago() {
        // Creamos el usuario
        Usuario usuario = new Usuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta");

        // Creamos el evento
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");

        // Creamos la compra con el Builder
        Compra compra = new Compra.Builder()
                .setIdCompra(25)
                .setTotal(200.0)
                .setTipoPago(TipoPago.CREDITO)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        // Creamos un medio de pago simulado, en este caso, tarjeta
        MedioPago medioPago = new MedioPago() {
            @Override
            public void procesarPago(double monto) {
                System.out.println("Procesando pago con tarjeta por $" + monto);
            }

            @Override
            public TipoPago getTipoPago() {
                return TipoPago.CREDITO;
            }
        };

        //Realizamos el pago con el metodo de prueba
        compra.realizarPago(medioPago);

        // Verificamos que el estado de la compra ya no sea EstadoCreada
        assertTrue(!(compra.getEstadoCompra() instanceof EstadoCreada));
    }

    //Test para verificar el correcto reembolso de una compra
    @Test
    void testReembolsarCompra() {
        // Creamos el usuario
        Usuario usuario = new Usuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta");

        // Creamos el evento
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");

        // Creamos la compra con el Builder
        Compra compra = new Compra.Builder()
                .setIdCompra(25)
                .setTotal(200.0)
                .setTipoPago(TipoPago.CREDITO)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        // Simulamos que la compra ya fue pagada para permitir el reembolso
        compra.pagarCompra();

        //Usamos el metodo a probar
        compra.reembolsarCompra();

        // Validamos que el estado de la compra sea EstadoReembolsada
        assertTrue(compra.getEstadoCompra() instanceof EstadoReembolsada);
    }

    // =========================================================================
    // NUEVOS TESTS: VALIDACIÓN DEL PATRÓN STATE
    // =========================================================================

    @Test
    void testFlujoEstadoExitosoYAsientos() {
        Usuario usuario = new Usuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta");
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");
        Zona zona = new Zona(1, "VIP", 50, 100.0);
        Asiento asiento = new Asiento(1, "A", "5");

        Compra compra = new Compra.Builder()
                .setIdCompra(1)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        // 1. Validar Estado Inicial (CREADA) y agregar entrada (asiento pasa a RESERVADO)
        assertTrue(compra.getEstadoCompra() instanceof EstadoCreada);
        assertEquals("CREADA", compra.getEstadoCompra().mostrarEstado());

        compra.generarEntrada(asiento, zona);
        assertEquals(EstadoAsiento.RESERVADO, asiento.getEstado());

        // 2. Transición: CREADA -> PAGADA (asiento pasa a VENDIDO)
        compra.pagarCompra();
        assertTrue(compra.getEstadoCompra() instanceof EstadoPagada);
        assertEquals("PAGADA", compra.getEstadoCompra().mostrarEstado());
        assertEquals(EstadoAsiento.VENDIDO, asiento.getEstado());

        // 3. Transición: PAGADA -> CONFIRMADA
        compra.confirmarCompra();
        assertTrue(compra.getEstadoCompra() instanceof EstadoConfirmada);
        assertEquals("CONFIRMADA", compra.getEstadoCompra().mostrarEstado());
    }

    @Test
    void testCancelarCompraCreadaYLiberarAsiento() {
        Usuario usuario = new Usuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta");
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");
        Zona zona = new Zona(1, "VIP", 50, 100.0);
        Asiento asiento = new Asiento(2, "A", "6");

        Compra compra = new Compra.Builder()
                .setIdCompra(2)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        compra.generarEntrada(asiento, zona);

        // Transición: CREADA -> CANCELADA (asiento debe LIBERARSE de nuevo a DISPONIBLE)
        compra.cancelarCompra();
        assertTrue(compra.getEstadoCompra() instanceof EstadoCancelada);
        assertEquals("CANCELADA", compra.getEstadoCompra().mostrarEstado());
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstado());
    }

    @Test
    void testRestriccionesDeEstadoInvalido() {
        Usuario usuario = new Usuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta");
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");

        Compra compra = new Compra.Builder()
                .setIdCompra(3)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        // Intentar confirmar sin pagar no debe alterar el estado CREADA
        compra.confirmarCompra();
        assertTrue(compra.getEstadoCompra() instanceof EstadoCreada);

        // Cancelamos la compra
        compra.cancelarCompra();
        assertTrue(compra.getEstadoCompra() instanceof EstadoCancelada);

        // Intentar pagar una compra ya cancelada debe mantenerla en CANCELADA
        compra.pagarCompra();
        assertTrue(compra.getEstadoCompra() instanceof EstadoCancelada);
    }
}