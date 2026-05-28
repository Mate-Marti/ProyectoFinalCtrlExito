package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Persona;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Usuario;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionUsuarioViewController implements Initializable {

    // ── Tabla ────────────────────────────────────────────────────
    @FXML private TableView<Usuario>           tablaUsuariosRegistrados;
    @FXML private TableColumn<Usuario, String> colId;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colCorreo;
    @FXML private TableColumn<Usuario, String> colTelefono;
    @FXML private TableColumn<Usuario, String> colMetodoPago;
    @FXML private TableColumn<Usuario, Number> colCompras;

    // ── Singleton ────────────────────────────────────────────────
    private final Plataforma plataforma = Plataforma.getInstancia();

    // ============================================================
    // INICIALIZACIÓN
    // ============================================================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId        .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        colNombre    .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreCompleto()));
        colCorreo    .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreo()));
        colTelefono  .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
        colMetodoPago.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMetodoPago()));
        colCompras   .setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getCompras().size()));

        refrescarTabla();
    }

    // ============================================================
    // Eliminar usuario seleccionado
    // ============================================================
    @FXML
    void eliminarUsuario(ActionEvent event) {
        Usuario seleccionado = tablaUsuariosRegistrados.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING,
                    "Sin selección", "Por favor selecciona un usuario de la tabla.");
            return;
        }

        // Confirmación antes de eliminar
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Estás seguro de que deseas eliminar la cuenta de \""
                + seleccionado.getNombreCompleto() + "\"?");

        confirm.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                boolean eliminado = plataforma.eliminarUsuario(seleccionado.getId());
                if (eliminado) {
                    refrescarTabla();
                    mostrarAlerta(Alert.AlertType.INFORMATION,
                            "Usuario eliminado",
                            "La cuenta de \"" + seleccionado.getNombreCompleto()
                                    + "\" fue eliminada correctamente.");
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR,
                            "Error", "No se pudo eliminar el usuario.");
                }
            }
        });
    }

    // ============================================================
    // Volver al menú administrador
    // ============================================================
    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(
                getClass().getResource(
                        "/co/edu/uniquindio/poo/proyectofinalctrlexito/AdministradorMenu.fxml"
                )
        )));
    }

    // ============================================================
    // UTILIDADES
    // ============================================================
    private void refrescarTabla() {
        List<Usuario> usuarios = new ArrayList<>();
        for (Persona persona : plataforma.getListaPersonas()) {
            if (persona instanceof Usuario) {
                usuarios.add((Usuario) persona);
            }
        }
        tablaUsuariosRegistrados.setItems(FXCollections.observableArrayList(usuarios));
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
