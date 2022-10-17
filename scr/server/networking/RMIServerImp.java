package server.networking;

import client.views.utils.images.CarImage;
import server.model.cars.CarsManager;
import server.model.reservation.ReservationManager;
import server.model.user.UsersManager;
import shared.networking.RMICarsServer;
import shared.networking.RMIReservationServer;
import shared.networking.RMIServer;
import shared.networking.RMIUserServer;
import shared.transferObjects.Car;
import shared.transferObjects.Reservation;
import shared.transferObjects.user.Email;
import shared.transferObjects.user.Password;
import shared.transferObjects.user.User;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.List;

public class RMIServerImp implements RMIServer, RMIUserServer, RMICarsServer, RMIReservationServer {

    UsersManager usersManager;
    CarsManager carsManager;
    ReservationManager reservationManager;

    public RMIServerImp(UsersManager usersManager, CarsManager carsManager, ReservationManager reservationManager) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.usersManager = usersManager;
        this.carsManager = carsManager;
        this.reservationManager = reservationManager;
    }

    @Override
    public void startServer() {
            try {
                Registry registry = LocateRegistry.createRegistry(1099);
                registry.rebind("Server", this);
                System.out.println("Server started...");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


    @Override
    public List<Car> search(Date fromDate, Date toDate, String location, double price) throws RemoteException {
        return null;
    }

    @Override
    public List<Car> getCarList() {
        return carsManager.getCarsList();
    }

    @Override
    public Car getCar(int carID) throws RemoteException {
        return carsManager.getCar(carID);
    }

    @Override
    public boolean isAvailable(Date fromDate, Date toDate) throws RemoteException {
        return false;
    }

    @Override
    public void addCar(Car car, CarImage[] carImages) throws RemoteException {
        carsManager.addCar(car, carImages);
    }

    @Override
    public void removeCar(int carID) throws RemoteException {
        carsManager.removeCar(carID);
    }

    @Override
    public void changeStatus() throws RemoteException {

    }

    @Override
    public void updateCar(int carID, Car newCar) throws RemoteException {
        carsManager.updateCar(carID, newCar);
    }

    @Override
    public User login(Email email, Password password) throws RemoteException {
        return usersManager.login(email, password);
    }

    @Override
    public boolean isAvailable(Email email) throws RemoteException {
        return usersManager.isAvailable(email);
    }


    @Override
    public void createUser(User user) throws RemoteException {

        usersManager.createUser(user);
    }

    @Override
    public void editUser(int userID, User user) throws RemoteException {
        usersManager.editUser(userID, user);
    }

    @Override
    public void deleteUser(int userID) throws RemoteException {
        usersManager.deleteUser(userID);
    }


    @Override
    public List<User> getUsersList() throws RemoteException {
        return usersManager.getUsersList();
    }

    @Override
    public void createReservation(Reservation reservation) throws RemoteException {
        reservationManager.createReservation(reservation);
    }

    @Override
    public void deleteReservation(int resID) throws RemoteException {
        reservationManager.deleteReservation(resID);
    }

    @Override
    public void updateReservation(int resID, Reservation newReservation) throws RemoteException {
        reservationManager.updateReservation(resID, newReservation);
    }

    @Override
    public Reservation getReservationById(int resID) throws RemoteException {
        return reservationManager.getReservationById(resID);
    }

    @Override
    public List<Reservation> getReservationsByCarID(int carID) throws RemoteException {
        return reservationManager.getReservationsByCarID(carID);
    }

    @Override
    public List<Reservation> getReservationByUserID(int userID) throws RemoteException {
        return reservationManager.getReservationByUserID(userID);
    }

    @Override
    public List<Reservation> getListReservations() throws RemoteException {
        return reservationManager.getListReservations();
    }
}

