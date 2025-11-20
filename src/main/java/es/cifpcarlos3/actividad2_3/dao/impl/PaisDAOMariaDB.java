package es.cifpcarlos3.actividad2_3.dao.impl;

import es.cifpcarlos3.actividad2_3.dao.PaisDAO;
import es.cifpcarlos3.actividad2_3.util.DatabaseConnection;

import java.sql.SQLException;

public class PaisDAOMariaDB implements PaisDAO {

    @Override
    public void actualizarCapital() {
        String sql = "UPDATE t_pais " +
                "SET capital = 'Capital city' " +
                "WHERE identificador = 107;";
        try (var conn = DatabaseConnection.getConnection();
             var pstm = conn.prepareStatement(sql))
        {
            var filas   = pstm.executeUpdate();
            System.out.println("Actualizando país id=107. Filas afectadas: " + filas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
