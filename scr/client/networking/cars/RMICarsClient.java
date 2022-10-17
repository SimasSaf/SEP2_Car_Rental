package client.networking.cars;

import client.networking.Connect;
import client.views.utils.images.CarImage;
import javafx.fxml.FXML;
import shared.networking.ClientCallback;
import shared.networking.RMICarsServer;
import shared.networking.RMIUserServer;
import shared.transferObjects.Car;

import java.beans.PropertyChangeListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.List;

public class RMICarsClient implements CarsClient, ClientCallback {

    private RMICarsServer carsServer;
    private Connect connect;

    public RMICarsClient(Connect connect) {
        this.connect = connect;
        carsServer = connect.getCarsServer();
    }


    @Override
    public List<Car> search(Date fromDate, Date toDate, String location, double price) {
        return null;
    }

    @Override
    public List<Car> getCarList() {
        try {
            return carsServer.getCarList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Car getCar(int carID) {
        try {
            return carsServer.getCar(carID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isAvailable(Date fromDate, Date toDate) {
        return false;
    }

    @Override
    public void addCar(Car car, CarImage[] carImages) {
        try {
            carsServer.addCar(car, carImages);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCar(int carID) {
        try {
            carsServer.removeCar(carID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void changeStatus() {

    }

    @Override
    public void updateCar(int carID, Car newCar) {
        try {
            carsServer.updateCar(carID, newCar);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addListener(String evt, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String evt, PropertyChangeListener listener) {

    }

    @Override
    public void updateChanges() throws RemoteException {

    }
}
