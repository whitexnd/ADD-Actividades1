package es.cifpcarlos3.actividad1_3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciclo {
    private String codigo;
    private String descripcion;
    private int numeroHoras;
    private String familiaProfesional;
}
