package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class GestionAsientoViewController {

    @FXML
    void abrirCrearAsiento(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/CrearAsiento.fxml", event);
    }

    @FXML
    void abrirCambiarEstado(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/CambiarEstadoAsiento.fxml", event);
    }

    @FXML
    void abrirConsultarDisponibilidad(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/ConsultarDisponibilidad.fxml", event);
    }

    private void cambiarVista(String ruta, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ruta));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/AdministradorMenu.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}