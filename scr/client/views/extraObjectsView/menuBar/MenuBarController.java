package client.views.extraObjectsView.menuBar;

import client.core.ViewModelFactory;
import client.core.viewHandler.View;
import client.core.viewHandler.ViewHandler;
import client.model.personal.Personal;
import client.model.personal.status.Stat;
import client.views.extraObjectsView.ExtraController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import shared.transferObjects.user.LoginType;

public class MenuBarController  implements ExtraController {
    @FXML private HBox welcomeHBox;
    @FXML private Text nameText;
    @FXML private Text logText;
    @FXML private Text text1;
    @FXML private Text text2;
    @FXML private Text text3;
    @FXML private Text text4;

    private ViewHandler viewHandler;
    private Personal personal;

    public MenuBarController() {
    }

    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        welcomeHBox.setVisible(false);
        logText.setText("|LogIn|");
        text1.setText("|Create Account|");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        personal = Personal.getPersonal();
        if (personal.getStatus().equals(Stat.ONLINE)) {
            nameText.setText(personal.getIdentity().getFName());
            welcomeHBox.setVisible(true);
            logText.setText("|Logout|");
            text1.setText("|My Account|");
            text2.setText(personal.getKind().equals(LoginType.CUSTOMER) ? "" : "|Manage Accounts|");
            text3.setText(personal.getKind().equals(LoginType.CUSTOMER) ? "" : "|Manage Reservations|");
            text4.setText(personal.getKind().equals(LoginType.CUSTOMER) ? "" : "|Manage Cars|");
        }

    }

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {

    }

    public void logInAction(MouseEvent mouseEvent) {
        if (personal.getStatus().equals(Stat.OFFLINE)) {
            viewHandler.openNewView(View.LOGIN);
        }

        if (personal.getStatus().equals(Stat.ONLINE)){
            personal.logOut();
            viewHandler.openView(View.SEARCH);
        }


    }

    public void onMyAccount(MouseEvent mouseEvent) {
        if (personal.getStatus().equals(Stat.ONLINE)) {
            viewHandler.openView(View.MY_ACCOUNT);
        } else {
            viewHandler.openNewView(View.CREATE_ACCOUNT);
        }

    }

    public void onManageAccount(MouseEvent mouseEvent) {
        viewHandler.openView(View.MANAGE_ACCOUNT);
    }

    public void onManageReservations(MouseEvent mouseEvent) {
        viewHandler.openView(View.MANAGE_RESERVATIONS);

    }

    public void onManageCars(MouseEvent mouseEvent) {
        viewHandler.openView(View.MANAGE_CARS);
    }

    public void goHome(MouseEvent mouseEvent) {
        viewHandler.openView(View.SEARCH);
    }

}
