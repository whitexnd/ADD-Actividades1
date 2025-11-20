package es.cifpcarlos3.actividad2_5.dao.impl;

import es.cifpcarlos3.actividad2_5.dao.UsuarioDAO;
import es.cifpcarlos3.actividad2_5.util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void crearTablaUsuarios(){
        String sqlcomprobar = "SHOW TABLES LIKE 'T_USUARIO';";
        String sqlcrear = "CREATE TABLE IF NOT EXISTS T_USUARIO ( dni VARCHAR(9)   PRIMARY KEY, password VARCHAR(100) NOT NULL, CONSTRAINT fk_usuario_cliente FOREIGN KEY (dni) REFERENCES T_CLIENTE(dni) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        try(var conn = DatabaseConnection.getConnection();
            var pstm = conn.prepareStatement(sqlcomprobar);
            var pstm2 = conn.prepareStatement(sqlcrear)
        ) {
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                pstm2.executeUpdate();
            }
            System.out.println("Tabla T_USUARIO creada (o ya existía).");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void importarUsuariosDesdeClientes(){
        String sqlimportar = "INSERT INTO T_USUARIO (dni, password) SELECT dni, '1234' FROM T_CLIENTE WHERE dni NOT IN (SELECT dni FROM T_USUARIO);";
        try(var conn = DatabaseConnection.getConnection();
            var pstm = conn.prepareStatement(sqlimportar)
        ) {
            int filas = pstm.executeUpdate();
            System.out.println("Se han importado " + filas + " usuarios nuevos a T_USUARIO.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
