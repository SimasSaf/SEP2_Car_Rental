package server.model.reservation;

import server.DataAccess.DataBaseReservationHandler;
import server.DataAccess.ReserveData;
import shared.transferObjects.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReservationManagerImp implements ReservationManager{

    ReserveData reserveData  = new DataBaseReservationHandler();

    @Override
    public void createReservation(Reservation reservation) {
        reserveData.storeReservation(reservation);
    }

    @Override
    public void deleteReservation(int resID) {
        reserveData.deleteReservation(resID);
    }

    @Override
    public void updateReservation(int resID, Reservation newReservation) {
        reserveData.deleteReservation(resID);
    }

    @Override
    public Reservation getReservationById(int resID) {
        return reserveData.getReservationById(resID);
    }

    @Override
    public List<Reservation> getReservationsByCarID(int carID) {
        return reserveData.getReservationsByCarID(carID);
    }

    @Override
    public List<Reservation> getReservationByUserID(int userID) {
        return reserveData.getReservationByUserID(userID);
    }

    @Override
    public List<Reservation> getListReservations() {
        return reserveData.getListReservations();
    }
}
