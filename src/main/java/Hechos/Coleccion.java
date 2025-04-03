package Hechos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Coleccion {

    private String titulo;
    private String descripcion;
    private List<Hecho> hechos;

    public void agregarHecho(Hecho hecho) {
        // TODO
    }
}
