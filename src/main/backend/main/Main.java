package main;

import hechos.Categoria;
import hechos.Hecho;
import usuarios.Administrador;
import usuarios.Contribuyente;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Clase principal de la aplicación.
 * Contiene el método main que realiza una demostración de las funcionalidades
 * de los usuarios y hechos.
 */
public class Main {
    /**
     * Método principal de la aplicación.
     *
     * @param args Argumentos proporcionados desde la línea de comandos.
     * @throws URISyntaxException Si la ruta del archivo a importar no es válida.
     */
    public static void main(String[] args) throws URISyntaxException {

        Administrador administrador = new Administrador();

        File archivoAimportar;

        URL url = Main.class.getClassLoader().getResource("desastres_tecnologicos_argentina.csv");
        if (url == null) {
            throw new IllegalArgumentException("El archivo 'desastres_tecnologicos_argentina.csv' no se encuentra en el paquete resources.");
        }
        // Obtengo la ruta del archivo hechosAImportar dentro del paquete resources
        archivoAimportar = new File(url.toURI());

        administrador.importarHechos(archivoAimportar.getAbsolutePath(), "primera coleccion", "esta es la primera coleccion");

        Hecho hecho = new Hecho();
        Categoria categoria = new Categoria();

        categoria.setNombre("climatico");

        hecho.setTitulo("Desastre Natural");
        hecho.setDescripcion("Inundación severa en la región X");
        hecho.setCategoria(categoria);
        hecho.setLatitud(-34.6037);
        hecho.setLongitud(-58.3816);
        hecho.setFechaDelAcontecimiento("2023-01-15");
        hecho.setFechaDeCarga("2023-01-16");
        hecho.setOrigen("Usuario");
        hecho.setSolicitudesEliminacion(new java.util.ArrayList<>());

        String descripcionSolicitud = "Este hecho contiene información inexacta y necesita ser eliminado.";

        Contribuyente contribuyente = new Contribuyente();

        contribuyente.solicitarEliminacionHecho(descripcionSolicitud, hecho);

        System.out.println("Hecho solicitado para eliminación: " + hecho.getTitulo());
        if (hecho.getSolicitudesEliminacion() != null && !hecho.getSolicitudesEliminacion().isEmpty()) {
            System.out.println("La solicitud de eliminación fue registrada con la siguiente descripción: " + hecho.getSolicitudesEliminacion().get(0).getDescripcion());
        } else {
            System.out.println("No se registró ninguna solicitud de eliminación.");
        }
    }
}