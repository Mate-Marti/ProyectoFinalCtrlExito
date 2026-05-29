package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Zona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CrearZonaViewController {

    @FXML private TextField txtIdRecinto;
    @FXML private TextField txtIdZona;
    @FXML private TextField txtNombreZona;
    @FXML private TextField txtCapacidad;
    @FXML private TextField txtPrecioBase;
    @FXML private Button btnAgregarZona;
    @FXML private TableView<Zona> tablaZonas;
    @FXML private Button btnVolver;

    private ObservableList<Zona> zonaObservableList = FXCollections.observableArrayList();

    private Recinto recintoActual;

    @FXML
    public void initialize() {
        TableColumn<Zona, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idZona"));

        TableColumn<Zona, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Zona, Integer> colCapacidad = new TableColumn<>("Capacidad");
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));

        TableColumn<Zona, Double> colPrecio = new TableColumn<>("Precio Base");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("preciobase"));

        colId.setPrefWidth(60);
        colNombre.setPrefWidth(150);
        colCapacidad.setPrefWidth(100);
        colPrecio.setPrefWidth(120);

        tablaZonas.getColumns().addAll(colId, colNombre, colCapacidad, colPrecio);
        tablaZonas.setItems(zonaObservableList);

        for (Recinto recinto : Plataforma.getInstancia().getListaRecintos()) {
            zonaObservableList.addAll(recinto.getListaZonas());
        }
    }

    @FXML
    void onAgregarZona(ActionEvent event) {
        try {
            if (txtIdRecinto.getText().isEmpty() || txtIdZona.getText().isEmpty() ||
                    txtNombreZona.getText().isEmpty() || txtCapacidad.getText().isEmpty() ||
                    txtPrecioBase.getText().isEmpty()) {
                mostrarAlerta("Campos Incompletos", "Por favor rellene todos los campos de texto.");
                return;
            }

            String nombre = txtNombreZona.getText().trim();
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                mostrarAlerta("Error de Formato", "El nombre de la zona solo debe contener letras y espacios.");
                return;
            }

            int idRecinto = Integer.parseInt(txtIdRecinto.getText().trim());
            int id        = Integer.parseInt(txtIdZona.getText().trim());
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            double precio = Double.parseDouble(txtPrecioBase.getText().trim());

            if (idRecinto < 0 || id < 0 || capacidad < 0 || precio < 0) {
                mostrarAlerta("Valores Inválidos", "No se permiten números negativos.");
                return;
            }

            if (recintoActual == null) {
                recintoActual = Plataforma.getInstancia().buscarRecintoPorId(idRecinto);
            }

            if (recintoActual == null) {
                mostrarAlerta("Recinto no encontrado", "No existe un recinto con el ID " + idRecinto + ".");
                return;
            }

            if (recintoActual.getIdRecinto() != idRecinto) {
                mostrarAlerta("Recinto incorrecto", "El ID ingresado no coincide con el recinto activo.");
                return;
            }

            // ✅ NUEVO: validar que la capacidad no supere la del recinto
            if (capacidad > recintoActual.getCapacidadMaxima()) {
                mostrarAlerta("Capacidad Inválida",
                        "La capacidad de la zona (" + capacidad + ") no puede superar " +
                                "la capacidad máxima del recinto (" + recintoActual.getCapacidadMaxima() + ").");
                return;
            }

            recintoActual.crearZona(id, nombre, capacidad, precio);
            zonaObservableList.add(recintoActual.consultarZona(id));
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Asegúrese de ingresar solo números enteros en ID/Capacidad, y números decimales en Precio.");
        }
    }

    private void limpiarCampos() {
        txtIdRecinto.clear();
        txtIdZona.clear();
        txtNombreZona.clear();
        txtCapacidad.clear();
        txtPrecioBase.clear();
        recintoActual = null;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void setRecintoActual(Recinto recinto) {
        this.recintoActual = recinto;
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(
                "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionZona.fxml"))));
    }
}