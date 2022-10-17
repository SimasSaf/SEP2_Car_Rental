package client.core.viewHandler;

import client.core.ViewModelFactory;
import client.core.viewHandler.View;
import client.views.ViewController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewHandler {
    private ViewModelFactory viewModelFactory;
    private Stage stage;
    private Parent root;
    private Scene scene;
    private ViewController viewController;
    private String actualPath;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        stage = new Stage();
    }


    public void openView(View view){
        actualPath = view.getPath();
        scene = new Scene(getRoot(actualPath));
        stage.setScene(scene);
        stage.show();
    }

    public void refreshActualView() {
        scene = new Scene(getRoot(actualPath));
        stage.setScene(scene);
        stage.show();
    }

    public void openNewView(View view){
        Scene newScene = new Scene(getRoot(view.getPath()));
        NewWindow.Open(newScene, root);
    }



    private Parent getRoot(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            root = loader.load();
            viewController = loader.getController();
            viewController.init(this, viewModelFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }




}
