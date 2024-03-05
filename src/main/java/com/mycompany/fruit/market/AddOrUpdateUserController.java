/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;

import DataTransfer.ObjectTransfer;
import model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author x
 */
public class AddOrUpdateUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    TextField textFieldUsername;
    @FXML
    ComboBox comboBoxRole;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        var choosenUser = (User)ObjectTransfer.getInstance().getData();
        textFieldUsername.setText(choosenUser.getUsername());
        comboBoxRole.setValue(choosenUser.getRole());
        
        
    }    
    
}
