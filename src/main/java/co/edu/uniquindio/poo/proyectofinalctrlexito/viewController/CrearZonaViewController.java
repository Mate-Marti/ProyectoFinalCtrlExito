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

import java.util.Random;

public class CrearZonaViewController {

    @FXML private TextField txtIdRecinto;
    // txtIdZona ELIMINADO — el ID se genera automáticamente
    @FXML private TextField txtNombreZona;
    @FXML private TextField txtCapacidad;
    @FXML private TextField txtPrecioBase;
    @FXML private Button btnAgregarZona;
    @FXML private TableView<Zona> tablaZonas;
    @FXML private Button btnVolver;

    private ObservableList<Zona> zonaObservableList = FXCollections.observableArrayList();

    private Recinto recintoActual;

    // ─────────────────────────────────────────────────────────────
    //  Genera un ID de zona aleatorio (100-999) que no esté en uso
    // ─────────────────────────────────────────────────────────────
    private int generarIdZona() {
        Random random = new Random();
        int id;
        do {
            id = 100 + random.nextInt(900); // rango 100 – 999
        } while (recintoActual != null && recintoActual.consultarZona(id) != null);
        return id;
    }

    // ─────────────────────────────────────────────────────────────
    //  Inicialización de la tabla (sin cambios respecto al original)
    // ─────────────────────────────────────────────────────────────
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

    // ─────────────────────────────────────────────────────────────
    //  Acción del botón "Guardar Zona"
    // ─────────────────────────────────────────────────────────────
    @FXML
    void onAgregarZona(ActionEvent event) {
        try {
            // Validación de campos (ya NO incluye txtIdZona)
            if (txtIdRecinto.getText().isEmpty() ||
                    txtNombreZona.getText().isEmpty() ||
                    txtCapacidad.getText().isEmpty()  ||
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
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            double precio = Double.parseDouble(txtPrecioBase.getText().trim());

            if (idRecinto < 0 || capacidad < 0 || precio < 0) {
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

            if (capacidad > recintoActual.getCapacidadMaxima()) {
                mostrarAlerta("Capacidad Inválida",
                        "La capacidad de la zona (" + capacidad + ") no puede superar " +
                                "la capacidad máxima del recinto (" + recintoActual.getCapacidadMaxima() + ").");
                return;
            }

            // ID de zona generado automáticamente (ya no se toma del campo de texto)
            int idZona = generarIdZona();

            recintoActual.crearZona(idZona, nombre, capacidad, precio);
            zonaObservableList.add(recintoActual.consultarZona(idZona));
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Asegúrese de ingresar solo números enteros en ID Recinto/Capacidad, y números decimales en Precio.");
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  Limpia los campos del formulario (sin txtIdZona)
    // ─────────────────────────────────────────────────────────────
    private void limpiarCampos() {
        txtIdRecinto.clear();
        txtNombreZona.clear();
        txtCapacidad.clear();
        txtPrecioBase.clear();
        recintoActual = null;
    }

    // ─────────────────────────────────────────────────────────────
    //  Helpers (sin cambios respecto al original)
    // ─────────────────────────────────────────────────────────────
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