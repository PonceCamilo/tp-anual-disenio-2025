package Main;

import Usuarios.Administrador;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        Administrador administrador = new Administrador();

        File archivoAImportar;
        try {
            // Obtengo la ruta del archivo hechosAImportar dentro del paquete resources
            archivoAImportar = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("desastres_sanitarios_contaminacion_argentina.csv")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        administrador.importarHechos(archivoAImportar.getAbsolutePath(), "primera coleccion", "esta es la primera coleccion");
    }
}