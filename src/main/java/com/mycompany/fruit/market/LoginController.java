/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import Connection.UserTb;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginController implements Initializable {
    
    @FXML
    private void handleSignUpButton(ActionEvent event) throws IOException {
    // Thực hiện các hành động để chuyển đến trang đăng ký
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent signUpRoot = loader.load();
        stage.setScene(new Scene(signUpRoot));
        stage.show();

    // Đóng cửa sổ hiện tại (trang đăng nhập)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;
    
    private List<User> users = new ArrayList<>();
    
    @FXML
private void handleLoginButton(ActionEvent event) throws IOException, SQLException {
    String username = usernameTextField.getText();
    String password = passwordTextField.getText();

    // Check user credentials and role against the database
    UserTb userTB = new UserTb();
    User user = userTB.getUserByUsernameAndPassword(username, password);

    if (user != null) {
        if (user.getRole().equals("Admin")) {
            // Role is Admin, go to masterpage.fxml

            // Tạo và hiển thị cửa sổ mới chứa trang masterpage
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("masterpage.fxml"));
            Parent masterpageRoot = loader.load();
            stage.setScene(new Scene(masterpageRoot));
            stage.show();

            // Đóng cửa sổ hiện tại (trang đăng nhập)
            ((Node) (event.getSource())).getScene().getWindow().hide();

            System.out.println("Login success as Admin");
            showSuccessAlert("Login success as Admin!");
        } else {
            // Role is not Admin, go to market.fxml

            // Tạo và hiển thị cửa sổ mới chứa trang market
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("market.fxml"));
            Parent marketRoot = loader.load();
            stage.setScene(new Scene(marketRoot));
            stage.show();

            // Đóng cửa sổ hiện tại (trang đăng nhập)
            ((Node) (event.getSource())).getScene().getWindow().hide();

            System.out.println("Login success");
            showSuccessAlert("Login success!");
        }
    } else {
        // Tên đăng nhập hoặc mật khẩu không chính xác, xử lý thông báo lỗi hoặc các hành động khác
        System.out.println("Login failed");
        // Add code for error handling, such as displaying an error message
        showErrorAlert("Login failed!");
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
        showSuccessAlert("You are moving to the market page!");
    // Đóng cửa sổ hiện tại 
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
