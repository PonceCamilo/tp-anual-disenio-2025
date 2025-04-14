package hechos;

import java.util.ArrayList;
import java.util.List;
import filtros.CriterioDePertenencia;

import filtros.Filtro;
import fuentes.FuenteEstatica;
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

    public Coleccion() {
        this.contadorLineas = 0;
        this.contadorRepetidos = 0;
        this.contadorErrores = 0;
        this.hechos = new ArrayList<>();
    }

    public void agregarHecho(Hecho hecho) {
        this.hechos.add(hecho);
    }
    public List<Hecho> buscarHechosConFiltros(List<Filtro> filtros) {

        return null;
    }
    public List<Hecho> importarCsv(List<CriterioDePertenencia> criteriosDePertenencia) {
        // TODO
        return null;
    }
}
