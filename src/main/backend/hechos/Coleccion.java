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
    private int contadorLineas = 0;
    private int contadorRepetidos = 0;
    private int contadorErrores = 0;

    public void contadorLineas() {
        contadorLineas++;
    }

    public void contadorRepetidos() {
        contadorRepetidos++;
    }

    public void contadorErrores() {
        contadorRepetidos++;
    }

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
