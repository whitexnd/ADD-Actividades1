package es.cifpcarlos3.actividad2_4.dao;

import java.math.BigDecimal;

public interface CuentaDAO {
    void listarCuentas();
    void insertarCuenta(int id, String cuenta, BigDecimal saldo);
    void actualizarSaldo(int id, BigDecimal saldo);
    void transferirSaldo(int idOrigen, int idDestino, BigDecimal cantidad);
}
