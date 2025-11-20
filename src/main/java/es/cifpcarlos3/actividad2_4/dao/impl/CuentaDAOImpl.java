package es.cifpcarlos3.actividad2_4.dao.impl;

import es.cifpcarlos3.actividad2_4.dao.CuentaDAO;
import es.cifpcarlos3.actividad2_4.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class CuentaDAOImpl implements CuentaDAO {
    @Override
    public void listarCuentas() {
        String sql = "SELECT c.id_cuenta as \"id\", c.numero_cuenta as \"cuenta\", cl.nombre, c.saldo FROM `t_cuenta` c JOIN t_cliente cl ON (c.id_cliente = cl.id_cliente);";
        try(var conn = DatabaseConnection.getConnection();
            var pstm = conn.prepareStatement(sql);
            var rs   = pstm.executeQuery()
        ) {
            int totalCuentas = 0;
            System.out.println("ID   Nº CUENTA            TITULAR      SALDO");
            System.out.println("---- ------------------- ------------ ---------");
            while (rs.next()) {
                System.out.printf("%-4d %-19s %-12s %.2f%n",
                        rs.getInt("id"),
                        rs.getString("cuenta"),
                        rs.getString("nombre"),
                        rs.getDouble("saldo"));
                totalCuentas++;
            }
            System.out.println("");
            System.out.println("(" + totalCuentas + " cuentas)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertarCuenta(int id, String cuenta, BigDecimal saldo) {
        String sql1 = "INSERT INTO t_cuenta (id_cliente, numero_cuenta, saldo) VALUES (?, ?, ?);";
        String sql2 = "SELECT id_cliente FROM t_cliente WHERE id_cliente = ?;";
        try(var conn = DatabaseConnection.getConnection();
            var pstm = conn.prepareStatement(sql1);
            var pstm2 = conn.prepareStatement(sql2);
        ) {
            pstm2.setInt(1, id);
            var rs = pstm2.executeQuery();
            if (!rs.next()) {
                System.out.println("Error: el cliente "+ id +" no existe. No se creó la cuenta.");
            } else {
                pstm.setInt(1, id);
                pstm.setString(2, cuenta);
                pstm.setBigDecimal(3, saldo);
                int filas = pstm.executeUpdate();
                System.out.println("Cuenta insertada correctamente. Filas afectadas: " + filas);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizarSaldo(int id, BigDecimal saldo) {
        String sql1 = "UPDATE t_cuenta SET saldo = ? WHERE id_cuenta = ?;";
        String sql2 = "SELECT id_cuenta FROM t_cuenta WHERE id_cuenta = ?;";
        try(var conn = DatabaseConnection.getConnection();
            var pstm = conn.prepareStatement(sql1);
            var pstm2 = conn.prepareStatement(sql2);
        ) {
            pstm2.setInt(1, id);
            var rs = pstm2.executeQuery();
            if (!rs.next()) {
                System.out.println("No se actualizó ninguna cuenta (id no encontrado).");
            } else {
                pstm.setBigDecimal(1, saldo);
                pstm.setInt(2, id);
                int filas = pstm.executeUpdate();
                System.out.println("Saldo actualizado. Filas afectadas: " + filas);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transferirSaldo(int idOrigen, int idDestino, BigDecimal cantidad) {
        String sqlVerificar = "SELECT id_cuenta, saldo FROM t_cuenta WHERE id_cuenta IN (?, ?);";
        String sqlActualizar = "UPDATE t_cuenta SET saldo = ? WHERE id_cuenta = ?;";
        try(var conn = DatabaseConnection.getConnection();
            var pstmVerificar = conn.prepareStatement(sqlVerificar);
            var pstmActualizar = conn.prepareStatement(sqlActualizar);
        ) {
            conn.setAutoCommit(false);

            pstmVerificar.setInt(1, idOrigen);
            pstmVerificar.setInt(2, idDestino);
            var rs = pstmVerificar.executeQuery();

            BigDecimal saldoOrigen = null;
            BigDecimal saldoDestino = null;

            while (rs.next()) {
                int idCuenta = rs.getInt("id_cuenta");
                BigDecimal saldo = rs.getBigDecimal("saldo");
                if (idCuenta == idOrigen) {
                    saldoOrigen = saldo;
                } else if (idCuenta == idDestino) {
                    saldoDestino = saldo;
                }
            }

            if (saldoOrigen == null || saldoDestino == null || saldoOrigen.compareTo(cantidad) < 0) {
                System.out.println("Transferencia NO realizada (saldo insuficiente o cuenta inexistente)");
                conn.rollback();
                return;
            }

            BigDecimal nuevoSaldoOrigen = saldoOrigen.subtract(cantidad);
            BigDecimal nuevoSaldoDestino = saldoDestino.add(cantidad);

            pstmActualizar.setBigDecimal(1, nuevoSaldoOrigen);
            pstmActualizar.setInt(2, idOrigen);
            pstmActualizar.executeUpdate();

            pstmActualizar.setBigDecimal(1, nuevoSaldoDestino);
            pstmActualizar.setInt(2, idDestino);
            pstmActualizar.executeUpdate();

            conn.commit();
            System.out.println("Transferencia OK");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
