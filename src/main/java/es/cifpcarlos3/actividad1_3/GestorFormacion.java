package es.cifpcarlos3.actividad1_3;

import es.cifpcarlos3.actividad1_3.vo.Ciclo;
import es.cifpcarlos3.actividad1_3.vo.FamiliaProfesional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorFormacion {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Uso: java GestorFormacion <codigo_familia>");
            return;
        }
        FamiliaProfesional f1 = cargarFamilia(args[0]);
        if(f1 == null){
            System.out.println("No se ha encontrado la familia profesional con codigo: " + args[0]);
            return;
        }
        List<Ciclo> ciclos = cargarCiclosPorFamilia(args[0]);
        System.out.println(f1);
        if(ciclos != null){
            for (Ciclo c1 : ciclos){
                System.out.println(c1);
            }
        }
    }

    public static FamiliaProfesional cargarFamilia(String codigo){
        File f = new File("src/main/java/es/cifpcarlos3/actividad1_3/familia_profesional.dat");
        ArrayList<String> linea = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))){
            String lineaTexto;
            while((lineaTexto = br.readLine()) != null){
                String[] partes = lineaTexto.split("=");
                if (partes[0].trim().equals(codigo)) {
                    return new FamiliaProfesional(partes[0], partes[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    public static List<Ciclo> cargarCiclosPorFamilia(String codigoFamilia){
        File f = new File("src/main/java/es/cifpcarlos3/actividad1_3/informacion_ciclos.txt");
        List<Ciclo> ciclos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))){
            String lineaTexto;
            while((lineaTexto = br.readLine()) != null){
                String[] partes = lineaTexto.replace("'", "").split(",");
                if(lineaTexto.contains(codigoFamilia)){
                    Ciclo c = new Ciclo(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3]);
                    ciclos.add(c);
                }
            }
            return ciclos;
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }
}
