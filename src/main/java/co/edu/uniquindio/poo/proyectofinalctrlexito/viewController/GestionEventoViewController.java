package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionEventoViewController {

    /**
     * Abre la vista para crear un nuevo evento.
     */
    @FXML
    void abrirCrearEvento(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/CrearEvento.fxml");
    }

    /**
     * Abre la vista para actualizar un evento existente.
     */
    @FXML
    void abrirActualizarEvento(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/ActualizarEvento.fxml");
    }

    /**
     * Abre la vista para eliminar un evento.
     */
    @FXML
    void abrirEliminarEvento(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/EliminarEvento.fxml");
    }

    /**
     * Abre la vista para consultar la información de un evento.
     */
    @FXML
    void abrirConsultarEvento(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/ConsultarEvento.fxml");
    }

    /**
     * Regresa al menú principal del administrador.
     */
    @FXML
    void volver(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/proyectofinalctrlexito/AdministradorMenu.fxml");
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
}

