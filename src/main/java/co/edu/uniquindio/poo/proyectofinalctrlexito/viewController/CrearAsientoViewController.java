package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Asiento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Zona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearAsientoViewController implements Initializable {

    @FXML
    private TextField txtFila;

    @FXML
    private TextField txtNumero;

    @FXML
    private ComboBox<Zona> cmbZona;

    @FXML
    private TableView<Asiento> tablaAsientos;

    private TableColumn<Asiento, Integer> colId;
    private TableColumn<Asiento, String>  colFila;
    private TableColumn<Asiento, String>  colNumero;
    private TableColumn<Asiento, String>  colEstado;

    private ObservableList<Asiento> listaObservable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        configurarValidaciones();
        cargarZonas();
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
        colEstado.setPrefWidth(120);

        tablaAsientos.getColumns().addAll(colId, colFila, colNumero, colEstado);
    }

    private void configurarValidaciones() {
        txtFila.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("[a-zA-Z]*")) {
                txtFila.setText(oldVal);
            }
        });

        txtNumero.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty() && !newVal.matches("[1-9][0-9]*")) {
                txtNumero.setText(oldVal);
            }
        });
    }

    private void cargarZonas() {
        // Recorre todos los recintos y agrega sus zonas al ComboBox
        for (Recinto recinto : Plataforma.getInstancia().getListaRecintos()) {
            cmbZona.getItems().addAll(recinto.getListaZonas());
        }
    }

    private void cargarTabla() {
        listaObservable = FXCollections.observableArrayList(
                Plataforma.getInstancia().getListaAsientos()
        );
        tablaAsientos.setItems(listaObservable);
    }

    @FXML
    void guardarAsiento(ActionEvent event) {
        String fila   = txtFila.getText().trim();
        String numero = txtNumero.getText().trim();
        Zona zona     = cmbZona.getValue();

        if (fila.isEmpty() || numero.isEmpty() || zona == null) {
            mostrarAlerta("Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        int nuevoId = Plataforma.getInstancia().getListaAsientos().size() + 1;

        Asiento nuevo = Plataforma.getInstancia().crearAsiento(nuevoId, fila, numero, zona);
        listaObservable.add(nuevo);

        txtFila.clear();
        txtNumero.clear();
        cmbZona.setValue(null);

        mostrarAlerta("Éxito", "Asiento " + fila + "-" + numero + " registrado correctamente.");
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