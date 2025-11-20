package es.cifpcarlos3.actividad1_7.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "catalogo")
public class CatalogoCoches {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "coche")
    private List<Coche> coche;
}
