package client.core.viewHandler;

public enum View {

    CATALOG("catalogView/catalogView.fxml"),
    CREATE_ACCOUNT("createAccountView/createAccountView.fxml"),
    CREATE_CAR("createCarView/createCarView.fxml"),
    LOGIN("loginView/loginView.fxml"),
    MY_ACCOUNT("myAccountView/myAccountView.fxml"),
    MANAGE_ACCOUNT("manageAccountView/manageAccountsView.fxml"),
    MANAGE_RESERVATIONS("manageReservationsView/manageReservationsView.fxml"),
    MANAGE_CARS("manageCarsView/manageCarsView.fxml"),
    SEARCH("searchView/searchView.fxml");



    private final String path;

    private final String startPath = "../../views/";

    private View(String path) {
        this.path = startPath + path;
    }

    String getPath() {
        return this.path;
    }

}
