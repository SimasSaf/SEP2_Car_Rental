package client.views.catalogView;

import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.extraObjectsView.LoadPanel;
import client.views.extraObjectsView.carAd.CarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.ArrayList;

public class CatalogViewController implements ViewController {
    @FXML private FlowPane brandFlowPane;
    @FXML private FlowPane kindFlowPane;
    @FXML private Pane pane;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox paneVBox;
    @FXML private BorderPane borderPane;


    @FXML private Slider sliderPrice;
    @FXML private Slider sliderSeat;

    @FXML private HBox menuBarHBox;

    @FXML private CheckBox checkBox = new CheckBox("Other");
    @FXML private CheckBox checkBox2 = new CheckBox("Other2");

    private ArrayList<String> list = new ArrayList<>();

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        menuBarHBox.getChildren().add(new LoadPanel().load("../extraObjectsView/menuBar/menuBar.fxml", viewHandler));
        brandFlowPane.getChildren().add(checkBox);
        kindFlowPane.getChildren().add(checkBox2);

        borderPane.setBottom(null);



        list.add("Tesla");
        list.add("Ford");
        list.add("BMW");
        list.add("Audi");
        list.add("Fiat");
        list.add("Apple");

        showCars(list.size());

    }



    private void showCars(int nCars) {
        paneVBox.getChildren().clear();
        for (int i = 0; i < nCars; i++) {
            String car = list.get(i);
            Pane newLoadedPane = null;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../extraObjectsView/carAd/car.fxml"));
                newLoadedPane = loader.load();
                CarController viewController = loader.getController();
                viewController.init(car);
            } catch (IOException e) {
                e.printStackTrace();
            }


            paneVBox.getChildren().add(newLoadedPane);

        }
        scrollPane.setContent(paneVBox);
    }

    public void logInAction(MouseEvent mouseEvent) {

        }

    public void refreshList(InputMethodEvent inputMethodEvent) {
        System.out.println("Change");
    }

    public void refreshMouse(MouseEvent mouseEvent) {
        showCars((int) sliderSeat.getValue());
    }

    public void actionDone(MouseEvent mouseEvent) {
        System.out.println("yess");
    }
}
