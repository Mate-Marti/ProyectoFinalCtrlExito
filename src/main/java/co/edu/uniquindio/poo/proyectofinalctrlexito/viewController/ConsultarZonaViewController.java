package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Zona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConsultarZonaViewController {

    @FXML
    private TextField txtIdBuscar;

    @FXML
    private TextArea txtResultado;

    /**
     * Busca una zona por su ID recorriendo todos los recintos de la plataforma
     * y llamando a {@link Recinto#consultarZona(int)}.
     * Muestra la información encontrada en el área de texto.
     */
    @FXML
    void buscarZona(ActionEvent event) {

        String input = txtIdBuscar.getText().trim();

        if (input.isEmpty()) {
            mostrarAlerta("Campo vacío", "Por favor ingresa el ID de la zona.");
            return;
        }

        int idZona;
        try {
            idZona = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            mostrarAlerta("ID inválido", "El ID debe ser un número entero.");
            return;
        }

        // Buscar la zona en todos los recintos usando Recinto#consultarZona
        Zona zonaEncontrada = null;
        Recinto recintoContenedor = null;

        for (Recinto recinto : Plataforma.getInstancia().getListaRecintos()) {
            Zona zona = recinto.consultarZona(idZona);
            if (zona != null) {
                zonaEncontrada = zona;
                recintoContenedor = recinto;
                break;
            }
        }

        if (zonaEncontrada == null) {
            txtResultado.setText("No se encontró ninguna zona con ID: " + idZona);
            return;
        }

        // Mostrar información de la zona
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMACIÓN DE LA ZONA ===\n\n");
        info.append("ID Zona       : ").append(zonaEncontrada.getIdZona()).append("\n");
        info.append("Nombre        : ").append(zonaEncontrada.getNombre()).append("\n");
        info.append("Capacidad     : ").append(zonaEncontrada.getCapacidad()).append("\n");
        info.append("Precio base   : $").append(zonaEncontrada.getPreciobase()).append("\n");
        info.append("Disponibles   : ").append(zonaEncontrada.obtenerDisponibilidad()).append("\n");
        info.append("\n--- Recinto al que pertenece ---\n");
        info.append(recintoContenedor.toString());

        txtResultado.setText(info.toString());
    }

    /**
     * Regresa a la vista de gestión de zonas.
     */
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
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
