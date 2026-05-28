package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Evento;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class EventoViewController implements Initializable {

    // ✅ NUEVO: referencia al VBox del FXML donde se insertan las tarjetas
    @FXML
    private VBox vboxEventos;

    // ✅ NUEVO: acceso al singleton y formateador de fecha
    private final Plataforma plataforma = Plataforma.getInstancia();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    // ✅ NUEVO: carga las tarjetas al abrir la vista
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTarjetasEventos();
    }

    // ✅ NUEVO: genera una tarjeta por cada evento en la plataforma
    private void cargarTarjetasEventos() {
        vboxEventos.getChildren().clear();

        List<Evento> eventos = plataforma.getListaEventos();

        if (eventos == null || eventos.isEmpty()) {
            Label vacio = new Label("No hay eventos registrados en el sistema.");
            vacio.setStyle("-fx-text-fill: #718096; -fx-font-size: 14px; -fx-padding: 20;");
            vboxEventos.getChildren().add(vacio);
            return;
        }

        for (Evento ev : eventos) {
            vboxEventos.getChildren().add(crearTarjeta(ev));
        }
    }

    // ✅ NUEVO: construye la tarjeta visual de un evento
    private VBox crearTarjeta(Evento ev) {
        VBox tarjeta = new VBox(10);
        tarjeta.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #BEE3F8;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-padding: 18;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.07), 6, 0, 0, 2);"
        );

        // Fila superior: nombre + estado
        HBox filaTitulo = new HBox();
        filaTitulo.setSpacing(10);

        Label nombre = new Label(ev.getNombre());
        nombre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2B6CB0;");

        Region espaciador = new Region();
        HBox.setHgrow(espaciador, Priority.ALWAYS);

        Label estado = new Label("● " + ev.getEstado().toString());
        estado.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: "
                + colorEstado(ev.getEstado().toString()) + ";");

        filaTitulo.getChildren().addAll(nombre, espaciador, estado);

        // Línea separadora
        Region linea = new Region();
        linea.setPrefHeight(1);
        linea.setStyle("-fx-background-color: #E2E8F0;");

        // Campos
        Label id          = crearFila("🆔 ID:",          ev.getIdEvento());
        Label categoria   = crearFila("🎭 Categoría:",   ev.getCategoria());
        Label descripcion = crearFila("📝 Descripción:", ev.getDescripcion());
        Label fecha       = crearFila("📅 Fecha:",       sdf.format(ev.getFecha()));

        // Recinto
        Recinto recinto = ev.getRecinto();
        String infoRecinto = (recinto != null)
                ? recinto.getNombre() + " — " + recinto.getCiudad()
                : "Sin recinto asignado";
        Label lblRecinto = crearFila("🏟️ Recinto:", infoRecinto);

        // Usuarios inscritos
        int totalUsuarios = (ev.getUsuarios() != null) ? ev.getUsuarios().size() : 0;
        Label usuarios = crearFila("👥 Usuarios inscritos:", String.valueOf(totalUsuarios));

        tarjeta.getChildren().addAll(filaTitulo, linea, id, categoria, descripcion, fecha, lblRecinto, usuarios);
        return tarjeta;
    }

    // ✅ NUEVO: crea un label con formato "etiqueta  valor"
    private Label crearFila(String etiqueta, String valor) {
        Label label = new Label(etiqueta + "  " + valor);
        label.setStyle("-fx-font-size: 12px; -fx-text-fill: #4A5568;");
        label.setWrapText(true);
        return label;
    }

    // ✅ NUEVO: color según estado del evento
    private String colorEstado(String estado) {
        switch (estado) {
            case "PUBLICADO":  return "#276749";
            case "BORRADOR":   return "#B7791F";
            case "PAUSADO":    return "#2B6CB0";
            case "CANCELADO":  return "#C53030";
            case "FINALIZADO": return "#553C9A";
            default:           return "#4A5568";
        }
    }

    // INTACTO — exactamente igual al original
    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(
                getClass().getResource(
                        "/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"
                )
        )));
    }
}