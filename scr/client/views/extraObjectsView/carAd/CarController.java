package client.views.extraObjectsView.carAd;

import client.core.ViewModelFactory;
import client.core.viewHandler.ViewHandler;
import client.views.extraObjectsView.ExtraController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CarController implements ExtraController {
    @FXML private Button reserveButton;
    @FXML private Text nameText;
    String name;


    public void init(String car) {
        this.name = car;
        nameText.setText(car);
    }

    public void onReserve(ActionEvent actionEvent) {

        System.out.println(name);
    }

    @Override
    public void init(ViewHandler viewHandler) {

    }

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {

    }
}
