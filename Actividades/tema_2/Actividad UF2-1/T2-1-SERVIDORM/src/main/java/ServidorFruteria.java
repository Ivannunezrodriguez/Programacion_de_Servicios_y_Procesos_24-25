import java.io.*;
import java.net.*;
import java.util.TreeMap;

public class ServidorFruteria {
    private static final int PUERTO = 2001;
    private static final String IP = "localhost";


    public static void main(String[] args) {
        System.out.println("APLICACIÓN DE SERVIDOR MULTITAREA");
        System.out.println("----------------------------------");
        cargarProductos();
        try (ServerSocket servidor = new ServerSocket()) {
            InetSocketAddress direccion = new InetSocketAddress(IP, PUERTO);
            servidor.bind(direccion);
            System.out.println("Servidor listo para aceptar solicitudes");
            System.out.println("Dirección IP: " + direccion.getAddress());
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Comunicación entrante");
                new HiloCliente(cliente).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cargarProductos() {
        HiloCliente.productos.put("PL", new Fruta("Peras limoneras", 14, 5f));
        HiloCliente.productos.put("PC", new Fruta("Peras conferencia", 12, 7f));
        HiloCliente.productos.put("PN", new Fruta("Plátano canario", 5, 2.5f));
        HiloCliente.productos.put("BN", new Fruta("Bananas", 7, 1.3f));
        HiloCliente.productos.put("TP", new Fruta("Tomates tipo pera", 8, 1.7f));
        HiloCliente.productos.put("TR", new Fruta("Tomates Raf", 7, 5.3f));
        HiloCliente.productos.put("UN", new Fruta("Uvas negras", 8, 3.2f));
        HiloCliente.productos.put("UB", new Fruta("Uvas blancas", 5, 2.7f));
        HiloCliente.productos.put("PT", new Fruta("Picotas", 8, 4.3f));
        HiloCliente.productos.put("CR", new Fruta("Ciruelas rojas", 10, 2.8f));
        HiloCliente.productos.put("MR", new Fruta("Melocotones rojos", 3, 2.5f));
        HiloCliente.productos.put("MA", new Fruta("Melocotones amarillos", 4, 3.2f));
    }


}
