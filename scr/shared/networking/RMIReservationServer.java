package shared.networking;

import shared.transferObjects.Reservation;

import java.rmi.RemoteException;
import java.util.List;

public interface RMIReservationServer extends RMIServer{
    void createReservation(Reservation reservation) throws RemoteException;
    void deleteReservation(int resID) throws RemoteException;
    void updateReservation(int resID, Reservation newReservation) throws RemoteException;
    Reservation getReservationById(int resID) throws RemoteException;
    List<Reservation> getReservationsByCarID(int carID) throws RemoteException;
    List<Reservation> getReservationByUserID(int userID) throws RemoteException;
    List<Reservation> getListReservations() throws RemoteException;
}
