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
    private FuenteEstatica fuente;
    private List<Hecho> hechos;
    private Integer contadorLineas;
    private Integer contadorRepetidos;
    private Integer contadorErrores;

    public Coleccion(Integer contadorLineas, Integer contadorRepetidos, Integer contadorErrores, List<Hecho> hechos) {
        this.contadorLineas = 0;
        this.contadorRepetidos = 0;
        this.contadorErrores = 0;
        this.hechos = new ArrayList<>();
    }

    public void agregarHecho(Hecho hecho) {
        this.hechos.add(hecho);
    }
    public List<Hecho> buscarHechosConFiltros(List<Filtros> filtros){}
    public List<Hecho> importarCsv(List<CriterioDePertenecia>)
}
