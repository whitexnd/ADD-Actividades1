package es.cifpcarlos3.actividad2_2.dao.impl;

import es.cifpcarlos3.actividad2_2.dao.ContinenteDAO;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ContinenteDAOMariaDB implements ContinenteDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/mapa_mundi";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void nPaisesPorContinente() {
        String sql = "select c.nombre_continente, count(*) as paises from t_continente c join t_pais p ON (c.codigo = p.cod_continente) group by c.nombre_continente;";
        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql))
        {
            System.out.println("Listando paises Europeos");
            while (rs.next()) {
                System.out.println("** Nombre continente: " + rs.getString("nombre_continente"));
                System.out.println("Cantidad de paises: " + rs.getString("paises"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paisesEuropa() {
        String sql = "select p.nombre_pais from t_continente as c join t_pais as p on (p.cod_continente = c.codigo) where codigo = '04';";
        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql))
        {
            System.out.println("Listando paises Europeos");
            while (rs.next()) {
                System.out.println("Nombre pais: " + rs.getString("nombre_pais"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
