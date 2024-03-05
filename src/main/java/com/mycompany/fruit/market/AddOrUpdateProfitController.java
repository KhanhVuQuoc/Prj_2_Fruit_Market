/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;
import DataTransfer.ObjectTransfer;
import model.Profit;
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
public class AddOrUpdateProfitController implements Initializable{
     @FXML
    private TextField textFieldProfit;

    @FXML
    private ComboBox<Double> comboBoxProfit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        var choosenProfit = (Profit)ObjectTransfer.getInstance().getData();
        textFieldProfit.setText(choosenProfit.getFruitName3());
        comboBoxProfit.setValue(choosenProfit.getSale());
        
    }   
}
