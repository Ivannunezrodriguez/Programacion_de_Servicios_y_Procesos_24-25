import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Pelicula {
    private int id;
    private String titulo;
    private double rating;
    private String comentarios;
}