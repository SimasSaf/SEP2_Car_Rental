package client.views.searchView;

import client.core.viewHandler.View;
import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.extraObjectsView.LoadPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SearchViewController implements ViewController {

    @FXML private ComboBox<String> locationComboBox;
    @FXML private ComboBox<String> rangeComboBox;
    @FXML private DatePicker fromDatePicker;
    @FXML private DatePicker toDatePicker;
    @FXML private HBox menuBarHBox;

    private Label selectedLocation = new Label();



    private SearchViewModel searchViewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        searchViewModel = viewModelFactory.getSearchViewModel();
        menuBarHBox.getChildren().add(new LoadPanel().load("../extraObjectsView/menuBar/menuBar.fxml", viewHandler));
        rangeComboBox.getItems().setAll("0...1,000 DKK", "1,000...3,000 DKK", "3,000...5,000 DKK", "> 5,000 DKK");
        locationComboBox.getItems().setAll("Horsens", "Aarhus", "Kobenhavn", "Odense");

    }



    public void searchAction(ActionEvent actionEvent) {
        viewHandler.openView(View.CATALOG);
        searchViewModel.onSearch(locationComboBox.getSelectionModel().getSelectedItem(),
                                 rangeComboBox.getSelectionModel().getSelectedItem(),
                                 fromDatePicker.getValue(),
                                 toDatePicker.getValue());

    }
}
