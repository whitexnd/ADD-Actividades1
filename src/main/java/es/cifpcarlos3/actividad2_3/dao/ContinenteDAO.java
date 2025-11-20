package es.cifpcarlos3.actividad2_3.dao;

import es.cifpcarlos3.actividad2_3.model.Continente;
import es.cifpcarlos3.actividad2_3.model.Pais;

import java.util.Map;

public interface ContinenteDAO {
    Map<Pais, Continente> paisesAmericaSa();
    void insertarAntartida();
    void borrarCodigo();
}
