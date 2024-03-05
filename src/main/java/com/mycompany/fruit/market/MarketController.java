/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Connection.Connect;
import java.io.File;
import model.Fruit;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import model.SharedDataModel;
import DataTransfer.DatabaseHandler;
import DataTransfer.ProfitHandle;
public class MarketController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<Integer> comboBox1;

    @FXML
    private ComboBox<Integer> comboBox2;
    
    @FXML
    private Button tocart;
    
    @FXML
    private Button btnsearch;
   
    @FXML
    private TextField searcht;

    private List<Fruit> fruits = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    
    private SharedDataModel sharedDataModel = new SharedDataModel();
    

    
    
    // Other FXML elements...

    public ArrayList<Fruit> getAllFruits() throws SQLException {
        
        var connection = Connect.createConnection();
        String query = "select * from fruits";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<Fruit> fruits = new ArrayList<>();
        while (resultSet.next()) {
            var fruit = new Fruit();
            fruit.setFruitName(resultSet.getString("FruitName"));
            fruit.setPrice(resultSet.getDouble("Price"));
            fruit.setImgSrc(resultSet.getString("ImgSrc"));
            fruit.setColor(resultSet.getString("Color"));
            fruits.add(fruit);
        }
        connection.close();
        return fruits;
    }
    
  public ArrayList<Fruit> getFruitsByName(String fruitName) throws SQLException {
    var connection = Connect.createConnection();

    String query = "SELECT * FROM fruits WHERE FruitName = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, fruitName); // Set the parameter value
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Fruit> fruits = new ArrayList<>();

        while (resultSet.next()) {
            var fruit = new Fruit();
            fruit.setFruitName(resultSet.getString("FruitName"));
            fruit.setPrice(resultSet.getDouble("Price"));
            fruit.setImgSrc(resultSet.getString("ImgSrc"));
            fruit.setColor(resultSet.getString("Color"));
            fruits.add(fruit);
        }

        return fruits;

    } catch (SQLException e) {
        // Handle the exception according to your needs
        e.printStackTrace();
    } finally {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return null;
}

    
    @FXML
    public void handleSearchButtonAction() throws SQLException {
        String fruitName = searcht.getText().trim();

        if (!fruitName.isEmpty()) {
            ArrayList<Fruit> matchingFruits = getFruitsByName(fruitName);

            // Process the matching fruits as needed (e.g., display in UI)
            for (Fruit fruit : matchingFruits) {
                System.out.println("Fruit Name: " + fruit.getFruitName());
                System.out.println("Price: " + fruit.getPrice());
                System.out.println("ImgSrc: " + fruit.getImgSrc());
                System.out.println("Color: " + fruit.getColor());
                fruitNameLable.setText(fruit.getFruitName());
                fruitPriceLabel.setText(App.CURRENCY + fruit.getPrice());
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
                    fruitImg.setImage(image);
                } catch (Exception e) {
                    System.out.println("Error loading image: " + e.getMessage());
                    e.printStackTrace(); // Print the stack trace for detailed error information
                }
                chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
                        "    -fx-background-radius: 30;");
            }
        } else {
            System.out.println("Please enter a fruit name to search.");
        }
    }
    
    private void setChosenFruit(Fruit fruit) {
        fruitNameLable.setText(fruit.getFruitName());
        fruitPriceLabel.setText(App.CURRENCY + fruit.getPrice());
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
            fruitImg.setImage(image);
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed error information
        }
        chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }
    
   
    
    

    // Setter for sharedDataModel
    public void setSharedDataModel(SharedDataModel sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }
    
    private void addToCartClicked() {
        // Retrieve selected values from ComboBox1 and ComboBox2
        Integer selectedAmount1 = comboBox1.getValue();
        Integer selectedAmount2 = comboBox2.getValue();

        // Retrieve text values from labels
        String fruitName = fruitNameLable.getText();
        String fruitPrice = fruitPriceLabel.getText();
        
         // Ensure that selectedAmount1 and selectedAmount2 are not null
        selectedAmount1 = (selectedAmount1 != null) ? selectedAmount1 : 0;
        selectedAmount2 = (selectedAmount2 != null) ? selectedAmount2 : 0;

        // Calculate the sum of amount1 and amount2
        int amount1 = selectedAmount1 + selectedAmount2;
        
         // Access the Image from the ImageView
        Image images = fruitImg.getImage();

        // Get the URL of the Image
        String imageUrls = images.getUrl();

        // Perform actions with the retrieved values (e.g., add to cart logic)
        /*
        System.out.println("Selected Amount from ComboBox1: " + selectedAmount1);
        System.out.println("Selected Amount from ComboBox2: " + selectedAmount2);
        System.out.println("Sum of amount1 and amount2: " + amount1);
        System.out.println("Fruit Name: " + fruitName);
        System.out.println("Fruit Price: " + fruitPrice);
        System.out.println("Image URL: " + imageUrls);
        */
        // Add your logic to handle the selected values
        // ...
        
        if (sharedDataModel != null) {
            // Populate shared data model
            sharedDataModel.setAmount1(amount1);
            sharedDataModel.setFruitName2(fruitName);
            sharedDataModel.setFruitPrice(fruitPrice);
            sharedDataModel.setImageUrls(imageUrls);
            
             DatabaseHandler.insertSharedData(sharedDataModel);
            
            
            // Perform other actions
        } else {
            System.err.println("sharedDataModel is null. Make sure to initialize it.");
        }
    
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code goes here...
        
       //ProfitHandle.clearAllData(); 
        

    // Set sharedDataModel for CartController
       
        tocart.setOnAction(event -> addToCartClicked());
        
          
           
            
        

        // Other initialization code...

        // Pass the initialized SharedDataModel to other controllers
        
        // Initialize ComboBox1 with Integer values
        ObservableList<Integer> options1 = FXCollections.observableArrayList(
                2, 3, 4, 5, 6, 7, 8, 9, 10
        );
        comboBox1.setItems(options1);
        comboBox1.setPromptText("Select an option");

        // Initialize ComboBox2 with Integer values
        ObservableList<Integer> options2 = FXCollections.observableArrayList(
                20, 30
        );
        comboBox2.setItems(options2);
        comboBox2.setPromptText("Enter an integer");

        // Create a text formatter to allow only integer input
        TextFormatter<Integer> integerTextFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                0,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) { // Only allow digits
                        return change;
                    } else {
                        return null; // Reject the change
                    }
                });

        // Apply the text formatter to ComboBox2's editor
        comboBox2.getEditor().setTextFormatter(integerTextFormatter);

        // Set event handlers
        comboBox1.setOnAction(event -> comboBox2.setValue(0));
        comboBox2.setOnAction(event -> comboBox1.getSelectionModel().clearSelection());
    
        
        try {
        fruits.addAll(getAllFruits());
    } catch (SQLException e) {
        e.printStackTrace();
    }

    if (!fruits.isEmpty()) {
        setChosenFruit(fruits.get(0));

        myListener = new MyListener() {
            @Override
            public void onClickListener(Fruit fruit) {
                setChosenFruit(fruit);
            }
        };
    }

    int column = 0;
    int row = 3;
    
    

    try {
        for (Fruit fruit : fruits) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            // Trỏ đến tệp example.fxml trong thư mục resources/fxml

            fxmlLoader.setLocation(App.class.getResource("item.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ItemController itemController = fxmlLoader.getController();
            itemController.setData(fruit, myListener);

            if (column == 3) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row);

            // set grid width and height
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
} catch (IOException e) {
    e.printStackTrace();
}
  }

    
  @FXML
    private void handleLoginButton(ActionEvent event) throws IOException {
    // Thực hiện các hành động để chuyển đến trang login
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginRoot = loader.load();
        stage.setScene(new Scene(loginRoot));
        stage.show();

    // Đóng cửa sổ hiện tại
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    private void handleAboutButton(ActionEvent event) throws IOException {
    // Thực hiện các hành động để chuyển đến trang about
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutUsFruitMarket.fxml"));
        Parent aboutRoot = loader.load();
        stage.setScene(new Scene(aboutRoot));
        stage.show();

    // Đóng cửa sổ hiện tại 
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
   @FXML
    private void handleCartButton(ActionEvent event) throws IOException {
        // Load the CartController
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
        Parent cartRoot = loader.load();

       

        // Continue with your UI setup...
        

        // Show the cart window
        Stage stage = new Stage();
        stage.setScene(new Scene(cartRoot));
        stage.show();

        // Close the current window
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
     
   
}

