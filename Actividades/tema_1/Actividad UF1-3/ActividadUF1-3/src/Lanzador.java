import java.io.File;
import java.io.IOException;

public class Lanzador {

    public static void main(String[] args) {
        try {
            // Lanzar el primer proceso para el primer grupo de alumnos
            ProcessBuilder proceso1 = new ProcessBuilder("java", "-cp", "src", "Principal", "Pepe", "Juan", "Luis");
            File salidaExamen1 = new File("src/salida/examen1.txt");
            proceso1.redirectOutput(salidaExamen1);
            Process p1 = proceso1.start();

            // Lanzar el segundo proceso para el segundo grupo de alumnos
            ProcessBuilder proceso2 = new ProcessBuilder("java", "-cp", "src", "Principal", "Rosa", "Miguel", "Pedro");
            File salidaExamen2 = new File("src/salida/examen2.txt");
            proceso2.redirectOutput(salidaExamen2);
            Process p2 = proceso2.start();

            // Esperar a que ambos procesos terminen
            p1.waitFor();
            p2.waitFor();

            System.out.println("Los exámenes se han generado correctamente.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
