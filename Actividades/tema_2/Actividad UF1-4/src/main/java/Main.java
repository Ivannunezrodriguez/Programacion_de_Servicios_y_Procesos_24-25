import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[4];

        System.out.println("Introduce 4 números para verificar si son primos:");
        for (int i = 0; i < 4; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Introduce un número válido.");
                scanner.next();
            }
            numeros[i] = scanner.nextInt();
        }

        System.out.print("¿Quieres guardar los resultados en un archivo? (S/N): ");
        boolean guardarEnArchivo = scanner.next().equalsIgnoreCase("S");

        System.out.println("\nProcesando números...\n");

        HiloPrimo[] hilos = new HiloPrimo[4];
        for (int i = 0; i < 4; i++) {
            hilos[i] = new HiloPrimo(numeros[i], guardarEnArchivo);
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (HiloPrimo hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error esperando a los hilos: " + e.getMessage());
            }
        }

        System.out.println("\nProcesamiento terminado.");
    }
}
