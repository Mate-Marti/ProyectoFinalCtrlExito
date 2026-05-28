package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Evento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EliminarEventoViewController implements Initializable {

    @FXML
    private ListView<Evento> listaEventos;

    /**
     * Carga todos los eventos registrados en la plataforma
     * dentro del ListView al inicializar la vista.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaEventos.setItems(
                FXCollections.observableArrayList(
                        Plataforma.getInstancia().getListaEventos()
                )
        );
    }

    /**
     * Elimina el evento seleccionado llamando a
     * {@link Plataforma#eliminarEvento(String)}.
     */
    @FXML
    void eliminarEvento(ActionEvent event) {
        Evento seleccionado = listaEventos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Sin selección", "Por favor selecciona un evento de la lista.");
            return;
        }

        Plataforma.getInstancia().eliminarEvento(seleccionado.getIdEvento());
        listaEventos.getItems().remove(seleccionado);
        mostrarAlerta("Éxito", "Evento \"" + seleccionado.getNombre() + "\" eliminado correctamente.");
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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
