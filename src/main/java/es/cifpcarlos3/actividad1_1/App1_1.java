package es.cifpcarlos3.actividad1_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class App1_1 {
    public static void main(String[] args) {
        File f = new File("src/main/java/es/cifpcarlos3/actividad1_1/informacion_ciclos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(f))){
            for (String line; (line = br.readLine()) != null;) {
                if(!line.startsWith("#")  && !line.trim().isEmpty()){
                    ArrayList<String> curso = new ArrayList<>(Arrays.asList(line.split(",")));
                    if(curso.size() > 3){
                        System.out.printf("INSERT INTO T_CICLO VALUES (%s, %s, %s, %s);%n", curso.get(0).trim(), curso.get(1).trim(), curso.get(2).trim(), curso.get(3).trim());
                    }
                };
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}