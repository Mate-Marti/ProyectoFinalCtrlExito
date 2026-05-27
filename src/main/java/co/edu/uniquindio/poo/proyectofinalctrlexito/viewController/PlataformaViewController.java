package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Evento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PlataformaViewController {

    @FXML
    private StackPane contentArea; // Asegúrate que el ID coincida con el FXML
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
    void toggleMenu(ActionEvent event) {
        if (menuLateral != null) {
            boolean estadoActual = menuLateral.isVisible();
            menuLateral.setVisible(!estadoActual);
            menuLateral.setManaged(!estadoActual);
        }
    }

    // --- MÉTODOS DE NAVEGACIÓN (AHORA TODOS CARGAN DENTRO DEL CENTRO) ---

    @FXML
    void mostrarInicio(ActionEvent event) {
        // Para volver al inicio real (el ScrollPane con los eventos),
        // simplemente limpiamos el contentArea de cualquier vista externa
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
    void abrirUsuarioMenu(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/UsuarioMenu.fxml");
    }

    @FXML
    void abrirCompra(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Compra.fxml");
    }

    @FXML
    void abrirEntrada(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Entrada.fxml");
    }

    @FXML
    void abrirEvento(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Evento.fxml");
    }

    @FXML
    void abrirGestionAsiento(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionAsiento.fxml");
    }

    @FXML
    void abrirGestionCompraUsuario(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionCompraUsuario.fxml");
    }

    @FXML
    void abrirRegistro(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/RegistroUsuario.fxml");
    }

    // --- LÓGICA DE CARGA INTERNA ---

    public void cargarVistaExterna(String rutaFxml) {
        try {
            if (contentArea != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
                Parent vistaSecundaria = loader.load();

                // Importante: No limpiamos todo si queremos mantener el primer nodo (el inicio)
                // Pero si queremos que la nueva vista cubra todo el centro, usamos:
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

    /**
     * Genera dinámicamente las tarjetas de eventos en el HBox
     */
    public void cargarEventosActivos(List<Evento> eventos) {
        hboxEventosDisponibles.getChildren().clear();
        for (Evento ev : eventos) {
            VBox tarjeta = new VBox();
            tarjeta.setPrefSize(180, 180);
            tarjeta.setStyle("-fx-background-color: white; -fx-border-color: #CBD5E0; -fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 10;");

            Label titulo = new Label(ev.getNombre());
            titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #2D3748;");

            Label desc = new Label(ev.getDescripcion());
            desc.setWrapText(true);
            desc.setStyle("-fx-font-size: 10px; -fx-text-fill: #718096;");

            tarjeta.getChildren().addAll(titulo, desc);
            hboxEventosDisponibles.getChildren().add(tarjeta);
        }
    }

    // Mantenemos este por si alguna acción realmente requiere cambiar TODA la ventana (como un Logout)
    private void cambiarVistaCompleta(String ruta, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ruta));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}