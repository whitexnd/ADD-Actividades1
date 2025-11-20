package es.cifpcarlos3.actividad1_9.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @JacksonXmlProperty(isAttribute=true)
    @JsonProperty(required = true)
    private int id;

    @JsonProperty(required = true)
    private String nombre;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "sucursal")
    @JsonProperty(required = true)
    private List<Sucursal> sucursales;

    private String text;

    public void validar() {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser mayor que 0");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío (id: " + id + ")");
        }
        if (sucursales == null || sucursales.isEmpty()) {
            throw new IllegalArgumentException("El cliente debe tener al menos una sucursal (id: " + id + ", nombre: " + nombre + ")");
        }

        for (int i = 0; i < sucursales.size(); i++) {
            try {
                sucursales.get(i).validar();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Error en sucursal " + (i + 1) + " del cliente '" + nombre + "' (id: " + id + "): " + e.getMessage());
            }
        }
    }
}
