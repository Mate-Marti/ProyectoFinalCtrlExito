package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Compra;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Evento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.EstadoEvento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.TipoPago;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Usuario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.Random;

public class CompraViewController {

    @FXML
    private ComboBox<Evento> cbEventos;

    @FXML
    private ComboBox<TipoPago> cbTipoPago;

    @FXML
    public void initialize() {
        Plataforma plataforma = Plataforma.getInstancia();

        // Cargar los eventos registrados en la plataforma
        cbEventos.setItems(FXCollections.observableArrayList(plataforma.getListaEventos()));

        // Formatear visualmente las celdas del ComboBox de Eventos
        cbEventos.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Evento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " [" + item.getEstado() + "]");
                }
            }
        });
        cbEventos.setConverter(new StringConverter<>() {
            @Override
            public String toString(Evento item) {
                return item == null ? "" : item.getNombre() + " [" + item.getEstado() + "]";
            }
            @Override
            public Evento fromString(String string) {
                return null;
            }
        });

        // Cargar los valores de tu Enum TipoPago directamente al ComboBox
        cbTipoPago.setItems(FXCollections.observableArrayList(TipoPago.values()));
    }

    @FXML
    void procesarCompra(ActionEvent event) {
        // 1. Validar selección del evento
        Evento eventoSeleccionado = cbEventos.getSelectionModel().getSelectedItem();
        if (eventoSeleccionado == null) {
            mostrarAlerta("Error", "Selección Requerida", "Por favor, selecciona un evento de la cartelera.");
            return;
        }

        // 2. NUEVA VALIDACIÓN: Validar que el evento esté estrictamente PUBLICADO
        if (eventoSeleccionado.getEstado() != EstadoEvento.PUBLICADO) {
            mostrarAlerta("Venta No Disponible", "Evento Inhabilitado",
                    "Lo sentimos, solo se pueden adquirir boletas para eventos en estado PUBLICADO.\n" +
                            "El estado actual de este evento es: " + eventoSeleccionado.getEstado());
            return;
        }

        // 3. Validar selección del método de pago
        TipoPago pagoSeleccionado = cbTipoPago.getSelectionModel().getSelectedItem();
        if (pagoSeleccionado == null) {
            mostrarAlerta("Error", "Selección Requerida", "Por favor, selecciona un método de pago.");
            return;
        }

        // 4. NUEVA LÓGICA: Obtener el precio real dinámico desde el Recinto y sus Zonas
        double precioRealBoleto = 0.0;

        if (eventoSeleccionado.getRecinto() != null && !eventoSeleccionado.getRecinto().getListaZonas().isEmpty()) {
            // Extraemos el precio base de la zona del recinto (usando el formato de tu Recinto.java)
            precioRealBoleto = eventoSeleccionado.getRecinto().getListaZonas().get(0).getPreciobase();
        } else {
            // Alerta de seguridad por si el administrador creó el evento pero olvidó asignarle recinto o zonas
            mostrarAlerta("Error de Configuración", "Recinto sin Zonas",
                    "Este evento no tiene un precio asignado porque su recinto no cuenta con zonas registradas.");
            return;
        }

        Plataforma plataforma = Plataforma.getInstancia();

        // ===============================================================
        // AQUÍ ESTÁ EL CAMBIO PRINCIPAL: USAR EL USUARIO DE LA SESIÓN
        // ===============================================================
        Usuario usuarioLogueado = plataforma.getUsuarioSesionActiva();

        // Validamos que realmente haya alguien logueado por seguridad
        if (usuarioLogueado == null) {
            mostrarAlerta("Sesión Requerida", "Acceso Denegado", "Debes iniciar sesión para realizar una compra.");
            return;
        }

        // Generar un ID aleatorio de compra
        int idCompraAleatorio = new Random().nextInt(90000) + 10000;

        // Construcción de la transacción pasándole la opción seleccionada y el precio real calculados dinámicamente
        Compra nuevaCompra = new Compra.Builder()
                .setIdCompra(idCompraAleatorio)
                .setEvento(eventoSeleccionado)
                .setUsuario(usuarioLogueado)
                .setTipoPago(pagoSeleccionado)
                .setTotal(precioRealBoleto) // ASIGNADO: Ahora usa el precio base configurado en la zona del recinto
                .build();

        // Guardar oficialmente la compra en la lista interna de tu modelo Usuario
        usuarioLogueado.agregarCompra(nuevaCompra);

        mostrarAlerta("Éxito", "Boleto Adquirido", "¡Compra exitosa para " + usuarioLogueado.getNombreCompleto() + "!\n\n" +
                " ID Compra: #" + idCompraAleatorio + "\n" +
                " Evento: " + eventoSeleccionado.getNombre() + "\n" +
                " Total Pagado: $" + precioRealBoleto + "\n" +
                " Medio de Pago: " + pagoSeleccionado);

        // Limpiar selecciones de la pantalla
        cbEventos.getSelectionModel().clearSelection();
        cbTipoPago.getSelectionModel().clearSelection();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"))));
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}