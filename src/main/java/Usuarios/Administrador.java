package Usuarios;

import Hechos.Coleccion;
import Hechos.Hecho;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
public class Administrador {

    private List<Coleccion> colecciones = new ArrayList<>();

    public void importarHechos(String rutaArchivo, String tituloColeccion, String descripcionColeccion) {
        // Uso un LinkedHashMap para manejar hechos únicos, basados en el título y
        // garantizar su orden de inserción.
        LinkedHashMap<String, Hecho> mapaHechos = new LinkedHashMap<>();

        // intento acceder al archivo CSV.
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            // Consulto si la linea que se esta leyendo tiene el formato correcto.
            while ((linea = br.readLine()) != null) {
                // Hace que el delimitador sea una coma.
                String[] datos = linea.split(",");

                // Delimita la cantidad de datos por linea y que las fechas sean correctas.
                if(datos.length == 8 && esFechaValida(datos[5].trim(), "dd/MM/yyyy")){
                    Hecho hechoObtenido = new Hecho();

                    hechoObtenido.setTitulo(datos[0].trim());
                    hechoObtenido.setDescripcion(datos[1].trim());
                    hechoObtenido.setCategoria(datos[2].trim());
                    hechoObtenido.setLatitud(Double.valueOf(datos[3].trim()));
                    hechoObtenido.setLongitud(Double.valueOf(datos[4].trim()));
                    hechoObtenido.setFechaDelAcontecimiento(datos[5].trim());
                    hechoObtenido.setFechaDeCarga(datos[6].trim());
                    hechoObtenido.setOrigen(datos[7].trim());

                    // Agrega el hecho al mapa, reemplazando cualquier duplicado automáticamente.
                    // El título del hecho lo transformo en minúsculas (`hechoObtenido.getTitulo().toLowerCase()`),
                    // eliminando la necesidad de comprobaciones explícitas como `equalsIgnoreCase`.
                    mapaHechos.put(hechoObtenido.getTitulo().toLowerCase(), hechoObtenido);
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        // Obtengo los valores del mapa como una lista para pasarlos a las colecciones.
        List<Hecho> hechosImportados = new ArrayList<>(mapaHechos.values());

        analizarHechosRepetidos(hechosImportados);

        crearColeccion(tituloColeccion, descripcionColeccion, hechosImportados);
    }

    public Boolean esFechaValida(String fecha, String formato){
        try {
            // Utilizo SimpleDateFormat para formatear y analizar fechas, donde
            // recibe un formato que define como debe intrepretarse la fecha.
            SimpleDateFormat sdf = new SimpleDateFormat(formato);

            // Impide fechas inexistentes, como 30/02/12.
            sdf.setLenient(false);

            // Analiza la fecha recibida.
            sdf.parse(fecha);

            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void analizarHechosRepetidos(List<Hecho> hechos) {
        // Uso un LinkedHashMap para almacenar el último Hecho con un título específico
        // y para que no se desordene la lista.
        LinkedHashMap<String, Hecho> mapaHechos = new LinkedHashMap<>();

        // Itero sobre los Hechos, sobrescribiendo los valores en caso de que el título exista
        for (Hecho hecho : hechos) {
            // Si hay un título repetido, se reemplaza con la última aparición
            mapaHechos.put(hecho.getTitulo(), hecho);
        }

        // Los valores del mapa ahora tienen solo el último registro por título
        hechos.clear();
        hechos.addAll(mapaHechos.values());
    }

    public void crearColeccion(String titulo, String descripcion, List<Hecho> hechos){
        Coleccion nuevaColeccion = new Coleccion();

        nuevaColeccion.setTitulo(titulo);
        nuevaColeccion.setDescripcion(descripcion);
        nuevaColeccion.setHechos(hechos);

        this.colecciones.add(nuevaColeccion);
    }
}
