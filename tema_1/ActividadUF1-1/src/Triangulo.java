import java.time.LocalDateTime;

public class Triangulo {
    public static void main(String[] args) {
        //se introduce fecha de entrada
        System.out.println("Fecha y hora de inicio: " + LocalDateTime.now());
        if (args.length == 0) {
            System.out.println("Se requiere un argumento");
            return;
        }
        int filas = Integer.parseInt(args[0]);
        for (int i = filas; i >= 1; i--) {
            for (int n = 1; n <= i; n++) {
                System.out.print(n);
            }
            System.out.println();
        }
        //se introduce fecha de salida
        System.out.println("Fecha y hora de salida: " + LocalDateTime.now());
    }
}

