package es.cifpcarlos3.actividad1_8.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Origen {
    @JsonProperty(required = true)
    private String pais;
    private String region;

}
