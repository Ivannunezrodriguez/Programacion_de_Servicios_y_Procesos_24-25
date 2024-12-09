import java.io.IOException;
import java.util.Arrays;

public class LanzadorHolaMundo {
    public static void main(String[] args) {

        String classpath = System.getProperty("java.class.path");

        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", classpath, "HolaMundo", "987"
        );

        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
