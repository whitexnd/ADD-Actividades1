package es.cifpcarlos3.actividad1_2;

import java.io.*;

/**
 * Clase principal que procesa frases desde un archivo de entrada
 * y genera archivos con las frases en mayúsculas y minúsculas.
 */
public class Main {
    public static void main(String[] args) {
        File ficheroEntrada = new File("src/main/java/es/cifpcarlos3/actividad1_2/frases.txt");
        File carpetaSalida = new File("src/main/java/es/cifpcarlos3/actividad1_2/procesados");

        if (!carpetaSalida.exists()) {
            carpetaSalida.mkdir();
        }

        File ficheroSalida = new File("src/main/java/es/cifpcarlos3/actividad1_2/procesados/frases_filtradas.txt");
        if (!ficheroSalida.exists()) {
            try {
                ficheroSalida.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de salida: " + e.getMessage());
                return;
            }
        }

        try(
                BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada));
                BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))
        ){
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                String[] partes = linea.split("\\|");
                if (partes.length != 3) continue;
                if(linea.startsWith("2")){
                    String codigo = partes[0].trim();
                    String frase = partes[1].trim();
                    String autor = partes[2].trim();

                    bw.write("\""+frase+"\""+autor+"\n");
                }
            }
            br.close();
            bw.close();
        } catch ( Exception e){
            System.err.println("Error al leer el archivo de salida: " + e.getMessage());
        }

        if(ficheroSalida.exists()){
            if(ficheroEntrada.exists()){
                ficheroEntrada.delete();
            }
        }
    }
}

