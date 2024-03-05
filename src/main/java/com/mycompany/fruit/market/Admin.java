/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author admin
 */
public class Admin implements Initializable{
    @FXML
    private Button btnUsers;
    
    @FXML
    private Button btnFruits;
    
    @FXML
    private Button btnProfit;
    
    @FXML
    private Label lbstats;
    
    @FXML
    BorderPane Adminpage;
    
     @FXML
    private void handleMarketMenuItem(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Market.fxml"));
        Parent marketRoot = loader.load();
        stage.setScene(new Scene(marketRoot));
        stage.show();

        // Đóng cửa sổ hiện tại
        Stage currentStage = (Stage) scene.getWindow();
        currentStage.hide();
    }
    
    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == btnUsers){
            try {
                // Load the content from users.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserTB.fxml"));
                Pane usersPane = loader.load();

                // Set the content of the BorderPane to the loaded content
                Adminpage.setCenter(usersPane);

                lbstats.setText("Users");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == btnFruits){
            try {
                    // Load the content from FruitTB.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FruitTB.fxml"));
                    Pane fruitsPane = loader.load();

                    // Set the content of the BorderPane to the loaded content
                    Adminpage.setCenter(fruitsPane);

                    lbstats.setText("Fruits");
                } catch (IOException e) {
                    e.printStackTrace();
                }
              
        }
        else if(event.getSource() == btnProfit){
            try {
                // Load the content from ProfitTB.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfitTB.fxml"));
                Pane profitPane = loader.load();

                // Set the content of the BorderPane to the loaded content
                Adminpage.setCenter(profitPane);

                lbstats.setText("Profit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
