package client.networking.cars;

import client.views.utils.images.CarImage;
import shared.transferObjects.Car;
import shared.util.Subject;

import java.sql.Date;
import java.util.List;

public interface CarsClient extends Subject {
    List<Car> search(Date fromDate, Date toDate, String location, double price);
    List<Car> getCarList();
    Car getCar(int carID);
    boolean isAvailable(Date fromDate, Date toDate);
    void addCar(Car car, CarImage[] carImages);
    void removeCar(int carID);
    void changeStatus();
    void updateCar(int carID, Car newCar);
}
