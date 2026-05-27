package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.function.UnaryOperator;
import java.util.Optional;

public class RegistroUsuarioViewController {
    @FXML private TextField txtId, txtNombre, txtCorreo, txtTelefono;
    @FXML private Label lblErrorCorreo;

    @FXML
    public void initialize() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            return text.matches("\\d*") ? change : null;
        };
        txtId.setTextFormatter(new TextFormatter<>(filter));
        txtTelefono.setTextFormatter(new TextFormatter<>(filter));
    }

    @FXML
    void registrar() {
        String correo = txtCorreo.getText();

        if (!correo.contains("@")) {
            lblErrorCorreo.setVisible(true);
            return;
        } else {
            lblErrorCorreo.setVisible(false);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro Exitoso");
        alert.setHeaderText("Usuario creado y sesión iniciada");
        alert.setContentText("Serás redirigido a la página principal.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            PlataformaViewController.getInstancia().mostrarInicio(null);
        }
    }
}