import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Principal {

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.out.println("Por favor, pasa los nombres de los alumnos como argumentos.");
            return;
        }

        // Archivos de salida para los exámenes
        PrintWriter salidaExamen1 = null;
        PrintWriter salidaExamen2 = null;

        try {
            salidaExamen1 = new PrintWriter(new FileWriter("src/salida/examen1.txt", true));
            salidaExamen2 = new PrintWriter(new FileWriter("src/salida/examen2.txt", true));

            BufferExamenes generador = new BufferExamenes();

            // Crear hilos de examinados con base en los argumentos
            for (String nombre : args) {
                if (nombre.equals("Pepe") || nombre.equals("Juan") || nombre.equals("Luis")) {
                    new ProductorExamenes(generador, salidaExamen1);
                    new Examinado(nombre, generador, salidaExamen1);
                } else if (nombre.equals("Rosa") || nombre.equals("Miguel") || nombre.equals("Pedro")) {
                    new ProductorExamenes(generador, salidaExamen2);
                    new Examinado(nombre, generador, salidaExamen2);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            if (salidaExamen1 != null) salidaExamen1.close();
            if (salidaExamen2 != null) salidaExamen2.close();
        }
    }
}
