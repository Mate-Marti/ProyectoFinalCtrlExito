package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioMenuViewController {


    @FXML void abrirGestionCompraUsuario(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionCompraUsuario.fxml", event); }
    @FXML void abrirHistorialCompra(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/HistorialCompra.fxml", event); }
    @FXML void abrirCompra(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/Compra.fxml", event); }
    @FXML void abrirEvento(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/Evento.fxml", event); }
    @FXML void abrirGestionPerfil(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionPerfil.fxml", event); }

    private void cambiarVista(String ruta, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ruta));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }


    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"))));
    }
}
