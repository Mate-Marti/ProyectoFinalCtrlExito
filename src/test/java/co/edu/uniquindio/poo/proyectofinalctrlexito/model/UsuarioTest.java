package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void testSeleccionarEntrada() {

//Creamos los datos base para probar este metodo, con la zona, el asiento, el evento, la compra y el cliente para comprobar que la boleta del asiento fue seleccionada y comprada correctamente
        Usuario usuario = new Usuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta");
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");
        Compra compra = new Compra.Builder()
                .setIdCompra(1)
                .setTotal(0)
                .setTipoPago(TipoPago.CREDITO)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        usuario.agregarCompra(compra);
        Zona zonaVIP = new Zona(1, "VIP", 100, 200.0);
        Asiento asientoA5 = new Asiento(1, "A", "5"); // fila A, número 5
        Entrada entrada = new Entrada(zonaVIP.getPreciobase(), EstadoEntrada.ACTIVA, asientoA5, zonaVIP);

        // Ejecutamos el metodo de seleccionar la entrada
        usuario.seleccionarEntrada(compra, entrada);

        // Verificamos que la entrada quedó dentro de la compra
        assertTrue(compra.getEntradas().contains(entrada));
    }

    @Test
    void testDescargarComprobante() {
        // Creamos el usuario
        Usuario usuario = new Usuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta");

        // Creamos el evento
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");

        // Creamos la compra con el Builder
        Compra compra = new Compra.Builder()
                .setIdCompra(25)
                .setTotal(200.0)
                .setTipoPago(TipoPago.EFECTIVO)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        //Le damos al usuario esa compra
        usuario.agregarCompra(compra);

        //Usamos el metodo descargar comprobante
        usuario.descargarComprobante(compra);

        // Validamos que el comprobante corresponde a la compra ID 25
        assertTrue(compra.getIdCompra() == 25);
    }
    //Test que verifica que se cree un servicio y sea agregado y verificado correctamente
    @Test
    void testAgregarServicioACompra() {
        // Creamos el usuario
        Usuario usuario = new Usuario("U1", "Carlos Pérez", "carlos@gmail.com", "12345", "Tarjeta");

        // Creamos el evento
        Evento evento = new Evento("10", "Concierto", "Música", "Evento de prueba");

        // Creamos la compra con el Builder
        Compra compra = new Compra.Builder()
                .setIdCompra(25)
                .setTotal(0.0)
                .setTipoPago(TipoPago.CREDITO)
                .setUsuario(usuario)
                .setEvento(evento)
                .build();

        usuario.agregarCompra(compra);

        // Creamos un servicio adicional
        ServicioAdicional servicio = new ServicioAdicional(1, "Evento", "Servicio de evento especial", 50.0);

        // Ejecutamos el metodo de agregar el servicio
        usuario.agregarServicioACompra(compra, servicio);

        // Validamos que la lista de servicios no sea nula
        assertNotNull(compra.getServiciosAdicionales());

        // Validamos que el servicio efectivamente esté dentro de la lista
        assertTrue(compra.getServiciosAdicionales().contains(servicio));
    }
}