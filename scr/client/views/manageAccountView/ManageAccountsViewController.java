package client.views.manageAccountView;

import client.core.viewHandler.View;
import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.extraObjectsView.LoadPanel;
import client.views.myAccountView.MyAccountViewModel;
import client.views.utils.other.AlertControl;
import client.views.utils.other.Message;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shared.transferObjects.user.Admin;
import shared.transferObjects.user.LoginType;
import shared.transferObjects.user.User;

public class ManageAccountsViewController implements ViewController {

    @FXML private HBox menuBarHBox;
    @FXML private TableView<User> usersTableView;
    @FXML private TableColumn<User, String> typeTableColumn;
    @FXML private TableColumn<User, String> idTableColumn;
    @FXML private TableColumn<User, String> nameTableColumn;
    @FXML private TableColumn<User, String> lastNameTableColumn;
    @FXML private TableColumn<User, String> emailTableColumn;

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
    @FXML private ToggleGroup kindToggle;
    @FXML private RadioButton admin, frontDesk, customer;


    ViewHandler viewHandler;
    ManageAccountsViewModel manageAccountsViewModel;

    LoginType loginType = LoginType.CUSTOMER;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        manageAccountsViewModel = viewModelFactory.getManageAccountsViewModel();
        menuBarHBox.getChildren().add(new LoadPanel().load("../extraObjectsView/menuBar/menuBar.fxml", viewHandler));
        manageAccountsViewModel.loadUsers();
        usersTableView.setItems(manageAccountsViewModel.getUsers());
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("fName"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lName"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        //set the user's table selection

        usersTableView.setRowFactory(userTableView -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 1 && (!row.isEmpty())) {
                    User userSelected = row.getItem();
                    manageAccountsViewModel.selectUser(userSelected);
                    loginType = userSelected.getType();
                }
            });
            return row;
        });

        //BINDING////
        firstNameTextField.textProperty().bindBidirectional(manageAccountsViewModel.firstNameProperty());
        lastNameTextField.textProperty().bindBidirectional(manageAccountsViewModel.lastNameProperty());
        emailTextField.textProperty().bindBidirectional(manageAccountsViewModel.emailProperty());
        phoneTextField.textProperty().bindBidirectional(manageAccountsViewModel.phoneProperty());
        streetTextField.textProperty().bindBidirectional(manageAccountsViewModel.streetProperty());
        numberTextField.textProperty().bindBidirectional(manageAccountsViewModel.numberProperty());
        cityTextField.textProperty().bindBidirectional(manageAccountsViewModel.cityProperty());
        countryTextField.textProperty().bindBidirectional(manageAccountsViewModel.countryProperty());
        zipTextField.textProperty().bind(manageAccountsViewModel.zipProperty().asString());
        passwordPasswordField.textProperty().bindBidirectional(manageAccountsViewModel.passwordProperty());
        rePasswordPasswordField.textProperty().bindBidirectional(manageAccountsViewModel.rePasswordProperty());
        errorText.textProperty().bindBidirectional(manageAccountsViewModel.errorTextProperty());
        kindToggle.selectToggle(setKind(loginType));
    }


    public void onUpdate(MouseEvent mouseEvent) {
        manageAccountsViewModel.onUpdate(getKind());
    }

    public void onCancel(MouseEvent mouseEvent) {
        if (AlertControl.confirmationBox(Message.CANCEL)) viewHandler.openView(View.SEARCH);
    }

    public void onCreateUser(MouseEvent mouseEvent) {
        viewHandler.openNewView(View.CREATE_ACCOUNT);
    }

    private LoginType getKind() {
        if (kindToggle.getSelectedToggle().equals(admin)) return LoginType.ADMIN;
        if (kindToggle.getSelectedToggle().equals(customer)) return LoginType.CUSTOMER;
        if (kindToggle.getSelectedToggle().equals(frontDesk)) return LoginType.FRONT_DESK;
        return null;
    }

    private RadioButton setKind(LoginType loginType) {
        if (loginType.equals(loginType.ADMIN)) return admin;
        if (loginType.equals(LoginType.FRONT_DESK)) return frontDesk;
        if (loginType.equals(loginType.CUSTOMER)) return customer;
        return customer;
    }

    public void onDelete(MouseEvent mouseEvent) {
        if (AlertControl.confirmationBox(Message.DELETE_ACCOUNT)) {
            manageAccountsViewModel.onDelete();
            viewHandler.refreshActualView();
        }
    }
}
