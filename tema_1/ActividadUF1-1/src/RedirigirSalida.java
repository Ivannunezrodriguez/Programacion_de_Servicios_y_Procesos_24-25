import java.io.File;
import java.io.IOException;

public class RedirigirSalida {
    public static void main(String[] args) {
        try {
            // se crea processbuilder para el notepad
            ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
            //se crean archivos
            processBuilder.redirectOutput(new File("salida.txt"));

            processBuilder.redirectErrorStream(true);
            // se inicia el proceso
            Process process=processBuilder.start();
            // se espera a que el proceso termine o se cierre el bloc de notar
            process.waitFor();
            System.out.println("El proceso ha terminado. Revisa el archivo de salida txt");
        }catch (IOException|InterruptedException e){
            e.printStackTrace();
        }
    }
}
