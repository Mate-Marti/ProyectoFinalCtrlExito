package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionZonaViewController {

    @FXML
    void abrirCrearZona(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/CrearZona.fxml");
    }

    @FXML
    void abrirActualizarZona(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/ActualizarZona.fxml");
    }

    @FXML
    void abrirEliminarZona(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/EliminarZona.fxml");
    }

    @FXML
    void abrirConsultarZona(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/ConsultarZona.fxml");
    }

    @FXML
    void volver(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/AdministradorMenu.fxml");
    }

    private void cambiarVista(ActionEvent event, String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
