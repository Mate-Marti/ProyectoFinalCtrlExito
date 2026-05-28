package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionRecintoViewController {
    /**
     * Abre la vista para crear un nuevo recinto.
     */
    @FXML
    void abrirCrearRecinto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/CrearRecinto.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = obtenerStage(event);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // Navegación: Actualizar Recinto
    // -------------------------------------------------------

    /**
     * Abre la vista para actualizar un recinto existente.
     */
    @FXML
    void abrirActualizarRecinto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/ActualizarRecinto.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = obtenerStage(event);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // Navegación: Eliminar Recinto
    // -------------------------------------------------------

    /**
     * Abre la vista para eliminar un recinto.
     */
    @FXML
    void abrirEliminarRecinto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/EliminarRecinto.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = obtenerStage(event);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // Navegación: Consultar Recinto
    // -------------------------------------------------------

    /**
     * Abre la vista para consultar la información de un recinto.
     */
    @FXML
    void abrirConsultarRecinto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/ConsultarRecinto.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = obtenerStage(event);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // Navegación: Volver al menú del administrador
    // -------------------------------------------------------

    /**
     * Regresa a la vista del menú principal del administrador.
     */
    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/AdministradorMenu.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = obtenerStage(event);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // Utilidad: obtener el Stage actual desde el evento
    // -------------------------------------------------------

    /**
     * Extrae el Stage activo a partir del ActionEvent.
     *
     * @param event evento de acción disparado por un botón.
     * @return Stage actual de la aplicación.
     */
    private Stage obtenerStage(ActionEvent event) {
        return (Stage) ((javafx.scene.Node) event.getSource())
                .getScene()
                .getWindow();
    }
}

