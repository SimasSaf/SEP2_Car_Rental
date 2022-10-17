package client.views.myReservationsView;

import client.core.ViewModelFactory;
import client.core.viewHandler.ViewHandler;
import client.views.ViewController;
import client.views.extraObjectsView.ExtraController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.transferObjects.Reservation;
import shared.transferObjects.user.User;

public class MyReservationsController implements ExtraController {

    ViewHandler viewHandler;
    MyReservationsViewModel myReservationsViewModel;

    @FXML
    private TableView<Reservation> reservationsTableView;
    @FXML private TableColumn<Reservation, String> idTableColumn;
    @FXML private TableColumn<Reservation, String> startTableColumn;
    @FXML private TableColumn<Reservation, String> endTableColumn;
    @FXML private TableColumn<Reservation, String> carIDTableColumn;


    @FXML private TextField carIdTextField;
    @FXML private DatePicker fromDatePicker, toDatePicker;

    @Override
    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        myReservationsViewModel = viewModelFactory.getMyReservationsViewModel();
        myReservationsViewModel.loadUsers();
        reservationsTableView.setItems(myReservationsViewModel.getUsers());
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        startTableColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTableColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        carIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("carId"));

        reservationsTableView.setRowFactory(reservationTableView -> {
            TableRow<Reservation> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 1 && (!row.isEmpty())) {
                    Reservation reservationSelected = row.getItem();
                    myReservationsViewModel.selectReservation(reservationSelected);
                }
            });
            return row;
        });


        carIdTextField.textProperty().bindBidirectional(carIDTableColumn.idProperty());



    }

    public void onCancel(MouseEvent mouseEvent) {
    }

    public void onUpdate(MouseEvent mouseEvent) {
        myReservationsViewModel.onUpdate();
    }


    public void onDelete(MouseEvent mouseEvent) {
    }
}
