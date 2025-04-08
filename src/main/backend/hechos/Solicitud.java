package hechos;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Solicitud, la cual representara cada solicitud de eliminacion de hecho.
 */
@Getter
@Setter
public class Solicitud {

    private String descripcion;
    private Hecho hechoSolicitado;
}
