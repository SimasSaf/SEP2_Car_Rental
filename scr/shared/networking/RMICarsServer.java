package shared.networking;

import client.views.utils.images.CarImage;
import shared.transferObjects.Car;


import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

public interface RMICarsServer extends RMIServer {
    List<Car> search(Date fromDate, Date toDate, String location, double price) throws RemoteException;
    List<Car> getCarList() throws RemoteException;;
    Car getCar(int carID) throws RemoteException;
    boolean isAvailable(Date fromDate, Date toDate) throws RemoteException;
    void addCar(Car car, CarImage[] carImages) throws RemoteException;
    void removeCar(int carID) throws RemoteException;
    void changeStatus() throws RemoteException;
    void updateCar(int carID, Car newCar) throws RemoteException;
}
