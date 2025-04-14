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
    private Ubicacion ubicacion;
    private String fechaDelAcontecimiento;
    private String fechaDeCarga;
    private String origen;
    private List<SolicitudDeEliminacion> solicitudesEliminacion;
    public Boolean visualizarHecho = true;

    public void agregarSolicitud(SolicitudDeEliminacion solicitud) {
        solicitudesEliminacion.add(solicitud);
    }
}
