package es.cifpcarlos3.actividad2_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pais {

    private int cod_continente;

    @NonNull
    private int identificador;

    private String nombre_pais;
    private String capital;

}
