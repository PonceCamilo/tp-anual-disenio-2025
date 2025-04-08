package usuarios;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Persona, para definir a cada persona que acceda a nuestro sistema, sea denominada como sea.
 */
@Getter
@Setter
public class Persona {

    private String nombre;
    private String apellido;
    private String edad;
}

