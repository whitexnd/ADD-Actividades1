package es.cifpcarlos3.actividad1_4;

import es.cifpcarlos3.actividad1_4.vo.Ciclo;
import es.cifpcarlos3.actividad1_4.vo.FamiliaProfesional;
import es.cifpcarlos3.actividad1_4.vo.Grado;

import java.io.*;
import java.util.List;

public class GestorFormacion2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: GestorFormacion2 <codigo_familia> <codigo_grado>");
            return;
        }
        FamiliaProfesional fp = cargarFamilia(args[0]);
        Grado g1 = cargarGrado(args[1]);

        if(fp == null || g1 == null){
            System.out.println("No se ha encontrado la familia profesional o el grado con los codigos proporcionados.");
        }else{
            System.out.println("Familia Profesional: " + fp.getNombre() + " (" + fp.getCodigo() + ")");
            System.out.println("Grado seleccionado: " + g1.getNombreGrado());
            List<Ciclo> ciclos = cargarCiclo(args[0], g1.getCodigoGrado());
            System.out.println();
            System.out.println("Ciclos encontrados:");
            System.out.println();
            for (Ciclo c1 : ciclos) {
                System.out.println("El ciclo "+ c1.getDescripcion()+ " incluido en la familia de "+ fp.getNombre() +" es un grado "+ g1.getNombreGrado());
            }
            if(!ciclos.isEmpty()){
                serializarListaCiclos(ciclos);
                System.out.println();
                System.out.println("Lista serializada correctamente en lista_ciclos.ser");
            }

        }
    }

    public static FamiliaProfesional cargarFamilia(String codigo){
        File f = new File("src/main/java/es/cifpcarlos3/actividad1_4/familia_profesional.dat");
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] partes = linea.split("=");
                if(partes[0].trim().equals(codigo)){
                    return new FamiliaProfesional(partes[0], partes[1]);
                }
            }
        } catch (Exception e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    public static Grado cargarGrado(String codigoGrado){
        File f = new File("src/main/java/es/cifpcarlos3/actividad1_4/grados.csv");
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String linea;
            br.readLine();

            while((linea = br.readLine()) != null){
                String[] partes = linea.split("#");
                if(partes.length >= 3 && partes[0].trim().equals(codigoGrado)){
                    return new Grado(partes[0].trim(), partes[1].trim(), partes[2].trim());
                }
            }
        } catch (Exception e){
            System.out.println("Error al leer el archivo de grados: " + e.getMessage());
        }
        return null;
    }

    public static List<Ciclo> cargarCiclo(String codigoFamilia, String codigoGrado){
        File f = new File("src/main/java/es/cifpcarlos3/actividad1_4/informacion_ciclos.txt");
        List<Ciclo> ciclos = new java.util.ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String linea;
            while((linea = br.readLine()) != null){
                linea = linea.replace("'", "");
                String[] partes = linea.split(",");
                if(partes.length >= 4 && partes[3].trim().equals(codigoFamilia) && partes[4].trim().equals(codigoGrado)){
                    Ciclo c = new Ciclo(partes[0].trim(), partes[1].trim(), Integer.parseInt(partes[2].trim()), partes[3].trim(), codigoGrado);
                    ciclos.add(c);
                }
            }
        } catch (Exception e){
            System.out.println("Error al leer el archivo de ciclos: " + e.getMessage());
        }
        return ciclos;
    }

    public static void serializarListaCiclos(List<Ciclo> ciclos){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/es/cifpcarlos3/actividad1_4/lista_ciclos.ser"))){
            oos.writeObject(ciclos);
        } catch (Exception e){
            System.out.println("Error al serializar la lista de ciclos: " + e.getMessage());
        }
    }
}
