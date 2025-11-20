package es.cifpcarlos3.actividad2_5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    private int idCuenta;
    private String numeroCuenta;
    private int idCliente;
    private double saldo;
}