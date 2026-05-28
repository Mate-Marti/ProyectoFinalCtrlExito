package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Usuario;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionPerfilViewController {

    @FXML private TextField txtPerfilNombre;
    @FXML private TextField txtPerfilCorreo;
    @FXML private TextField txtPerfilTelefono;

    private final Plataforma plataforma = Plataforma.getInstancia();

    @FXML
    public void initialize() {
        // Pre-carga los datos actuales del usuario logueado
        Usuario usuario = Session.getUsuarioActual();
        if (usuario != null) {
            txtPerfilNombre.setText(usuario.getNombreCompleto());
            txtPerfilCorreo.setText(usuario.getCorreo());
            txtPerfilTelefono.setText(usuario.getTelefono());
        }
    }

    @FXML
    void guardarCambios(ActionEvent event) {
        Usuario usuario = Session.getUsuarioActual();
        if (usuario == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No hay sesión activa.");
            return;
        }

        String nombre   = txtPerfilNombre.getText().trim();
        String correo   = txtPerfilCorreo.getText().trim();
        String telefono = txtPerfilTelefono.getText().trim();

        // Llama actualizarUsuario pasando null en metodoPago para no tocarlo
        boolean actualizado = plataforma.actualizarUsuario(
                usuario.getId(),
                nombre,
                correo,
                telefono,
                null   // metodoPago: null → el método ya lo ignora si es null
        );

        if (actualizado) {
            // Session sigue apuntando al mismo objeto en memoria,
            // que ya fue mutado por los setters dentro de actualizarUsuario
            mostrarAlerta(Alert.AlertType.INFORMATION,
                    "Éxito", "Datos actualizados correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Error", "No se pudo actualizar el usuario.");
        }
    }

    @FXML
    void volver(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml");
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
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
