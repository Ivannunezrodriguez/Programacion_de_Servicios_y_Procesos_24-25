import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ClienteVideoclub {
    public static void main(String[] args) {
        try (Socket cliente = new Socket("localhost", 2001)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter writer = new PrintWriter(cliente.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String respuesta;
            while ((respuesta = reader.readLine()) != null) {
                System.out.println(respuesta);
                String comando = scanner.nextLine();
                writer.println(comando);

                if (comando.equalsIgnoreCase("CONSULTA") || comando.equalsIgnoreCase("MODIFICAR")) {
                    respuesta = reader.readLine();
                    System.out.println(respuesta);
                    String parametro = scanner.nextLine();
                    writer.println(parametro);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
