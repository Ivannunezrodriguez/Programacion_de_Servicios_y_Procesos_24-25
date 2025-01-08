import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class HiloVideoclub implements Runnable {
    private static List<Pelicula> peliculas = cargarPeliculas();
    private static int contadorClientes = 0;
    private Socket enchufeAlCliente;
    private Thread hilo;

    public HiloVideoclub(Socket cliente) {
        this.enchufeAlCliente = cliente;
        contadorClientes++;
        hilo = new Thread(this, "Cliente-" + contadorClientes);
        hilo.start();
    }

    private static List<Pelicula> cargarPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        try (Reader reader = new FileReader("src/main/resources/peliculas.json")) {

            Gson gson = new Gson();
            Pelicula[] arrayPeliculas = gson.fromJson(reader, Pelicula[].class);
            peliculas = Arrays.asList(arrayPeliculas);
        } catch (IOException e) {
            System.out.println("Error al cargar las películas: " + e.getMessage());
        }
        return peliculas;
    }


    private static void modificarPelicula(int id, String campo, String nuevoValor) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {
                if (campo.equalsIgnoreCase("rating")) {
                    pelicula.setRating(Double.parseDouble(nuevoValor));
                } else if (campo.equalsIgnoreCase("comentarios")) {
                    pelicula.setComentarios(nuevoValor);
                } else {
                    System.out.println("Campo no válido: " + campo);
                }
                break;
            }
        }
    }

    @Override
    public void run() {
        try {
            InputStream entrada = enchufeAlCliente.getInputStream();
            OutputStream salida = enchufeAlCliente.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));
            PrintWriter writer = new PrintWriter(salida, true);

            String comando;
            writer.println("Bienvenido al videoclub. Envía un comando (CONSULTA, MODIFICAR, FIN):");

            while ((comando = reader.readLine()) != null) {
                if (comando.equalsIgnoreCase("FIN")) {
                    writer.println("Gracias por usar el videoclub. ¡Hasta pronto!");
                    break;
                } else if (comando.equalsIgnoreCase("CONSULTA")) {
                    writer.println("Catálogo de películas:");
                    for (Pelicula pelicula : peliculas) {
                        writer.println(pelicula);
                    }
                } else if (comando.startsWith("MODIFICAR")) {
                    String[] partes = comando.split(" ", 3);
                    if (partes.length >= 3) {
                        try {
                            int id = Integer.parseInt(partes[1]);
                            String campo = partes[2];
                            writer.println("Introduce el nuevo valor para " + campo + ":");
                            String nuevoValor = reader.readLine();
                            modificarPelicula(id, campo, nuevoValor);
                            writer.println("Película actualizada correctamente.");
                        } catch (NumberFormatException e) {
                            writer.println("Error: ID inválido.");
                        }
                    } else {
                        writer.println("Error: Comando MODIFICAR incorrecto.");
                    }
                } else {
                    writer.println("Comando no reconocido. Usa CONSULTA, MODIFICAR o FIN.");
                }
            }

            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (IOException e) {
            System.out.println("Error con cliente: " + e.getMessage());
        }
    }
}
