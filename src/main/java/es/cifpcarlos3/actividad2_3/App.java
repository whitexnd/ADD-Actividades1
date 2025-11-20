package es.cifpcarlos3.actividad2_3;

import es.cifpcarlos3.actividad2_3.dao.impl.ContinenteDAOMariaDB;
import es.cifpcarlos3.actividad2_3.dao.impl.PaisDAOMariaDB;
import es.cifpcarlos3.actividad2_3.model.Continente;
import es.cifpcarlos3.actividad2_3.model.Pais;

import java.util.Map;
import java.util.Scanner;

public class App {
    private static final PaisDAOMariaDB paisDAO = new PaisDAOMariaDB();
    private static final ContinenteDAOMariaDB continenteDAO = new ContinenteDAOMariaDB();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige opción: \n");
        System.out.println("1) Listar países del continente América con capital que empiece por \"Sa\"  \n");
        System.out.println("2) Insertar nuevo continente \"Antártida\" \n");
        System.out.println("3) Actualizar capital del país con id 107 -> \"Capital city\" \n");
        System.out.println("4) Eliminar continente con código \"06\"  \n");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                Map<Pais, Continente> output = continenteDAO.paisesAmericaSa();
                for (Map.Entry<Pais, Continente> entry : output.entrySet()) {
                    Pais pais = entry.getKey();
                    Continente continente = entry.getValue();
                    System.out.printf("%s capital de %s (%02d) pertenece al continente %s (%02d)%n \n",
                            pais.getCapital(),
                            pais.getNombre_pais(),
                            pais.getIdentificador(),
                            continente.getNombre_continente(),
                            continente.getCodigo());
                }
                break;
            case 2:
                    continenteDAO.insertarAntartida();
                break;
            case 3:
                    paisDAO.actualizarCapital();
                break;
            case 4:
                    continenteDAO.borrarCodigo();
                break;
            default:
                break;
        }
    }

}
