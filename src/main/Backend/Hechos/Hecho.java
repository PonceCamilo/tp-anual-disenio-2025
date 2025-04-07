package Hechos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Hecho {

    private String titulo;
    private String descripcion;
    private String categoria;
    private Double latitud;
    private Double longitud;
    private String fechaDelAcontecimiento;
    private String fechaDeCarga;
    private String origen;
    private List<Solicitud> solicitudesEliminacion;

    public void agregarSolicitud(Solicitud solicitud) {
        solicitudesEliminacion.add(solicitud);
    }
}
