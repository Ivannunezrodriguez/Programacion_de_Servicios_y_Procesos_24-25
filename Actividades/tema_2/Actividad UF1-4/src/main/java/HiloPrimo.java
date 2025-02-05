import lombok.AllArgsConstructor;
import java.time.Instant;

@AllArgsConstructor
public class HiloPrimo extends Thread {
    private final int numero;
    private final boolean guardarEnArchivo;

    @Override
    public void run() {
        Instant inicio = Instant.now();
        boolean esPrimo = esNumeroPrimo(numero);
        Instant fin = Instant.now();

        NumeroPrimo resultado = NumeroPrimo.crear(numero, esPrimo, getName(), inicio, fin);

        // Mostrar resultado en pantalla
        System.out.println(resultado.getResultado());

        // Guardar en archivo si el usuario lo ha solicitado
        if (guardarEnArchivo) {
            Resultado.guardarResultado(resultado);
        }
    }

    private boolean esNumeroPrimo(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
