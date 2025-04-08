package hechos;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerCategorias {
  private final Set<String> categorias;

  public ManagerCategorias() {
    this.categorias = new HashSet<>();
  }

  /**
   * Normaliza una categoría eliminando espacios, caracteres especiales
   * y acentos (diacríticos).
   *
   * @param categoria La categoría a normalizar.
   * @return El texto completamente normalizado.
   */
  private String normalizarCategoria(String categoria) {
    if (categoria == null) {
      return "";
    }
    // Eliminar espacios redundantes
    String limpiado = categoria.trim().replaceAll("\\s+", " ");

    // Convertir a minúsculas (puedes usar toUpperCase() si prefieres mayúsculas)
    limpiado = limpiado.toLowerCase();

    // Eliminar tildes y otros diacríticos
    limpiado = Normalizer.normalize(limpiado, Normalizer.Form.NFD);
    limpiado = limpiado.replaceAll("\\p{M}", ""); // Quita marcas de acentos

    // Eliminar caracteres especiales (comas, puntos, comillas, etc.)
    limpiado = limpiado.replaceAll("[^a-z0-9 ]", ""); // Solo deja letras, números y espacios

    return limpiado;
  }

  /**
   * Agrega una categoría si no está repetida, usando la normalización.
   *
   * @param categoria La categoría a agregar.
   * @return true si la categoría fue agregada, false si ya existía.
   */
  public boolean agregarCategoria(String categoria) {
    // Normalizar la categoría antes de comparar
    String normalizada = normalizarCategoria(categoria);

    // Intentar agregar al HashSet (false si ya existe)
    return categorias.add(normalizada);
  }

  public Set<String> getCategorias() {
    return categorias;
  }
}