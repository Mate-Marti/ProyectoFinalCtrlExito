package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.VerificacionAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministradorViewController {

    @FXML private TextField txtUsuarioCajero;
    @FXML private PasswordField txtContrasenaCajero;

    private final VerificacionAdmin verificadorProxy = new VerificacionAdmin("admin", "admin123");

    @FXML
    void abrirAdministradorMenu(ActionEvent event) throws IOException {
        String usuarioIngresado = txtUsuarioCajero.getText().trim();
        String contraseniaIngresada = txtContrasenaCajero.getText();

        if (usuarioIngresado.isEmpty() || contraseniaIngresada.isEmpty()) {
            mostrarAlertaError("Campos incompletos", "Por favor ingresa el Usuario y la Frase de Seguridad.");
            return;
        }

        if (verificadorProxy.login(usuarioIngresado, contraseniaIngresada)) {
            // Si el Proxy da luz verde, permitimos el acceso al menú de administración
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/AdministradorMenu.fxml"))));
        } else {
            mostrarAlertaError("Acceso Denegado", "Las credenciales de Administrador son incorrectas.");
        }
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"))));
    }
}