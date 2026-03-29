package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlataformaViewController {


    @FXML void abrirAdministrador(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/Administrador.fxml", event); }
    @FXML void abrirUsuario(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/Usuario.fxml", event); }


    private void cambiarVista(String ruta, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ruta));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
