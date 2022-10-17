package server.DataAccess;

import shared.transferObjects.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface ReserveData {
    void storeReservation(Reservation reservation);
    void deleteReservation(int resID);
    void updateReservation(int resID, Reservation newReservation);
    Reservation getReservationById(int resID);
    List<Reservation> getReservationsByCarID(int carID);
    List<Reservation> getReservationByUserID(int userID);
    List<Reservation> getListReservations();
}
