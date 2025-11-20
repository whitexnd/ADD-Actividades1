package es.cifpcarlos3.actividad1_6.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {
    private int año;
    private String titulo;
    private String artista;
    private String duracion;
    private boolean esEspanol;

}
