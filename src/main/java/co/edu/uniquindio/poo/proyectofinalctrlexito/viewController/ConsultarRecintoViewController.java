package co.edu.uniquindio.poo.proyectofinalctrlexito.viewController;

import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Plataforma;
import co.edu.uniquindio.poo.proyectofinalctrlexito.model.Recinto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConsultarRecintoViewController {

    @FXML private TextField txtIdBuscar;
    @FXML private TextArea  txtResultado;

    @FXML
    void buscarRecinto(ActionEvent event) {

        String idTexto = txtIdBuscar.getText().trim();

        if (idTexto.isEmpty()) {
            txtResultado.setText("Por favor ingresa un ID.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            txtResultado.setText("El ID debe ser un número entero.");
            return;
        }

        Recinto recinto = Plataforma.getInstancia().buscarRecintoPorId(id);

        if (recinto == null) {
            txtResultado.setText("No se encontró ningún recinto con el ID " + id + ".");
            return;
        }

        txtResultado.setText(
                "ID:        " + recinto.getIdRecinto()  + "\n" +
                        "Nombre:    " + recinto.getNombre()     + "\n" +
                        "Dirección: " + recinto.getDireccion()  + "\n" +
                        "Ciudad:    " + recinto.getCiudad()
        );
    }

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/poo/proyectofinalctrlexito/GestionRecinto.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}