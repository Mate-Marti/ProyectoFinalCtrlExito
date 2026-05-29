package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Compra;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Persona;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistorialCompraViewController {

    @FXML private TableView<Compra> tablaHistorial;
    @FXML private Button            btnReembolsar;
    @FXML private Label             lblSaldo;

    private ObservableList<Compra> comprasObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        TableColumn<Compra, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idCompra"));
        colId.setPrefWidth(50);

        TableColumn<Compra, String> colUsuario = new TableColumn<>("Usuario");
        colUsuario.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getUsuario().getNombreCompleto()));
        colUsuario.setPrefWidth(160);

        TableColumn<Compra, String> colEvento = new TableColumn<>("Evento");
        colEvento.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEvento().getNombre()));
        colEvento.setPrefWidth(160);

        TableColumn<Compra, Double> colTotal = new TableColumn<>("Total");
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colTotal.setPrefWidth(90);

        TableColumn<Compra, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEstadoCompra().mostrarEstado()));
        colEstado.setPrefWidth(120);

        tablaHistorial.getColumns().addAll(colId, colUsuario, colEvento, colTotal, colEstado);
        tablaHistorial.setItems(comprasObservable);

        cargarTodasLasCompras();

        tablaHistorial.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, seleccionada) -> {
                    btnReembolsar.setDisable(seleccionada == null);
                    if (seleccionada != null) {
                        actualizarSaldo(seleccionada.getUsuario());
                    }
                });
    }

    private void cargarTodasLasCompras() {
        comprasObservable.clear();
        for (Persona persona : Plataforma.getInstancia().getListaPersonas()) {
            if (persona instanceof Usuario) {
                comprasObservable.addAll(((Usuario) persona).getCompras());
            }
        }
    }

    private void actualizarSaldo(Usuario usuario) {
        lblSaldo.setText(String.format("$%.2f", usuario.getSaldo()));
    }

    @FXML
    void onReembolsar(ActionEvent event) {
        Compra seleccionada = tablaHistorial.getSelectionModel().getSelectedItem();
        if (seleccionada == null) return;

        seleccionada.reembolsarCompra();

        Usuario usuario = seleccionada.getUsuario();
        usuario.agregarSaldo(seleccionada.getTotal());

        actualizarSaldo(usuario);
        tablaHistorial.refresh();

        mostrarAlerta("Reembolso exitoso",
                "Se han reembolsado $" + String.format("%.2f", seleccionada.getTotal()) +
                        " a la billetera de " + usuario.getNombreCompleto() + ".");
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(
                "/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"))));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}