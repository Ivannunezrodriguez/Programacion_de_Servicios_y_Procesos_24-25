import java.io.File;
import java.io.IOException;


public class LanzadorTriangulo {
    public static void main(String[] args) {
        try {
            //se crea classpath para ubicar archivos
            String classpath = System.getProperty("java.class.path");
            //se crean procesos con argumento
            ProcessBuilder triangulo5 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", String.valueOf(5));
            ProcessBuilder triangulo7 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", String.valueOf(7));
            ProcessBuilder triangulo9 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", String.valueOf(9));
//se crean salidas
            triangulo5.redirectOutput(new File("src/triangulo5.txt"));
            triangulo7.redirectOutput(new File("src/triangulo7.txt"));
            triangulo9.redirectOutput(new File("src/triangulo9.txt"));
// se si inician procesos
            triangulo5.start();
            triangulo7.start();
            triangulo9.start();
            //se recoge exception
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




