import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Resultado {
    private static final String NOMBRE_ARCHIVO = "resultados_primos.txt";

    public static synchronized void guardarResultado(NumeroPrimo resultado) {
        try (FileWriter fw = new FileWriter(NOMBRE_ARCHIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            pw.println(timestamp + " -> " + resultado.toFileString());

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
