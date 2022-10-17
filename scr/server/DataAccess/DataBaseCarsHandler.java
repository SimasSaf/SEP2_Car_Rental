package server.DataAccess;

import shared.transferObjects.Car;
import shared.transferObjects.PlateNo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseCarsHandler implements CarsData{

    @Override
    public int storeCar(Car car) {
        String query = "INSERT INTO \"" + DataConnection.SCHEMA + "\".car (make, model, year, price, type, range, km, plate_no, location) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = executeQuery(query);
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setDouble(4, car.getPrice());
            preparedStatement.setString(5,car.getType());
            preparedStatement.setInt(6, car.getRange());
            preparedStatement.setInt(7, car.getKm());
            preparedStatement.setString(8, car.getPlateNo().getPlate());
            preparedStatement.setString(9, car.getPickupPoint());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getCarIdByPlate(car.getPlateNo());
    }
    @Override
    public void updateCar(int carId, Car car) {
        deleteCar(carId);
        storeCar(car);
        String query = "UPDATE \"" + DataConnection.SCHEMA + "\".car SET car_id = ? WHERE email = ?"; // Update the user_ID
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = executeQuery(query);
            preparedStatement.setInt(1, carId);
            preparedStatement.setString(2, car.getPlateNo().getPlate());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Car> getListCar()  {
        List<Car> carList = new ArrayList<>();
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\".car";
        try {
            ResultSet resultSet = getFromDatabase(query);
            do {
                carList.add(createCar(resultSet));
            } while (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }


    @Override
    public Car getCarById(int carID)  {
        String query = "SELECT * FROM \"" + DataConnection.SCHEMA + "\"." + "car" + " WHERE car_id = '" + carID + "'";
        try {
            ResultSet resultSet = getFromDatabase(query);
            return createCar(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int getCarIdByPlate(PlateNo plateNo) {
        String query = "SELECT car_id FROM \"" + DataConnection.SCHEMA + "\"." + "car" + " WHERE plate_no = '" + plateNo.getPlate() + "'";
        try {
            ResultSet resultSet = getFromDatabase(query);
            return resultSet.getInt("car_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isAvailable(int carId, Date from, Date to) {
        return false;
    }


    @Override
    public void deleteCar(int carId) {
        String query = "DELETE FROM \"" + DataConnection.SCHEMA + "\".car WHERE car_id = ?";
        try {
            PreparedStatement preparedStatement = executeQuery(query);
            preparedStatement.setInt(1, carId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Car createCar(ResultSet resultSet) throws SQLException {
        return new Car(resultSet.getInt("car_id"),
                resultSet.getString("make"),
                resultSet.getString("model"),
                resultSet.getInt("year"),
                resultSet.getDouble("price"),
                resultSet.getString("type"),
                resultSet.getInt("range"),
                resultSet.getInt("km"),
                resultSet.getString("location"),
                new PlateNo(resultSet.getString("plate_no")));
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
