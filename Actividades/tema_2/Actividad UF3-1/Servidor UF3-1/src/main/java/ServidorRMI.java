import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class ServidorRMI {
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(2021);
            registro.rebind("Constelaciones", new ConstelacionRMI());
            registro.rebind("Planetas", new PlanetaRMI());
            System.out.println("Servidor RMI en ejecución con Constelaciones y Planetas.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
