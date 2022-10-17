package server.model.cars;

import client.views.utils.images.CarImage;
import server.DataAccess.CarsData;
import server.DataAccess.DataBaseCarsHandler;
import shared.transferObjects.Car;

import java.sql.Date;
import java.util.List;

public class CarsManagerImp implements CarsManager{

    CarsData carsData = new DataBaseCarsHandler();


    @Override
    public List<Car> search(Date fromDate, Date toDate, String location, double price) {
        return null;
    }

    @Override
    public List<Car> getCarsList() {
        return carsData.getListCar();
    }

    @Override
    public Car getCar(int carID) {
        return carsData.getCarById(carID);
    }

    @Override
    public boolean isAvailable(Date fromDate, Date toDate) {
        return false;
    }

    @Override
    public void addCar(Car car, CarImage[] carImages) {
        int carID = carsData.storeCar(car);
        for (CarImage carImage: carImages) {
            if (carImage != null)
                carImage.imageIoWrite(carID);
        }
    }

    @Override
    public void removeCar(int carID) {
        carsData.deleteCar(carID);
    }

    @Override
    public void changeStatus() {

    }

    @Override
    public void updateCar(int carID, Car newCar) {
        carsData.updateCar(carID, newCar);
    }
}
