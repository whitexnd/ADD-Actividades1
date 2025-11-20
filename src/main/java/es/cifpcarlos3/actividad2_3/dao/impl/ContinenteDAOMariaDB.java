package es.cifpcarlos3.actividad2_3.dao.impl;

import es.cifpcarlos3.actividad2_3.dao.ContinenteDAO;
import es.cifpcarlos3.actividad2_3.model.Continente;
import es.cifpcarlos3.actividad2_3.model.Pais;
import es.cifpcarlos3.actividad2_3.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ContinenteDAOMariaDB implements ContinenteDAO {

    @Override
    public Map<Pais, Continente> paisesAmericaSa() {
        Map<Pais, Continente> resultado = new HashMap<>();
        String sql = "SELECT " +
                "p.identificador, " +
                "p.nombre_pais, " +
                "p.capital, " +
                "c.codigo " +
                "AS cod_continente, " +
                "c.nombre_continente AS nombre_continente " +
                "FROM t_pais p " +
                "JOIN t_continente c ON p.cod_continente = c.codigo " +
                "WHERE c.nombre_continente = 'América' " +
                "AND p.capital LIKE 'Sa%' " +
                "ORDER BY p.nombre_pais;";

        try (var conn = DatabaseConnection.getConnection();
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql))
        {
            System.out.println("Listando paises Europeos");
            while (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("cod_continente"),
                        rs.getInt("identificador"),
                        rs.getString("nombre_pais"),
                        rs.getString("capital")
                );
                Continente continente = new Continente(
                        rs.getInt("cod_continente"),
                        rs.getString("nombre_continente")
                );
                resultado.put(pais, continente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public void insertarAntartida() {

        String sql = "INSERT INTO t_continente (codigo, nombre_continente) VALUES ('06', 'Antártida');";
        try (var conn = DatabaseConnection.getConnection();
             var pstm = conn.prepareStatement(sql))
        {
            var filas   = pstm.executeUpdate();
            System.out.println("Insertado continente Antártida (06). Filas afectadas: " + filas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrarCodigo() {

        String sql1 = "DELETE FROM t_pais WHERE cod_continente = '06';";
        String sql2 = "DELETE FROM t_continente WHERE codigo = '06';";
        try (var conn = DatabaseConnection.getConnection();
             var pstm1 = conn.prepareStatement(sql1);
             var pstm2 = conn.prepareStatement(sql2)) {
            pstm1.executeUpdate();
            pstm2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
