import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBiblioteca {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Socket cliente = new Socket(HOST, PUERTO);
             ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            String opcion;
            do {
                System.out.println("\n--- Menú de la Biblioteca ---");
                System.out.println("1. Consultar libro por ISBN");
                System.out.println("2. Consultar libro por título");
                System.out.println("3. Consultar libros por autor");
                System.out.println("4. Añadir libro");
                System.out.println("5. Salir de la aplicación");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextLine();
                salida.writeObject(opcion);

                switch (opcion) {
                    case "1":
                        System.out.print("Introduzca el ISBN: ");
                        String isbn = scanner.nextLine();
                        salida.writeObject(isbn);
                        System.out.println("Respuesta: " + entrada.readObject());
                        break;
                    case "2":
                        System.out.print("Introduzca el título: ");
                        String titulo = scanner.nextLine();
                        salida.writeObject(titulo);
                        System.out.println("Respuesta: " + entrada.readObject());
                        break;
                    case "3":
                        System.out.print("Introduzca el autor: ");
                        String autor = scanner.nextLine();
                        salida.writeObject(autor);
                        System.out.println("Respuesta: " + entrada.readObject());
                        break;
                    case "4":
                        System.out.print("ISBN: ");
                        String nuevoISBN = scanner.nextLine();
                        System.out.print("Título: ");
                        String nuevoTitulo = scanner.nextLine();
                        System.out.print("Autor: ");
                        String nuevoAutor = scanner.nextLine();
                        salida.writeObject(new Libro(nuevoISBN, nuevoTitulo, nuevoAutor));
                        System.out.println("Respuesta: " + entrada.readObject());
                        break;
                    case "5":
                        System.out.println("Cerrando la sesión...");
                        System.out.println("Respuesta del servidor: " + entrada.readObject());
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (!opcion.equals("5"));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
