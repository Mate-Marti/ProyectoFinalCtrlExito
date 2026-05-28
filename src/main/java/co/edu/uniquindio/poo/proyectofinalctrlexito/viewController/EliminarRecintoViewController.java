package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
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

public class EliminarRecintoViewController implements Initializable {

    @FXML private ListView<Recinto> listaRecintos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaRecintos.setItems(
                FXCollections.observableArrayList(
                        Plataforma.getInstancia().getListaRecintos()
                )
        );
    }

    @FXML
    void eliminarRecinto(ActionEvent event) {
        Recinto seleccionado = listaRecintos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Sin selección", "Por favor selecciona un recinto de la lista.");
            return;
        }

        Plataforma.getInstancia().eliminarRecinto(seleccionado.getIdRecinto());
        listaRecintos.getItems().remove(seleccionado);
        mostrarAlerta("Éxito", "Recinto \"" + seleccionado.getNombre() + "\" eliminado correctamente.");
    }

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionRecinto.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene().getWindow();
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