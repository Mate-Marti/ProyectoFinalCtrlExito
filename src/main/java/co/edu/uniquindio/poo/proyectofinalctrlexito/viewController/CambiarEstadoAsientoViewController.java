package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Asiento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.EstadoAsiento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CambiarEstadoAsientoViewController implements Initializable {

    @FXML
    private ComboBox<Asiento> cmbAsiento;

    @FXML
    private ComboBox<EstadoAsiento> cmbEstado;

    @FXML
    private TableView<Asiento> tablaAsientos;

    private TableColumn<Asiento, Integer> colId;
    private TableColumn<Asiento, String> colFila;
    private TableColumn<Asiento, String>  colNumero;
    private TableColumn<Asiento, String>  colEstado;

    private ObservableList<Asiento> listaObservable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        cargarAsientos();
        cargarEstados();
        cargarTabla();
    }

    private void configurarColumnas() {
        colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idAsiento"));
        colId.setPrefWidth(60);

        colFila = new TableColumn<>("Fila");
        colFila.setCellValueFactory(new PropertyValueFactory<>("fila"));
        colFila.setPrefWidth(100);

        colNumero = new TableColumn<>("Número");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNumero.setPrefWidth(100);

        colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colEstado.setPrefWidth(150);

        tablaAsientos.getColumns().addAll(colId, colFila, colNumero, colEstado);
    }

    private void cargarAsientos() {
        cmbAsiento.getItems().addAll(Plataforma.getInstancia().getListaAsientos());

        // Al seleccionar un asiento, muestra su estado actual en el ComboBox
        cmbAsiento.setOnAction(e -> {
            Asiento seleccionado = cmbAsiento.getValue();
            if (seleccionado != null) {
                cmbEstado.setValue(seleccionado.getEstado());
            }
        });
    }

    private void cargarEstados() {
        cmbEstado.getItems().addAll(EstadoAsiento.values());
    }

    private void cargarTabla() {
        listaObservable = FXCollections.observableArrayList(
                Plataforma.getInstancia().getListaAsientos()
        );
        tablaAsientos.setItems(listaObservable);
    }

    @FXML
    void cambiarEstado(ActionEvent event) {
        Asiento asiento = cmbAsiento.getValue();
        EstadoAsiento nuevoEstado = cmbEstado.getValue();

        if (asiento == null || nuevoEstado == null) {
            mostrarAlerta("Campos vacíos", "Por favor selecciona un asiento y un estado.");
            return;
        }

        if (nuevoEstado == EstadoAsiento.DISPONIBLE) {
            asiento.habilitarAsiento();
        } else if (nuevoEstado == EstadoAsiento.BLOQUEADO) {
            asiento.bloquearAsiento();
        } else if (nuevoEstado == EstadoAsiento.RESERVADO) {
            asiento.reservarAsiento();
        } else if (nuevoEstado == EstadoAsiento.VENDIDO) {
            asiento.venderAsiento();
        }

        tablaAsientos.refresh();
        cmbAsiento.setValue(null);
        cmbEstado.setValue(null);

        mostrarAlerta("Éxito", "Estado actualizado correctamente.");
    }

    @FXML
    void volver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionAsiento.fxml"
            ));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}