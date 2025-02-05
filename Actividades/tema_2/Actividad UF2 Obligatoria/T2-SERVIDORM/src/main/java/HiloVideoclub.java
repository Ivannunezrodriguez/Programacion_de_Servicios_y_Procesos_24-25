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
            peliculas = new ArrayList<>(Arrays.asList(arrayPeliculas));
        } catch (IOException e) {
            System.out.println("Error al cargar las películas: " + e.getMessage());
            e.printStackTrace();
        }
        return peliculas;
    }


    private static synchronized void modificarPelicula(int id, String campo, String nuevoValor, PrintWriter writer) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {


                switch (campo) {
                    case "rating":
                        try {
                            pelicula.setRating(Double.parseDouble(nuevoValor));
                        } catch (NumberFormatException e) {
                            writer.println("Error: Valor de rating inválido.");
                            return;
                        }
                        break;
                    case "comentarios":
                        pelicula.setComentarios(nuevoValor);
                        break;
                    default:
                        writer.println("Error: Campo no válido. Solo se permite modificar 'rating' o 'comentarios'.");
                        return;
                }

                writer.println("Estado modificado: " + pelicula);
                writer.println("Película actualizada correctamente.");
                return;
            }
        }
        writer.println("Error: No se encontró ninguna película con el ID proporcionado.");
    }

    @Override
    public void run() {
        try (
                InputStream entrada = enchufeAlCliente.getInputStream();
                OutputStream salida = enchufeAlCliente.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));
                PrintWriter writer = new PrintWriter(salida, true)
        ) {
            writer.println("Bienvenido al videoclub. Envía un comando (CONSULTA, MODIFICAR, FIN):");

            String comando;
            while ((comando = reader.readLine()) != null) {
                if (comando.equalsIgnoreCase("FIN")) {
                    writer.println("Gracias por usar el videoclub. ¡Hasta pronto!");
                    break;

                } else if (comando.equalsIgnoreCase("CONSULTA")) {
                    writer.println("Introduce el tipo de consulta (ID o TITULO):");
                    String tipoConsulta = reader.readLine();

                    if (tipoConsulta.equalsIgnoreCase("ID")) {
                        writer.println("Introduce el ID de la película:");
                        try {
                            int id = Integer.parseInt(reader.readLine());
                            List<Pelicula> encontradas = new ArrayList<>();
                            for (Pelicula p : peliculas) {
                                if (p.getId() == id) {
                                    encontradas.add(p);
                                }
                            }

                            if (!encontradas.isEmpty()) {
                                for (Pelicula p : encontradas) {
                                    writer.println(p);
                                }
                            } else {
                                writer.println("No se encontraron películas con id que contenga: " + id);
                            }

                        } catch (NumberFormatException e) {
                            writer.println("Error: ID debe ser un número entero.");
                        }
                    } else if (tipoConsulta.equalsIgnoreCase("TITULO")) {
                        writer.println("Introduce el título de la película:");
                        String titulo = reader.readLine().trim().toLowerCase();
                        List<Pelicula> encontradas = new ArrayList<>();
                        for (Pelicula p : peliculas) {
                            if (p.getTitulo().toLowerCase().contains(titulo)) {
                                encontradas.add(p);
                            }
                        }

                        if (!encontradas.isEmpty()) {
                            for (Pelicula p : encontradas) {
                                writer.println(p);
                            }
                        } else {
                            writer.println("No se encontraron películas con título que contenga: " + titulo);
                        }
                    } else {
                        writer.println("Tipo de consulta no válido. Usa ID o TITULO.");
                    }
                } else if (comando.equalsIgnoreCase("MODIFICAR")) {

                    writer.println("Introduce el ID de la película:");
                    int id = Integer.parseInt(reader.readLine());

                    writer.println("Introduce el campo a modificar (rating, comentarios):");
                    String campo = reader.readLine();
                    writer.println("Introduce el nuevo valor:");
                    String nuevoValor = reader.readLine();
                    modificarPelicula(id, campo, nuevoValor, writer);

                } else {
                    writer.println("Comando no reconocido. Usa CONSULTA, MODIFICAR o FIN.");
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
