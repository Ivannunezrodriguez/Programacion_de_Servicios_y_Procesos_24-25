import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Pelicula {
    private int id;
    private String titulo;
    private String creator;
    private double rating;
    private String dates;
    private String comentarios;
}
