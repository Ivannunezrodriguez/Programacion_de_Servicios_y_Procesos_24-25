import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Cliente RMI
class ClienteRMI {
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 2021);
            ConstelacionInterfaceRMI constelaciones = (ConstelacionInterfaceRMI) registro.lookup("Constelaciones");
            PlanetaInterfaceRMI planetas = (PlanetaInterfaceRMI) registro.lookup("Planetas");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Buscar Constelación");
                System.out.println("2. Buscar Planeta");
                System.out.println("3. Salir");
                System.out.print("Opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 3) {
                    System.out.println("Sesión finalizada.");
                    break;
                } else if (opcion == 1) {
                    System.out.print("Nombre de la constelación: ");
                    String nombreConst = scanner.nextLine().trim().toUpperCase();
                    System.out.println(constelaciones.buscarConstelacion(nombreConst));
                } else if (opcion == 2) {
                    System.out.println("\nSeleccione una consulta sobre el planeta:");
                    System.out.println("1. Descripción");
                    System.out.println("2. Tamaño");
                    System.out.println("3. Temperatura");
                    System.out.print("Opción: ");
                    int subOpcion = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nombre del planeta: ");
                    String nombrePlaneta = scanner.nextLine().trim().toUpperCase();

                    switch (subOpcion) {
                        case 1:
                            System.out.println(planetas.obtenerDescripcion(nombrePlaneta));
                            break;
                        case 2:
                            System.out.println(planetas.obtenerTamano(nombrePlaneta));
                            break;
                        case 3:
                            System.out.println(planetas.obtenerTemperatura(nombrePlaneta));
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                } else {
                    System.out.println("Opción inválida.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
