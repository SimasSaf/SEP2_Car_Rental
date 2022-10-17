package client.views.myReservationsView;

import client.model.personal.Personal;
import client.model.reservations.ReservationModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferObjects.Reservation;
import shared.transferObjects.user.User;

import java.util.List;

public class MyReservationsViewModel {

    private IntegerProperty id, carID;
    private StringProperty start, end;

    private ObservableList<Reservation> reservations;
    private Reservation reservationSelected;
    private User identity = Personal.getPersonal().getIdentity();

    private ReservationModel reservationModel;

    public MyReservationsViewModel(ReservationModel reservationModel) {
        this.reservationModel = reservationModel;
        id = new SimpleIntegerProperty();
        start = new SimpleStringProperty();
        end = new SimpleStringProperty();
        carID = new SimpleIntegerProperty();

    }

    public void loadUsers() {
        List<Reservation> reservationList = reservationModel.getListReservations(); //Change This
        this.reservations = FXCollections.observableArrayList(reservationList);
    }

    public ObservableList<Reservation> getUsers() {
        return reservations;
    }

    public void onUpdate() {
        System.out.println(reservationModel.getListReservations());
    }

    public void selectReservation(Reservation reservationSelected) {
        System.out.println(reservationSelected);
        this.reservationSelected = reservationSelected;
        id.set(reservationSelected.getReservationId());
        start.set(reservationSelected.getStart().toString());
        end.set(reservationSelected.getEnd().toString());
        carID.set(reservationSelected.getCarId());

    }

    public IntegerProperty idProperty() {
        return id;
    }

    public IntegerProperty carIDProperty() {
        return carID;
    }

    public StringProperty startProperty() {
        return start;
    }

    public StringProperty endProperty() {
        return end;
    }
}
