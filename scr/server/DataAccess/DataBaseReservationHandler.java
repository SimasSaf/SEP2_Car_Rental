package server.DataAccess;

import shared.transferObjects.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseReservationHandler implements ReserveData{
    @Override
    public void storeReservation(Reservation reservation) {
        String query = "INSERT INTO \"" + DataConnection.SCHEMA + "\".reservation (start_time, end_time, km_start, km_end, user_id, car_id) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = executeQuery(query);
            preparedStatement.setTimestamp(1, reservation.getStart());
            preparedStatement.setTimestamp(2, reservation.getEnd());
            preparedStatement.setInt(3, reservation.getKmStart());
            preparedStatement.setInt(4, reservation.getKmEnd());
            preparedStatement.setInt(5, reservation.getCustomerId());
            preparedStatement.setInt(6, reservation.getCarId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReservation(int resID) {
        String query = "DELETE FROM \"" + DataConnection.SCHEMA + "\".reservation WHERE reservation_id = ?";
        try {
            PreparedStatement preparedStatement = executeQuery(query);
            preparedStatement.setInt(1, resID);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateReservation(int resID, Reservation newReservation) {

    }

    @Override
    public Reservation getReservationById(int resID)  {
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\"." + "reservation" + " WHERE reservation_id = '" + resID + "'";
        try {
            ResultSet resultSet = getFromDatabase(query);
            return createReservation(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<Reservation> getReservationsByCarID(int carID) {
        List<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\"." + "reservation" + " WHERE car_id = '" + carID + "'";
        try {
            ResultSet resultSet = getFromDatabase(query);
            do {
                reservationList.add(createReservation(resultSet));
            } while (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    @Override
    public List<Reservation> getReservationByUserID(int userID) {
        List<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\"." + "reservation" + " WHERE user_id = '" + userID + "'";
        try {
            ResultSet resultSet = getFromDatabase(query);
            do {
                reservationList.add(createReservation(resultSet));
            } while (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    @Override
    public List<Reservation> getListReservations() {
        List<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\".reservation";
        try {
            ResultSet resultSet = getFromDatabase(query);
            do {
                reservationList.add(createReservation(resultSet));
            } while (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }


    private Reservation createReservation(ResultSet resultSet) throws SQLException {
        return new Reservation(resultSet.getInt("reservation_id"),
                resultSet.getTimestamp("start_time"),
                resultSet.getTimestamp("end_time"),
                resultSet.getInt("km_start"),
                resultSet.getInt("km_end"),
                resultSet.getInt("user_id"),
                resultSet.getInt("car_id"));
    }


    private ResultSet getFromDatabase(String query) throws SQLException {
        Connection connection = DataConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet;
    }

    private PreparedStatement executeQuery(String query) throws SQLException {
        Connection connection = DataConnection.getInstance().getConnection();
        return connection.prepareStatement(query);
    }
}
