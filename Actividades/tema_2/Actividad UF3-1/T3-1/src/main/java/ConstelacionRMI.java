import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

class ConstelacionRMI extends UnicastRemoteObject implements ConstelacionInterfaceRMI {
    @Serial
    private static final long serialVersionUID = -9029108738150223029L;
    private List<Constelacion> constelaciones;

    protected ConstelacionRMI() throws RemoteException {
        constelaciones = new ArrayList<>();
        constelaciones.add(new Constelacion("Osa Mayor", "Se desplaza en círculos alrededor del polo norte."));

        constelaciones.add(new Constelacion("Osa Menor", "Su estrella más conocida es la polar que se encuentra en la prolongación del eje de la tierra."));

        constelaciones.add(new Constelacion("Tauro", "Una de las constelaciones más conocidas desde tiempos remotos."));

        constelaciones.add(new Constelacion("Leo", "De las más brillantes del Zodíaco."));

        constelaciones.add(new Constelacion("Escorpio", "Sus estrellas forman un escorpión."));

        constelaciones.add(new Constelacion("Can Mayor", "Contiene la estrella Sirio, la más brillante en el cielo nocturno."));

        constelaciones.add(new Constelacion("Casiopea", "Tiene forma de M o W. Es conocida desde mucha antigüedad."));

        constelaciones.add(new Constelacion("El Boyero", "Contiene la estrella Arturo, uno de las más luminosas del cielo."));

        constelaciones.add(new Constelacion("Cruz del sur", "Señala al polo sur. Constelación muy pequeña."));

        constelaciones.add(new Constelacion("Acuario", "Una de las más antiguas. Incluye 56 estrellas."));

        constelaciones.add(new Constelacion("Géminis", "Destaca por sus dos gemelos, las estrellas Cástor y Pólux."));
    }

    @Override
    public String buscarConstelacion(String nombre) throws RemoteException {
        return constelaciones.stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .map(Constelacion::toString)
                .findFirst()
                .orElse("Constelación no encontrada");
    }
}