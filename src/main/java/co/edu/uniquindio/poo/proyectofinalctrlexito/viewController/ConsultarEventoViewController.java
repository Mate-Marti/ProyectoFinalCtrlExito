package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.List;

public class ConsultarEventoViewController {

    // ── Búsqueda ──────────────────────────────────────────────
    @FXML private TextField txtIdBuscar;

    // ── Card evento ───────────────────────────────────────────
    @FXML private VBox  cardEvento;
    @FXML private Label lblId;
    @FXML private Label lblNombre;
    @FXML private Label lblCategoria;
    @FXML private Label lblDescripcion;
    @FXML private Label lblFecha;
    @FXML private Label lblEstado;

    // ── Card recinto ──────────────────────────────────────────
    @FXML private VBox  cardRecinto;
    @FXML private Label lblRecintoId;
    @FXML private Label lblRecintoNombre;
    @FXML private Label lblRecintoDireccion;
    @FXML private Label lblRecintoCiudad;
    @FXML private Label lblRecintoDisponibilidad;

    // ── Card zonas ────────────────────────────────────────────
    @FXML private VBox cardZonas;
    @FXML private VBox contenedorZonas;

    // ── Error ─────────────────────────────────────────────────
    @FXML private Label lblError;

    // ── Singleton ─────────────────────────────────────────────
    private final Plataforma plataforma = Plataforma.getInstancia();

    // ─────────────────────────────────────────────────────────
    // Acción: Buscar evento
    // ─────────────────────────────────────────────────────────
    @FXML
    private void buscarEvento() {
        String id = txtIdBuscar.getText().trim();

        ocultarTodo();

        if (id.isEmpty()) {
            mostrarError("Por favor ingresa un ID de evento.");
            return;
        }

        Evento evento = buscarEventoPorId(id);

        if (evento == null) {
            mostrarError("No se encontró ningún evento con el ID: " + id);
            return;
        }

        mostrarDatosEvento(evento);

        if (evento.getRecinto() != null) {
            mostrarDatosRecinto(evento.getRecinto());
            mostrarDisponibilidadZonas(evento.getRecinto().getListaZonas());
        } else {
            mostrarError("ℹ️ Este evento no tiene un recinto asignado aún.");
        }
    }

    // ─────────────────────────────────────────────────────────
    // Mostrar datos del evento
    // ─────────────────────────────────────────────────────────
    private void mostrarDatosEvento(Evento evento) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        lblId.setText("ID: " + evento.getIdEvento());
        lblNombre.setText("Nombre: " + evento.getNombre());
        lblCategoria.setText("Categoría: " + evento.getCategoria());
        lblDescripcion.setText("Descripción: " + evento.getDescripcion());
        lblFecha.setText("Fecha: " + (evento.getFecha() != null
                ? sdf.format(evento.getFecha()) : "No definida"));
        lblEstado.setText("Estado: " + evento.getEstado());

