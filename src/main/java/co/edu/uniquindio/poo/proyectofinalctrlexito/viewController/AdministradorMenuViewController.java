package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministradorMenuViewController {


    @FXML void abrirGestionEvento(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionEvento.fxml", event); }
    @FXML void abrirGestionZona(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionZona.fxml", event); }
    @FXML void abrirGestionRecinto(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionRecinto.fxml", event); }
    @FXML void abrirGestionAsiento(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionAsiento.fxml", event); }
    @FXML void abrirGestionCompra(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionCompra.fxml", event); }
    @FXML void abrirMetrica(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/Metrica.fxml", event); }
    @FXML void abrirIncidencia(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/Incidencia.fxml", event); }
    @FXML void abrirGestionUsuario(ActionEvent event) throws IOException { cambiarVista("/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionUsuario.fxml", event); }

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
