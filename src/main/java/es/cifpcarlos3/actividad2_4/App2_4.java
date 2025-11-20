package es.cifpcarlos3.actividad2_4;

import es.cifpcarlos3.actividad2_4.dao.impl.ClienteDAOImpl;
import es.cifpcarlos3.actividad2_4.dao.impl.CuentaDAOImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class App2_4 {

    private final static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) {
        ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
        CuentaDAOImpl cuentaDAO = new CuentaDAOImpl();

        mostrarMenu();

        int opcion = leerInt("");

        do{
            switch(opcion) {
                case 1:
                    clienteDAO.listarClientes();
                    break;
                case 2:
                    cuentaDAO.listarCuentas();
                    break;
                case 3:
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    clienteDAO.insertarCliente(dni, nombre, telefono, email);
                    break;
                case 4:
                    int id = leerInt("ID del cliente: ");
                    System.out.print("Número de cuenta: ");
                    String cuenta = sc.nextLine();
                    BigDecimal saldo = leerBigDecimal("Saldo inicial: ");
                    cuentaDAO.insertarCuenta(id, cuenta, saldo);
                    break;
                case 5:
                    int idCuenta = leerInt("ID de la cuenta: ");
                    BigDecimal saldoCuenta = leerBigDecimal("Nuevo saldo: ");
                    cuentaDAO.actualizarSaldo(idCuenta, saldoCuenta);
                    break;
                case 6:
                    int idOrigen = leerInt("Cuenta origen (id): ");
                    int idDestino = leerInt("Cuenta destino (id): ");
                    BigDecimal cantidad = leerBigDecimal("Importe: ");
                    cuentaDAO.transferirSaldo(idOrigen, idDestino, cantidad);
                    break;
                case 7:
                    int idCliente = leerInt("ID del cliente a eliminar: ");
                    clienteDAO.eliminarCliente(idCliente);
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("Elige opción: \n");
        System.out.println("1) Listar todos los clientes \n");
        System.out.println("2) Listar todas las cuentas con su titular \n");
        System.out.println("3) Insertar nuevo cliente \n");
        System.out.println("4) Insertar nueva cuenta para un cliente \n");
        System.out.println("5) Actualizar saldo de una cuenta \n");
        System.out.println("6) Transferir saldo entre dos cuentas // CON TRANSACCIÓN \n");
        System.out.println("7) Eliminar cliente (si no tiene cuentas) \n");
        System.out.println("0) Salir \n");
        System.out.print("Opción: ");
    }

    private static int leerInt(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Introduce un número válido.");
            return leerInt(mensaje);
        }
    }

    private static BigDecimal leerBigDecimal(String mensaje) {
        System.out.print(mensaje);
        try{
            return new BigDecimal(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Introduce un número válido.");
            return leerBigDecimal(mensaje);
        }
    }
}