        colorearEstado(evento.getEstado());
        mostrar(cardEvento);
    }

    // ─────────────────────────────────────────────────────────
    // Mostrar datos del recinto
    // ─────────────────────────────────────────────────────────
    private void mostrarDatosRecinto(Recinto recinto) {
        lblRecintoId.setText("ID: " + recinto.getIdRecinto());
        lblRecintoNombre.setText("Nombre: " + recinto.getNombre());
        lblRecintoDireccion.setText("Dirección: " + recinto.getDireccion());
        lblRecintoCiudad.setText("Ciudad: " + recinto.getCiudad());
        lblRecintoDisponibilidad.setText(
                "Disponibilidad total: " + recinto.obtenerDisponibilidad() + " asientos disponibles"
        );
        mostrar(cardRecinto);
    }

    // ─────────────────────────────────────────────────────────
    // Mostrar disponibilidad por zonas
    // ─────────────────────────────────────────────────────────
    private void mostrarDisponibilidadZonas(List<Zona> zonas) {
        contenedorZonas.getChildren().clear();

        if (zonas == null || zonas.isEmpty()) {
            Label sinZonas = new Label("Este recinto no tiene zonas registradas.");
            sinZonas.setStyle("-fx-text-fill: #718096;");
            contenedorZonas.getChildren().add(sinZonas);
            mostrar(cardZonas);
            return;
        }

        for (Zona zona : zonas) {
            contenedorZonas.getChildren().add(construirTarjetaZona(zona));
        }

        mostrar(cardZonas);
    }

    // ─────────────────────────────────────────────────────────
    // Tarjeta visual por zona
    // ─────────────────────────────────────────────────────────
    private VBox construirTarjetaZona(Zona zona) {
        VBox card = new VBox(6);
        card.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #C6F6D5; "
                + "-fx-background-radius: 8; -fx-border-radius: 8; -fx-padding: 10;");

        int disponibles = zona.obtenerDisponibilidad();
        int total       = zona.getListaAsientos().size();
        int ocupados    = total - disponibles;

        Label titulo = new Label(
                "Zona: " + zona.getNombre()
                        + "  |  ID: " + zona.getIdZona()
                        + "  |  Precio base: $" + zona.getPreciobase()
        );
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #276749;");
        titulo.setFont(Font.font(13));

        Label resumen = new Label(
                "Capacidad: " + zona.getCapacidad()
                        + "   ✅ Disponibles: " + disponibles
                        + "   ❌ Ocupados/Bloqueados: " + ocupados
        );
        resumen.setStyle("-fx-text-fill: #4A5568;");

        card.getChildren().addAll(titulo, resumen);

        List<Asiento> asientos = zona.getListaAsientos();
        if (!asientos.isEmpty()) {
            Label tituloAsientos = new Label("Asientos:");
            tituloAsientos.setStyle("-fx-font-weight: bold; -fx-text-fill: #555; -fx-font-size: 12;");
            card.getChildren().add(tituloAsientos);

            VBox filasAsientos = new VBox(4);
            javafx.scene.layout.HBox filaActual = new javafx.scene.layout.HBox(6);
            int contador = 0;

            for (Asiento asiento : asientos) {
                filaActual.getChildren().add(construirChipAsiento(asiento));
                contador++;
                if (contador % 6 == 0) {
                    filasAsientos.getChildren().add(filaActual);
                    filaActual = new javafx.scene.layout.HBox(6);
                }
            }

            if (!filaActual.getChildren().isEmpty()) {
                filasAsientos.getChildren().add(filaActual);
            }

            card.getChildren().add(filasAsientos);

        } else {
            Label sinAsientos = new Label("Sin asientos registrados en esta zona.");
            sinAsientos.setStyle("-fx-text-fill: #A0AEC0; -fx-font-style: italic;");
            card.getChildren().add(sinAsientos);
        }

        return card;
    }

    // ─────────────────────────────────────────────────────────
    // Chip por asiento
    // ─────────────────────────────────────────────────────────
    private Label construirChipAsiento(Asiento asiento) {
        Label chip = new Label(asiento.getFila() + asiento.getNumero());
        chip.setMinWidth(48);
        chip.setMinHeight(28);
        chip.setAlignment(javafx.geometry.Pos.CENTER);
        chip.setStyle("-fx-background-radius: 6; -fx-font-size: 11; "
                + "-fx-font-weight: bold; -fx-padding: 4 6 4 6; "
                + colorPorEstado(asiento.getEstado()));

        javafx.scene.control.Tooltip tooltip = new javafx.scene.control.Tooltip(
                "ID: "      + asiento.getIdAsiento()
                        + "\nFila: "   + asiento.getFila()
                        + "\nNúmero: " + asiento.getNumero()
                        + "\nEstado: " + asiento.getEstado()
        );
        javafx.scene.control.Tooltip.install(chip, tooltip);

        return chip;
    }

    // ─────────────────────────────────────────────────────────
    // Color chip según EstadoAsiento — switch clásico
    // ─────────────────────────────────────────────────────────
    private String colorPorEstado(EstadoAsiento estado) {
        switch (estado) {
            case DISPONIBLE: return "-fx-background-color: #C6F6D5; -fx-text-fill: #276749;";
            case RESERVADO:  return "-fx-background-color: #FEFCBF; -fx-text-fill: #744210;";
            case VENDIDO:    return "-fx-background-color: #FED7D7; -fx-text-fill: #9B2335;";
            case BLOQUEADO:  return "-fx-background-color: #E2E8F0; -fx-text-fill: #718096;";
            default:         return "-fx-background-color: #EDF2F7; -fx-text-fill: #4A5568;";
        }
    }

    // ─────────────────────────────────────────────────────────
    // Color Label estado del evento — switch clásico
    // ─────────────────────────────────────────────────────────
    private void colorearEstado(EstadoEvento estado) {
        String color;
        switch (estado) {
            case PUBLICADO:  color = "#276749"; break;
            case BORRADOR:   color = "#744210"; break;
            case PAUSADO:    color = "#2B6CB0"; break;
            case CANCELADO:  color = "#9B2335"; break;
            case FINALIZADO: color = "#553C9A"; break;
            default:         color = "#4A5568"; break;
        }
        lblEstado.setStyle("-fx-font-weight: bold; -fx-text-fill: " + color + ";");
    }

    // ─────────────────────────────────────────────────────────
    // Buscar evento por ID en la plataforma
    // ─────────────────────────────────────────────────────────
    private Evento buscarEventoPorId(String id) {
        for (Evento evento : plataforma.getListaEventos()) {
            if (evento.getIdEvento().equals(id)) {
                return evento;
            }
        }
        return null;
    }

    // ─────────────────────────────────────────────────────────
    // Utilidades show/hide
    // ─────────────────────────────────────────────────────────
    private void ocultarTodo() {
        ocultar(cardEvento);
        ocultar(cardRecinto);
        ocultar(cardZonas);
        ocultar(lblError);
    }

    private void mostrar(javafx.scene.Node nodo) {
        nodo.setVisible(true);
        nodo.setManaged(true);
    }

    private void ocultar(javafx.scene.Node nodo) {
        nodo.setVisible(false);
        nodo.setManaged(false);
    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        mostrar(lblError);
    }

    // ─────────────────────────────────────────────────────────
    // Volver al menú
    // ─────────────────────────────────────────────────────────
    @FXML
    public void volver() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionEvento.fxml"
                    )
            );
            javafx.scene.Parent root = loader.load();
            txtIdBuscar.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}