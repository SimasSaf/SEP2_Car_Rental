package client.core;

import client.views.catalogView.CatalogViewModel;
import client.views.createAccountView.CreateAccountViewModel;
import client.views.createCarView.CreateCarViewModel;
import client.views.loginView.LoginViewModel;
import client.views.manageAccountView.ManageAccountsViewModel;
import client.views.manageCarsView.ManageCarsViewModel;
import client.views.myAccountView.MyAccountViewModel;
import client.views.myReservationsView.MyReservationsViewModel;
import client.views.searchView.SearchViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;

    private CatalogViewModel catalogViewModel;
    private CreateAccountViewModel createAccountViewModel;
    private CreateCarViewModel createCarViewModel;
    private LoginViewModel loginViewModel;
    private ManageAccountsViewModel manageAccountsViewModel;
    private ManageCarsViewModel manageCarsViewModel;
    private SearchViewModel searchViewModel;
    private MyAccountViewModel myAccountViewModel;
    private MyReservationsViewModel myReservationsViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public CatalogViewModel getCatalogViewModel() {
        if (catalogViewModel == null)
        return new CatalogViewModel();
        return catalogViewModel;
    }

    public CreateAccountViewModel getCreateAccountViewModel() {
        if (createAccountViewModel == null)
        return new CreateAccountViewModel(modelFactory.getUsersModel());
        return createAccountViewModel;
    }

    public CreateCarViewModel getCreateCarViewModel() {
        if (createCarViewModel == null)
            return new CreateCarViewModel(modelFactory.getCarsModel());
        return createCarViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        if (loginViewModel == null)
            return new LoginViewModel(modelFactory.getUsersModel());
        return loginViewModel;
    }

    public ManageAccountsViewModel getManageAccountsViewModel() {
        if (manageAccountsViewModel == null)
            return new ManageAccountsViewModel(modelFactory.getUsersModel());
        return manageAccountsViewModel;
    }

    public ManageCarsViewModel getManageCarsViewModel() {
        if (manageCarsViewModel == null)
            return new ManageCarsViewModel(modelFactory.getCarsModel());
        return manageCarsViewModel;
    }

    public SearchViewModel getSearchViewModel() {
        if (searchViewModel == null)
            return new SearchViewModel(modelFactory.getCarsModel());
        return searchViewModel;
    }

    public MyAccountViewModel getMyAccountViewModel() {
        if (myAccountViewModel == null)
            return new MyAccountViewModel(modelFactory.getUsersModel());
        return myAccountViewModel;
    }

    public MyReservationsViewModel getMyReservationsViewModel() {
        if (myReservationsViewModel == null)
            return new MyReservationsViewModel(modelFactory.getReservationModel());
        return myReservationsViewModel;
    }
}
