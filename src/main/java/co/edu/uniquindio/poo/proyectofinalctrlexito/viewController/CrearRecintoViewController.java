package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearRecintoViewController implements Initializable {

    // -------------------------------------------------------
    // Campos de la vista
    // -------------------------------------------------------

    @FXML
    private TextField txtNombreRecinto;

    @FXML
    private TextField txtCapacidadMax;

    @FXML
    private TextField txtDireccion;   // NUEVO

    @FXML
    private TextField txtCiudad;      // NUEVO

    @FXML
    private TableView<Recinto> tablaRecintos;

    // -------------------------------------------------------
    // Columnas de la tabla
    // -------------------------------------------------------

    private TableColumn<Recinto, Integer> colId;
    private TableColumn<Recinto, String>  colNombre;
    private TableColumn<Recinto, String>  colDireccion;
    private TableColumn<Recinto, String>  colCiudad;

    // -------------------------------------------------------
    // Datos observables
    // -------------------------------------------------------

    private ObservableList<Recinto> listaObservable;

    // -------------------------------------------------------
    // Inicialización
    // -------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        configurarValidaciones();
        cargarTabla();
    }

    private void configurarColumnas() {

        colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idRecinto"));
        colId.setPrefWidth(60);

        colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(180);

        colDireccion = new TableColumn<>("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colDireccion.setPrefWidth(180);

        colCiudad = new TableColumn<>("Ciudad");
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colCiudad.setPrefWidth(120);

        tablaRecintos.getColumns().addAll(colId, colNombre, colDireccion, colCiudad);
    }

    private void configurarValidaciones() {

        txtNombreRecinto.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
                txtNombreRecinto.setText(oldVal);
            }
        });

        txtCapacidadMax.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty() && !newVal.matches("[1-9][0-9]*")) {
                txtCapacidadMax.setText(oldVal);
            }
        });
    }

    private void cargarTabla() {
        listaObservable = FXCollections.observableArrayList(
                Plataforma.getInstancia().getListaRecintos()
        );
        tablaRecintos.setItems(listaObservable);
    }

    // -------------------------------------------------------
    // Acción: Registrar Recinto
    // -------------------------------------------------------
    @FXML
    void guardarRecinto(ActionEvent event) {

        String nombre    = txtNombreRecinto.getText().trim();
        String capacidad = txtCapacidadMax.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String ciudad    = txtCiudad.getText().trim();

        if (nombre.isEmpty() || capacidad.isEmpty() || direccion.isEmpty() || ciudad.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        int nuevoId = Plataforma.getInstancia().getListaRecintos().size() + 1;
        int capacidadMaxima = Integer.parseInt(capacidad); // ✅ convertir

        Recinto nuevoRecinto = Plataforma.getInstancia().crearRecinto(
                nuevoId, nombre, direccion, ciudad, capacidadMaxima // ✅ pasar capacidad
        );

        listaObservable.add(nuevoRecinto);

        txtNombreRecinto.clear();
        txtCapacidadMax.clear();
        txtDireccion.clear();
        txtCiudad.clear();

        mostrarAlerta("Éxito", "Recinto \"" + nombre + "\" registrado correctamente.");
    }

    // -------------------------------------------------------
    // Acción: Volver
    // -------------------------------------------------------

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionRecinto.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // Utilidad
    // -------------------------------------------------------

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}