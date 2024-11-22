import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LanzadorTriangulo {
    public static void main(String[] args) {
        try {
            // Crear classpath para ubicar archivos
            String classpath = System.getProperty("java.class.path");

            // Archivos de salida
            File archivo5 = new File("src/triangulo5.txt");
            File archivo7 = new File("src/triangulo7.txt");
            File archivo9 = new File("src/triangulo9.txt");

            // Escribir fecha de inicio en cada archivo
            LocalDateTime fechaInicio = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            añadirDatos(archivo5, "Fecha de inicio: " + fechaInicio.format(formato) + "\n\n");
            añadirDatos(archivo7, "Fecha de inicio: " + fechaInicio.format(formato) + "\n\n");
            añadirDatos(archivo9, "Fecha de inicio: " + fechaInicio.format(formato) + "\n\n");

            // Crear procesos con argumentos
            ProcessBuilder triangulo5 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "5");
            ProcessBuilder triangulo7 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "7");
            ProcessBuilder triangulo9 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "9");

            // Redirigir salidas
            triangulo5.redirectOutput(ProcessBuilder.Redirect.appendTo(archivo5));
            triangulo7.redirectOutput(ProcessBuilder.Redirect.appendTo(archivo7));
            triangulo9.redirectOutput(ProcessBuilder.Redirect.appendTo(archivo9));

            // Iniciar procesos
            Process proceso5 = triangulo5.start();
            Process proceso7 = triangulo7.start();
            Process proceso9 = triangulo9.start();

            // Esperar a que terminen
            proceso5.waitFor();
            proceso7.waitFor();
            proceso9.waitFor();

            // Escribir fecha de finalización en cada archivo
            LocalDateTime fechaFin = LocalDateTime.now();
            añadirDatos(archivo5, "\nFecha de finalización: " + fechaFin.format(formato) + "\n");
            añadirDatos(archivo7, "\nFecha de finalización: " + fechaFin.format(formato) + "\n");
            añadirDatos(archivo9, "\nFecha de finalización: " + fechaFin.format(formato) + "\n");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void añadirDatos(File file, String content) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(content);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + file.getName() + ": " + e.getMessage());
        }
    }
}
