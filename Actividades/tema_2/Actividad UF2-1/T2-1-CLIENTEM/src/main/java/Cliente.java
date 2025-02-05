import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 2001;

    public static void main(String[] args) {
        System.out.println(" APLICACIÓN CLIENTE");
        System.out.println("-----------------------------------");
        Scanner lector = new Scanner(System.in);
        try (Socket cliente = new Socket()) {
            InetSocketAddress direccionServidor = new InetSocketAddress(SERVIDOR, PUERTO);
            System.out.println("Esperando a que el servidor acepte la conexión");
            cliente.connect(direccionServidor);
            System.out.println("Comunicación establecida con el servidor");
            InputStream entrada = cliente.getInputStream();
            OutputStream salida = cliente.getOutputStream();
            String codigo;
            System.out.println("Ingrese el codigo o FIN para salir");
            while (true) {
                System.out.print("Código de producto: ");
                codigo = lector.nextLine().toUpperCase();
                if (codigo.equalsIgnoreCase("FIN")){
                    System.out.println("---------Sesion finalizada---------"); break;}

                salida.write((codigo + "\n").getBytes());
                byte[] mensaje = new byte[100];
                entrada.read(mensaje);
                System.out.println("Servidor responde: " + new String(mensaje).trim());
            }
        } catch (IOException e) {
            System.out.println("Error de comunicación");
            System.out.println(e.getMessage());
        }
    }
}
