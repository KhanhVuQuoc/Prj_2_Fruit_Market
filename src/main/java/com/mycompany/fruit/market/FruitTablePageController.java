/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;
import BusinessLayers.FruitBL;
import DataTransfer.ObjectTransfer;
import model.Fruit;
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
 *
 * @author admin
 */
public class FruitTablePageController implements Initializable {

    @FXML
    BorderPane FruitTablePage;

    FruitBL fruitBL = new FruitBL();

    ObservableList<Fruit> data;
    Fruit choosenFruit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();

        TableView<Fruit> tableFruit = new TableView<>();

        TableColumn<Fruit, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Fruit, String> fruitNameColumn = new TableColumn<>("Fruit Name");
        fruitNameColumn.setCellValueFactory(new PropertyValueFactory<>("fruitName"));

        TableColumn<Fruit, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Fruit, String> imgSrcColumn = new TableColumn<>("Image Source");
        imgSrcColumn.setCellValueFactory(new PropertyValueFactory<>("imgSrc"));

        TableColumn<Fruit, String> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        tableFruit.getColumns().add(idColumn);
        tableFruit.getColumns().add(fruitNameColumn);
        tableFruit.getColumns().add(priceColumn);
        tableFruit.getColumns().add(imgSrcColumn);
        tableFruit.getColumns().add(colorColumn);

        tableFruit.setItems(data);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem addFruit = new MenuItem("Add");
        MenuItem updateFruit = new MenuItem("Update");
        MenuItem deleteFruit = new MenuItem("Delete");
        contextMenu.getItems().add(addFruit);

        addFruit.setOnAction(event -> {
            try {
                Stage newStage = new Stage();
                newStage.setTitle("Add Fruit Window");
                var newFruit = new Fruit();
                ObjectTransfer.getInstance().removeData();
                ObjectTransfer.getInstance().setData((Object) newFruit);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("AddOrUpdateFruitController.fxml"));
                Parent addOrUpdateFruitScene = loader.load();
                var addOrUpdateFruitController = loader.getController();

                Scene newScene = new Scene(addOrUpdateFruitScene, 600, 400);
                newStage.setScene(newScene);
                newStage.show();
            } catch (IOException ex) {
                Logger.getLogger(FruitTablePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        updateFruit.setOnAction(event -> {
            try {
                ObjectTransfer.getInstance().removeData();
                ObjectTransfer.getInstance().setData((Object) choosenFruit);
                Stage newStage = new Stage();
                newStage.setTitle("Update Fruit Window");

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("AddOrUpdateFruitController.fxml"));
                Parent addOrUpdateFruitScene = loader.load();
                var addOrUpdateFruitController = loader.getController();

                Scene newScene = new Scene(addOrUpdateFruitScene, 600, 400);
                newStage.setScene(newScene);
                newStage.show();
            } catch (IOException ex) {
                Logger.getLogger(FruitTablePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        contextMenu.getItems().add(updateFruit);
        contextMenu.getItems().add(deleteFruit);

        tableFruit.setOnMouseClicked(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
            choosenFruit = tableFruit.getSelectionModel().getSelectedItem();
            if (event.getButton() == javafx.scene.input.MouseButton.SECONDARY) {
                contextMenu.show(tableFruit, event.getScreenX(), event.getScreenY());
            }
        });

        FruitTablePage.setCenter(tableFruit);
    }

    private void loadData() {
        try {
            ArrayList<Fruit> fruits = fruitBL.getAllFruits();
            data = FXCollections.observableArrayList(fruits);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
