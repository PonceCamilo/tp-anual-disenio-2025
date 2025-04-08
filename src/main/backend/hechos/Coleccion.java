package hechos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Coleccion, la cual agrupara una cantidad de hechos.
 */
@Getter
@Setter
public class Coleccion {

    private String titulo;
    private String descripcion;
    private List<Hecho> hechos;

    public Coleccion() {
        this.hechos = new ArrayList<>();
    }

    public void agregarHecho(Hecho hecho) {
        this.hechos.add(hecho);
    }

    public List<Hecho> listarHechos() {
        return this.hechos.stream().filter(Hecho::getVisualizarHecho).collect(Collectors.toList());
    }
}
