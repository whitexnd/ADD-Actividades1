package es.cifpcarlos3.actividad2_5.dao;

import java.math.BigDecimal;

public interface CuentaDAO {
    void listarCuentas(String dni, String password);
    void listarCuentasInseguro(String dni, String password);
    void eliminarCuenta(String dni, String password, int cuentaId);
}
