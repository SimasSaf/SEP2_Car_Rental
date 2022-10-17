package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    public void startServer() throws RemoteException;;

}
