package usuarios;

import hechos.Categoria;
import hechos.Coleccion;
import hechos.Hecho;
import hechos.Solicitud;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Administrador. Va a representar a cada administrador que se loguee en nuestra app.
 */
@Getter
@Setter
public class Administrador {

    private List<Coleccion> colecciones;

    public Administrador() {
        this.colecciones = new ArrayList<>();
    }

    /**
     * Metodo para importar hechos desde archivos de tipo CSV.
     */
    public void importarHechos(String rutaArchivo, String tituloColeccion, String descripcionColeccion) {
        // intento acceder al archivo CSV.
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            crearColeccion(tituloColeccion, descripcionColeccion);
            Coleccion ultimaColeccion = colecciones.get(colecciones.size() - 1);
            // Consulto si la línea que se está leyendo tiene el formato correcto.
            while ((linea = br.readLine()) != null) {
                ultimaColeccion.setContadorLineas(ultimaColeccion.getContadorLineas() + 1);

                // Hace que el delimitador sea una coma.
                // Asegura que las comas fuera de las comillas dobles sean las que se utilicen como separadores.
                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                almacenarHecho(datos[0].trim(), datos[1].trim(), datos[2].trim(), Double.valueOf(datos[3].trim()), Double.valueOf(datos[4].trim()), datos[5].trim(), datos.length, linea, ultimaColeccion);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    /**
     * Metodo para almacenar un hecho en la lista de hechos no repetidos y sin errores.
     */
    public void almacenarHecho(String titulo, String descripcion, String categoria, Double latitud, Double longitud, String fechaAcontecimiento, Integer cantidadDatos, String lineaLeida, Coleccion coleccionCreada) {
        if (cantidadDatos == 6 && fechaValida(fechaAcontecimiento, "dd/MM/yyyy")) {

            if (analizarHechoRepetido(titulo, coleccionCreada.getHechos())) {
                coleccionCreada.setContadorRepetidos(coleccionCreada.getContadorRepetidos() + 1);
            }

            Categoria categoriaObtenida = new Categoria();

            categoriaObtenida.setNombre(categoria);

            Hecho hechoObtenido = new Hecho();

            hechoObtenido.setTitulo(titulo);
            hechoObtenido.setDescripcion(descripcion);
            hechoObtenido.setCategoria(categoriaObtenida);
            hechoObtenido.setLatitud(latitud);
            hechoObtenido.setLongitud(longitud);
            hechoObtenido.setFechaDelAcontecimiento(fechaAcontecimiento);

            coleccionCreada.agregarHecho(hechoObtenido);
        } else {
            corroborarError(cantidadDatos, fechaAcontecimiento, "dd/MM/yyyy", lineaLeida);

            coleccionCreada.setContadorErrores(coleccionCreada.getContadorErrores() + 1);
        }
    }

    public Boolean analizarHechoRepetido(String tituloHecho, List<Hecho> listaHechosActuales) {
        // Verifico si la lista de hechos ya contiene un hecho con el mismo título.
        return listaHechosActuales.stream().anyMatch(h -> h.getTitulo().equalsIgnoreCase(tituloHecho));
    }

    /**
     * Metodo para corroborar cuál fue el error que tuvo algún hecho, por el cual no pudo ser almacenado.
     */
    public void corroborarError(Integer cantidadDatos, String fecha, String formato, String lineaLeida) {
        if (cantidadDatos != 6) {
            System.out.println("Línea con cantidad de datos incorrecta:");
            System.out.println(lineaLeida);
            System.out.println("Se esperaban 6 datos, pero se encontraron " + cantidadDatos);
        } else if (!fechaValida(fecha, formato)) {
            System.out.println("Linea con fecha incorrecta:");
            System.out.println(lineaLeida);
        } else {
            System.out.println("Linea con error desconocido:");
            System.out.println(lineaLeida);
        }
    }

    /**
     * Metodo para crear la coleccion donde se van a almacenar varios hechos.
     */
    public void crearColeccion(String titulo, String descripcion) {
        Coleccion nuevaColeccion = new Coleccion();

        nuevaColeccion.setTitulo(titulo);
        nuevaColeccion.setDescripcion(descripcion);

        this.colecciones.add(nuevaColeccion);
    }

    /**
     * Metodo para comprobar si la fecha enviada es valida o no.
     */
    public Boolean fechaValida(String fecha, String formato) {
        try {
            // Utilizo SimpleDateFormat para formatear y analizar fechas, donde
            // recibe un formato que define como debe interpretarse la fecha.
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

    /**
     * Metodo para aprobar una solicitud de eliminacion.
     */
    public void aceptarSolicitud(Solicitud solicitud) {
        Hecho hecho = solicitud.getHechoSolicitado();

        hecho.setVisualizarHecho(false);

        System.out.println("solicitud aprobada y hecho: " + hecho.getTitulo() + ", eliminado con exito");
    }

    /**
     * Metodo para rechazar una solicitud de eliminacion.
     */
    public void rechazarSolicitud(Solicitud solicitud) {
        Hecho hecho = solicitud.getHechoSolicitado();

        hecho.getSolicitudesEliminacion().remove(solicitud);

        System.out.println("solicitud rechazada");
    }
}