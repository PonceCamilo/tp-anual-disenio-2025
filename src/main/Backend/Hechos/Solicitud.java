package Hechos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Solicitud {

    private String descripcion;
    private Hecho hechoSolicitado;
    private Boolean aprobada;
}
