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
import model.User;
import Connection.UserTb;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SignupController implements Initializable {

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
    private TextField uname;
    
    @FXML
     private PasswordField passw;
    
    @FXML
    private RadioButton rbfm;
    
    @FXML
    private void handleSignUpButton(ActionEvent event) throws SQLException {
    String username = uname.getText().trim();
    String password = passw.getText().trim();
    String gender = rbfm.isSelected() ? "Female" : "Male";

    UserTb userTB = new UserTb();
    boolean success = userTB.insertUser(username, password, gender);
    
    /// Thuc hien so sanh username vua tao voi cac username trc do, neu trung thi bao loi
    
    if (success) {
        System.out.println("User successfully signed up");
        showSuccessAlert("User successfully signed up!");
        // Add any additional logic or UI updates here
    } else {
        System.out.println("Failed to sign up");
        showErrorAlert("Failed to sign up!");
        // Add error handling or display an error message
    }
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

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thành công");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
