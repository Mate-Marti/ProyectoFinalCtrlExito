package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Evento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PlataformaViewController {

    @FXML
    private StackPane contentArea;
    @FXML
    private VBox menuLateral;
    @FXML
    private HBox hboxEventosDisponibles;
    @FXML
    private HBox hboxEventosInscritos;

    private static PlataformaViewController instancia;

    public PlataformaViewController() {
        instancia = this;
    }

    public static PlataformaViewController getInstancia() {
        return instancia;
    }

    @FXML
    public void initialize() {
        Plataforma plataforma = Plataforma.getInstancia();
        if (plataforma != null && plataforma.getListaEventos() != null) {
            cargarEventosActivos(plataforma.getListaEventos());
        }
    }

    @FXML
    void toggleMenu(ActionEvent event) {
        if (menuLateral != null) {
            boolean estadoActual = menuLateral.isVisible();
            menuLateral.setVisible(!estadoActual);
            menuLateral.setManaged(!estadoActual);
        }
    }

    @FXML
    void mostrarInicio(ActionEvent event) {
        contentArea.getChildren().removeIf(node -> node != contentArea.getChildren().get(0));
    }

    @FXML
    void abrirAdministrador(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Administrador.fxml");
    }

    @FXML
    void abrirUsuario(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Usuario.fxml");
    }

    @FXML
    void abrirGestionPerfil(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionPerfil.fxml");
    }

    @FXML
    void abrirCompra(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Compra.fxml");
    }

    @FXML
    void abrirEvento(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Evento.fxml");
    }

    @FXML
    void abrirGestionCompraUsuario(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionCompraUsuario.fxml");
    }

    @FXML
    void abrirIncidencia(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Incidencia.fxml");
    }

    @FXML
    void abrirRegistro(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/RegistroUsuario.fxml");
    }

    // ✅ NUEVO: abre la vista de notificaciones en ventana modal
    @FXML
    void abrirNotificaciones(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/Notificacion.fxml")
            );
            Parent root = loader.load();

            NotificacionViewController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Notificaciones");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            controller.setStage(stage);
            stage.show();

        } catch (IOException e) {
            System.err.println("Error cargando vista: Notificacion.fxml");
            e.printStackTrace();
        }
    }

    public void cargarVistaExterna(String rutaFxml) {
        try {
            if (contentArea != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
                Parent vistaSecundaria = loader.load();

                if (contentArea.getChildren().size() > 1) {
                    contentArea.getChildren().remove(1);
                }
                contentArea.getChildren().add(vistaSecundaria);
            }
        } catch (IOException e) {
            System.err.println("Error cargando vista interna: " + rutaFxml);
            e.printStackTrace();
        }
    }

    public void cargarEventosActivos(List<Evento> eventos) {
        hboxEventosDisponibles.getChildren().clear();

        if (eventos == null || eventos.isEmpty()) {
            Label sinEventos = new Label("No hay eventos disponibles en este momento.");
            sinEventos.setStyle("-fx-text-fill: #718096; -fx-font-size: 13px;");
            hboxEventosDisponibles.getChildren().add(sinEventos);
            return;
        }

        for (Evento ev : eventos) {
            VBox tarjeta = new VBox(8);
            tarjeta.setPrefSize(190, 190);
            tarjeta.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #BEE3F8;" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-radius: 10;" +
                            "-fx-padding: 12;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6, 0, 0, 2);"
            );

            Label titulo = new Label(ev.getNombre());
            titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: #2B6CB0;");
            titulo.setWrapText(true);

            Label tipo = new Label("🎭 " + ev.getCategoria());
            tipo.setStyle("-fx-font-size: 10px; -fx-text-fill: #4A5568;");

            Label estado = new Label("● " + ev.getEstado().toString());
            estado.setStyle("-fx-font-size: 10px; -fx-text-fill: #276749; -fx-font-weight: bold;");

            Label desc = new Label(ev.getDescripcion());
            desc.setWrapText(true);
            desc.setStyle("-fx-font-size: 10px; -fx-text-fill: #718096;");

            Button btnVer = new Button("Ver detalles");
            btnVer.setStyle(
                    "-fx-background-color: #2B6CB0; -fx-text-fill: white;" +
                            "-fx-background-radius: 6; -fx-cursor: hand; -fx-font-size: 10px;"
            );
            btnVer.setOnAction(e -> abrirEvento(null));

            tarjeta.getChildren().addAll(titulo, tipo, estado, desc, btnVer);
            hboxEventosDisponibles.getChildren().add(tarjeta);
        }
    }

    private void cambiarVistaCompleta(String ruta, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ruta));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}