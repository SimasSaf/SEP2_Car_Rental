package client.views.manageAccountView;

import client.model.users.UsersModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shared.transferObjects.Address;
import shared.transferObjects.user.*;

import java.sql.Date;
import java.util.List;

public class ManageAccountsViewModel {

    private StringProperty firstName, lastName, email, phone, license, street, number, city, country, DOB, password, rePassword, errorText;
    private IntegerProperty zip;

    private UsersModel usersModel;
    private ObservableList<User> users;
    private User userSelected;


    public ManageAccountsViewModel(UsersModel usersModel) {
        this.users = new SimpleListProperty<User>();
        this.usersModel = usersModel;

        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        email = new SimpleStringProperty();
        phone = new SimpleStringProperty();
        //    if (identity.getType().equals(LoginType.CUSTOMER)) license = new SimpleStringProperty(((Customer) identity).getDriverLicenseNo());
        street = new SimpleStringProperty();
        number = new SimpleStringProperty();
        city = new SimpleStringProperty();
        country = new SimpleStringProperty();
        zip = new SimpleIntegerProperty();
        DOB = new SimpleStringProperty();
        password =  new SimpleStringProperty();
        rePassword = new SimpleStringProperty();
        errorText = new SimpleStringProperty();
    }

    public void loadUsers() {
        List<User> userList = usersModel.getUsersList();
        this.users = FXCollections.observableArrayList(userList);
    }
    public ObservableList<User> getUsers() {
        return users;
    }

    public void selectUser(User userSelected) {
        this.userSelected = userSelected;
        firstName.set(userSelected.getFName());
        lastName.set(userSelected.getLName());
        email.set(userSelected.getEmail());
        phone.set(userSelected.getPhoneNo());
        street.set(userSelected.getAddress().getStreet());
        number.set(userSelected.getAddress().getNumber());
        city.set(userSelected.getAddress().getCity());
        country.set(userSelected.getAddress().getCountry());
        zip.set(userSelected.getAddress().getZip());
        DOB.set(userSelected.getDob().toString());
        password.set(userSelected.getPassword().getPassword());
        rePassword.set(userSelected.getPassword().getPassword());

    }

    public void onUpdate(LoginType loginType) {
        errorText.set("");
        User newUser = null;
        try {
            Address address = new Address(country.getValue(), city.getValue(), street.getValue(), number.getValue(), zip.getValue());
            PhoneNo phoneNo = new PhoneNo(phone.getValue());
            Password pass = new Password(password.getValue()); //Change this to see if there are equals
            Email em = new Email(email.getValue());

            switch (loginType) {
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
            System.out.println("ready to save");
            usersModel.editUser(userSelected.getUserID(), newUser);
        } catch (Exception e) {
            errorText.set(e.getLocalizedMessage());
        }

    }

    public void onDelete() {
        usersModel.deleteUser(userSelected.getUserID());
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
