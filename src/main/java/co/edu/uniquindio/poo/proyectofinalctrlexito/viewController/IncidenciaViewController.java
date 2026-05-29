package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class IncidenciaViewController {

    @FXML private TextField txtAsunto;
    @FXML private TextArea txtDesdescripcion;
    @FXML private TextArea txtDescripcion;
    @FXML private Button btnEnviarReporte;

    @FXML
    void enviarReporte(ActionEvent event) {
        String asunto = txtAsunto.getText();
        String descripcion = txtDescripcion.getText();

        if (asunto == null || asunto.trim().isEmpty() || descripcion == null || descripcion.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llena todos los campos antes de enviar.");
            alert.showAndWait();
            return;
        }

        int nuevoId = Plataforma.getInstancia().consultarIncidencias().size() + 1;

        Incidencia nuevaIncidencia = new Incidencia(
                nuevoId,
                descripcion,
                new Date(),
                null,
                null,
                EstadoIncidencia.ABIERTA,
                asunto
        );

        // ✅ NUEVO: guardar quién reportó la incidencia
        if (Session.getUsuarioActual() != null) {
            nuevaIncidencia.setIdUsuarioReportante(Session.getUsuarioActual().getId());
        }

        Plataforma.getInstancia().getListaIncidencias().add(nuevaIncidencia);

        Alert alertExito = new Alert(Alert.AlertType.INFORMATION);
        alertExito.setTitle("Reporte Exitoso");
        alertExito.setHeaderText(null);
        alertExito.setContentText("La incidencia ha sido reportada correctamente.");
        alertExito.showAndWait();

        txtAsunto.clear();
        txtDescripcion.clear();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"))));
    }
}
