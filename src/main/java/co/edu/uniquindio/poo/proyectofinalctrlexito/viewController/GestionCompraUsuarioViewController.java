package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Compra;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionCompraUsuarioViewController {

    @FXML
    private TableView<Compra> tblCompras;

    @FXML
    private TableColumn<Compra, Integer> colId;

    @FXML
    private TableColumn<Compra, String> colEvento;

    @FXML
    private TableColumn<Compra, Double> colTotal;

    @FXML
    private TableColumn<Compra, String> colEstado;

    private ObservableList<Compra> listaComprasObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idCompra"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        colEvento.setCellValueFactory(celda -> {
            if (celda.getValue().getEvento() != null) {
                return new SimpleStringProperty(celda.getValue().getEvento().getNombre());
            }
            return new SimpleStringProperty("Sin evento");
        });

        colEstado.setCellValueFactory(celda -> {
            if (celda.getValue().getEstadoCompra() != null) {
                return new SimpleStringProperty(celda.getValue().getEstadoCompra().mostrarEstado());
            }
            return new SimpleStringProperty("Creada");
        });

        Plataforma plataforma = Plataforma.getInstancia();
        Usuario usuarioLogueado = null;

        for (Object p : plataforma.getListaPersonas()) {
            if (p instanceof Usuario) {
                usuarioLogueado = (Usuario) p;
                break; // Tomamos el usuario en sesión
            }
        }

        if (usuarioLogueado != null && usuarioLogueado.getCompras() != null) {
            listaComprasObservable.addAll(usuarioLogueado.getCompras());
            tblCompras.setItems(listaComprasObservable);
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"))));
    }
}