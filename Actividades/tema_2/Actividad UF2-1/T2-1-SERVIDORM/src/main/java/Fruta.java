import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Fruta implements Serializable {
    private String nombre;
    private int stock;
    private float precio;
}