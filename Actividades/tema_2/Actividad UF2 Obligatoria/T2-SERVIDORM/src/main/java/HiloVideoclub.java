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

    private static synchronized void modificarPelicula(int id, int opcionCampo, String nuevoValor, PrintWriter writer) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {
                switch (opcionCampo) {
                    case 1:
                        try {
                            pelicula.setRating(Double.parseDouble(nuevoValor));
                        } catch (NumberFormatException e) {
                            writer.println("Error: Valor de rating inválido.");
                            return;
                        }
                        break;
                    case 2:
                        pelicula.setComentarios(nuevoValor);
                        break;
                    default:
                        writer.println("Error: Opción no válida. Usa 1 para rating o 2 para comentarios.");
                        return;
                }
                writer.println("Película actualizada correctamente. " + pelicula);
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
            writer.println("Bienvenido al videoclub. Seleccione una opción: 1 - CONSULTA 2 - MODIFICAR 3 - FIN");


            String comando;
            while ((comando = reader.readLine()) != null) {
                switch (comando) {
                    case "3":
                        writer.println("Gracias por usar el videoclub. ¡Hasta pronto!");
                        return;
                    case "1":
                        writer.println("Seleccione el tipo de consulta: 1 - ID 2 - TÍTULO");
                        String tipoConsulta = reader.readLine();

                        if (tipoConsulta.equals("1")) {
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
                                    writer.println("No se encontraron películas con el ID: " + id);
                                }


                            } catch (NumberFormatException e) {
                                writer.println("Error: ID debe ser un número entero.");
                            }
                        } else if (tipoConsulta.equals("2")) {
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
                            writer.println("Opción no válida. Usa 1 para ID o 2 para TÍTULO.");

                        }
                        break;
                    case "2":
                        writer.println("Introduce el ID de la película:");
                        int id = Integer.parseInt(reader.readLine());
                        writer.println("Seleccione el campo a modificar: 1 - RATING  2 - COMENTARIOS");
                        int campo = Integer.parseInt(reader.readLine());
                        writer.println("Introduce el nuevo valor:");
                        String nuevoValor = reader.readLine();
                        modificarPelicula(id, campo, nuevoValor, writer);
                        break;
                    default:
                        writer.println("Comando no reconocido. Usa 1 para CONSULTA, 2 para MODIFICAR o 3 para FIN.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}