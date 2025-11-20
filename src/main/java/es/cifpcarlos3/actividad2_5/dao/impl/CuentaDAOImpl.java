package es.cifpcarlos3.actividad2_5.dao.impl;

import es.cifpcarlos3.actividad2_5.dao.CuentaDAO;
import es.cifpcarlos3.actividad2_5.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.SQLException;

public class CuentaDAOImpl implements CuentaDAO {
    @Override
    public void listarCuentas(String dni, String password) {
        String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.saldo FROM T_CUENTA c JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente JOIN T_USUARIO u ON u.dni = cli.dni where u.dni = ? AND u.password = ?;";
        try(var conn = DatabaseConnection.getConnection();
            var pstm = conn.prepareStatement(sql);
        ) {
            pstm.setString(1, dni);
            pstm.setString(2, password);
            var rs   = pstm.executeQuery();

            System.out.printf("%-4s %-10s %-18s %-10s \n", "ID", "DNI", "Nº CUENTA", "SALDO");
            System.out.println("---- ---------- ------------------ ----------");
            int contador = 0;
            while (rs.next()) {
                int idCuenta = rs.getInt("id_cuenta");
                String numeroCuenta = rs.getString("numero_cuenta");
                BigDecimal saldo = rs.getBigDecimal("saldo");
                System.out.printf("%-4d %-10s %-18s %-10.2f%n", idCuenta, dni, numeroCuenta, saldo);
                contador++;
            }
            if(contador != 0){
                System.out.println("(" + contador + " cuentas)");
            } else {
                System.out.println("No se encontraron cuentas para ese usuario o las credenciales no son válidas.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listarCuentasInseguro(String dni, String password) {
        String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.saldo " +
                "FROM T_CUENTA c " +
                "JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente " +
                "JOIN T_USUARIO u ON u.dni = cli.dni " +
                "WHERE u.dni = '" + dni + "' " +
                "AND u.password = '" + password + "';";
        try(var conn = DatabaseConnection.getConnection();
            var stm = conn.createStatement();
            var rs   = stm.executeQuery(sql);
        ) {
            System.out.printf("%-4s %-10s %-18s %-10s \n", "ID", "DNI", "Nº CUENTA", "SALDO");
            System.out.println("---- ---------- ------------------ ----------");
            int contador = 0;
            while (rs.next()) {
                int idCuenta = rs.getInt("id_cuenta");
                String numeroCuenta = rs.getString("numero_cuenta");
                BigDecimal saldo = rs.getBigDecimal("saldo");
                System.out.printf("%-4d %-10s %-18s %-10.2f%n", idCuenta, dni, numeroCuenta, saldo);
                contador++;
            }
            if(contador != 0){
                System.out.println("(" + contador + " cuentas)");
            } else {
                System.out.println("No se encontraron cuentas para ese usuario o las credenciales no son válidas.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminarCuenta(String dni, String password, int cuentaId) {
        String sql = "DELETE c \n" +
                "FROM T_CUENTA c \n" +
                "JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente \n" +
                "JOIN T_USUARIO u ON u.dni = cli.dni \n" +
                "WHERE u.dni = ? AND u.password = ? AND c.id_cuenta = ?;";
        try(var conn = DatabaseConnection.getConnection();
            var pstm = conn.prepareStatement(sql);
        ) {
            pstm.setString(1, dni);
            pstm.setString(2, password);
            pstm.setInt(3, cuentaId);
            int filasAfectadas = pstm.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Cuenta eliminada correctamente. Filas afectadas: " + filasAfectadas);
            } else {
                System.out.println("No se eliminó ninguna cuenta (credenciales incorrectas o cuenta no pertenece a ese usuario).");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
