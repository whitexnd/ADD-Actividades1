package es.cifpcarlos3.actividad1_8.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {
    @JsonProperty(required = true)
    private String nombre;
    @JsonProperty(required = true)
    private String tipo;
    private Origen origen;
    @JsonProperty(required = true)
    private List<Ingrediente> ingredientes;
}