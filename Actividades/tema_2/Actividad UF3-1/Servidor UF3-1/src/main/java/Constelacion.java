import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
class Constelacion implements Serializable {
    @Serial
    private static final long serialVersionUID = -4540135499251166707L;
    private String nombre;
    private String observaciones;
}