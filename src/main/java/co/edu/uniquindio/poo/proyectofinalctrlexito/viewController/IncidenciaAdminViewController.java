package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Incidencia;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.EstadoIncidencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class IncidenciaAdminViewController {

    @FXML private TableView<Incidencia> tablaIncidencias;
    @FXML private TableColumn<Incidencia, String> colAsunto;
    @FXML private TableColumn<Incidencia, String> colDescripcion;
    @FXML private TableColumn<Incidencia, String> colEstado;
    @FXML private Button btnSolucionar;
    @FXML private Button btnPosponer;

    public static Incidencia incidenciaSeleccionada;

    @FXML
    public void initialize() {
        colAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado")); // Vinculación con el atributo 'estado'

        if (Plataforma.getInstancia().getListaIncidencias() != null) {
            tablaIncidencias.getItems().addAll(Plataforma.getInstancia().getListaIncidencias());
        }
    }

    @FXML
    void solucionarIncidencia(ActionEvent event) throws IOException {
        Incidencia seleccionada = tablaIncidencias.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección Requerida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una incidencia de la tabla para solucionarla.");
            alert.showAndWait();
            return;
        }

        incidenciaSeleccionada = seleccionada;

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/SolucionIncidencia.fxml"))));
    }

    @FXML
    void posponerIncidencia(ActionEvent event) {
        Incidencia seleccionada = tablaIncidencias.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección Requerida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una incidencia de la tabla para posponerla.");
            alert.showAndWait();
            return;
        }

        Alert alertPospuesto = new Alert(Alert.AlertType.INFORMATION);
        alertPospuesto.setTitle("Incidencia Pospuesta");
        alertPospuesto.setHeaderText(null);
        alertPospuesto.setContentText("Se pospuso la incidencia correctamente.");
        alertPospuesto.showAndWait();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/AdministradorMenu.fxml"))));
    }
}