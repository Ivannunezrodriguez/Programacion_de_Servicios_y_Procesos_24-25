import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Duration;
import java.time.Instant;

@Data
@AllArgsConstructor
public class NumeroPrimo {
    private int numero;
    private boolean esPrimo;
    private String nombreHilo;
    private long tiempoProcesamiento;

    public static NumeroPrimo crear(int numero, boolean esPrimo, String nombreHilo, Instant inicio, Instant fin) {
        long tiempo = Duration.between(inicio, fin).toMillis();
        return new NumeroPrimo(numero, esPrimo, nombreHilo, tiempo);
    }

    public String getResultado() {
        return String.format("Número: %d | Procesado por: %s | Tiempo: %d ms | %s",
                numero, nombreHilo, tiempoProcesamiento, (esPrimo ? "Es primo" : "No es primo"));
    }

    public String toFileString() {
        return numero + "," + nombreHilo + "," + tiempoProcesamiento + "," + (esPrimo ? "Es primo" : "No es primo");
    }
}
