package client.model.reservations;

import client.networking.reservations.ReservationsClient;
import server.model.reservation.ReservationManager;
import shared.transferObjects.Reservation;

import java.util.List;

public class ReservationModelManager implements ReservationModel {

    private ReservationsClient reservationsClient;

    public ReservationModelManager(ReservationsClient reservationsClient) {
        this.reservationsClient = reservationsClient;
    }

    @Override
    public void createReservation(Reservation reservation) {
        reservationsClient.createReservation(reservation);
    }

    @Override
    public void deleteReservation(int resID) {
        reservationsClient.deleteReservation(resID);
    }

    @Override
    public void updateReservation(int resID, Reservation newReservation) {
        reservationsClient.deleteReservation(resID);
    }

    @Override
    public Reservation getReservationById(int resID) {
        return reservationsClient.getReservationById(resID);
    }

    @Override
    public List<Reservation> getReservationsByCarID(int carID) {
        return reservationsClient.getReservationsByCarID(carID);
    }

    @Override
    public List<Reservation> getReservationByUserID(int userID) {
        return reservationsClient.getReservationByUserID(userID);
    }

    @Override
    public List<Reservation> getListReservations() {
        return reservationsClient.getListReservations();
    }
}
