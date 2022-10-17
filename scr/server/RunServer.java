package server;

import server.model.cars.CarsManagerImp;
import server.model.reservation.ReservationManagerImp;
import server.model.user.UsersManagerImp;
import server.networking.RMIServerImp;
import shared.networking.RMIServer;

import java.rmi.RemoteException;

public class RunServer {
    public static void main(String[] args) throws RemoteException {
        RMIServer rmiServer = new RMIServerImp(new UsersManagerImp(), new CarsManagerImp(), new ReservationManagerImp());
        rmiServer.startServer();

    }
}