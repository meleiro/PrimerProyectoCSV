package csv;

import java.util.List;
import java.util.Map;

public class CsvPrinter {

    private CsvPrinter() {}

    public static void print(CsvTable table){

        //1. obtener cabeceras y filas
        List<String> headers = table.getHeaders();
        List<Map<String, String>> rows = table.getRows();


        int[] widths = new int[headers.size()];

        for (int i = 0;  i < headers.size(); i++){
            widths[i] = headers.get(i).length();
        }

        for (Map<String, String> row : rows){

            for (int i = 0; i < headers.size(); i++){

                String val = row.get(headers.get(i));

                if (val != null){
                    widths[i] = Math.max( widths[i], val.length());
                }
            }

        }

        for (int i = 0; i < headers.size(); i++){
            System.out.print(rellenarPavo(headers.get(i), widths[i] + 2));

        }


        System.out.println();

        for (Map<String, String> row : rows){

            for (int i = 0; i < headers.size(); i++){

                String val = row.get(headers.get(i));
                System.out.print(rellenarPavo(val, widths[i] + 2));

            }

            System.out.println();

        }







    }

    private static String rellenarPavo(String texto, int ancho){
        if (texto.length() >= ancho){
            return texto;
        }

        return texto + " ".repeat(ancho - texto.length());

    }


}
