package hechos;

import java.util.ArrayList;
import java.util.List;

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
    public List<Hecho> hechos = new ArrayList<>();
    public Integer contadorLineas = 0;
    public Integer contadorRepetidos = 0;
    public Integer contadorErrores = 0;

    public void agregarHecho(Hecho hecho) {
        this.hechos.add(hecho);
    }
}
