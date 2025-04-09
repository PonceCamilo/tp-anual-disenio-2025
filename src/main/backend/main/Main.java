package main;

import hechos.Categoria;
import hechos.Coleccion;
import hechos.Hecho;
import usuarios.Administrador;
import usuarios.Contribuyente;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Clase principal de la aplicación.
 * Contiene el método main que realiza una demostración de las funcionalidades
 * de los usuarios y hechos.
 */
public class Main {
    /**
     * Método principal de la aplicación.
     */
    public static void main(String[] args) throws URISyntaxException {

        Administrador administrador = new Administrador();

        File archivoAimportar1;
        File archivoAimportar2;
        File archivoAimportar3;

        URL url1 = Main.class.getClassLoader().getResource("desastres_tecnologicos_argentina.csv");
        URL url2 = Main.class.getClassLoader().getResource("desastres_sanitarios_contaminacion_argentina.csv");
        URL url3 = Main.class.getClassLoader().getResource("desastres_naturales_argentina.csv");

        if (url1 == null) {
            throw new IllegalArgumentException("El archivo 'desastres_tecnologicos_argentina.csv' no se encuentra en el paquete resources.");
        } else if (url2 == null) {
            throw new IllegalArgumentException("El archivo 'desastres_sanitarios_contaminacion_argentina.csv' no se encuentra en el paquete resources.");
        } else if (url3 == null) {
            throw new IllegalArgumentException("El archivo 'desastres_naturales_argentina.csv' no se encuentra en el paquete resources.");
        }

        // Obtengo la ruta de los archivos hechosAImportar dentro del paquete resources
        archivoAimportar1 = new File(url1.toURI());
        archivoAimportar2 = new File(url2.toURI());
        archivoAimportar3 = new File(url3.toURI());

        administrador.importarHechos(archivoAimportar1.getAbsolutePath(), "primera coleccion", "esta es la primera coleccion");
        administrador.importarHechos(archivoAimportar2.getAbsolutePath(), "segunda coleccion", "esta es la segunda coleccion");
        administrador.importarHechos(archivoAimportar3.getAbsolutePath(), "tercera coleccion", "esta es la tercera coleccion");

        for (Coleccion coleccion : administrador.getColecciones()) {
            System.out.println(coleccion.getTitulo());
            System.out.println(coleccion.getDescripcion());
            System.out.println("Total líneas leídas: " + coleccion.getContadorLineas());
            System.out.println("Total hechos listados: " + (coleccion.getHechos().size() - coleccion.getContadorErrores() - coleccion.getContadorRepetidos()));
            System.out.println("Total hechos repetidos: " + coleccion.getContadorRepetidos());
            System.out.println("Total hechos con errores: " + coleccion.getContadorErrores());
            System.out.println(" ");
        }

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
        hecho.setSolicitudesEliminacion(new ArrayList<>());

        Contribuyente contribuyente = new Contribuyente();

        String descripcionSolicitud = "Este hecho contiene información inexacta y necesita ser eliminado.";

        contribuyente.solicitarEliminacionHecho(descripcionSolicitud, hecho);

        System.out.println("Hecho solicitado para eliminación: " + hecho.getTitulo());
        if (hecho.getSolicitudesEliminacion() != null && !hecho.getSolicitudesEliminacion().isEmpty()) {
            System.out.println("La solicitud de eliminación fue registrada con la siguiente descripción: " + hecho.getSolicitudesEliminacion().get(0).getDescripcion());
        } else {
            System.out.println("No se registró ninguna solicitud de eliminación.");
        }

        administrador.aceptarSolicitud(hecho.getSolicitudesEliminacion().get(0));
    }
}