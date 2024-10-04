import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // se crea un proceso de tipo processbuilder
        ProcessBuilder proceso ;
        // se le introcuce una ruta
        proceso = new ProcessBuilder("c:/windows/System32/calc.exe");

        try {
            //se crea un process de tipo process para adminitrar proceso del sistema
            Process process= proceso.start();
            //se inicia
            proceso.start();
            System.out.println("Proceso lanzado con exito");
            //sirve para esperar a que termine el proceso
             process.waitFor();
             System.out.println("proceso terminado");
             //se crean excepciones
        } catch (IOException | InterruptedException e) {
            // se pide que genere mensajes de erro propios de la excepcion
            System.out.println(e.getMessage());
        }
    }
}