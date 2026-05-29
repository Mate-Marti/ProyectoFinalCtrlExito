package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Asiento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.EstadoAsiento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ConsultarDisponibilidadViewController implements Initializable {

    @FXML
    private GridPane gridAsientos;

    private final Map<String, Integer> mapaFilas = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarMapaAsientos();
    }

    public void cargarMapaAsientos() {
        gridAsientos.getChildren().clear();
        mapaFilas.clear();

        List<Asiento> asientos = Plataforma.getInstancia().getListaAsientos();

        if (asientos == null || asientos.isEmpty()) {
            return;
        }

        int filaIndex = 0;
        for (Asiento asiento : asientos) {
            String fila = asiento.getFila().toUpperCase();
            if (!mapaFilas.containsKey(fila)) {
                mapaFilas.put(fila, filaIndex);
                filaIndex++;
            }
        }

        for (Asiento asiento : asientos) {
            Button btnAsiento = crearBotonAsiento(asiento);
            int row = mapaFilas.get(asiento.getFila().toUpperCase());
            int col = Integer.parseInt(asiento.getNumero()) - 1;
            gridAsientos.add(btnAsiento, col, row);
        }
    }

    private Button crearBotonAsiento(Asiento asiento) {
        Button btn = new Button(asiento.getFila() + asiento.getNumero());
        btn.setPrefWidth(50.0);
        btn.setPrefHeight(40.0);
        btn.setMouseTransparent(true);
        btn.setStyle(
                "-fx-background-color: " + obtenerColor(asiento.getEstado()) + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 11px;" +
                        "-fx-background-radius: 6;"
        );
        Tooltip tooltip = new Tooltip(
                "Asiento: " + asiento.getFila() + "-" + asiento.getNumero() +
                        "\nEstado: " + asiento.getEstado()
        );
        btn.setTooltip(tooltip);
        return btn;
    }

    private String obtenerColor(EstadoAsiento estado) {
        if (estado == EstadoAsiento.DISPONIBLE) return "#38A169";
        if (estado == EstadoAsiento.VENDIDO)    return "#E53E3E";
        if (estado == EstadoAsiento.BLOQUEADO)  return "#D69E2E";
        if (estado == EstadoAsiento.RESERVADO)  return "#3182CE";
        return "#A0AEC0";
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionAsiento.fxml"
        ));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}