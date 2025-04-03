package Main;

import Usuarios.Administrador;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        Administrador administrador = new Administrador();

        File archivoHechosAImportar;
        try {
            // Obtengo la ruta del archivo hechosAImportar dentro del paquete resources
            archivoHechosAImportar = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("hechosAImportar")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        administrador.importarHechos(archivoHechosAImportar.getAbsolutePath(), "primera coleccion", "esta es la primera coleccion");

        System.out.println("el tamanio de la lista de colecciones del administrador es de " + administrador.getColecciones().size() + " coleccion");
        System.out.println(administrador.getColecciones().get(0).getTitulo());
        System.out.println(administrador.getColecciones().get(0).getDescripcion());
        System.out.println("el tamanio de la lista de hechos de la primer coleccion es de " + administrador.getColecciones().get(0).getHechos().size() + " hechos");
        System.out.println(administrador.getColecciones().get(0).getHechos().get(0).getTitulo());
        System.out.println(administrador.getColecciones().get(0).getHechos().get(1).getTitulo());
        System.out.println(administrador.getColecciones().get(0).getHechos().get(1).getDescripcion());
        System.out.println(administrador.getColecciones().get(0).getHechos().get(2).getTitulo());
    }
}