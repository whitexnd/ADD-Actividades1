package es.cifpcarlos3.actividad2_2.dao.impl;

import es.cifpcarlos3.actividad2_2.dao.PaisDAO;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PaisDAOMariaDB implements PaisDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/mapa_mundi";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void paisesSinCapital() {
        String sql = "select * from t_pais WHERE capital is null";
        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Codigo continente: " + rs.getInt("cod_continente"));
                System.out.println("Identificador: " + rs.getInt("identificador"));
                System.out.println("Nombre pais: " + rs.getString("nombre_pais"));
                System.out.println("Capital: " + rs.getString("capital"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void capitalesQueEmpiezanPorSan(){
        String sql = "select capital from t_pais WHERE capital like \"San%\";";
        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Capital: " + rs.getString("capital"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
