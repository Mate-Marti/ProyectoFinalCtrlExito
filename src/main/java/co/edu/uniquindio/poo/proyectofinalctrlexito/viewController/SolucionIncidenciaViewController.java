package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Incidencia;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.EstadoIncidencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class SolucionIncidenciaViewController {

    @FXML private TextArea txtSolucion;
    @FXML private Label lblIncidencia;

    @FXML
    public void initialize() {
        Incidencia i = IncidenciaAdminViewController.incidenciaSeleccionada;
        if (i != null) {
            lblIncidencia.setText("Solucionando: " + i.getAsunto());
        }
    }

    @FXML
    void enviarSolucion(ActionEvent event) throws IOException {
        Incidencia i = IncidenciaAdminViewController.incidenciaSeleccionada;

        if (txtSolucion.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo Vacío");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, escribe un mensaje de solución antes de enviarlo.");
            alert.showAndWait();
            return;
        }

        if (i != null) {
            i.setEstado(EstadoIncidencia.RESUELTA);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText("Solución Enviada");
        alert.setContentText("La solución ha sido enviada con éxito al usuario y el estado cambió a RESUELTA.");
        alert.showAndWait();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/IncidenciaAdmin.fxml"))));
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/IncidenciaAdmin.fxml"))));
    }
}