package csv;  // Indica que esta clase pertenece al paquete 'csv'

import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    // Constructor privado para evitar que se creen instancias de esta clase,
    // ya que solo contiene métodos estáticos de utilidad.
    private CsvUtils() { }

    // Ejemplo
    // Línea CSV de entrada:
    // Ana, "Petardos,  ""SL"" ", "2300"
    //
    // Resultado esperado tras parsear:
    // ["Ana", "Petardos,  SL ", "2300"]

    /**
     * Método que recibe una línea CSV y devuelve sus campos como una lista.
     * Soporta:
     * - Comas como separadores de campo.
     * - Campos que van entre comillas dobles.
     * - Comillas escapadas dentro del campo usando doble comilla ("").
     *
     * @param line Línea CSV completa (ej. "Ana, \"Petardos, SL\", 2300")
     * @return Lista de campos individuales sin las comillas envolventes.
     */
    public static List<String> parseCSVLine (String line) {

        // Lista donde guardaremos los campos (cada columna del CSV).
        List<String> out = new ArrayList<>();

        // Acumulador para ir construyendo el campo actual.
        StringBuilder sb = new StringBuilder();

        // Bandera para saber si estamos dentro de comillas.
        boolean enComillas = false;

        // Recorremos la línea carácter por carácter.
        for (int i = 0; i < line.length(); i++) {

            // Obtenemos el carácter actual.
            char ch = line.charAt(i);

            // 1. Si el carácter es una comilla doble:
            if (ch == '"') {

                // Caso A: ya estamos dentro de comillas y la siguiente comilla es otra comilla (doble comilla):
                // Esto indica una comilla escapada: "" → se añade una sola comilla real al texto.
                if (enComillas && i + 1 < line.length() && line.charAt(i+1) == '"') {
                    sb.append('"'); // añadimos una comilla literal al campo
                    i++;            // saltamos la segunda comilla
                }
                // Caso B: no es comilla escapada → alternamos el estado "dentro o fuera de comillas".
                else {
                    enComillas = !enComillas;
                }
            }

            // 2. Si es una coma y NO estamos dentro de comillas, significa que termina un campo.
            else if (ch == ',' && !enComillas) {
                // Añadimos el campo acumulado a la lista de salida
                out.add(sb.toString());
                // Vaciamos el acumulador para el siguiente campo
                sb.setLength(0);
            }

            // 3. Para cualquier otro carácter, lo agregamos al campo actual
            else {
                sb.append(ch);
            }
        }

        // Al finalizar el bucle, añadimos el último campo que haya quedado en sb.
        // Esto es necesario porque el último campo no termina en coma.
        out.add(sb.toString());

        // Devolvemos la lista con los campos parseados
        return out;
    }

}
