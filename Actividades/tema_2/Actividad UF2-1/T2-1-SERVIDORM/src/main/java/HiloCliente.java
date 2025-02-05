import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.TreeMap;

class HiloCliente extends Thread {
    private Socket socket;
    static TreeMap<String, Fruta> productos = new TreeMap<>();
    public HiloCliente(Socket socket) {
        this.socket = socket;
    }
    public static Fruta buscarProducto(String codigo) {
        return productos.get(codigo);
    }
    @Override
    public void run() {
        System.out.println("Estableciendo comunicación con " + this.getName());
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            String codigo;
            while ((codigo = entrada.readLine()) != null) {
                Fruta producto = buscarProducto(codigo);
                if (producto != null) {
                    salida.println("Producto encontrado: " + producto);
                } else {
                    salida.println("Producto no encontrado para código: " + codigo);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}