package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CompraViewController {

    @FXML private ComboBox<Evento>    cbEventos;
    @FXML private ComboBox<Zona>      cbZonas;
    @FXML private ComboBox<TipoPago>  cbTipoPago;
    @FXML private GridPane            gridAsientos;
    @FXML private Label               lblAsientoSeleccionado;

    private Asiento asientoSeleccionado = null;
    private final Map<String, Integer> mapaFilas = new HashMap<>();

    // -------------------------------------------------------
    // Inicialización
    // -------------------------------------------------------
    @FXML
    public void initialize() {
        Plataforma plataforma = Plataforma.getInstancia();

        cbEventos.setItems(FXCollections.observableArrayList(plataforma.getListaEventos()));
        cbEventos.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Evento item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? null : item.getNombre() + " [" + item.getEstado() + "]");
            }
        });
        cbEventos.setConverter(new StringConverter<>() {
            @Override public String toString(Evento item) {
                return item == null ? "" : item.getNombre() + " [" + item.getEstado() + "]";
            }
            @Override public Evento fromString(String s) { return null; }
        });

        cbZonas.setConverter(new StringConverter<>() {
            @Override public String toString(Zona z) {
                return z == null ? "" : z.getNombre() + " — $" + z.getPreciobase();
            }
            @Override public Zona fromString(String s) { return null; }
        });

        cbTipoPago.setItems(FXCollections.observableArrayList(TipoPago.values()));
    }

    // -------------------------------------------------------
    // Al seleccionar evento → cargar zonas
    // -------------------------------------------------------
    @FXML
    void onEventoSeleccionado(ActionEvent event) {
        Evento ev = cbEventos.getValue();
        cbZonas.getItems().clear();
        gridAsientos.getChildren().clear();
        asientoSeleccionado = null;
        lblAsientoSeleccionado.setText("Asiento seleccionado: ninguno");

        if (ev != null && ev.getRecinto() != null) {
            cbZonas.setItems(FXCollections.observableArrayList(ev.getRecinto().getListaZonas()));
        }
    }

    // -------------------------------------------------------
    // Al seleccionar zona → cargar mapa
    // -------------------------------------------------------
    @FXML
    void onZonaSeleccionada(ActionEvent event) {
        Zona zona = cbZonas.getValue();
        asientoSeleccionado = null;
        lblAsientoSeleccionado.setText("Asiento seleccionado: ninguno");

        if (zona != null) {
            cargarMapaAsientos(zona.getListaAsientos());
        }
    }

    // -------------------------------------------------------
    // Cargar mapa de asientos dinámicamente
    // -------------------------------------------------------
    public void cargarMapaAsientos(List<Asiento> asientos) {
        gridAsientos.getChildren().clear();
        mapaFilas.clear();

        if (asientos == null || asientos.isEmpty()) return;

        int filaIndex = 0;
        for (Asiento a : asientos) {
            String fila = a.getFila().toUpperCase();
            if (!mapaFilas.containsKey(fila)) {
                mapaFilas.put(fila, filaIndex);
                filaIndex++;
            }
        }

        for (Asiento a : asientos) {
            Button btn = crearBotonAsiento(a);
            int row = mapaFilas.get(a.getFila().toUpperCase());
            int col = Integer.parseInt(a.getNumero()) - 1;
            gridAsientos.add(btn, col, row);
        }
    }

    // -------------------------------------------------------
    // Crear botón por asiento
    // -------------------------------------------------------
    private Button crearBotonAsiento(Asiento asiento) {
        Button btn = new Button(asiento.getFila() + asiento.getNumero());
        btn.setPrefWidth(50.0);
        btn.setPrefHeight(40.0);

        actualizarColorBoton(btn, asiento);

        boolean disponible = asiento.getEstado() == EstadoAsiento.DISPONIBLE;
        btn.setDisable(!disponible);

        if (disponible) {
            btn.setOnAction(e -> {
                asientoSeleccionado = asiento;
                lblAsientoSeleccionado.setText(
                        "Asiento seleccionado: " + asiento.getFila() + asiento.getNumero()
                );
            });
        }

        btn.setTooltip(new Tooltip(
                "Asiento: " + asiento.getFila() + "-" + asiento.getNumero() +
                        "\nEstado: " + asiento.getEstado()
        ));

        return btn;
    }

    // -------------------------------------------------------
    // Actualizar color del botón según estado
    // -------------------------------------------------------
    public void actualizarColorBoton(Button btn, Asiento asiento) {
        String color;
        if (asiento.getEstado() == EstadoAsiento.DISPONIBLE) {
            color = "#38A169";
        } else if (asiento.getEstado() == EstadoAsiento.VENDIDO) {
            color = "#E53E3E";
        } else {
            color = "#D69E2E";
        }
        btn.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 11px;" +
                        "-fx-background-radius: 6;"
        );
    }

    // -------------------------------------------------------
    // Procesar compra
    // -------------------------------------------------------
    @FXML
    void procesarCompra(ActionEvent event) {
        Evento eventoSeleccionado = cbEventos.getValue();
        if (eventoSeleccionado == null) {
            mostrarAlerta("Error", "Selección Requerida", "Por favor, selecciona un evento.");
            return;
        }

        if (eventoSeleccionado.getEstado() != EstadoEvento.PUBLICADO) {
            mostrarAlerta("Venta No Disponible", "Evento Inhabilitado",
                    "Solo se pueden adquirir boletas para eventos en estado PUBLICADO.\n" +
                            "Estado actual: " + eventoSeleccionado.getEstado());
            return;
        }

        if (cbZonas.getValue() == null) {
            mostrarAlerta("Error", "Selección Requerida", "Por favor, selecciona una zona.");
            return;
        }

        if (asientoSeleccionado == null) {
            mostrarAlerta("Error", "Selección Requerida", "Por favor, selecciona un asiento del mapa.");
            return;
        }

        TipoPago pagoSeleccionado = cbTipoPago.getValue();
        if (pagoSeleccionado == null) {
            mostrarAlerta("Error", "Selección Requerida", "Por favor, selecciona un método de pago.");
            return;
        }

        double precio = cbZonas.getValue().getPreciobase();

        Plataforma plataforma = Plataforma.getInstancia();
        Usuario usuarioLogueado = plataforma.getUsuarioSesionActiva();

        if (usuarioLogueado == null) {
            mostrarAlerta("Sesión Requerida", "Acceso Denegado", "Debes iniciar sesión para realizar una compra.");
            return;
        }

        // Marcar asiento como vendido
        asientoSeleccionado.venderAsiento();

        // Actualizar color en el grid
        for (javafx.scene.Node node : gridAsientos.getChildren()) {
            if (node instanceof Button) {
                Button b = (Button) node;
                if (b.getText().equals(asientoSeleccionado.getFila() + asientoSeleccionado.getNumero())) {
                    actualizarColorBoton(b, asientoSeleccionado);
                    b.setDisable(true);
                    b.setOnAction(null);
                    break;
                }
            }
        }

        int idCompra = new Random().nextInt(90000) + 10000;

        Compra nuevaCompra = new Compra.Builder()
                .setIdCompra(idCompra)
                .setEvento(eventoSeleccionado)
                .setUsuario(usuarioLogueado)
                .setTipoPago(pagoSeleccionado)
                .setTotal(precio)
                .build();

        usuarioLogueado.agregarCompra(nuevaCompra);

        mostrarAlerta("Éxito", "Boleto Adquirido",
                "¡Compra exitosa para " + usuarioLogueado.getNombreCompleto() + "!\n\n" +
                        " ID Compra: #" + idCompra + "\n" +
                        " Evento: " + eventoSeleccionado.getNombre() + "\n" +
                        " Asiento: " + asientoSeleccionado.getFila() + asientoSeleccionado.getNumero() + "\n" +
                        " Total Pagado: $" + precio + "\n" +
                        " Medio de Pago: " + pagoSeleccionado);

        asientoSeleccionado = null;
        lblAsientoSeleccionado.setText("Asiento seleccionado: ninguno");
        cbTipoPago.getSelectionModel().clearSelection();
    }

    // -------------------------------------------------------
    // Volver
    // -------------------------------------------------------
    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(
                "/co/edu/uniquindio/poo/proyectofinalctrlexito/Plataforma.fxml"
        ))));
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}