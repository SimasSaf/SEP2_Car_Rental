package client.views.myAccountView;


import client.core.viewHandler.View;
import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;
import client.model.personal.Personal;
import client.views.ViewController;
import client.views.extraObjectsView.LoadPanel;
import client.views.utils.other.AlertControl;
import client.views.utils.other.Message;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shared.transferObjects.user.LoginType;
import shared.transferObjects.user.User;

public class MyAccountViewController implements ViewController {

    MyAccountViewModel myAccountViewModel;

    @FXML private HBox menuBarHBox;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField licenseTextField;
    @FXML private DatePicker DOBDatePicker;
    @FXML private TextField streetTextField;
    @FXML private TextField numberTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField countryTextField;
    @FXML private TextField zipTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField rePasswordPasswordField;
    @FXML private Text errorText;
   // @FXML private HBox kindHBox;
    @FXML private VBox myBox;


    private ViewHandler viewHandler;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        myAccountViewModel = viewModelFactory.getMyAccountViewModel();
        if ((Personal.getPersonal().getKind().equals(LoginType.ADMIN) || Personal.getPersonal().getKind().equals(LoginType.FRONT_DESK))) {

            myBox.getChildren().add(new LoadPanel().load("../extraObjectsView/myBusinessInformation/myBusinessInformation.fxml", viewHandler));
        } else {

            myBox.getChildren().add(new LoadPanel().load("../myReservationsView/myReservations.fxml", viewHandler, viewModelFactory));
        }

        errorText.setText("this is an error text");

        menuBarHBox.getChildren().add(new LoadPanel().load("../extraObjectsView/menuBar/menuBar.fxml", viewHandler));
        firstNameTextField.setDisable(true);
        lastNameTextField.setDisable(true);
        emailTextField.setDisable(true);
        firstNameTextField.textProperty().bindBidirectional(myAccountViewModel.firstNameProperty());
        lastNameTextField.textProperty().bindBidirectional(myAccountViewModel.lastNameProperty());
        emailTextField.textProperty().bindBidirectional(myAccountViewModel.emailProperty());
        phoneTextField.textProperty().bindBidirectional(myAccountViewModel.phoneProperty());
        streetTextField.textProperty().bindBidirectional(myAccountViewModel.streetProperty());
        numberTextField.textProperty().bindBidirectional(myAccountViewModel.numberProperty());
        cityTextField.textProperty().bindBidirectional(myAccountViewModel.cityProperty());
        countryTextField.textProperty().bindBidirectional(myAccountViewModel.countryProperty());
        zipTextField.textProperty().bind(myAccountViewModel.zipProperty().asString());
        passwordPasswordField.textProperty().bindBidirectional(myAccountViewModel.passwordProperty());
        rePasswordPasswordField.textProperty().bindBidirectional(myAccountViewModel.rePasswordProperty());
        errorText.textProperty().bindBidirectional(myAccountViewModel.errorTextProperty());
       // DOBDatePicker.converterProperty().bind(myAccountViewModel.DOBProperty()); FIX THIS
    }

    public void onCancel(MouseEvent mouseEvent) {
        if (AlertControl.confirmationBox(Message.CANCEL)) {
            viewHandler.openView(View.SEARCH);
        }

    }

    public void onUpdate(MouseEvent mouseEvent) {
        myAccountViewModel.onUpdate();
    }

    public void onDeleteAccount(MouseEvent mouseEvent) {
        if (AlertControl.confirmationBox(Message.DELETE_ACCOUNT)) {
            myAccountViewModel.onDelete();
            Personal personal = Personal.getPersonal();
            personal.logOut();
            viewHandler.openView(View.SEARCH);
        }
    }
}
