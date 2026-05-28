package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Zona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class EliminarZonaViewController implements Initializable {

    @FXML
    private ListView<Zona> listaZonas;

    /**
     * Inicializa la vista cargando todas las zonas de todos los recintos
     * registrados en la plataforma, igual que EliminarRecintoViewController
     * carga todos los recintos.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Zona> todasLasZonas = FXCollections.observableArrayList();

        for (Recinto recinto : Plataforma.getInstancia().getListaRecintos()) {
            todasLasZonas.addAll(recinto.getListaZonas());
        }

        listaZonas.setItems(todasLasZonas);
    }

    /**
     * Elimina la zona seleccionada llamando a {@link Recinto#eliminarZona(int)}
     * sobre el recinto al que pertenece la zona.
     */
    @FXML
    void eliminarZona(ActionEvent event) {
        Zona seleccionada = listaZonas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta("Sin selección", "Por favor selecciona una zona de la lista.");
            return;
        }

        Recinto recinto = seleccionada.getRecinto();

        if (recinto == null) {
            mostrarAlerta("Error", "La zona no tiene un recinto asociado.");
            return;
        }

        recinto.eliminarZona(seleccionada.getIdZona());
        listaZonas.getItems().remove(seleccionada);
        mostrarAlerta("Éxito", "Zona \"" + seleccionada.getNombre() + "\" eliminada correctamente.");
    }

    /**
     * Regresa a la vista de gestión de zonas.
     */
    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionZona.fxml"
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
