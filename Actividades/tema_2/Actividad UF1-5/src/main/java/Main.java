public class Main {
    public static void main(String[] args) {
        //capacidad 5 cartas maximo
        BufferEmail buffer = new BufferEmail(5);

        // crear 3 productores
        Thread productor1 = new Thread(new ProductorEmail(buffer, "Productor_1"));
        Thread productor2 = new Thread(new ProductorEmail(buffer, "Productor_2"));
        Thread productor3 = new Thread(new ProductorEmail(buffer, "Productor_3"));

        //crrear 2 consumidores
        Thread consumidor1 = new Thread(new ConsumidorEmail(buffer, "Consumidor_1"));
        Thread consumidor2 = new Thread(new ConsumidorEmail(buffer, "Consumidor_2"));

        //iniciar hilos
        productor1.start();
        productor2.start();
        productor3.start();
        consumidor1.start();
        consumidor2.start();
    }
}
