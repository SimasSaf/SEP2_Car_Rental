package client.networking;



import shared.networking.RMICarsServer;
import shared.networking.RMIReservationServer;
import shared.networking.RMIServer;
import shared.networking.RMIUserServer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Connect implements Remote {

        RMIServer rmiServer;

        public Connect() {
            try {
                UnicastRemoteObject.exportObject(this, 0);
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                rmiServer = (RMIServer) registry.lookup("Server");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }

    public RMICarsServer getCarsServer() {
        return (RMICarsServer) rmiServer;
    }



    public RMIUserServer getUserServer() {
        return (RMIUserServer) rmiServer;
    }

    public RMIReservationServer getReservationServer() {
        return (RMIReservationServer) rmiServer;
    }
}
