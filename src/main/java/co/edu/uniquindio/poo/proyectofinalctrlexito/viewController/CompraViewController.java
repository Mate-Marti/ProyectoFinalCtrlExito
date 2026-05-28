package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Compra;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Evento;
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
    private ComboBox<TipoPago> cbTipoPago; // NUEVO: Enlazado con el FXML

    @FXML
    public void initialize() {
        Plataforma plataforma = Plataforma.getInstancia();

        // 1. Cargar los eventos registrados en la plataforma
        cbEventos.setItems(FXCollections.observableArrayList(plataforma.getListaEventos()));

        // Formatear visualmente las celdas del ComboBox de Eventos
        cbEventos.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Evento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " (" + item.getCategoria() + ")");
                }
            }
        });
        cbEventos.setConverter(new StringConverter<>() {
            @Override
            public String toString(Evento item) {
                return item == null ? "" : item.getNombre() + " (" + item.getCategoria() + ")";
            }
            @Override
            public Evento fromString(String string) {
                return null;
            }
        });

        // 2. NUEVO: Cargar los valores de tu Enum TipoPago directamente al ComboBox
        cbTipoPago.setItems(FXCollections.observableArrayList(TipoPago.values()));
    }

    @FXML
    void procesarCompra(ActionEvent event) {
        // Validar selección del evento
        Evento eventoSeleccionado = cbEventos.getSelectionModel().getSelectedItem();
        if (eventoSeleccionado == null) {
            mostrarAlerta("Error", "Selección Requerida", "Por favor, selecciona un evento de la cartelera.");
            return;
        }

        // NUEVO: Validar selección del método de pago
        TipoPago pagoSeleccionado = cbTipoPago.getSelectionModel().getSelectedItem();
        if (pagoSeleccionado == null) {
            mostrarAlerta("Error", "Selección Requerida", "Por favor, selecciona un método de pago.");
            return;
        }

        Plataforma plataforma = Plataforma.getInstancia();

        // Buscar el usuario activo en el sistema
        Usuario usuarioLogueado = null;
        for (Object p : plataforma.getListaPersonas()) {
            if (p instanceof Usuario) {
                usuarioLogueado = (Usuario) p;
                break;
            }
        }

        // Si no hay usuarios registrados, creamos uno de prueba para la simulación
        if (usuarioLogueado == null) {
            plataforma.registrarUsuario("999", "Cliente Demo Éxito", "demo@mail.com", "3150000", "Efectivo", "1234");
            for (Object p : plataforma.getListaPersonas()) {
                if (p instanceof Usuario) {
                    usuarioLogueado = (Usuario) p;
                    break;
                }
            }
        }

        // Generar un ID aleatorio de compra
        int idCompraAleatorio = new Random().nextInt(90000) + 10000;

        // Construcción de la transacción pasándole la opción seleccionada dinámicamente
        Compra nuevaCompra = new Compra.Builder()
                .setIdCompra(idCompraAleatorio)
                .setEvento(eventoSeleccionado)
                .setUsuario(usuarioLogueado)
                .setTipoPago(pagoSeleccionado) // ASIGNADO: El TipoPago elegido por el usuario
                .setTotal(75000.0) // Tarifa estándar simulada
                .build();

        // Guardar oficialmente la compra en la lista interna de tu modelo Usuario
        usuarioLogueado.agregarCompra(nuevaCompra);

        mostrarAlerta("Éxito", "Boleto Adquirido", "¡Compra exitosa!\n\n" +
                "🎫 ID Compra: #" + idCompraAleatorio + "\n" +
                "📅 Evento: " + eventoSeleccionado.getNombre() + "\n" +
                "💳 Medio de Pago: " + pagoSeleccionado);

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