package es.cifpcarlos3.actividad2_4.dao.impl;

import es.cifpcarlos3.actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.actividad2_4.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;

public class ClienteDAOImpl implements ClienteDAO {
        @Override
        public void listarClientes() {
            String sql = "SELECT * FROM t_cliente;";
            try(var conn = DatabaseConnection.getConnection();
                var pstm = conn.prepareStatement(sql);
                var rs   = pstm.executeQuery()
            ) {
                int totalClientes = 0;
                System.out.println("ID   DNI         NOMBRE        TELEFONO    EMAIL");
                System.out.println("---- ----------- ------------- ----------- -------------");
                while (rs.next()){
                    System.out.printf("%-4d %-12s %-12s %-9s %-17s %n",
                            rs.getInt("id_cliente"),
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                    totalClientes++;
                }
                System.out.println("");
                System.out.println("("+totalClientes+" clientes)");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void insertarCliente(String dni, String nombre, String telefono, String email) {
            String sql1 = "INSERT INTO t_cliente (dni, nombre, telefono, email) VALUES (?, ?, ?, ?);";
            String sql2 = "SELECT dni FROM t_cliente WHERE dni = ?;";
            try(var conn = DatabaseConnection.getConnection();
                var pstm = conn.prepareStatement(sql1);
                var pstm2 = conn.prepareStatement(sql2);
            ) {
                pstm2.setString(1, dni);
                var rs = pstm2.executeQuery();
                if (rs.next()) {
                    System.out.println("No se pudo insertar el cliente: DNI duplicado.");
                } else {
                    pstm.setString(1, dni);
                    pstm.setString(2, nombre);
                    pstm.setString(3, telefono);
                    pstm.setString(4, email);
                    int filas = pstm.executeUpdate();
                    System.out.println("Cliente insertado correctamente. Filas afectadas: " + filas);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void eliminarCliente(int idCliente) {
            String sql1 = "SELECT id_cuenta FROM t_cuenta WHERE id_cliente = ?;";
            String sql2 = "DELETE FROM t_cliente WHERE id_cliente = ?;";
            try(var conn = DatabaseConnection.getConnection();
                var pstm1 = conn.prepareStatement(sql1);
                var pstm2 = conn.prepareStatement(sql2);
            ) {
                pstm1.setInt(1, idCliente);
                var rs = pstm1.executeQuery();
                if (rs.next()) {
                    System.out.println("No se puede eliminar: el cliente tiene cuentas asociadas.");
                } else {
                    pstm2.setInt(1, idCliente);
                    int filas = pstm2.executeUpdate();
                    System.out.println("Cliente eliminado. Filas afectadas: " + filas);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
