package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedioPagoAdapterTest {

    @Test
    void testProcesarPagoCreditoYTipo() {
        // CORRECCIÓN: Instanciación directa de la clase concreta real
        PagoCredito pagoCreditoReal = new PagoCredito();

        ACredito adaptadorCredito = new ACredito(pagoCreditoReal);

        // Test de tipo de pago correcto
        assertEquals(TipoPago.CREDITO, adaptadorCredito.getTipoPago());

        // Test de ejecución sin excepciones
        assertDoesNotThrow(() -> adaptadorCredito.procesarPago(150000.0));
    }

    @Test
    void testProcesarPagoDebitoYTipo() {
        // CORRECCIÓN: Instanciación directa de la clase concreta real
        PagoDebito pagoDebitoReal = new PagoDebito();

        ADebito adaptadorDebito = new ADebito(pagoDebitoReal);

        assertEquals(TipoPago.DEBITO, adaptadorDebito.getTipoPago());
        assertDoesNotThrow(() -> adaptadorDebito.procesarPago(85000.0));
    }

    @Test
    void testProcesarPagoEfectivoYTipo() {
        // CORRECCIÓN: Instanciación directa de la clase concreta real
        PagoEfectivo pagoEfectivoReal = new PagoEfectivo();

        AEfectivo adaptadorEfectivo = new AEfectivo(pagoEfectivoReal);

        assertEquals(TipoPago.EFECTIVO, adaptadorEfectivo.getTipoPago());
        assertDoesNotThrow(() -> adaptadorEfectivo.procesarPago(50000.0));
    }

    @Test
    void testProcesarPagoPuntosYTipo() {
        // CORRECCIÓN: Instanciación directa de la clase concreta real
        PagoPuntos pagoPuntosReal = new PagoPuntos();

        APuntos adaptadorPuntos = new APuntos(pagoPuntosReal);

        assertEquals(TipoPago.PUNTOS, adaptadorPuntos.getTipoPago());
        // Enviamos un double para comprobar que procesarPago realice el casteo a int internamente sin romperse
        assertDoesNotThrow(() -> adaptadorPuntos.procesarPago(250.7));
    }
}