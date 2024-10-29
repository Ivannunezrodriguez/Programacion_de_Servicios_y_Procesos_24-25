import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Libro implements Serializable {
    private String ISBN;
    private String titulo;
    private String autor;

    public Libro(String ISBN, String titulo, String autor) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getISBN() { return ISBN; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }

    @Override
    public String toString() {
        return "ISBN: " + ISBN + ", Título: " + titulo + ", Autor: " + autor;
    }

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
