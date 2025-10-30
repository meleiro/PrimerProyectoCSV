package csv;
//  La clase pertenece al paquete 'csv'.
// Esto permite agrupar todas las clases relacionadas con el manejo de archivos CSV
// (por ejemplo, CsvTable, CsvUtils, CsvPrinter...).

import java.util.List;
import java.util.Map;
// Se importan las clases necesarias para manejar listas (List) y mapas (Map).

/**
 * 💡 Clase que imprime por pantalla el contenido de una tabla CSV (CsvTable).
 *
 * Esta versión es más simple: imprime las columnas alineadas
 * pero sin bordes ni líneas separadoras (solo espacios).
 */
public class CsvPrinter {

    // -------------------------------------------------------
    //  CONSTRUCTOR PRIVADO
    // -------------------------------------------------------

    private CsvPrinter() {}
    //  Constructor privado para impedir crear instancias de esta clase.
    // CsvPrinter solo tiene métodos estáticos (de utilidad), así que no se necesita
    // crear objetos tipo "new CsvPrinter()".

    // -------------------------------------------------------
    //  MÉTODO PRINCIPAL: IMPRIMIR TABLA CSV
    // -------------------------------------------------------

    /**
     * Imprime la tabla CSV de manera alineada, con columnas ajustadas.
     *
     * @param table Objeto CsvTable que contiene las cabeceras y filas leídas de un CSV.
     */
    public static void print(CsvTable table) {

        //  OBTENER CABECERAS Y FILAS DE LA TABLA
        // -----------------------------------------
        // Obtenemos las listas del objeto CsvTable.
        List<String> headers = table.getHeaders();              // Lista de cabeceras (ej. ["id", "nombre", "email"])
        List<Map<String, String>> rows = table.getRows();       // Lista de filas, cada una como Map<columna, valor>

        //  CALCULAR EL ANCHO DE CADA COLUMNA
        // -----------------------------------------
        // Creamos un array de enteros donde guardaremos el ancho máximo de cada columna.
        int[] widths = new int[headers.size()];

        // Inicialmente, el ancho de cada columna es el largo de su cabecera.
        for (int i = 0; i < headers.size(); i++) {
            widths[i] = headers.get(i).length();
        }

        // Recorremos todas las filas para ver si algún valor es más largo que la cabecera.
        for (Map<String, String> row : rows) {

            // Recorremos cada columna dentro de la fila
            for (int i = 0; i < headers.size(); i++) {

                // Obtenemos el valor de esa columna según el nombre de cabecera
                String val = row.get(headers.get(i));

                // Si el valor no es nulo, comprobamos si su longitud es mayor que la actual
                if (val != null) {
                    // Math.max() se queda con el número mayor entre el ancho actual y el nuevo valor
                    widths[i] = Math.max(widths[i], val.length());
                }
            }
        }

        //  IMPRIMIR LAS CABECERAS
        // -----------------------------------------
        // Recorremos las cabeceras y las imprimimos con el ancho calculado.
        // Se usan espacios de relleno para que queden alineadas.
        for (int i = 0; i < headers.size(); i++) {
            System.out.print(rellenarPavo(headers.get(i), widths[i] + 2));
            // "+ 2" añade margen extra.
        }

        // Salto de línea tras imprimir todas las cabeceras
        System.out.println();

        //  IMPRIMIR TODAS LAS FILAS DE DATOS
        // -----------------------------------------
        for (Map<String, String> row : rows) {

            // Para cada columna de la fila
            for (int i = 0; i < headers.size(); i++) {

                // Obtenemos el valor correspondiente a la cabecera
                String val = row.get(headers.get(i));

                // Lo imprimimos alineado con la función rellenarPavo()
                System.out.print(rellenarPavo(val, widths[i] + 2));
            }

            // Salto de línea después de cada fila
            System.out.println();
        }

        //  Fin del método: la tabla ya se ha mostrado por consola.
    }

    // -------------------------------------------------------
    //  MÉTODO AUXILIAR PARA RELLENAR CON ESPACIOS
    // -------------------------------------------------------

    /**
     * Rellena un texto con espacios a la derecha hasta alcanzar el ancho indicado.
     *
     * @param texto Texto original (ej. "Ana")
     * @param ancho Longitud total que debe ocupar el texto (ej. 10)
     * @return Texto con espacios añadidos al final (ej. "Ana       ")
     */
    private static String rellenarPavo(String texto, int ancho) {

        // Si el texto ya tiene el tamaño igual o mayor al ancho pedido,
        // lo devolvemos tal cual (no necesita relleno).
        if (texto.length() >= ancho) {
            return texto;
        }

        // En caso contrario, añadimos los espacios necesarios a la derecha.
        // " ".repeat(n) crea una cadena de n espacios.
        return texto + " ".repeat(ancho - texto.length());
    }
}
