package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompraTest {

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
}
