package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioViewController {

        @FXML
        void abrirUsuarioMenu(ActionEvent event) throws IOException {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/UsuarioMenu.fxml"))));
        }

        @FXML
        void volver(ActionEvent event) throws IOException {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"))));
        }
}
