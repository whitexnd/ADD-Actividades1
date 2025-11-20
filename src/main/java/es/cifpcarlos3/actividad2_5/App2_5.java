package es.cifpcarlos3.actividad2_5;


import es.cifpcarlos3.actividad2_5.dao.impl.CuentaDAOImpl;
import es.cifpcarlos3.actividad2_5.dao.impl.UsuarioDAOImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class App2_5 {
    private final static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        CuentaDAOImpl cuentaDAO = new CuentaDAOImpl();

        int opcion = 0;
        do{
            mostrarMenu();
            opcion = leerInt("Opción: ");
            switch(opcion) {
                case 1:
                    usuarioDAO.crearTablaUsuarios();
                    break;
                case 2:
                    usuarioDAO.importarUsuariosDesdeClientes();
                    break;
                case 3:
                    String dni = leerLineaNoVacia("DNI: ");
                    String password = leerLineaNoVacia("Password: ");
                    cuentaDAO.listarCuentas(dni, password);
                    break;
                case 4:
                    String dniInseguro = leerLineaNoVacia("DNI: ");
                    String passwordInsegura = leerLineaNoVacia("Password: ");
                    cuentaDAO.listarCuentasInseguro(dniInseguro, passwordInsegura);
                    break;
                case 5:
                    String dniEliminar = leerLineaNoVacia("DNI: ");
                    String passwordEliminar = leerLineaNoVacia("Password: ");
                    int cuentaId = leerInt("ID de la cuenta a eliminar: ");
                    cuentaDAO.eliminarCuenta(dniEliminar, passwordEliminar, cuentaId);
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            System.out.println("--------------------------------");
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("Elige opción: \n");
        System.out.println("1) Crear tabla de usuarios \n");
        System.out.println("2) Importar usuarios a partir de clientes \n");
        System.out.println("3) Listar cuentas de un usuario con dni y password (SEGURO) \n");
        System.out.println("4) Listar cuentas de un usuario con dni y password (INSEGURO) \n");
        System.out.println("5) Eliminar cuenta de un usuario con dni, password e Id \n");
        System.out.println("0) Salir \n");
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

    private static String leerLineaNoVacia(String mensaje){
        System.out.print(mensaje);
        String linea = sc.nextLine();
        if(linea.isBlank()){
            System.out.println("La entrada no puede estar vacía.");
            return leerLineaNoVacia(mensaje);
        }
        return linea;
    }
}
