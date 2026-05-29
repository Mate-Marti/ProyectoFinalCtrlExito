package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
public class CrearEventoViewController implements Initializable {

    @FXML private TextField         txtIdEvento;
    @FXML private TextField         txtNombreEvento;
    @FXML private TextField         txtDescripcionEvento;
    @FXML private ComboBox<String>  comboTipoEvento;
    @FXML private TextField         txtIdRecinto;
    @FXML private Label             lblRecintoInfo;

    @FXML private TableView<Evento>           tablaEventos;
    @FXML private TableColumn<Evento, String> colId;
    @FXML private TableColumn<Evento, String> colNombre;
    @FXML private TableColumn<Evento, String> colTipo;
    @FXML private TableColumn<Evento, String> colEstado;
    @FXML private TableColumn<Evento, String> colDesc;

    private final Plataforma plataforma = Plataforma.getInstancia();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboTipoEvento.setItems(FXCollections.observableArrayList(
                "Concierto", "Teatro", "Conferencia"
        ));

        colId     .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdEvento()));
        colNombre .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colTipo   .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCategoria()));
        colEstado .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado().toString()));
        colDesc   .setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));

        refrescarTabla();
    }

    @FXML
    void buscarRecinto() {
        String idTexto = txtIdRecinto.getText().trim();
        if (idTexto.isEmpty()) {
            lblRecintoInfo.setText("");
            return;
        }
        try {
            int idRecinto = Integer.parseInt(idTexto);
            Recinto recinto = plataforma.buscarRecintoPorId(idRecinto);
            if (recinto != null) {
                lblRecintoInfo.setStyle("-fx-font-style: italic; -fx-text-fill: #276749;");
                lblRecintoInfo.setText("✅ " + recinto.getNombre() + " — " + recinto.getCiudad());
            } else {
                lblRecintoInfo.setStyle("-fx-font-style: italic; -fx-text-fill: #E53E3E;");
                lblRecintoInfo.setText("❌ No se encontró recinto con ese ID");
            }
        } catch (NumberFormatException e) {
            lblRecintoInfo.setStyle("-fx-font-style: italic; -fx-text-fill: #E53E3E;");
            lblRecintoInfo.setText("❌ El ID debe ser un número entero");
        }
    }

    @FXML
    void crearEvento(ActionEvent event) {

        if (txtIdEvento.getText().trim().isEmpty()
                || txtNombreEvento.getText().trim().isEmpty()
                || txtDescripcionEvento.getText().trim().isEmpty()
                || comboTipoEvento.getValue() == null) {
            mostrarAlerta("Campos incompletos",
                    "Por favor completa todos los campos antes de crear el evento.");
            return;
        }

        int idEvento;
        try {
            idEvento = Integer.parseInt(txtIdEvento.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta("ID inválido", "El ID debe ser un número entero.");
            return;
        }

        String nombre      = txtNombreEvento.getText().trim();
        String descripcion = txtDescripcionEvento.getText().trim();
        String tipo        = comboTipoEvento.getValue();

        EventoFactory factory;
        switch (tipo) {
            case "Concierto":
                factory = new ConciertoFactory();
                break;
            case "Teatro":
                factory = new TeatroFactory();
                break;
            case "Conferencia":
                factory = new ConferenciaFactory();
                break;
            default:
                mostrarAlerta("Tipo inválido", "Selecciona un tipo de evento válido.");
                return;
        }

        Evento nuevoEvento = factory.crearEvento(idEvento, nombre, descripcion, new Date());
        plataforma.getListaEventos().add(nuevoEvento);

        String idRecintoTexto = txtIdRecinto.getText().trim();
        if (!idRecintoTexto.isEmpty()) {
            try {
                int idRecinto = Integer.parseInt(idRecintoTexto);
                Recinto recinto = plataforma.buscarRecintoPorId(idRecinto);
                if (recinto != null) {
                    nuevoEvento.setRecinto(recinto);
                    mostrarAlerta("Evento creado",
                            "El evento \"" + nombre + "\" (" + tipo + ") fue creado con estado: BORRADOR"
                                    + "\nRecinto asociado: " + recinto.getNombre());
                } else {
                    mostrarAlerta("Evento creado",
                            "El evento fue creado pero no se encontró el recinto con ID: " + idRecintoTexto);
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Evento creado",
                        "El evento fue creado pero el ID del recinto no es válido.");
            }
        } else {
            mostrarAlerta("Evento creado",
                    "El evento \"" + nombre + "\" (" + tipo + ") fue creado con estado: BORRADOR.");
        }

        // Solo notifica si el evento NO está en BORRADOR
        if (nuevoEvento.getEstado() != EstadoEvento.BORRADOR) {
            notificarUsuariosSobreEvento(
                    "📅 Nuevo evento: \"" + nuevoEvento.getNombre()
                            + "\" (" + nuevoEvento.getCategoria() + ") — Estado: "
                            + nuevoEvento.getEstado().toString()
            );
        }

        refrescarTabla();
        limpiarFormulario();
        notificarPlataforma();
    }

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionEvento.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notificarUsuariosSobreEvento(String mensaje) {
        List<Persona> personas = plataforma.getListaPersonas();
        if (personas == null) return;
        for (Persona p : personas) {
            if (p instanceof Usuario) {
                GestorNotificaciones.getInstancia()
                        .agregarNotificacion(((Usuario) p).getId(), mensaje);
            }
        }
    }

    private void notificarPlataforma() {
        PlataformaViewController plataformaVC = PlataformaViewController.getInstancia();
        if (plataformaVC != null) {
            plataformaVC.cargarEventosActivos(plataforma.getListaEventos());
        }
    }

    private void refrescarTabla() {
        tablaEventos.setItems(
                FXCollections.observableArrayList(plataforma.getListaEventos())
        );
    }

    private void limpiarFormulario() {
        txtIdEvento.clear();
        txtNombreEvento.clear();
        txtDescripcionEvento.clear();
        comboTipoEvento.setValue(null);
        txtIdRecinto.clear();
        lblRecintoInfo.setText("");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
