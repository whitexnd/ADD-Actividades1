package es.cifpcarlos3.actividad2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private static final String URL = "jdbc:mariadb://localhost:3306/mapa_mundi";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige opción: \n");
        System.out.println("1) Listar países sin capital \n");
        System.out.println("2) Nº de países por continente \n");
        System.out.println("3) Países de Europa \n");
        System.out.println("4) Capitales que empiezan por “San” \n");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                paisesSinCapital();
                break;
            case 2:
                nPaisesPorContinente();
                break;
            case 3:
                paisesEuropa();
                break;
            case 4:
                capitalesQueEmpiezanPorSan();
                break;
            default:
                break;
        }
    }


    public static void paisesSinCapital() {
        String sql = "select * from t_pais WHERE capital is null";
        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Codigo continente: " + rs.getInt("cod_continente"));
                System.out.println("Identificador: " + rs.getInt("identificador"));
                System.out.println("Nombre pais: " + rs.getString("nombre_pais"));
                System.out.println("Capital: " + rs.getString("capital"));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public static void nPaisesPorContinente() {
        String sql = "select c.nombre_continente, count(*) as paises from t_continente c join t_pais p ON (c.codigo = p.cod_continente) group by c.nombre_continente;";
        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql))
        {
            System.out.println("Listando paises Europeos");
            while (rs.next()) {
                System.out.println("** Nombre continente: " + rs.getString("nombre_continente"));
                System.out.println("Cantidad de paises: " + rs.getString("paises"));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public static void paisesEuropa() {
        String sql = "select p.nombre_pais from t_continente as c join t_pais as p on (p.cod_continente = c.codigo) where codigo = '04';";
        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql))
        {
            System.out.println("Listando paises Europeos");
            while (rs.next()) {
                System.out.println("Nombre pais: " + rs.getString("nombre_pais"));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public static void capitalesQueEmpiezanPorSan(){
        String sql = "select capital from t_pais WHERE capital like \"San%\";";
        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs   = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Capital: " + rs.getString("capital"));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

}
