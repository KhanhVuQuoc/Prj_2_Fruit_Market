/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;
import DataTransfer.ObjectTransfer;
import model.Fruit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/**
 *
 * @author admin
 */
public class AddOrUpdateFruitController implements Initializable{

      @FXML
    private TextField textFieldFruitname;
    @FXML
    private ComboBox<Double> comboBoxPrice;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        var choosenFruit = (Fruit)ObjectTransfer.getInstance().getData();
        textFieldFruitname.setText(choosenFruit.getFruitName());
        comboBoxPrice.setValue(choosenFruit.getPrice());
        
        
    }    
    
}
