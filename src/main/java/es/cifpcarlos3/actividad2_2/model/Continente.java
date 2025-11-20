package es.cifpcarlos3.actividad2_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Continente {

    @NonNull
    private int codigo;

    private String nombre_continente;

}
