package Usuarios;

import Hechos.Coleccion;
import Hechos.Hecho;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
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
    private int contadorLineas;
    private int contadorRepetidos;
    private int contadorErrores;

    public void importarHechos(String rutaArchivo, String tituloColeccion, String descripcionColeccion) {
        // Uso un LinkedHashMap para manejar hechos únicos, basados en el título y
        // garantizar su orden de inserción.
        LinkedHashMap<String, Hecho> mapaHechos = new LinkedHashMap<>();

        // intento acceder al archivo CSV.
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            // Consulto si la línea que se está leyendo tiene el formato correcto.
            while ((linea = br.readLine()) != null) {
                contadorLineas ++;

                // Hace que el delimitador sea una coma.
                // Asegura que las comas fuera de las comillas dobles sean las que se utilicen como separadores.
                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                almacenarHecho(datos[0].trim(), datos[1].trim(), datos[2].trim(), Double.valueOf(datos[3].trim()), Double.valueOf(datos[4].trim()), datos[5].trim(), datos.length, linea, mapaHechos);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        // Obtengo los valores del mapa como una lista para pasarlos a las colecciones.
        List<Hecho> hechosImportados = new ArrayList<>(mapaHechos.values());

        crearColeccion(tituloColeccion, descripcionColeccion, hechosImportados);

        System.out.println("Total colecciones creadas: " + this.colecciones.size());
        System.out.println("Total líneas leídas: " + contadorLineas);
        System.out.println("Total hechos importados: " + mapaHechos.size());
        System.out.println("Total hechos con errores: " + contadorErrores);
        System.out.println("Total hechos repetidos: " + contadorRepetidos);
    }

    public void almacenarHecho(String titulo, String descripcion, String categoria, Double latitud, Double longitud, String fechaAcontecimiento, Integer cantidadDatos, String lineaLeida, LinkedHashMap<String, Hecho> mapaHechosImportados) {
        if(cantidadDatos == 6 && fechaValida(fechaAcontecimiento, "dd/MM/yyyy")) {
            Hecho hechoObtenido = new Hecho();

            hechoObtenido.setTitulo(titulo);
            hechoObtenido.setDescripcion(descripcion);
            hechoObtenido.setCategoria(categoria);
            hechoObtenido.setLatitud(latitud);
            hechoObtenido.setLongitud(longitud);
            hechoObtenido.setFechaDelAcontecimiento(fechaAcontecimiento);

            if(!analizarHechoRepetido(hechoObtenido, mapaHechosImportados)) {
                agregarHecho(hechoObtenido, mapaHechosImportados);
            } else {
                System.out.println("Hecho repetido y actualizado: " + titulo);

                // Sobrescribo con el nuevo hecho.
                agregarHecho(hechoObtenido, mapaHechosImportados);

                contadorRepetidos++;
            }
        } else {
            corroborarError(cantidadDatos, fechaAcontecimiento, "dd/MM/yyyy", lineaLeida);

            contadorErrores++;
        }
    }

    public Boolean analizarHechoRepetido(Hecho nuevoHecho, LinkedHashMap<String, Hecho> hechosAImportar) {
        // Verifico si el mapa de hechos ya contiene un hecho con el mismo título.
        return hechosAImportar.containsKey(nuevoHecho.getTitulo());
    }

    public void agregarHecho(Hecho hecho, LinkedHashMap<String, Hecho> hechosAImportar) {
        hechosAImportar.put(hecho.getTitulo(), hecho);
    }

    public void corroborarError(Integer cantidadDatos, String fecha, String formato, String lineaLeida) {
        if(cantidadDatos != 6) {
            System.out.println("Línea con cantidad de datos incorrecta:");
            System.out.println(lineaLeida);
            System.out.println("Se esperaban 6 datos, pero se encontraron " + cantidadDatos);
        } else if (!fechaValida(fecha, formato)){
            System.out.println("Fecha incorrecta:");
            System.out.println(lineaLeida);
        } else {
            System.out.println("Error desconocido en linea: ");
            System.out.println(lineaLeida);
        }
    }

    public void crearColeccion(String titulo, String descripcion, List<Hecho> hechos){
        Coleccion nuevaColeccion = new Coleccion();

        nuevaColeccion.setTitulo(titulo);
        nuevaColeccion.setDescripcion(descripcion);
        nuevaColeccion.setHechos(hechos);

        this.colecciones.add(nuevaColeccion);
    }

    public Boolean fechaValida(String fecha, String formato){
        try {
            // Utilizo SimpleDateFormat para formatear y analizar fechas, donde
            // recibe un formato que define como debe interpretarse la fecha.
            SimpleDateFormat sdf = new SimpleDateFormat(formato);

            // Impide fechas inexistentes, como 30/02/12.
            sdf.setLenient(false);

            // Analiza las fechas recibidas.
            sdf.parse(fecha);

            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
