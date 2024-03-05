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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AboutUsFruitMarketController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    @FXML
    private void handleMarketButton(ActionEvent event) throws IOException {
    // Thực hiện các hành động để chuyển đến trang market
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Market.fxml"));
        Parent marketRoot = loader.load();
        stage.setScene(new Scene(marketRoot));
        stage.show();

    // Đóng cửa sổ hiện tại 
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
