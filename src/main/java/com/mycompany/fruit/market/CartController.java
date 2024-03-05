/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruit.market;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import Connection.Connect;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.SharedDataModel;
import DataTransfer.DatabaseHandler;
import Connection.DataTb;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import model.Fruit;
import DataTransfer.ProfitHandle;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import javafx.scene.control.Label;
/**
 *
 * @author admin
 */
public class CartController implements Initializable{
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @FXML
    private Button tocart;
    
    @FXML
    private Label lbprice;
    
    private List<SharedDataModel> datalist = new ArrayList<>();
    
     public ArrayList<SharedDataModel> getAllData() throws SQLException {
        
        var connection = Connect.createConnection();
        String query = "select * from SharedData";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<SharedDataModel> datas = new ArrayList<>();
        while (resultSet.next()) {
            var data = new SharedDataModel();
             data.setAmount1(resultSet.getInt("amount1"));
            data.setFruitName2(resultSet.getString("fruitName"));
            data.setFruitPrice(resultSet.getString("fruitPrice"));
            data.setImageUrls(resultSet.getString("imageUrls"));
            datas.add(data);
        }
        connection.close();
        return datas;
    }
    
    public void displayTotalSales() {
        double totalSales = ProfitHandle.getSumOfSales();
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedTotalSales = df.format(totalSales);

        // Create the receipt content
        StringBuilder receiptContent = new StringBuilder();
        ArrayList<SharedDataModel> datas;
        try {
            datas = getAllData();
            for (SharedDataModel data : datas) {
                String entry = data.getFruitName2() + " X " + data.getAmount1() + " | " + data.getFruitPrice() + " -- " + "\n";
                receiptContent.append(entry).append("\n");
            }
            receiptContent.append("Total Sales: ").append(App.CURRENCY).append(formattedTotalSales);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
            return;
        }

        // Write the receipt content to "Receipt.docx"
        try (FileOutputStream fileOutputStream = new FileOutputStream("Receipt.docx")) {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(receiptContent.toString());
            document.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        }

        lbprice.setText(App.CURRENCY + formattedTotalSales);
    }
     
     
    @FXML
    private void handleMarketButton(ActionEvent event) throws IOException {
        //ProfitHandle.clearAllData();
    // Thực hiện các hành động để chuyển đến trang market
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Market.fxml"));
        Parent marketRoot = loader.load();
        stage.setScene(new Scene(marketRoot));
        stage.show();

    // Đóng cửa sổ hiện tại 
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    private void handleDB(ActionEvent event){
        ProfitHandle.clearAllData();
        System.out.println("Clear!");
        //after clear you have to click on the cart icon twice for it to display the total again
    }
    
   private ProductController productController; // Assuming you have an instance of ProductController

    // ... Other fields and methods in CartController

   
    
    @FXML
    GridPane cartcontent;
    
  
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayTotalSales();
        
        int column = 0;
        int row = 1;
    
        
        try {
        datalist.addAll(getAllData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
         double topMargin = 0; // Initial top margin
        
        try {
        for (SharedDataModel data : datalist) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource("Products.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
         AnchorPane.setTopAnchor(anchorPane, topMargin);
        
        ProductController productController = fxmlLoader.getController();
        productController.setData(data);

         if (column == 1) {
                column = 0;
                row++;
         }
         
         topMargin += 10; // Adjust this value as needed
         
        
        
        cartcontent.add(anchorPane, column++, row);
        cartcontent.setMinWidth(Region.USE_COMPUTED_SIZE);
        cartcontent.setPrefWidth(Region.USE_COMPUTED_SIZE);
        cartcontent.setMaxWidth(Region.USE_PREF_SIZE);

        cartcontent.setMinHeight(Region.USE_COMPUTED_SIZE);
        cartcontent.setPrefHeight(Region.USE_COMPUTED_SIZE);
        cartcontent.setMaxHeight(Region.USE_PREF_SIZE);
        GridPane.setMargin(anchorPane, new Insets(10));
        }
        
        
        
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        
    }
    
    
}
