module co.edu.uniquindio.poo.proyectofinalctrlexito {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens co.edu.uniquindio.poo.proyectofinalctrlexito to javafx.fxml;
    opens co.edu.uniquindio.poo.proyectofinalctrlexito.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.proyectofinalctrlexito.model to javafx.base;

    exports co.edu.uniquindio.poo.proyectofinalctrlexito;
}