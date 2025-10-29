package app;

import csv.CsvTable;
import csv.CsvUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AppLeerCsv {

    // Definir la ruta
   private static final Path RUTA = Paths.get("data",  "clientesResumen.csv");


    public static void main(String[] args) {
        //asegurarse de que existe el directorio y el archivo
          try{
              Files.createDirectories(RUTA.getParent());
          } catch (IOException e){
              System.err.println("No se pudo asegurar : " + e.getMessage());
          }

          CsvTable table = new CsvTable();




        //apertura y lectura línea a línea

        try ( BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {

            String linea;

            linea = br.readLine();

            if (linea == null ){
                System.err.println("CSV VACÍO");
                return;
            }

            List<String> headers = CsvUtils.parseCSVLine(linea);

            while (( linea = br.readLine()) != null  ){

                List<String> campos = CsvUtils.parseCSVLine(linea);
                //table.addRow(campos)
            }

        } catch (NoSuchFileException e){

            System.err.println("No aparece el archivo: " + RUTA.toAbsolutePath());
        } catch (IOException e) {
             e.printStackTrace();
        }


        //Mostrar el contenido en una tabla






    }
}
