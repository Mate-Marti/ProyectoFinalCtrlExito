package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.EstadoEvento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Evento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ActualizarEventoViewController implements Initializable {

    // ── Búsqueda ─────────────────────────────────────────────────
    @FXML private TextField          txtIdBuscar;

    // ── Datos editables ──────────────────────────────────────────
    @FXML private TextField          txtNombre;
    @FXML private TextField          txtDescripcion;
    @FXML private TextField          txtCategoria;

    // ── Estado ───────────────────────────────────────────────────
    @FXML private Label              lblEstadoActual;
    @FXML private ComboBox<EstadoEvento> comboEstado;

    // ── Evento cargado actualmente ───────────────────────────────
    private Evento eventoActual;

    private final Plataforma plataforma = Plataforma.getInstancia();

    // ============================================================
    // INICIALIZACIÓN
    // ============================================================

    /**
     * Carga los valores del enum EstadoEvento en el ComboBox de estado.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboEstado.setItems(FXCollections.observableArrayList(EstadoEvento.values()));
    }

    // ============================================================
    // ACCIONES
    // ============================================================

    /**
     * Busca el evento por ID y carga sus datos en el formulario.
     */
    @FXML
    void buscarEvento(ActionEvent event) {

        String id = txtIdBuscar.getText().trim();
        if (id.isEmpty()) {
            mostrarAlerta("Campo vacío", "Ingresa el ID del evento a buscar.");
            return;
        }

        // Buscar en la lista de eventos de la plataforma
        Evento encontrado = null;
        for (Evento e : plataforma.getListaEventos()) {
            if (e.getIdEvento().equals(id)) {
                encontrado = e;
                break;
            }
        }

        if (encontrado == null) {
            mostrarAlerta("No encontrado", "No existe un evento con ID: " + id);
            limpiarFormulario();
            return;
        }

        // Cargar datos en el formulario
        eventoActual = encontrado;
        txtNombre.setText(eventoActual.getNombre());
        txtDescripcion.setText(eventoActual.getDescripcion());
        txtCategoria.setText(eventoActual.getCategoria());
        lblEstadoActual.setText("Estado actual: " + eventoActual.getEstado());
        comboEstado.setValue(eventoActual.getEstado());
    }

    /**
     * Actualiza nombre, categoría y descripción del evento
     * llamando a {@link Evento#actualizarEvento}.
     */
    @FXML
    void actualizarEvento(ActionEvent event) {

        if (eventoActual == null) {
            mostrarAlerta("Sin evento", "Primero busca un evento por ID.");
            return;
        }

        if (txtNombre.getText().trim().isEmpty()
                || txtDescripcion.getText().trim().isEmpty()
                || txtCategoria.getText().trim().isEmpty()) {
            mostrarAlerta("Campos incompletos", "Completa todos los campos antes de guardar.");
            return;
        }

        // Llama al método del modelo — la fecha se mantiene con new Date() ya que
        // actualizarEvento() la recibe pero el constructor la inicializa igual
        eventoActual.actualizarEvento(
                txtNombre.getText().trim(),
                txtCategoria.getText().trim(),
                txtDescripcion.getText().trim(),
                new Date()
        );

        mostrarAlerta("Actualizado",
                "El evento \"" + eventoActual.getNombre() + "\" fue actualizado correctamente.");
    }

    /**
     * Cambia el estado del evento usando los métodos específicos de Evento
     * (publicarEvento, pausarEvento, cancelarEvento, finalizarEvento).
     * Esto activa el patrón Observer notificando a los usuarios suscritos.
     */
    @FXML
    void cambiarEstado(ActionEvent event) {

        if (eventoActual == null) {
            mostrarAlerta("Sin evento", "Primero busca un evento por ID.");
            return;
        }

        EstadoEvento nuevoEstado = comboEstado.getValue();
        if (nuevoEstado == null) {
            mostrarAlerta("Sin estado", "Selecciona un estado del listado.");
            return;
        }

        // Usa el método correspondiente del modelo para activar el Observer
        switch (nuevoEstado) {
            case PUBLICADO:
                eventoActual.publicarEvento();
                break;
            case PAUSADO:
                eventoActual.pausarEvento();
                break;
            case CANCELADO:
                eventoActual.cancelarEvento();
                break;
            case FINALIZADO:
                eventoActual.finalizarEvento();
                break;
            case BORRADOR:
                eventoActual.setEstado(EstadoEvento.BORRADOR);
                break;
        }

        lblEstadoActual.setText("Estado actual: " + eventoActual.getEstado());
        mostrarAlerta("Estado actualizado",
                "Estado cambiado a: " + nuevoEstado);
    }

    /**
     * Regresa a la vista de gestión de eventos.
     */
    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionEvento.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================================================
    // UTILIDADES
    // ============================================================

    private void limpiarFormulario() {
        eventoActual = null;
        txtNombre.clear();
        txtDescripcion.clear();
        txtCategoria.clear();
        lblEstadoActual.setText("Estado actual: —");
        comboEstado.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
