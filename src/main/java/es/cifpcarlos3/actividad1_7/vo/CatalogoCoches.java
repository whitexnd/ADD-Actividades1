package es.cifpcarlos3.actividad1_7.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoCoches {
    private List<Coche> Catalogo;
}
