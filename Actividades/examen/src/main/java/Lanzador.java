public class Lanzador {
    public static void main(String[] args) {
        BufferColor buffer = new BufferColor();

        for (int i = 0; i < 5; i++) {
            if (i < 4) {
                new ProductorColor(buffer);
            } else {
                new ConsumidorColor(buffer);
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Proceso completado.");
    }
}
