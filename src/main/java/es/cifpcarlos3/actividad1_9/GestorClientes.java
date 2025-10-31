package es.cifpcarlos3.actividad1_9;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import es.cifpcarlos3.actividad1_9.vo.Cliente;
import es.cifpcarlos3.actividad1_9.vo.ListaClientes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestorClientes {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_9");
        Path xml = base.resolve("clientes.xml");

        var xmlMapper = XmlMapper.builder()
                .build();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);

        try (var br = Files.newBufferedReader(xml, StandardCharsets.UTF_8)) {
            ListaClientes listaClientes = xmlMapper.readValue(br, ListaClientes.class);

            for (int i = 0; i < listaClientes.getClientes().size(); i++) {
                Cliente cliente = listaClientes.getClientes().get(i);

                try {
                    cliente.validar();
                    System.out.println("Cliente: " + cliente.getNombre() + " (id: " + cliente.getId() + ")");
                    System.out.println("Sucursales (" + cliente.getSucursales().size() + "):");

                    for(var sucursal : cliente.getSucursales()) {
                        if(sucursal.getProvincia() != null && !sucursal.getProvincia().isEmpty()) {
                            System.out.println("  • " + sucursal.getCalle() + ", " + sucursal.getCiudad() + " [" + sucursal.getProvincia() + "] - CP " + sucursal.getCp());
                        } else {
                            System.out.println("  • " + sucursal.getCalle() + ", " + sucursal.getCiudad() + " - CP " + sucursal.getCp());
                        }
                    }
                    System.out.println();
                } catch (IllegalArgumentException e) {
                    System.err.println("Error de validación: " + e.getMessage());
                }
            }

        } catch (MismatchedInputException e) {
            System.err.println("Error leyendo/deserializando:");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo XML: " + e.getMessage());
        }
    }
}
