import java.util.LinkedList;
import java.util.Queue;

public class BufferEmail {
    private int capacidad;
    private Queue<Email> buffer = new LinkedList<>();

    public BufferEmail(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void poner(Email email) {
        // 5 cartas maximo
        while (buffer.size() >= capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
// filtro a correo concreto
        if ("pikachu@gmail.com".equals(email.getDestinatario())) {
            System.out.println("Descartado email a pikachu@gmail.com: " + email);
            return;
        }
//se mete en buffer
        buffer.add(email);
        System.out.println("Añadido al buffer: " + email);
        notifyAll();
    }

    public synchronized Email sacar() {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Email email = buffer.poll();
        notifyAll();
        return email;
    }
}
