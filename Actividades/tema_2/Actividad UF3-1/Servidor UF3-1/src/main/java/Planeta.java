import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Planeta {
    private final String nombre;
    private final String descripcion;
    private final String tamano;
    private final String temperatura;


}