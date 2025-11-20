package es.cifpcarlos3.actividad1_9.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {
    @JsonProperty(required = true)
    private String calle;

    @JsonProperty(required = true)
    private String ciudad;

    private String provincia;
    private int cp;

    public void validar() {
        if (calle == null || calle.trim().isEmpty()) {
            throw new IllegalArgumentException("La calle no puede estar vacía");
        }
        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad no puede estar vacía");
        }
    }
}
