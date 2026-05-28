package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MetricaViewController {

    @FXML private PieChart pieChartVentas;
    @FXML private PieChart pieChartIncidencias;
    @FXML private Button btnActualizarMetricas;
    @FXML private Button btnDescargarPDF;
    @FXML private Button btnVolver;

    @FXML
    public void initialize() {
        actualizarDatos(null);
    }

    @FXML
    void actualizarDatos(ActionEvent event) {
        Plataforma plataforma = Plataforma.getInstancia();

        Map<String, Double> ventasPorEvento = new HashMap<>();
        for (Persona p : plataforma.getListaPersonas()) {
            if (p instanceof Usuario) {
                Usuario usuario = (Usuario) p;
                for (Compra compra : usuario.getCompras()) {
                    if (compra.getEvento() != null) {
                        String nombreEvento = compra.getEvento().getNombre();
                        double totalCompra = compra.getTotal();
                        ventasPorEvento.put(nombreEvento, ventasPorEvento.getOrDefault(nombreEvento, 0.0) + totalCompra);
                    }
                }
            }
        }

        ObservableList<PieChart.Data> datosVentas = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : ventasPorEvento.entrySet()) {
            datosVentas.add(new PieChart.Data(entry.getKey() + " ($" + entry.getValue() + ")", entry.getValue()));
        }
        pieChartVentas.setData(datosVentas);

        // 2. Lógica para Incidencias por Estado
        Map<EstadoIncidencia, Integer> incidenciasPorEstado = new HashMap<>();
        if (plataforma.getListaIncidencias() != null) {
            for (Incidencia incidencia : plataforma.getListaIncidencias()) {
                EstadoIncidencia estado = incidencia.getEstado();
                incidenciasPorEstado.put(estado, incidenciasPorEstado.getOrDefault(estado, 0) + 1);
            }
        }

        ObservableList<PieChart.Data> datosIncidencias = FXCollections.observableArrayList();
        for (Map.Entry<EstadoIncidencia, Integer> entry : incidenciasPorEstado.entrySet()) {
            datosIncidencias.add(new PieChart.Data(entry.getKey().toString() + " (" + entry.getValue() + ")", entry.getValue()));
        }
        pieChartIncidencias.setData(datosIncidencias);
    }

    @FXML
    void descargarPDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Texto/Reporte", "*.txt"));

        String userHome = System.getProperty("user.home");
        File downloadsDir = new File(userHome, "Downloads");
        if (downloadsDir.exists()) {
            fileChooser.setInitialDirectory(downloadsDir);
        }
        fileChooser.setInitialFileName("Reporte_Metricas_CtrlExito.txt");

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            ReportePDF reporte = new ReportePDF(file.getAbsolutePath());
            reporte.generarReporteDesdePlataforma(Plataforma.getInstancia());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Reporte Generado");
            alert.setContentText("El reporte se ha guardado correctamente en:\n" + file.getAbsolutePath());
            alert.showAndWait();
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinalctrlexito/AdministradorMenu.fxml"))));
    }
}