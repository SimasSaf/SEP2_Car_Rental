package client.views.createAccountView;

import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;
import client.model.personal.Personal;
import client.views.ViewController;
import client.views.utils.other.Error;
import com.sun.javafx.geom.transform.Identity;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import shared.transferObjects.user.LoginType;
import shared.transferObjects.user.Password;
import shared.transferObjects.user.User;

import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.util.Date;


public class CreateAccountViewController implements ViewController {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField licenseTextField;
    @FXML
    private DatePicker DOBDatePicker;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField zipTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private PasswordField rePasswordPasswordField;
    @FXML
    private Text errorText;
    @FXML
    private HBox kindHBox;
    @FXML
    Text messageText;
    @FXML
    RadioButton adminRadioButton, frontDeskRadioButton, customerRadioButton;
    @FXML
    ToggleGroup userToggle;

    CreateAccountViewModel createAccountViewModel;
    String toggleGroupValue;

    Personal personal = Personal.getPersonal();


    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        createAccountViewModel = viewModelFactory.getCreateAccountViewModel();
        firstNameTextField.textProperty().bindBidirectional(createAccountViewModel.firstNameProperty());
        lastNameTextField.textProperty().bindBidirectional(createAccountViewModel.lastNameProperty());
        emailTextField.textProperty().bindBidirectional(createAccountViewModel.emailProperty());
        phoneTextField.textProperty().bindBidirectional(createAccountViewModel.phoneProperty());
        licenseTextField.textProperty().bindBidirectional(createAccountViewModel.licenseProperty());
        streetTextField.textProperty().bindBidirectional(createAccountViewModel.addressStreetProperty());
        numberTextField.textProperty().bindBidirectional(createAccountViewModel.addressNumberProperty());
        cityTextField.textProperty().bindBidirectional(createAccountViewModel.addressCityProperty());
        countryTextField.textProperty().bindBidirectional(createAccountViewModel.getAddressCountryProperty());
        zipTextField.textProperty().bind(createAccountViewModel.getAddressZipProperty().asString());
        createAccountViewModel.DOBProperty().bind(DOBDatePicker.styleProperty());
        errorText.textProperty().bindBidirectional(createAccountViewModel.errorTextProperty());

        kindHBox.setVisible(personal.getKind().equals(LoginType.ADMIN));
        messageText.setVisible(personal.getKind().equals(LoginType.NO_ACCESS));

        toggleGroupValue = ((RadioButton) userToggle.getSelectedToggle()).getText();
    }

    public void onClose(MouseEvent mouseEvent) {
        Window window = firstNameTextField.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }


    public void createAccount(MouseEvent mouseEvent) { //change to through errors
        if (fieldsEmpty()) {
            errorText.setText(Error.EMPTY_FIELDS.getMessage());
        } else if (!equalPasswords()) {
            errorText.setText(Error.PASSWORD_NOT_MACH.getMessage());
        } else if (isMinor()) {
            errorText.setText(Error.USER_AGE_INVALID.getMessage());
        } else {
            try {
                Password password = new Password(passwordPasswordField.getText());
                createAccountViewModel.createAccount(password, getKind());
            } catch (Exception e) {
                errorText.setText(e.getMessage());
            }

        }

    }

    private LoginType getKind() {
        switch (((RadioButton) userToggle.getSelectedToggle()).getText()) {
            case "Admin" : return LoginType.ADMIN;
            case "Customer" : return LoginType.CUSTOMER;
            default: return LoginType.FRONT_DESK;
        }
    }

    private boolean equalPasswords() { //change to through errors
        return passwordPasswordField.getText().equals(rePasswordPasswordField.getText());
    }

    private boolean fieldsEmpty() { //change to through errors
        boolean response = true;
        try {
            response = firstNameTextField.getText().isEmpty() ||
                    lastNameTextField.getText().isEmpty() ||
                    emailTextField.getText().isEmpty() ||
                    phoneTextField.getText().isEmpty() ||
                    licenseTextField.getText().isEmpty() ||
                    zipTextField.getText().isEmpty() ||
                    (DOBDatePicker.getValue() == null) ||
                    streetTextField.getText().isEmpty() ||
                    numberTextField.getText().isEmpty() ||
                    cityTextField.getText().isEmpty() ||
                    countryTextField.getText().isEmpty();
        } catch (Exception ignored) {
        }
        ;
        return response;
    }

    private boolean isMinor() {
        return (!DOBDatePicker.getValue().isBefore(LocalDate.now().minusYears(18)));
    }

}
