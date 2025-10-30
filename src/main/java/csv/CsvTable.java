package csv;
// Indica que esta clase pertenece al paquete 'csv'.
// Permite agruparla junto con otras clases relacionadas (como CsvPrinter o CsvUtils).

import java.util.*;
// Importamos utilidades de la librería estándar de Java:
// - List y Map → estructuras de datos para listas y mapas clave-valor.
// - ArrayList → implementación concreta de List.
// - LinkedHashMap → implementación de Map que mantiene el orden de inserción.
// - Collections → clase con utilidades para colecciones, como crear vistas inmodificables.

/**
 * Clase que representa una tabla CSV genérica y dinámica.
 *
 * Esta clase no conoce la estructura del CSV por adelantado:
 *   - Las cabeceras (columnas) se guardan en una lista (`headers`).
 *   - Cada fila se almacena como un `Map` donde la clave es el nombre de la columna.
 *
 * Ejemplo de cómo se almacenaría un CSV con cabecera:
 *
 *  id,nombre,email
 *  1,Ana,ana@correo.com
 *  2,Luis,luis@correo.com
 *
 * → headers = ["id", "nombre", "email"]
 * → rows = [
 *      { "id"="1", "nombre"="Ana", "email"="ana@correo.com" },
 *      { "id"="2", "nombre"="Luis", "email"="luis@correo.com" }
 *   ]
 */
public class CsvTable {

    // ---------------------------------------------------------
    // ATRIBUTOS PRINCIPALES
    // ---------------------------------------------------------

    // Lista que contiene las cabeceras (nombres de las columnas del CSV).
    // Se usa 'final' porque la referencia nunca cambia (aunque su contenido sí puede).
    private final List<String> headers = new ArrayList<>();

    // Lista de filas, donde cada fila es un Map<nombreColumna, valor>.
    // También es 'final' por la misma razón: el objeto se mantiene, aunque se modifique.
    private final List<Map<String, String>> rows = new ArrayList<>();


    // ---------------------------------------------------------
    //  MÉTODOS GETTER
    // ---------------------------------------------------------

    /**
     * Devuelve una vista de solo lectura (inmodificable) de las cabeceras.
     *  Esto protege la estructura interna de la clase, evitando que desde fuera
     * se puedan añadir o eliminar columnas accidentalmente.
     */
    public List<String> getHeaders() {
        return Collections.unmodifiableList(headers);
    }

    /**
     * Devuelve una vista inmodificable de las filas.
     * Cada fila es un mapa donde las claves son las cabeceras y los valores son los datos.
     */
    public List<Map<String, String>> getRows() {
        return Collections.unmodifiableList(rows);
    }


    // ---------------------------------------------------------
    //  MÉTODO PARA ESTABLECER LAS CABECERAS
    // ---------------------------------------------------------

    /**
     * Reemplaza las cabeceras actuales por una nueva lista.
     *
     * @param hdrs Lista con los nombres de las columnas del CSV (en orden).
     */
    public void setHeaders(List<String> hdrs) {
        headers.clear();      // Eliminamos cualquier cabecera previa.
        headers.addAll(hdrs); // Añadimos todas las nuevas cabeceras.
    }


    // ---------------------------------------------------------
    //  MÉTODO PARA AÑADIR UNA NUEVA FILA
    // ---------------------------------------------------------

    /**
     * Añade una fila de datos a la tabla.
     * Cada valor se asocia con la cabecera correspondiente por su posición.
     *
     * Ejemplo:
     *   headers = ["id", "nombre", "email"]
     *   valores = ["1", "Ana", "ana@correo.com"]
     *
     * Resultado → fila guardada como:
     *   { "id"="1", "nombre"="Ana", "email"="ana@correo.com" }
     *
     * Si hay menos valores que cabeceras, los campos faltantes se dejan vacíos ("").
     */
    public void addRow(List<String> valores) {

        // Creamos una fila vacía (mapa ordenado) donde clave = cabecera, valor = dato.
        // Se usa LinkedHashMap para mantener el orden de las columnas.
        Map<String, String> row = new LinkedHashMap<>();

        // Guardamos el número de cabeceras para iterar.
        int n = headers.size();

        // Recorremos cada columna esperada según la lista de cabeceras.
        for (int i = 0; i < n; i++) {

            // Obtenemos la cabecera actual (nombre de la columna).
            String key = headers.get(i);

            // Si hay un valor correspondiente en la lista, lo usamos.
            // Si no (lista de valores más corta), se guarda una cadena vacía.
            String value = (i < valores.size()) ? valores.get(i) : "";

            // Insertamos el par clave-valor en la fila.
            row.put(key, value);
        }

        // Finalmente, añadimos la fila completa a la lista de filas de la tabla.
        rows.add(row);
    }


    // ---------------------------------------------------------
    //  MÉTODO AUXILIAR
    // ---------------------------------------------------------

    /**
     * Devuelve el número total de filas almacenadas en la tabla.
     * @return número de registros (sin contar la cabecera).
     */
    public int size() {
        return rows.size();
    }

}
