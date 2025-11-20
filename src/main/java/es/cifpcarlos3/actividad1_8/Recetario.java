package es.cifpcarlos3.actividad1_8;

import com.fasterxml.jackson.databind.json.JsonMapper;
import es.cifpcarlos3.actividad1_8.vo.Ingrediente;
import es.cifpcarlos3.actividad1_8.vo.Receta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Recetario {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_8");
        Path json = base.resolve("receta.json");

        var mapper = JsonMapper.builder().build();
        try (var reader = Files.newBufferedReader(json)) {
            Receta receta = mapper.readValue(reader, Receta.class);

            // Comprobar campos obligatorios
            if (receta.getNombre() == null) {
                throw new IllegalArgumentException("El campo 'nombre' es obligatorio");
            } else if (receta.getTipo() == null) {
                throw new IllegalArgumentException("El campo 'tipo' es obligatorio");
            } else if (receta.getOrigen() == null || receta.getOrigen().getPais() == null) {
                throw new IllegalArgumentException("El campo 'pais' es obligatorio");
            } else if (receta.getIngredientes().size() <= 1) {
                throw new IllegalArgumentException("La lista de ingredientes no es correcta, ademas deben contar con nombre y cantidad");
            }

            System.out.println("Receta: " + receta.getNombre() + " (tipo: " + receta.getTipo() + ")");
            System.out.println("Origen: " + receta.getOrigen().getPais() + " - " + receta.getOrigen().getRegion());
            System.out.println();
            System.out.println("Ingredientes (" + receta.getIngredientes().size() + "):");
            for (Ingrediente ingrediente : receta.getIngredientes()) {
                System.out.println("• "+ ingrediente.getNombre() + " - " + ingrediente.getCantidad());
            }
        } catch ( IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo JSON: " + ex.getMessage());
        }
    }
}
