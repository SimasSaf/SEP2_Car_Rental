package client.core;


import client.networking.Connect;
import client.networking.cars.CarsClient;
import client.networking.cars.RMICarsClient;
import client.networking.reservations.RMIReservationsClient;
import client.networking.reservations.ReservationsClient;
import client.networking.user.RMIUsersClient;
import client.networking.user.UsersClient;

public class ClientFactory {
    private UsersClient userClient;
    private CarsClient carsClient;
    private ReservationsClient reservationsClient;
    private Connect connect = new Connect();

    public UsersClient getUserClient() {
        if (this.userClient == null) {
            return (new RMIUsersClient(connect));
        }
        return userClient;
    }

    public CarsClient getCarsClient() {
        if (this.carsClient == null) {
            return (new RMICarsClient(connect));
        }
        return carsClient;
    }

    public ReservationsClient getReservationsClient() {
        if (this.reservationsClient == null) {
            return (new RMIReservationsClient(connect));
        }
        return reservationsClient;
    }
}
