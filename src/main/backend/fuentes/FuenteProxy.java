package fuentes;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Proxy que hereda de Fuente.
 * Esta clase actua como un proxy para la clase Fuente, permitiendo acceder a los
 * hechos de una fuente sin necesidad de conocer su implementacion concreta.
 */
@Getter
@Setter
public class FuenteProxy extends Fuente {

}
