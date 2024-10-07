import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LanzadorTriangulos {
    public static void main(String[] args) {
        // definir los tres números de filas para los triangulados
        int[] filas = {5, 7, 9};
        //obtener el classpath actual para qué los procesos puedan encontrar la clase triángula
        String classpath = System.getProperty("java.class.path");
//lanzar tres procesos simultáneamente
        for (int numero : filas) {
            try {
                Timestamp timestamp=new Timestamp(System.currentTimeMillis());

                ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", classpath, "Triangulo",timestamp.toString(), String.valueOf(numero));
                processBuilder.redirectOutput(new java.io.File("triangulo" + numero + ".txt"));
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();

                System.out.println("Iniciado el proceso para el triángulo de " + numero + " filas");

            } catch (IOException e) {
                System.out.println("Error al iniciar el proceso para el triángulo de " + numero + " : " + e.getMessage());


            }
        }
    }
}

