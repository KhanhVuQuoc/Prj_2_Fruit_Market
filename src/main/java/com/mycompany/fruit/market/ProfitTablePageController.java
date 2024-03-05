/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;
import BusinessLayers.ProfitBL;
import DataTransfer.ObjectTransfer;
import model.Profit;
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

public class ProfitTablePageController implements Initializable {

    @FXML
    private BorderPane ProfitTablePage;

    private ProfitBL profitBL = new ProfitBL();

    private ObservableList<Profit> data;
    private Profit chosenProfit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();

        TableView<Profit> tableProfit = new TableView<>();

        TableColumn<Profit, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Profit, String> fruitNameColumn = new TableColumn<>("Fruit Name");
        fruitNameColumn.setCellValueFactory(new PropertyValueFactory<>("fruitName3"));

        TableColumn<Profit, Double> saleColumn = new TableColumn<>("Sale");
        saleColumn.setCellValueFactory(new PropertyValueFactory<>("sale"));

        TableColumn<Profit, Double> buyColumn = new TableColumn<>("Buy");
        buyColumn.setCellValueFactory(new PropertyValueFactory<>("buy"));

        tableProfit.getColumns().add(idColumn);
        tableProfit.getColumns().add(fruitNameColumn);
        tableProfit.getColumns().add(saleColumn);
        tableProfit.getColumns().add(buyColumn);

        tableProfit.setItems(data);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem addProfit = new MenuItem("Add");
        MenuItem updateProfit = new MenuItem("Update");
        MenuItem deleteProfit = new MenuItem("Delete");
        contextMenu.getItems().add(addProfit);

        addProfit.setOnAction(event -> {
            try {
                Stage newStage = new Stage();
                newStage.setTitle("Add Profit Window");
                var newProfit = new Profit();
                ObjectTransfer.getInstance().removeData();
                ObjectTransfer.getInstance().setData(newProfit);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("AddOrUpdateProfitController.fxml"));
                Parent addOrUpdateProfitScene = loader.load();
                var addOrUpdateProfitController = loader.getController();

                Scene newScene = new Scene(addOrUpdateProfitScene, 600, 400);
                newStage.setScene(newScene);
                newStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProfitTablePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        updateProfit.setOnAction(event -> {
            try {
                ObjectTransfer.getInstance().removeData();
                ObjectTransfer.getInstance().setData(chosenProfit);
                Stage newStage = new Stage();
                newStage.setTitle("Update Profit Window");

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("AddOrUpdateProfitController.fxml"));
                Parent addOrUpdateProfitScene = loader.load();
                var addOrUpdateProfitController = loader.getController();

                Scene newScene = new Scene(addOrUpdateProfitScene, 600, 400);
                newStage.setScene(newScene);
                newStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProfitTablePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        contextMenu.getItems().add(updateProfit);
        contextMenu.getItems().add(deleteProfit);

        tableProfit.setOnMouseClicked(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
            chosenProfit = tableProfit.getSelectionModel().getSelectedItem();
            if (event.getButton() == javafx.scene.input.MouseButton.SECONDARY) {
                contextMenu.show(tableProfit, event.getScreenX(), event.getScreenY());
            }
        });

        ProfitTablePage.setCenter(tableProfit);
    }

    private void loadData() {
        try {
            ArrayList<Profit> profits = profitBL.getAllProfits();
            data = FXCollections.observableArrayList(profits);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
