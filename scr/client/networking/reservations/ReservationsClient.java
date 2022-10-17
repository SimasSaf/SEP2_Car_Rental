package client.networking.reservations;

import shared.transferObjects.Reservation;

import java.util.List;

public interface ReservationsClient {
    void createReservation(Reservation reservation);
    void deleteReservation(int resID);
    void updateReservation(int resID, Reservation newReservation);
    Reservation getReservationById(int resID);
    List<Reservation> getReservationsByCarID(int carID);
    List<Reservation> getReservationByUserID(int userID);
    List<Reservation> getListReservations();
}
