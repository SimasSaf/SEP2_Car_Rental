package client.views.createCarView;

import client.model.cars.CarsModel;
import client.views.utils.images.CarImage;
import client.views.utils.other.Error;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import shared.transferObjects.Car;
import shared.transferObjects.PlateNo;

public class CreateCarViewModel {
    private StringProperty makeTextField, modelTextField, licenseTextField, yearTextField,
                            seatsTextField, rangeTextField, kmTextField, priceTextField, errorMessage;

    private CarsModel carsModel;

    public CreateCarViewModel(CarsModel carsModel){
        this.carsModel = carsModel;
        this.makeTextField = new SimpleStringProperty();
        this.modelTextField = new SimpleStringProperty();
        this.licenseTextField = new SimpleStringProperty();
        this.yearTextField = new SimpleStringProperty();
        this.seatsTextField = new SimpleStringProperty();
        this.rangeTextField = new SimpleStringProperty();
        this.kmTextField = new SimpleStringProperty();
        this.priceTextField = new SimpleStringProperty();
        this.errorMessage = new SimpleStringProperty();
    }


    public void createCar(CarImage[] carImage) { /// CHANGE HERE THE TYPE AND PICKUOT POINT
        Car car = null;
        try {
             car = new Car(makeTextField.getValue(),
                    makeTextField.getValue(),
                    (int) giveMeValue(yearTextField.getValue()),
                    giveMeValue(priceTextField.getValue()),
                    "Sedan",
                    (int) giveMeValue(rangeTextField.getValue()),
                    (int) giveMeValue(kmTextField.getValue()),
                    "Aarhus",
                    new PlateNo(licenseTextField.getValue()));
        } catch (Exception e) {
            errorMessage.set(e.getLocalizedMessage());
        }
        carsModel.addCar(car, carImage);
    }

    public StringProperty getMakeTextField() {
        return makeTextField;

    }

    public double giveMeValue(String field) throws NumberFormatException{
            double number = Double.parseDouble(field);
            return number;
    }

    public StringProperty makeTextFieldProperty() {
        return makeTextField;
    }

    public StringProperty modelTextFieldProperty() {
        return modelTextField;
    }

    public StringProperty licenseTextFieldProperty() {
        return licenseTextField;
    }

    public StringProperty yearTextFieldProperty() {
        return yearTextField;
    }

    public StringProperty seatsTextFieldProperty() {
        return seatsTextField;
    }

    public StringProperty rangeTextFieldProperty() {
        return rangeTextField;
    }

    public StringProperty kmTextFieldProperty() {
        return kmTextField;
    }

    public StringProperty priceTextFieldProperty() {
        return priceTextField;
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }
}
