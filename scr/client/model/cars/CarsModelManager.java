package client.model.cars;

import client.networking.cars.CarsClient;
import client.views.utils.images.CarImage;
import shared.transferObjects.Car;

import java.sql.Date;
import java.util.List;

public class CarsModelManager implements CarsModel{

    private CarsClient carsClient;

    public CarsModelManager(CarsClient carsClient) {
        this.carsClient = carsClient;
    }


    @Override
    public List<Car> search(Date fromDate, Date toDate, String location, double price) {
        return null;
    }

    @Override
    public List<Car> getCarList() {
        return carsClient.getCarList();
    }


    @Override
    public Car getCar(int carID) {
        return carsClient.getCar(carID);
    }

    @Override
    public boolean isAvailable(Date fromDate, Date toDate) {
        return false;
    }

    @Override
    public void addCar(Car car, CarImage[] carImages) {
        carsClient.addCar(car, carImages);

    }

    @Override
    public void removeCar(int carID) {
        carsClient.removeCar(carID);
    }

    @Override
    public void changeStatus() {

    }

    @Override
    public void updateCar(int carID, Car newCar) {
        carsClient.updateCar(carID, newCar);
    }
}
