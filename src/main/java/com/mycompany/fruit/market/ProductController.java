/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;

import Connection.Connect;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.SharedDataModel;
import model.Fruit;
import model.Profit;
import DataTransfer.ProfitHandle;
/**
 *
 * @author admin
 */
public class ProductController {
    @FXML
    private ImageView Pic;
    @FXML
    private Label lbfname;
    @FXML
    private Label lbamount;
    @FXML
    private Label lbprice;
    // Initial amount

    private int currentAmount = 10; 
    
    private double sum = 0.0;
    @FXML 
    private Button incre;
    
    @FXML
    private Button decre;
    
    @FXML
    AnchorPane ProductPane;
    
    private SharedDataModel data;
    
    private Profit profit = new Profit();

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
    public void SearchFrutiName(String fruitName) throws SQLException {
       

        if (!fruitName.isEmpty()) {
            ArrayList<Fruit> matchingFruits = getFruitsByName(fruitName);

            // Process the matching fruits as needed (e.g., display in UI)
            for (Fruit fruit : matchingFruits) {
                //System.out.println("Fruit Name: " + fruit.getFruitName());
                //System.out.println("Price: " + fruit.getPrice());
                double total = currentAmount * fruit.getPrice();
                DecimalFormat df = new DecimalFormat("#.00");
                String formattedTotal = df.format(total);
                //System.out.println("Total for " + fruitName + ": $" + formattedTotal);
                
                    profit.setFruitName3(fruitName);
                    profit.setSale(total);
                    ProfitHandle.insertProfit(profit);
                
            }
        } else {
            System.out.println("Please enter a fruit name to search.");
        }
    }
    
    
    public void setData(SharedDataModel data) throws SQLException{
        this.data = data;
        lbamount.setText(String.valueOf(data.getAmount1()));
        lbfname.setText(data.getFruitName2());
        lbprice.setText(data.getFruitPrice());
        Pic.setImage(new javafx.scene.image.Image(data.getImageUrls()));
        setCurrentAmount(data.getAmount1());
        SearchFrutiName(data.getFruitName2());
    }

    public void setCurrentAmount(int newAmount) {
    this.currentAmount = newAmount;
    //System.out.println("Current Amount set to: " + newAmount);
    }
    
    @FXML
    private void initialize() {
        
        
        updateLabel(); // Set the initial value

        // Event handler for the "+" button
        incre.setOnAction(event -> handleIncrement());

        // Event handler for the "-" button
        decre.setOnAction(event -> handleDecrement());
    }

    private void handleIncrement() {
        currentAmount++;
        updateLabel();
    }

    private void handleDecrement() {
        if (currentAmount > 0) {
            currentAmount--;
            updateLabel();
        }
    }

    private void updateLabel() {
        lbamount.setText(String.valueOf(currentAmount));
    }
    
     @FXML
    private void handleDelete() {
        // Clear all children of the AnchorPane
        ProductPane.getChildren().clear();
    }
    
     
   
}
