package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Persona;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Session;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioViewController {

    @FXML private TextField     txtUsuarioCajero;
    @FXML private PasswordField txtContrasenaCajero;
    @FXML private Label         lblError;

    private final Plataforma plataforma = Plataforma.getInstancia();

    @FXML
    void iniciarSesion(ActionEvent event) {
        String input      = txtUsuarioCajero.getText().trim();
        String contrasena = txtContrasenaCajero.getText();

        if (input.isEmpty() || contrasena.isEmpty()) {
            mostrarError("Por favor completa todos los campos.");
            return;
        }

        Usuario encontrado = null;
        for (Persona persona : plataforma.getListaPersonas()) {
            if (persona instanceof Usuario) {
                Usuario u = (Usuario) persona;
                if ((u.getCorreo().equals(input) || u.getNombreCompleto().equals(input))
                        && u.getContrasena().equals(contrasena)) {
                    encontrado = u;
                    break;
                }
            }
        }

        if (encontrado == null) {
            mostrarError("Credenciales incorrectas. Verifica tu usuario y contraseña.");
            return;
        }

        // Guarda el usuario en sesión antes de navegar
        Session.setUsuarioActual(encontrado);

        // Navega a la vista principal
        PlataformaViewController.getInstancia().mostrarInicio(null);
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de inicio de sesión");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void abrirUsuarioMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(
                "/co/edu/uniquindio/poo/proyectofinalctrlexito/UsuarioMenu.fxml"))));
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(
                "/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"))));
    }
}