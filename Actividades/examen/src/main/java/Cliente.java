import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String SERVIDOR = "127.0.0.1";
        final int PUERTO = 6665;

        try (Socket socket = new Socket(SERVIDOR, PUERTO)) {
            System.out.println("Conectado al servidor en " + SERVIDOR + ":" + PUERTO);

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Seleccione una opción:");
            System.out.println("1. Kilos de Naranjas en el stock");
            System.out.println("2. Kilos de Manzanas en el stock");
            System.out.print("Ingrese su opción: ");

            String opcion = scanner.nextLine();
            writer.println(opcion);

            String respuesta = reader.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}
