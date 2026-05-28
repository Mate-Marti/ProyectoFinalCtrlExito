package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter;

import java.util.Optional;
import java.util.function.UnaryOperator;

public class RegistroUsuarioViewController {

    @FXML private TextField txtId, txtNombre, txtCorreo, txtTelefono;
    @FXML private PasswordField txtContrasena, txtConfirmarContrasena;
    @FXML private Label lblErrorCorreo, lblErrorGeneral;
    @FXML private Label lblErrorContrasena;

    private final Plataforma plataforma = Plataforma.getInstancia();

    @FXML
    public void initialize() {
        UnaryOperator<TextFormatter.Change> soloNumeros = change -> {
            String text = change.getControlNewText();
            return text.matches("\\d*") ? change : null;
        };

        UnaryOperator<TextFormatter.Change> soloLetras = change -> {
            String text = change.getControlNewText();
            return text.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*") ? change : null;
        };

        txtId.setTextFormatter(new TextFormatter<>(soloNumeros));
        txtTelefono.setTextFormatter(new TextFormatter<>(soloNumeros));
        txtNombre.setTextFormatter(new TextFormatter<>(soloLetras));

        txtCorreo.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty() && !newVal.contains("@")) {
                lblErrorCorreo.setVisible(true);
            } else {
                lblErrorCorreo.setVisible(false);
            }
        });
    }

    @FXML
    void registrar() {
        String correo = txtCorreo.getText().trim();
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String contrasena = txtContrasena.getText();
        String confirmarContrasena = txtConfirmarContrasena.getText();

        lblErrorCorreo.setVisible(false);
        lblErrorGeneral.setVisible(false);
        lblErrorContrasena.setVisible(false);

        if (!correo.contains("@")) {
            lblErrorCorreo.setVisible(true);
            return;
        }

        if (id.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || contrasena.isEmpty()) {
            lblErrorGeneral.setText("Por favor completa todos los campos.");
            lblErrorGeneral.setVisible(true);
            return;
        }

        // NUEVO
        if (!contrasena.equals(confirmarContrasena)) {
            lblErrorContrasena.setVisible(true);
            return;
        }

        if (plataforma.buscarUsuario(id)) {
            lblErrorGeneral.setText("Ya existe un usuario con esa cédula.");
            lblErrorGeneral.setVisible(true);
            return;
        }

        plataforma.registrarUsuario(id, nombre, correo, telefono, "", contrasena);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro Exitoso");
        alert.setHeaderText("Usuario creado y sesión iniciada");
        alert.setContentText("Serás redirigido a inicio sesión.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            PlataformaViewController.getInstancia()
                    .cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Usuario.fxml");
        }
    }
}