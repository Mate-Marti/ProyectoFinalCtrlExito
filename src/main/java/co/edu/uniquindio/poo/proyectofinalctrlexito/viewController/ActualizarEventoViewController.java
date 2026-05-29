package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.*;
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
import java.util.List;
import java.util.ResourceBundle;

public class ActualizarEventoViewController implements Initializable {

    @FXML private TextField              txtIdBuscar;
    @FXML private TextField              txtNombre;
    @FXML private TextField              txtDescripcion;
    @FXML private TextField              txtCategoria;
    @FXML private Label                  lblEstadoActual;
    @FXML private ComboBox<EstadoEvento> comboEstado;

    private Evento eventoActual;
    private final Plataforma plataforma = Plataforma.getInstancia();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboEstado.setItems(FXCollections.observableArrayList(EstadoEvento.values()));
    }

    @FXML
    void buscarEvento(ActionEvent event) {
        String id = txtIdBuscar.getText().trim();
        if (id.isEmpty()) {
            mostrarAlerta("Campo vacío", "Ingresa el ID del evento a buscar.");
            return;
        }

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

        eventoActual = encontrado;
        txtNombre.setText(eventoActual.getNombre());
        txtDescripcion.setText(eventoActual.getDescripcion());
        txtCategoria.setText(eventoActual.getCategoria());
        lblEstadoActual.setText("Estado actual: " + eventoActual.getEstado());
        comboEstado.setValue(eventoActual.getEstado());
    }

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

        eventoActual.actualizarEvento(
                txtNombre.getText().trim(),
                txtCategoria.getText().trim(),
                txtDescripcion.getText().trim(),
                new Date()
        );

        mostrarAlerta("Actualizado",
                "El evento \"" + eventoActual.getNombre() + "\" fue actualizado correctamente.");
    }

    // ✅ onAction="#cambiarEstado" — debe tener ActionEvent como parámetro
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

        switch (nuevoEstado) {
            case PUBLICADO:
                eventoActual.publicarEvento();
                notificarTodosLosUsuarios("📢 El evento \"" + eventoActual.getNombre() + "\" ha sido PUBLICADO.");
                break;
            case PAUSADO:
                eventoActual.pausarEvento();
                notificarTodosLosUsuarios("⏸ El evento \"" + eventoActual.getNombre() + "\" ha sido PAUSADO.");
                break;
            case CANCELADO:
                eventoActual.cancelarEvento();
                notificarTodosLosUsuarios("❌ El evento \"" + eventoActual.getNombre() + "\" ha sido CANCELADO.");
                break;
            case FINALIZADO:
                eventoActual.finalizarEvento();
                notificarTodosLosUsuarios("✅ El evento \"" + eventoActual.getNombre() + "\" ha FINALIZADO.");
                break;
            case BORRADOR:
                // Solo cambia estado, sin notificar
                eventoActual.setEstado(EstadoEvento.BORRADOR);
                break;
        }

        lblEstadoActual.setText("Estado actual: " + eventoActual.getEstado());
        mostrarAlerta("Estado actualizado", "Estado cambiado a: " + nuevoEstado);
    }

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

    private void notificarTodosLosUsuarios(String mensaje) {
        List<Persona> personas = plataforma.getListaPersonas();
        if (personas == null) return;
        for (Persona p : personas) {
            if (p instanceof Usuario) {
                GestorNotificaciones.getInstancia()
                        .agregarNotificacion(((Usuario) p).getId(), mensaje);
            }
        }
    }

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
