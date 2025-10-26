package es.cifpcarlos3.actividad1_7.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coche {
    private String marca;
    private String modelo;
    private String color;
    @JacksonXmlProperty(localName = "anio")
    private int anyo;
}
