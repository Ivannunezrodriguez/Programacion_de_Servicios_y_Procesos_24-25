
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlanetaInterfaceRMI extends Remote {
    String obtenerDescripcion(String nombre) throws RemoteException;
    String obtenerTamano(String nombre) throws RemoteException;
    String obtenerTemperatura(String nombre) throws RemoteException;
}