import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorVideoclub {
    public static void main(String[] args) {
        System.out.println("SERVIDOR DEL VIDEOCLUB");
        try {
            ServerSocket servidor = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("localhost", 2001);
            servidor.bind(direccion);
            System.out.println("Servidor listo en: " + direccion.getAddress());

            while (true) {
                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Nueva conexión de cliente");
                new HiloVideoclub(enchufeAlCliente);
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
