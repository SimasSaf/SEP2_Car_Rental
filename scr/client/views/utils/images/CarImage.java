package client.views.utils.images;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class CarImage implements Serializable {
    private static final String locationPath = "../OhmElectricCarRentals/scr/server/carImagesSystem/";
    private String fileName, extension;
    private byte[] rawImage;

    public CarImage() {
        this.fileName = "";
        this.extension = "jpeg";
        try {
            this.rawImage = giveMeRawImage(ImageIO.read(getClass().getResource("car_model_default.png")));
        } catch (IOException e) {
            this.rawImage = null;
        }
    }

    public CarImage(BufferedImage bufferedImage, String file) {
        this.fileName = file.substring(0, file.lastIndexOf('.'));
        this.extension = file.substring(file.lastIndexOf('.') + 1, (file.length()));
        this.rawImage = giveMeRawImage(bufferedImage);
    }

    private byte[] giveMeRawImage(BufferedImage bufferedImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(bufferedImage, extension, baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public Image getImage() {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = javax.imageio.ImageIO.read(new ByteArrayInputStream(rawImage));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            return image;
        } catch (IOException e) {
        }
        return new Image(getClass().getResourceAsStream("car_model_default.png"));
    }

    public BufferedImage getBufferedImage() {
        InputStream is = new ByteArrayInputStream(rawImage);
        BufferedImage bufferedImage = null;
        try {
             bufferedImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // add a text on top on the image
        Graphics2D g = bufferedImage.createGraphics();
        g.setFont(new Font("TimesRoman", Font.BOLD, 300));
        g.setColor(Color.LIGHT_GRAY);

        g.drawString("OHM", 200, 300);
        return bufferedImage;
    }


    public void imageIoWrite(int carID) {
        String completeFileName = carID + "-" + this.fileName + "." + this.extension;
        try {
            new File(locationPath + carID).mkdirs();
            ImageIO.write(getBufferedImage(), "jpg", new File(locationPath + carID + "/" + completeFileName));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        System.out.println("Images were written succesfully.");
    }

    public static String getLocationPath() {
        return locationPath;
    }

}
