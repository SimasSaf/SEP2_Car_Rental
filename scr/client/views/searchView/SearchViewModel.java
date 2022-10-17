package client.views.searchView;


import client.model.cars.CarsModel;

import java.time.LocalDate;

public class SearchViewModel {

    CarsModel carsModel;

    public SearchViewModel(CarsModel carsModel) {
        this.carsModel = carsModel;
    }

    public void onSearch(String selectedItem, String item, LocalDate dateFrom, LocalDate dateTo) {

    }

}
