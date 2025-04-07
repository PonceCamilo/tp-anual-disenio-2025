package Usuarios;

import Hechos.Hecho;
import Hechos.Solicitud;
import Usuarios.Administrador;

public class Contribuyente {

    public void solicitarEliminacionHecho(String descripcionSolicitud, Hecho hecho, Administrador administrador) {
        Solicitud nuevaSolicitud = new Solicitud();

        nuevaSolicitud.setDescripcion(descripcionSolicitud);
        nuevaSolicitud.setHechoSolicitado(hecho);

        administrador.revisarSolicitud(nuevaSolicitud);
    }
}