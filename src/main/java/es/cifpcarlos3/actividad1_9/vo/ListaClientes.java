package es.cifpcarlos3.actividad1_9.vo;


import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("clientes")
public class ListaClientes {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "cliente")
    private List<Cliente> clientes;
}
