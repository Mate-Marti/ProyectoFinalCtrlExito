package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PlataformaViewController {

    private static PlataformaViewController instancia;

    @FXML
    private StackPane contenedorPrincipal;
    @FXML
    private VBox menuLateral;
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
    @FXML
    void mostrarInicio(ActionEvent event) {
        cargarVistaExterna("/co/edu/uniquindio/poo/proyectofinalctrlexito/Evento.fxml");
    }
    @FXML
    void abrirAdministrador(ActionEvent event) throws IOException {
        cambiarVistaCompleta("/co/edu/uniquindio/poo/proyectofinalctrlexito/Administrador.fxml", event);
    }
    @FXML
    void abrirUsuario(ActionEvent event) throws IOException {
        cambiarVistaCompleta("/co/edu/uniquindio/poo/proyectofinalctrlexito/Usuario.fxml", event);
    }
    @FXML
    void abrirUsuarioMenu(ActionEvent event) throws IOException {
        cambiarVistaCompleta("/co/edu/uniquindio/poo/proyectofinalctrlexito/UsuarioMenu.fxml", event);
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
    private void cambiarVistaCompleta(String ruta, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ruta));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    public void cargarVistaExterna(String rutaFxml) {
        try {
            if (contenedorPrincipal != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
                Parent vistaSecundaria = loader.load();

                contenedorPrincipal.getChildren().clear();
                contenedorPrincipal.getChildren().add(vistaSecundaria);
            } else {
                System.err.println("Error: No se encontró un contenedor con fx:id='contenedorPrincipal' asignado en tu FXML.");
            }
        } catch (IOException e) {
            System.err.println("Error de carga crítica en el archivo FXML: " + rutaFxml);
            e.printStackTrace();
        }
    }
}