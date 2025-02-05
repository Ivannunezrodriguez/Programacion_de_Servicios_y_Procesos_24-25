import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
class Constelacion implements Serializable {
    private String nombre;
    private String observaciones;
}