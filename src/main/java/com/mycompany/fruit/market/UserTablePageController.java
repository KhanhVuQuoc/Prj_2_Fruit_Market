/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;

import BusinessLayers.UserBL;
import DataTransfer.ObjectTransfer;
import model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author x
 */
public class UserTablePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    BorderPane UserTablePage;

    UserBL userBL = new UserBL();

    ObservableList<User> data;
    User choonsenUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        //Tao ra bang
        TableView<User> tableUser = new TableView<>();
        //Tao ra cac cot
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, Integer> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, Integer> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        //Them cot vao bang
        tableUser.getColumns().add(idColumn);
        tableUser.getColumns().add(usernameColumn);
        tableUser.getColumns().add(roleColumn);

        //DAt du lieu vao bang
        tableUser.setItems(data);

        //Tao context menu cho su kien an chuot phai
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addUser = new MenuItem("Add");
        MenuItem updateUser = new MenuItem("Update");
        MenuItem deleteUser = new MenuItem("Delete");
        contextMenu.getItems().add(addUser);

        var currentUser = new User();
        addUser.setOnAction(event -> {
            try {
                //Cua so
                Stage newStage = new Stage();
                newStage.setTitle("Save user window");
                var newUser = new User();
                ObjectTransfer.getInstance().removeData();
                ObjectTransfer.getInstance().setData((Object) newUser);

                //Lay trang addOrUpdateUser de tao ra 1 sence
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("addOrUpdateUser.fxml"));
                Parent addOrUpdateUserSence = loader.load();
                var addOrUpdateUserController = loader.getController();
                //Sence (Canh)

                Scene newScene = new Scene(addOrUpdateUserSence, 600, 400);
                newStage.setScene(newScene);
                newStage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserTablePageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        updateUser.setOnAction(event -> {
            try {
                ObjectTransfer.getInstance().removeData();
                ObjectTransfer.getInstance().setData((Object) choonsenUser);
                //Cua so
                Stage newStage = new Stage();
                newStage.setTitle("Save user window");
                //Lay trang addOrUpdateUser de tao ra 1 sence
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("addOrUpdateUser.fxml"));
                Parent addOrUpdateUserSence = loader.load();
                var addOrUpdateUserController = loader.getController();
                //Sence (Canh)
                
                Scene newScene = new Scene(addOrUpdateUserSence, 600, 400);
                newStage.setScene(newScene);
                newStage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserTablePageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        contextMenu.getItems().add(updateUser);

        contextMenu.getItems().add(deleteUser);

        tableUser.setOnMouseClicked(event -> {

            //Dong no lai
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
            choonsenUser = tableUser.getSelectionModel().getSelectedItem();

            if (event.getButton() == javafx.scene.input.MouseButton.SECONDARY) {

                //gan contextMenu vao vi tru cua tro chuot dc an
                contextMenu.show(tableUser, event.getScreenX(), event.getScreenY());

            }

        });

        //Add bang vao borderpane
        UserTablePage.setCenter(tableUser);
    }

    private void loadData() {
        try {
            ArrayList<User> users = userBL.getAllUsers();
            data = FXCollections.observableArrayList(users);
        } catch (Exception e) {
        }

    }

}
