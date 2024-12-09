import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        final int KILOS_NARANJAS = 1250;
        final int KILOS_MANZANAS = 890;
        final int PUERTO = 6665;

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                try (Socket cliente = servidor.accept()) {
                    System.out.println("Cliente conectado: " + cliente.getInetAddress());

                    InputStream input = cliente.getInputStream();
                    OutputStream output = cliente.getOutputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    PrintWriter writer = new PrintWriter(output, true);

                    String mensajeCliente = reader.readLine();
                    System.out.println("Mensaje recibido del cliente: " + mensajeCliente);

                    String respuesta;
                    switch (mensajeCliente) {
                        case "1":
                            respuesta = "Kilos de Naranjas disponibles: " + KILOS_NARANJAS;
                            break;
                        case "2":
                            respuesta = "Kilos de Manzanas disponibles: " + KILOS_MANZANAS;
                            break;
                        default:
                            respuesta = "No se encuentra disponible esa opción en el menú.";
                            break;
                    }

                    writer.println(respuesta);
                    System.out.println("Respuesta enviada al cliente: " + respuesta);
                } catch (IOException e) {
                    System.out.println("Error al manejar la conexión con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
