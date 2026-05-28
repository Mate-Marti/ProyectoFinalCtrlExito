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

public class ActualizarZonaViewController {

    @FXML private TextField txtIdRecinto;
    @FXML private TextField txtIdZona;
    @FXML private TextField txtNombreZona;
    @FXML private TextField txtCapacidad;
    @FXML private TextField txtPrecioBase;

    @FXML
    void actualizarZona(ActionEvent event) {

        String idRecintoTxt = txtIdRecinto.getText().trim();
        String idZonaTxt    = txtIdZona.getText().trim();
        String nombre       = txtNombreZona.getText().trim();
        String capacidadTxt = txtCapacidad.getText().trim();
        String precioTxt    = txtPrecioBase.getText().trim();

        if (idRecintoTxt.isEmpty() || idZonaTxt.isEmpty() || nombre.isEmpty()
                || capacidadTxt.isEmpty() || precioTxt.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            mostrarAlerta("Error de Formato", "El nombre solo debe contener letras y espacios.");
            return;
        }

        int idRecinto, idZona, capacidad;
        double precio;

        try {
            idRecinto = Integer.parseInt(idRecintoTxt);
            idZona    = Integer.parseInt(idZonaTxt);
            capacidad = Integer.parseInt(capacidadTxt);
            precio    = Double.parseDouble(precioTxt);
        } catch (NumberFormatException e) {
            mostrarAlerta("Dato inválido", "ID y capacidad deben ser enteros, precio debe ser decimal.");
            return;
        }

        if (idRecinto < 0 || idZona < 0 || capacidad < 0 || precio < 0) {
            mostrarAlerta("Valores inválidos", "No se permiten números negativos.");
            return;
        }

        Recinto recinto = Plataforma.getInstancia().buscarRecintoPorId(idRecinto);

        if (recinto == null) {
            mostrarAlerta("Recinto no encontrado", "No existe un recinto con el ID " + idRecinto + ".");
            return;
        }

        boolean actualizado = recinto.actualizarZona(idZona, nombre, capacidad, precio);

        if (actualizado) {
            mostrarAlerta("Éxito", "Zona actualizada correctamente.");
            txtIdRecinto.clear();
            txtIdZona.clear();
            txtNombreZona.clear();
            txtCapacidad.clear();
            txtPrecioBase.clear();
        } else {
            mostrarAlerta("Zona no encontrada", "No existe una zona con el ID " + idZona
                    + " en el recinto " + idRecinto + ".");
        }
    }

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionZona.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
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