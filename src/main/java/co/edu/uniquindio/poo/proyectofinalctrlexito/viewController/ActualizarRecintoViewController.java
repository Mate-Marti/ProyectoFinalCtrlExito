package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ActualizarRecintoViewController {

    @FXML private TextField txtIdRecinto;
    @FXML private TextField txtNombreRecinto;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtCiudad;

    @FXML
    void actualizarRecinto(ActionEvent event) {

        String idTexto   = txtIdRecinto.getText().trim();
        String nombre    = txtNombreRecinto.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String ciudad    = txtCiudad.getText().trim();

        if (idTexto.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || ciudad.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("ID inválido", "El ID debe ser un número entero.");
            return;
        }

        // Verificar que el recinto exista antes de actualizar
        boolean existe = Plataforma.getInstancia().getListaRecintos()
                .stream()
                .anyMatch(r -> r.getIdRecinto() == id);

        if (!existe) {
            mostrarAlerta("No encontrado", "No existe un recinto con el ID " + id + ".");
            return;
        }

        Plataforma.getInstancia().actualizarRecinto(id, nombre, direccion, ciudad);

        mostrarAlerta("Éxito", "Recinto actualizado correctamente.");
        txtIdRecinto.clear();
        txtNombreRecinto.clear();
        txtDireccion.clear();
        txtCiudad.clear();
    }

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionRecinto.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}