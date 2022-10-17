package client.views.loginView;

import client.core.viewHandler.View;
import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.utils.other.Error;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.beans.PropertyChangeEvent;

public class LoginViewController implements ViewController {

    @FXML private TextField textFieldEmail;
    @FXML private PasswordField passwordFieldPassword;
    @FXML private Text errorText;


    private ViewHandler viewHandler;
    private LoginViewModel loginViewModel;


    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {

        this.viewHandler = viewHandler;
        this.loginViewModel = viewModelFactory.getLoginViewModel();
        loginViewModel.addListener("LOGIN_ERROR", this::onErrorLogin);
        loginViewModel.addListener("LOGIN_SUCCESS", this::onSuccessLogin);

        //Access when I press enter
        passwordFieldPassword.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                login(null);
            }
        });

    }


    private void onSuccessLogin(PropertyChangeEvent event) {
       viewHandler.refreshActualView();
        closeWindow();
    }

    private void onErrorLogin(PropertyChangeEvent event) {
        errorText.setText(Error.LOGIN.getMessage());
    }

    public void onClose(MouseEvent mouseEvent) {
        closeWindow();
    }

    public void onCreateAccount(MouseEvent mouseEvent) {
        viewHandler.openNewView(View.CREATE_ACCOUNT);

    }

    public void login(MouseEvent mouseEvent) {
        loginViewModel.login(textFieldEmail.getText(), passwordFieldPassword.getText());

    }

    public void requestPassword(MouseEvent mouseEvent) {

    }

    private void closeWindow() {
        Window window = textFieldEmail.getScene().getWindow();
        window.fireEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
