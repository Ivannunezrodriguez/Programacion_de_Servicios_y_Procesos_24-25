import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConstelacionInterfaceRMI extends Remote {
    String buscarConstelacion(String nombre) throws RemoteException;
}
