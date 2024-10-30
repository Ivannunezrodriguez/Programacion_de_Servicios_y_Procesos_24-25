import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorBiblioteca {
    private static final int PUERTO = 12345;
    private static List<Libro> libros = new ArrayList<>();
    private static volatile boolean enEjecucion = true;

    public static void main(String[] args) {
        inicializarLibros();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (enEjecucion) {
                Socket cliente = servidor.accept();
                pool.execute(() -> gestionarCliente(cliente));
            }
            pool.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Servidor cerrado.");
    }

    private static void inicializarLibros() {
        libros.add(new Libro("1234567890", "El Principito", "Antoine de Saint-Exupéry"));
        libros.add(new Libro("0987654321", "1984", "George Orwell"));
        libros.add(new Libro("5432167890", "Fahrenheit 451", "Ray Bradbury"));
        libros.add(new Libro("6789054321", "La Odisea", "Homero"));
        libros.add(new Libro("1029384756", "Moby Dick", "Herman Melville"));
    }

    private static void gestionarCliente(Socket cliente) {
        try (ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
             ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream())) {

            String opcion;
            while ((opcion = (String) entrada.readObject()) != null) {
                switch (opcion) {
                    case "1":
                        String isbn = (String) entrada.readObject();
                        salida.writeObject(Libro.consultarPorISBN(libros, isbn));
                        break;
                    case "2":
                        String titulo = (String) entrada.readObject();
                        salida.writeObject(Libro.consultarPorTitulo(libros, titulo));
                        break;
                    case "3":
                        String autor = (String) entrada.readObject();
                        salida.writeObject(Libro.consultarPorAutor(libros, autor));
                        break;
                    case "4":
                        Libro nuevoLibro = (Libro) entrada.readObject();
                        synchronized (libros) {
                            libros.add(nuevoLibro);
                        }
                        salida.writeObject("Libro añadido con éxito.");
                        break;
                    case "5":
                        System.out.println("Cliente " + cliente.getInetAddress() + " ha cerrado la sesión.");
                        salida.writeObject("Sesión cerrada correctamente.");

                        return;
                    default:
                        salida.writeObject("Opción no válida.");
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
