package hechos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Hecho, la cual representara cada hecho con todos sus atributos.
 */
@Getter
@Setter
public class Hecho {

    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private Double latitud;
    private Double longitud;
    private String fechaDelAcontecimiento;
    private String fechaDeCarga;
    private String origen;
    private Boolean visualizarHecho;
    private List<Solicitud> solicitudesEliminacion;

    public Hecho() {
        this.visualizarHecho = true;
    }

    public void agregarSolicitud(Solicitud solicitud) {
        solicitudesEliminacion.add(solicitud);
    }
}
