package es.cifpcarlos3.actividad1_4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grado implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoGrado;
    private String nombreGrado;
    private String categoria;
}
