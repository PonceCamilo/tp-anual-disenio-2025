package usuarios;

import hechos.Hecho;
import hechos.Solicitud;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Contribuyente, para marcar a cada persona que contribuya en nuestro sistema.
 */
@Getter
@Setter
public class Contribuyente {

    /**
     * Metodo para solicitar una eliminacion de un hecho.
     */
    public void solicitarEliminacionHecho(String descripcionSolicitud, Hecho hecho) {
        Solicitud nuevaSolicitud = new Solicitud();

        nuevaSolicitud.setDescripcion(descripcionSolicitud);
        nuevaSolicitud.setHechoSolicitado(hecho);

        hecho.agregarSolicitud(nuevaSolicitud);
    }
}