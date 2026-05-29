package co.edu.uniquindio.poo.proyectofinalctrlexito.model;

import java.util.ArrayList;
import java.util.List;

    public class Recinto implements ComponenteRecinto, Visitor {

        private int idRecinto;
        private String nombre;
        private String direccion;
        private String ciudad;
        private List<Zona> listaZonas;
        private int capacidadMaxima;

        public Recinto(int idRecinto, String nombre, String direccion,
                       String ciudad, int capacidadMaxima) {
            this(idRecinto, nombre, direccion, ciudad);
            this.capacidadMaxima = capacidadMaxima;
        }
        /**
         * Constructor de la clase Recinto.
         * Inicializa los atributos principales del recinto y
         * crea una lista vacía de zonas.
         *
         * @param idRecinto identificador único del recinto.
         * @param nombre nombre del recinto.
         * @param direccion dirección del recinto.
         * @param ciudad ciudad donde se encuentra el recinto.
         */
        public Recinto(int idRecinto,
                       String nombre,
                       String direccion,
                       String ciudad) {

            this.idRecinto = idRecinto;
            this.nombre = nombre;
            this.direccion = direccion;
            this.ciudad = ciudad;
            this.listaZonas = new ArrayList<Zona>();
            this.capacidadMaxima = Integer.MAX_VALUE;
        }

        /**
         * Agrega una nueva zona al recinto y establece
         * la referencia bidireccional zona → recinto.
         *
         * @param nuevaZona zona que será agregada al recinto.
         */
        public void agregarZona(Zona nuevaZona) {
            nuevaZona.setRecinto(this); // NUEVO: relación bidireccional
            this.listaZonas.add(nuevaZona);
        }

        /**
         * Calculates la disponibilidad total del recinto
         * sumando la disponibilidad de todas sus zonas.
         *
         * @return cantidad total de espacios disponibles.
         */
        @Override
        public int obtenerDisponibilidad() {

            int totalDisponibles = 0;

            for (int i = 0; i < listaZonas.size(); i++) {

                totalDisponibles = totalDisponibles +
                        listaZonas.get(i).obtenerDisponibilidad();
            }

            return totalDisponibles;
        }

        /**
         * Permite que un visitante genere reportes
         * utilizando el patrón Visitor.
         *
         * @param visitor visitante encargado de procesar el recinto.
         */
        @Override
        public void aceptarVisitante(ReporteVisitor visitor){
            visitor.visitarRecinto(this);
        }

        /**
         * Obtiene el identificador del recinto.
         *
         * @return identificador único del recinto.
         */
        public int getIdRecinto() {
            return idRecinto;
        }

        /**
         * Establece el identificador del recinto.
         *
         * @param idRecinto nuevo identificador del recinto.
         */
        public void setIdRecinto(int idRecinto) {
            this.idRecinto = idRecinto;
        }

        /**
         * Obtiene el nombre del recinto.
         *
         * @return nombre del recinto.
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Modifica el nombre del recinto.
         *
         * @param nombre nuevo nombre del recinto.
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        /**
         * Obtiene la dirección del recinto.
         *
         * @return dirección del recinto.
         */
        public String getDireccion() {
            return direccion;
        }

        /**
         * Modifica la dirección del recinto.
         *
         * @param direccion nueva dirección del recinto.
         */
        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        /**
         * Obtiene la ciudad donde se encuentra el recinto.
         *
         * @return ciudad del recinto.
         */
        public String getCiudad() {
            return ciudad;
        }

        /**
         * Modifica la ciudad del recinto.
         *
         * @param ciudad nueva ciudad del recinto.
         */
        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        @Override
        public String toString() {
            return "[" + idRecinto + "] " + nombre + " — " + ciudad + " — " + direccion;
        }

        // ==========================================================
        // MÉTODOS DE GESTIÓN DE ZONAS
        // ==========================================================

        /**
         * Crea e integra una nueva zona directamente al listado del recinto,
         * estableciendo la relación bidireccional zona → recinto.
         *
         * @param idZona identificador único de la zona.
         * @param nombre nombre descriptivo de la zona (ej: VIP, General).
         * @param capacidad capacidad máxima de asientos de la zona.
         * @param preciobase precio base asignado a la zona.
         */
        public void crearZona(int idZona, String nombre, int capacidad, double preciobase) {
            Zona nueva = new Zona(idZona, nombre, capacidad, preciobase);
            agregarZona(nueva); // agregarZona ya asigna this como recinto de la zona
        }

        /**
         * Busca una zona específica en el recinto por su identificador.
         *
         * @param idZona identificador de la zona a consultar.
         * @return El objeto Zona si se encuentra, null en caso contrario.
         */
        public Zona consultarZona(int idZona) {
            for (Zona zona : listaZonas) {
                if (zona.getIdZona() == idZona) {
                    return zona;
                }
            }
            return null;
        }

        /**
         * Actualiza los datos de una zona existente utilizando el método interno de Zona.
         *
         * @param idZona identificador de la zona a modificar.
         * @param nuevoNombre nuevo nombre descriptivo de la zona.
         * @param nuevaCapacidad nueva capacidad máxima de la zona.
         * @param nuevoPreciobase nuevo precio base aplicable a la zona.
         * @return true si la zona fue encontrada y actualizada, false de lo contrario.
         */
        public boolean actualizarZona(int idZona, String nuevoNombre, int nuevaCapacidad, double nuevoPreciobase) {
            Zona zona = consultarZona(idZona);
            if (zona != null) {
                zona.actualizarZona(nuevoNombre, nuevaCapacidad, nuevoPreciobase);
                return true;
            }
            return false;
        }

        /**
         * Elimina una zona de la lista del recinto a partir de su identificador.
         *
         * @param idZona identificador de la zona a remover.
         * @return true si la zona fue removida exitosamente, false si no existía.
         */
        public boolean eliminarZona(int idZona) {
            return listaZonas.removeIf(zona -> zona.getIdZona() == idZona);
        }

        /**
         * Consulta la ocupación detallada por cada zona imprimiendo su
         * disponibilidad actual individual en formato de texto.
         *
         * @return Reporte consolidado de ocupación de todas las zonas.
         */
        public String consultarOcupacionPorZona() {
            if (listaZonas.isEmpty()) {
                return "El recinto '" + nombre + "' no tiene zonas registradas.";
            }

            StringBuilder reporte = new StringBuilder();
            reporte.append("--- Reporte de Ocupación: ").append(nombre).append(" ---\n");

            for (Zona zona : listaZonas) {
                reporte.append("Zona: ").append(zona.getNombre())
                        .append(" (ID: ").append(zona.getIdZona()).append(")")
                        .append(" — Disponibles: ").append(zona.obtenerDisponibilidad())
                        .append("\n");
            }

            return reporte.toString().trim();
        }

        /**
         * Obtiene la lista de zonas del recinto.
         *
         * @return lista de zonas.
         */
        public List<Zona> getListaZonas() {
            return listaZonas;
        }

        /**
         * Asigna una lista de zonas al recinto.
         *
         * @param listaZonas nueva lista de zonas.
         */
        public void setListaZonas(List<Zona> listaZonas) {
            this.listaZonas = listaZonas;
        }
        public int getCapacidadMaxima() {
            return capacidadMaxima;
        }

        public void setCapacidadMaxima(int capacidadMaxima) {
            this.capacidadMaxima = capacidadMaxima;
        }
    }