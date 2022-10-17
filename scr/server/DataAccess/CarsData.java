package server.DataAccess;

import shared.transferObjects.Car;
import shared.transferObjects.PlateNo;

import java.util.Date;
import java.util.List;

public interface CarsData {
    int storeCar(Car car);
    void updateCar(int carId, Car car);
    List<Car> getListCar();
    Car getCarById(int carID);
    int getCarIdByPlate(PlateNo plateNo);
    boolean isAvailable(int carId, Date from, Date to);
    void deleteCar(int carId);
}
