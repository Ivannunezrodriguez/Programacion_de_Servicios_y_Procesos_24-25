

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

class PlanetaRMI extends UnicastRemoteObject implements PlanetaInterfaceRMI {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<String, Planeta> planetas;

    protected PlanetaRMI() throws RemoteException {
        planetas = new HashMap<>();
        planetas.put("MERCURIO", new Planeta("Mercurio", "El planeta más cercano al Sol.", "4,879 km de diámetro", "430°C de día, -180°C de noche"));
        planetas.put("VENUS", new Planeta("Venus", "Conocido por su atmósfera densa y temperaturas extremas.", "12,104 km de diámetro", "471°C"));
        planetas.put("TIERRA", new Planeta("Tierra", "Nuestro hogar, el único planeta conocido con vida.", "12,742 km de diámetro", "-88°C a 58°C"));
        planetas.put("MARTE", new Planeta("Marte", "El planeta rojo, con la montaña más alta del sistema solar.", "6,779 km de diámetro", "-87°C a -5°C"));
        planetas.put("JUPITER", new Planeta("Júpiter", "El gigante gaseoso con la Gran Mancha Roja.", "139,820 km de diámetro", "-145°C"));
        planetas.put("SATURNO", new Planeta("Saturno", "Famoso por sus anillos impresionantes.", "116,460 km de diámetro", "-178°C"));
        planetas.put("URANO", new Planeta("Urano", "Un gigante helado con rotación inclinada.", "50,724 km de diámetro", "-224°C"));
        planetas.put("NEPTUNO", new Planeta("Neptuno", "El planeta más lejano del sistema solar.", "49,244 km de diámetro", "-218°C"));
    }

    @Override
    public String obtenerDescripcion(String nombre) throws RemoteException {
        return planetas.getOrDefault(nombre, new Planeta("", "Planeta no encontrado", "", "")).getDescripcion();
    }

    @Override
    public String obtenerTamano(String nombre) throws RemoteException {
        return planetas.getOrDefault(nombre, new Planeta("", "", "Planeta no encontrado", "")).getTamano();
    }

    @Override
    public String obtenerTemperatura(String nombre) throws RemoteException {
        return planetas.getOrDefault(nombre, new Planeta("", "", "", "Planeta no encontrada")).getTemperatura();
    }
}