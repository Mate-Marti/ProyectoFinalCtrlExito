package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.GestorNotificaciones;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class NotificacionViewController {

    @FXML private VBox vboxNotificaciones;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        cargarNotificaciones();
    }

    private void cargarNotificaciones() {
        vboxNotificaciones.getChildren().clear();

        if (Session.getUsuarioActual() == null) {
            agregarMensajeVacio("Inicia sesión para ver tus notificaciones.");
            return;
        }

        String idUsuario = Session.getUsuarioActual().getId();
        List<String> mensajes = GestorNotificaciones.getInstancia().getNotificaciones(idUsuario);

        if (mensajes.isEmpty()) {
            agregarMensajeVacio("No tienes notificaciones por el momento.");
            return;
        }

        for (String mensaje : mensajes) {
            Label lbl = new Label(mensaje);
            lbl.setWrapText(true);
            lbl.setMaxWidth(520.0);
            lbl.setStyle(
                    "-fx-background-color: #F0FFF4;" +
                            "-fx-border-color: #9AE6B4;" +
                            "-fx-border-radius: 8;" +
                            "-fx-background-radius: 8;" +
                            "-fx-padding: 12;" +
                            "-fx-font-size: 13px;" +
                            "-fx-text-fill: #276749;"
            );
            vboxNotificaciones.getChildren().add(lbl);
        }
    }

    private void agregarMensajeVacio(String texto) {
        Label lbl = new Label("🔔  " + texto);
        lbl.setStyle("-fx-text-fill: #A0AEC0; -fx-font-size: 13px;");
        vboxNotificaciones.getChildren().add(lbl);
    }

    @FXML
    private void volverAtras() {
        if (stage != null) {
            stage.close();
        }
    }
}
