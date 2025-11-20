package es.cifpcarlos3.actividad2_2;

import es.cifpcarlos3.actividad2_2.dao.impl.ContinenteDAOMariaDB;
import es.cifpcarlos3.actividad2_2.dao.impl.PaisDAOMariaDB;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private static final PaisDAOMariaDB paisDAO = new PaisDAOMariaDB();
    private static final ContinenteDAOMariaDB continenteDAO = new ContinenteDAOMariaDB();

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
                paisDAO.paisesSinCapital();
                break;
            case 2:
                continenteDAO.nPaisesPorContinente();
                break;
            case 3:
                continenteDAO.paisesEuropa();
                break;
            case 4:
                paisDAO.capitalesQueEmpiezanPorSan();
                break;
            default:
                break;
        }
    }

}
