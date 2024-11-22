import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Libro implements Serializable {
    private String ISBN;
    private String titulo;
    private String autor;

        // Métodos de consulta
    public static Libro consultarPorISBN(List<Libro> libros, String isbn) {
        return libros.stream().filter(libro -> libro.getISBN().equals(isbn)).findFirst().orElse(null);
    }

    public static Libro consultarPorTitulo(List<Libro> libros, String titulo) {
        return libros.stream().filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null);
    }

    public static List<Libro> consultarPorAutor(List<Libro> libros, String autor) {
        return libros.stream().filter(libro -> libro.getAutor().equalsIgnoreCase(autor)).collect(Collectors.toList());
    }
}
