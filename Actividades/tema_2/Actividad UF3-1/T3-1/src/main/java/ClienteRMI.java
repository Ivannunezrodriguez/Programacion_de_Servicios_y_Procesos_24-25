import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Cliente RMI
class ClienteRMI {
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 5055);
            ConstelacionInterfaceRMI constelaciones = (ConstelacionInterfaceRMI) registro.lookup("Constelaciones");
            Scanner scanner = new Scanner(System.in);
            String nombreConst;

            while (true) {
                System.out.print("Nombre de la constelación (Escribe 'Fin' para salir): ");
                nombreConst = scanner.nextLine().trim();


                if (nombreConst.equalsIgnoreCase("Fin")) {
                    System.out.println("Sesión finalizada.");
                    break;
                }


                nombreConst = nombreConst.toUpperCase();

                System.out.println(constelaciones.buscarConstelacion(nombreConst));
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
