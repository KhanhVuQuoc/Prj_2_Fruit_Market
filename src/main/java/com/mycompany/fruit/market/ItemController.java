package com.mycompany.fruit.market;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Fruit;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(fruit);
    }

    private Fruit fruit;
    private MyListener myListener;

    public void setData(Fruit fruit, MyListener myListener) {
        this.fruit = fruit;
        this.myListener = myListener;
        nameLabel.setText(fruit.getFruitName());
        priceLable.setText(App.CURRENCY + fruit.getPrice());
        //Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
        //img.setImage(image);
        try {
        // Construct the file path using the fruit's image source
        String imagePath = "C:\\Users\\admin\\OneDrive\\Documents\\NetBeansProjects\\fruit-market\\src\\main\\resources\\values\\img\\" + fruit.getImgSrc();
        
        File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                System.out.println("Image file not found: " + imagePath);
                return;
            }

            // Create an Image object using the file path
            Image image = new Image(imageFile.toURI().toString());

            // Set the image to the ImageView
            img.setImage(image);
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed error information
        }
    }
}
