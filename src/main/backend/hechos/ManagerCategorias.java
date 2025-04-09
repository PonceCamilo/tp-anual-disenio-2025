package hechos;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase ManagerCategorias, va a representar un conjunto de categorías no repetidas.
 */
@Getter
@Setter
public class ManagerCategorias {
  public final Set<String> categorias = new HashSet<>();

  /**
   * Normaliza una categoría eliminando espacios, caracteres especiales
   * y acentos.
   */
  public String normalizarCategoria(String categoria) {
    if (categoria == null) {
      return "";
    }
    // Elimina los espacios redundantes.
    String limpiado = categoria.trim().replaceAll("\\s+", " ");

    // Convierte mayúsculas a minúsculas (se puede usar toUpperCase() si prefiriéramos mayúsculas).
    limpiado = limpiado.toLowerCase();

    // Elimina las tildes.
    limpiado = Normalizer.normalize(limpiado, Normalizer.Form.NFD);

    // Quita marcas de acentos
    limpiado = limpiado.replaceAll("\\p{M}", "");

    // Elimina los caracteres especiales (comas, puntos, comillas, etc.)
    limpiado = limpiado.replaceAll("[^a-z0-9 ]", ""); // Solo deja letras, números y espacios

    return limpiado;
  }

  /**
   * Agrega una categoría si no está repetida, usando la normalización.
   */
  public void posibleNuevaCategoria(String categoria) {
    // Normaliza la categoría antes de comparar
    String normalizada = normalizarCategoria(categoria);

    // Intenta agregar al HashSet.
    // Al ser un HashSet la lista de categorias, si no existe,
    // la agrega, pero si ya existe, no hace nada. Impide repetidos.
    categorias.add(normalizada);
  }
}