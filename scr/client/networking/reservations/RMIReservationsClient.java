package client.networking.reservations;

import client.networking.Connect;
import shared.networking.ClientCallback;
import shared.networking.RMIReservationServer;
import shared.transferObjects.Reservation;

import java.rmi.RemoteException;
import java.util.List;

public class RMIReservationsClient implements ReservationsClient, ClientCallback {

    private RMIReservationServer reservationServer;
    private Connect connect;

    public RMIReservationsClient(Connect connect) {
        this.connect = connect;
        reservationServer = connect.getReservationServer();
    }

    @Override
    public void createReservation(Reservation reservation) {
        try {
            reservationServer.createReservation(reservation);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReservation(int resID) {
        try {
            reservationServer.deleteReservation(resID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateReservation(int resID, Reservation newReservation)  {
        try {
            reservationServer.updateReservation(resID, newReservation);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getReservationById(int resID) {
        try {
            return reservationServer.getReservationById(resID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reservation> getReservationsByCarID(int carID) {
        try {
            return reservationServer.getReservationsByCarID(carID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reservation> getReservationByUserID(int userID) {
        try {
            return reservationServer.getReservationByUserID(userID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reservation> getListReservations() {
        try {
            return reservationServer.getListReservations();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateChanges() throws RemoteException {

    }
}
