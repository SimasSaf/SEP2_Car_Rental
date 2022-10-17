package client.core;

import client.model.cars.CarsModel;
import client.model.cars.CarsModelManager;
import client.model.reservations.ReservationModel;
import client.model.reservations.ReservationModelManager;
import client.model.users.UsersModelManager;
import client.model.users.UsersModel;
import server.model.reservation.ReservationManager;

public class ModelFactory {
    private ClientFactory clientFactory;
    private UsersModel usersModel;
    private CarsModel carsModel;
    private ReservationModel reservationModel;

    public ModelFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public UsersModel getUsersModel() {
        if (this.usersModel == null) {
            return new UsersModelManager(clientFactory.getUserClient());
        }
        return usersModel;
    }

    public CarsModel getCarsModel() {
        if (this.carsModel == null) {
            return new CarsModelManager(clientFactory.getCarsClient());
        }
        return carsModel;
    }

    public ReservationModel getReservationModel() {
        if (this.reservationModel == null) {
            return new ReservationModelManager(clientFactory.getReservationsClient());
        }
        return reservationModel;
    }
}
