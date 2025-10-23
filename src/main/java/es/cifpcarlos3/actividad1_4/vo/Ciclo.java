package es.cifpcarlos3.actividad1_4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciclo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String descripcion;
    private int numeroHoras;
    private String familiaProfesional;
    private String grado;
}
