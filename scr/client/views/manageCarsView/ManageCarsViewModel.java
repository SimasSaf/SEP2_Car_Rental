package client.views.manageCarsView;

import client.model.cars.CarsModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferObjects.Car;
import shared.transferObjects.user.User;

import java.util.List;


public class ManageCarsViewModel {

    public StringProperty make, model, type, pickUpPoint, plateNo, carID, year, range, km, price;


    public ManageCarsViewModel() {
        this.carID = new SimpleStringProperty("a");
        this.make = new SimpleStringProperty("a");
        this.model = new SimpleStringProperty("a");
        this.year = new SimpleStringProperty("a");
        this.price = new SimpleStringProperty("a");
        this.type = new SimpleStringProperty("a");
        this.range = new SimpleStringProperty("a");
        this.km = new SimpleStringProperty("a");
        this.plateNo = new SimpleStringProperty("a");
        this.pickUpPoint = new SimpleStringProperty("a");
        year.set("as");
        price.set("asd");
    }

    private ObservableList<Car> cars;

    private CarsModel carsModel;

    public ManageCarsViewModel(CarsModel carsModel) {
        this.carsModel = carsModel;
    }

    public void loadCars() {
            List<Car> carList = carsModel.getCarList();
            System.out.println(carList);
            this.cars = FXCollections.observableArrayList(carList);
    }

    public ObservableList<Car> getCars() {
        return cars;
    }

    public void selectCar(Car carSelected) {
        carID.set(carSelected.getCarId() +"");
        make.set(carSelected.getMake());
        model.set(carSelected.getModel());
        year.set(carSelected.getYear() +"");
        price.set(carSelected.getPrice() +"");
        type.set(carSelected.getType());
        range.set(carSelected.getRange() +"");
        km.set(carSelected.getRange() +"");
        plateNo.set(carSelected.getPlateNo().getPlate());
        pickUpPoint.set(carSelected.getPickupPoint());
    }

    public StringProperty carIDProperty() {
        return carID;
    }

    public StringProperty makeProperty() {
        return make;
    }

    public StringProperty modelProperty() {
        return model;
    }

    public StringProperty yearProperty() {
        return year;
    }

    public StringProperty priceProperty() {
        return price;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty rangeProperty() {
        return range;
    }

    public StringProperty kmProperty() {
        return km;
    }

    public StringProperty pickUpPointProperty() {
        return pickUpPoint;
    }

    public StringProperty plateNoProperty() {
        return plateNo;
    }
}
