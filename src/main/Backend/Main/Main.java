package Main;

import Hechos.Hecho;
import Usuarios.Administrador;
import Usuarios.Contribuyente;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        Administrador administrador = new Administrador();
        Contribuyente contribuyente = new Contribuyente();
        Hecho hecho = new Hecho();

        File archivoAImportar;
        try {
            // Obtengo la ruta del archivo hechosAImportar dentro del paquete resources
            archivoAImportar = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("desastres_tecnologicos_argentina.csv")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        administrador.importarHechos(archivoAImportar.getAbsolutePath(), "primera coleccion", "esta es la primera coleccion");

        hecho.setTitulo("Desastre Natural");
        hecho.setDescripcion("Inundación severa en la región X");
        hecho.setCategoria("Climático");
        hecho.setLatitud(-34.6037);
        hecho.setLongitud(-58.3816);
        hecho.setFechaDelAcontecimiento("2023-01-15");
        hecho.setFechaDeCarga("2023-01-16");
        hecho.setOrigen("Usuario");
        hecho.setSolicitudesEliminacion(new java.util.ArrayList<>());

        String descripcionSolicitud = "Este hecho contiene información inexacta y necesita ser eliminado.";

        contribuyente.solicitarEliminacionHecho(descripcionSolicitud, hecho, administrador);

        System.out.println("Hecho solicitado para eliminación: " + hecho.getTitulo());
        if (hecho.getSolicitudesEliminacion() != null && !hecho.getSolicitudesEliminacion().isEmpty()) {
            System.out.println("La solicitud de eliminación fue registrada con la siguiente descripción: " + hecho.getSolicitudesEliminacion().get(0).getDescripcion());
        } else {
            System.out.println("No se registró ninguna solicitud de eliminación.");
        }
    }
}