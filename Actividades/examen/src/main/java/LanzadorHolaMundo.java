import java.io.File;
import java.io.IOException;

public class LanzadorHolaMundo {
    public static void main(String[] args) {

        String classpath = System.getProperty("java.class.path");

        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", classpath, "HolaMundo", "987"
        );

        processBuilder.redirectOutput(new File("C:/windows/salida.txt"));
        processBuilder.redirectError(new File("C:/windows/error.txt"));

        try {
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
