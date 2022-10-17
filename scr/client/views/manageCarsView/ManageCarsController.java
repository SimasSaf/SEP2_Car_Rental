package client.views.manageCarsView;

import client.core.viewHandler.View;
import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.extraObjectsView.LoadPanel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import shared.transferObjects.Car;

import java.awt.image.TileObserver;


public class ManageCarsController implements ViewController {

    @FXML private ComboBox statusComboBox;
    @FXML private ImageView bigImageView;
    @FXML private ImageView oneImageView;
    @FXML private ImageView twoImageView;
    @FXML private ImageView threeImageView;

    @FXML private TableView<Car> carTableView;
    @FXML private TableColumn<Car, String> idTableColumn;
    @FXML private TableColumn<Car, String> makeTableColumn;
    @FXML private TableColumn<Car, String> modelTableColumn;
    @FXML private TableColumn<Car, String> yearTableColumn;

    @FXML public Text makeText, modelText, idText;
    @FXML public TextField yearTextField, typeTextField, rangeTextField, kmTextField, licenseTextField, priceTextField;


    @FXML private HBox menuBarHBox;

    private ViewHandler viewHandler;
    private ManageCarsViewModel manageCarsViewModel;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        manageCarsViewModel = viewModelFactory.getManageCarsViewModel();
        menuBarHBox.getChildren().add(new LoadPanel().load("../extraObjectsView/menuBar/menuBar.fxml", viewHandler));
        statusComboBox.getItems().addAll("Available","Service", "Cleaning", "Unavailable");

        manageCarsViewModel.loadCars();
        carTableView.setItems(manageCarsViewModel.getCars());
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("carId"));
        makeTableColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelTableColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTableColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        //set the cars's table selection


        carTableView.setRowFactory(carTableView -> {
            TableRow<Car> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 1 && (!row.isEmpty())) {
                    Car carSelected = row.getItem();
                    manageCarsViewModel.selectCar(carSelected);

                }
            });
            return row;
        });

        //BINDING////


    }

    public void onCancel(MouseEvent mouseEvent) {
        System.out.println("this controller");
    }

    public void onSave(MouseEvent mouseEvent) {
    }

    public void oneImageView(MouseEvent mouseEvent) {
        swipeImages(oneImageView);
    }

    public void twoImageView(MouseEvent mouseEvent) {
        swipeImages(twoImageView);
    }

    public void threeImageView(MouseEvent mouseEvent) {
        swipeImages(threeImageView);
    }

    private void swipeImages(ImageView image) {
        Image tempImageView = bigImageView.getImage();
        bigImageView.setImage(image.getImage());
        image.setImage(tempImageView);

    }

    public void onUpdate(MouseEvent mouseEvent) {
    }

    public void onCreateCar(MouseEvent mouseEvent) {
        viewHandler.openNewView(View.CREATE_CAR);
    }

    public void onDelete(MouseEvent mouseEvent) {
    }
}
