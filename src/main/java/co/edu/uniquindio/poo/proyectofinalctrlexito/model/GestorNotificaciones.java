package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorNotificaciones {

    private static GestorNotificaciones instancia;
    private final Map<String, List<String>> notificaciones = new HashMap<>();

    private GestorNotificaciones() {}

    public static GestorNotificaciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorNotificaciones();
        }
        return instancia;
    }

    public void agregarNotificacion(String idUsuario, String mensaje) {
        notificaciones.computeIfAbsent(idUsuario, k -> new ArrayList<>()).add(mensaje);
    }

    public List<String> getNotificaciones(String idUsuario) {
        return notificaciones.getOrDefault(idUsuario, new ArrayList<>());
    }

    public void limpiarNotificaciones(String idUsuario) {
        notificaciones.remove(idUsuario);
    }
}

