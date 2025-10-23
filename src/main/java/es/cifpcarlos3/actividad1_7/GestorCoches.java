package es.cifpcarlos3.actividad1_7;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import es.cifpcarlos3.actividad1_7.vo.CatalogoCoches;
import es.cifpcarlos3.actividad1_7.vo.Coche;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCoches {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_7");
        Path entrada = base.resolve("coches.txt");
        Path salida = base.resolve("coches.xml");
        List <Coche> listaCoches = new ArrayList<>();

        int lineasLeidas = 0;
        int validas = 0;
        int invalidas = 0;
        try(var br = Files.newBufferedReader(entrada)){
            String linea;
            while ((linea = br.readLine()) != null) {
                lineasLeidas++;
                // parsear y añadir a la lista
                String[] partes = linea.trim().split(",");
                if(partes.length == 4){
                    Coche c = new Coche(
                            partes[0],
                            partes[1],
                            partes[2],
                            Integer.parseInt(partes[3])
                    );
                    listaCoches.add(c);
                    validas++;
                } else {
                    invalidas++;
                }
            }
        } catch ( IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        CatalogoCoches catalogo = new CatalogoCoches(listaCoches);
        var mapper = new XmlMapper();
        var writer = mapper.writerWithDefaultPrettyPrinter();
        try {
            writer.writeValue(salida.toFile(), catalogo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Leidas: " + lineasLeidas + " | Válidas: " + validas + " | Ignoradas: " + invalidas);
        System.out.println("XML generado en:");
        System.out.println(salida.toAbsolutePath());
    }
}
