package client.views.createCarView;

import client.core.ViewModelFactory;
import client.core.viewHandler.ViewHandler;
import client.views.ViewController;
import client.views.utils.images.CarImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateCarViewController implements ViewController {

    @FXML private Button addMainPhoto, addOnePhoto, addTowPhoto, addThreePhoto;
    @FXML private ImageView oneImageView, twoImageView, threeImageView, mainImageView;
    @FXML private TextField makeTextField, modelTextField, licenseTextField, yearTextField, seatsTextField, rangeTextField, kmTextField, priceTextField;
    @FXML private ComboBox modelComboBox;
    @FXML private Spinner<String> yearSpinner;
    @FXML private Text errorText;

    private ViewHandler viewHandler;
    private CreateCarViewModel createCarViewModel;
    private CarImage[] carImage = new CarImage[4];
    private BufferedImage bufferedImage = null;




    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.createCarViewModel = viewModelFactory.getCreateCarViewModel();
        addMainPhoto.setOnAction(actionEvent -> addPhoto(mainImageView,0));
        addOnePhoto.setOnAction(actionEvent -> addPhoto(oneImageView,1));
        addTowPhoto.setOnAction(actionEvent -> addPhoto(twoImageView,2));
        addThreePhoto.setOnAction(actionEvent -> addPhoto(threeImageView,3));
        makeTextField.textProperty().bindBidirectional(createCarViewModel.getMakeTextField());
        makeTextField.textProperty().bindBidirectional(createCarViewModel.makeTextFieldProperty());
        modelTextField.textProperty().bindBidirectional(createCarViewModel.modelTextFieldProperty());
        licenseTextField.textProperty().bindBidirectional(createCarViewModel.licenseTextFieldProperty());
        yearTextField.textProperty().bindBidirectional(createCarViewModel.yearTextFieldProperty());
        seatsTextField.textProperty().bindBidirectional(createCarViewModel.seatsTextFieldProperty());
        rangeTextField.textProperty().bindBidirectional(createCarViewModel.rangeTextFieldProperty());
        kmTextField.textProperty().bindBidirectional(createCarViewModel.kmTextFieldProperty());
        priceTextField.textProperty().bindBidirectional(createCarViewModel.priceTextFieldProperty());
        errorText.textProperty().bindBidirectional(createCarViewModel.errorMessageProperty());
    }

    public void onClose(MouseEvent mouseEvent) {
        Window window = addMainPhoto.getScene().getWindow();
        window.fireEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSE_REQUEST));
    }



    public void addPhoto(ImageView imageView, int position) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            carImage[position] = new CarImage(bufferedImage, file.getName());
            imageView.setImage(carImage[position].getImage());
        }
    }


    public void onCreateCar(MouseEvent mouseEvent) {
        createCarViewModel.createCar(carImage);
    }


}
