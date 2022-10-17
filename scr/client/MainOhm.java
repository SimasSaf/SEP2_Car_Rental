package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.viewHandler.View;
import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainOhm extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        ClientFactory clientFactory = new ClientFactory();
        ModelFactory modelFactory = new ModelFactory(clientFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);

        viewHandler.openView(View.SEARCH);

    }


}
