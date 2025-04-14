package hechos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ubicacion {

  private Double latitud;
  private Double longitud;

  public Ubicacion(Double latitud, Double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }
}
