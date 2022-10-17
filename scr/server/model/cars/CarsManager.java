package server.model.cars;

import client.views.utils.images.CarImage;
import shared.transferObjects.Car;

import java.sql.Date;
import java.util.List;

public interface CarsManager {
    List<Car> search(Date fromDate, Date toDate, String location, double price);
    List<Car> getCarsList();
    Car getCar(int carID);
    boolean isAvailable(Date fromDate, Date toDate);
    void addCar(Car car, CarImage[] carImages);
    void removeCar(int carID);
    void changeStatus();
    void updateCar(int carID, Car newCar);
}
