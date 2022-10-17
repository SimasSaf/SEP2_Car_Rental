package client.views.myAccountView;

import client.model.personal.Personal;
import client.model.users.UsersModel;
import com.sun.javafx.geom.transform.Identity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server.RunServer;
import shared.transferObjects.Address;
import shared.transferObjects.user.*;

import java.beans.PropertyChangeSupport;
import java.sql.Date;

public class MyAccountViewModel {
    private StringProperty firstName, lastName, email, phone, license, street, number, city, country, DOB, password, rePassword, errorText;
    private IntegerProperty zip;
    private User identity = Personal.getPersonal().getIdentity();

    private UsersModel usersModel;
    private PropertyChangeSupport support;

    public MyAccountViewModel(UsersModel usersModel) {
        this.usersModel = usersModel;
        firstName = new SimpleStringProperty(identity.getFName());
        lastName = new SimpleStringProperty(identity.getLName());
        email = new SimpleStringProperty(identity.getEmail());
        phone = new SimpleStringProperty(identity.getPhoneNo() +"");
    //    if (identity.getType().equals(LoginType.CUSTOMER)) license = new SimpleStringProperty(((Customer) identity).getDriverLicenseNo());
        street = new SimpleStringProperty(identity.getAddress().getStreet());
        number = new SimpleStringProperty(identity.getAddress().getNumber());
        city = new SimpleStringProperty(identity.getAddress().getCity());
        country = new SimpleStringProperty(identity.getAddress().getCountry());
        zip = new SimpleIntegerProperty(identity.getAddress().getZip());
        DOB = new SimpleStringProperty(identity.getDob().toString());
        password = new SimpleStringProperty(identity.getPassword().getPassword());
        rePassword = new SimpleStringProperty(identity.getPassword().getPassword());
        errorText = new SimpleStringProperty();
    }

    public void onUpdate() {
        errorText.set("");
        User newUser = null;
        try {
            Address address = new Address(country.getValue(), city.getValue(), street.getValue(), number.getValue(), zip.getValue());
            PhoneNo phoneNo = new PhoneNo(phone.getValue());
            Password pass = new Password(password.getValue()); //Change this to see if there are equals
            Email em = new Email(email.getValue());

            switch (identity.getType()) {
                case FRONT_DESK -> {
                    newUser = createFrontDesk(address, phoneNo,pass, em, new Date(21-03-1983));
                    break;
                }
                case ADMIN -> {
                    newUser = createAdmin(address, phoneNo,pass, em, new Date(21-03-1983));
                    break;
                }
                case CUSTOMER -> {
                    newUser = createCustomer(address, phoneNo,pass, em, new Date(21-03-1983));
                    break;
                }
            }
            usersModel.editUser(identity.getUserID(), newUser);
        } catch (Exception e) {
            errorText.set(e.getLocalizedMessage());
        }

    }


    public void onDelete() {
        usersModel.deleteUser(identity.getUserID());

    }

    private User createAdmin(Address address, PhoneNo phoneNo, Password password, Email email, Date date) {

        return new Admin(firstName.getValue(), lastName.getValue(),address,phoneNo,password, email, date, new Ssn("1111111111"));
    }

    private User createFrontDesk(Address address, PhoneNo phoneNo, Password password, Email email, Date date) {
        return new FrontDesk(firstName.getValue(), lastName.getValue(),address,phoneNo,password, email, date, new Ssn("1111111111"), address);
    }


    private User createCustomer(Address address, PhoneNo phoneNo, Password password, Email email, Date date) {
        return new Customer(firstName.getValue(), lastName.getValue(),address,phoneNo,password, email, date, new DriverLicense("00000000"));
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty licenseProperty() {
        return  license;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public StringProperty numberProperty() {
        return number;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public IntegerProperty zipProperty() {
        return zip;
    }

    public StringProperty DOBProperty() {
        return DOB;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty rePasswordProperty() {
        return rePassword;
    }

    public StringProperty errorTextProperty() {
        return errorText;
    }

}
