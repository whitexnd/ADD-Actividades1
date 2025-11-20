package es.cifpcarlos3.actividad1_6;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.cifpcarlos3.actividad1_6.vo.Cancion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCanciones {
    public static void main(String[] args) {
        Path dir_base = Path.of("src","main","java","es","cifpcarlos3","actividad1_6");
        Path txt = dir_base.resolve("canciones.txt");
        Path json = dir_base.resolve("canciones.json");

        List<Cancion> listaCanciones = new ArrayList<>();
        int lineasLeidas = 0;
        int validas = 0;
        int invalidas = 0;
        try (var br = Files.newBufferedReader(txt, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineasLeidas++;
                String[] partes = linea.split(",");
                if (partes.length == 5){
                    validas++;
                    try {
                        Cancion c = new Cancion(
                                Integer.parseInt(partes[0].trim()),
                                partes[1].trim(),
                                partes[2].trim(),
                                partes[3].trim(),
                                Boolean.parseBoolean(partes[4].trim().toLowerCase())
                        );
                        listaCanciones.add(c);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        System.err.println("Línea " + lineasLeidas + " ignorada por formato incorrecto: '" + linea + "' -> " + ex.getMessage());
                        invalidas++;
                    }
                } else {
                    invalidas++;
                }

            }
        } catch ( IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        var mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(json.toFile(), listaCanciones);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }

        System.out.println("Leidas: " + lineasLeidas + " | Válidas: " + validas + " | Ignoradas: " + invalidas);
        System.out.println("JSON generado en:");
        System.out.println(json);

    }
}
