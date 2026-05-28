package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZonaTest {

    @Test
    void testConsultarCapacidad() {
        // Creamos la zona con ID 4
        Zona zona = new Zona(4, "General", 3, 100.0);

        // Creamos asientos y los agregamos a la lista
        Asiento asiento1 = new Asiento(1, "A", "1");
        Asiento asiento2 = new Asiento(2, "A", "2");
        Asiento asiento3 = new Asiento(3, "A", "3");

        zona.getListaAsientos().add(asiento1);
        zona.getListaAsientos().add(asiento2);
        zona.getListaAsientos().add(asiento3);

        // Reservamos uno de los asientos para reducir la capacidad disponible
        asiento2.reservarAsiento();

        //Usamos el metodo que calcula la capacidad de la zone
        int capacidadDisponible = zona.consultarCapacidadZona();

        // y esto debe ser igual ya que ocupamos un asiento
        assertEquals(2, capacidadDisponible);
    }
}
