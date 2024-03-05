/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransfer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.SharedDataModel;
/**
 *
 * @author admin
 */
public class DatabaseHandler {
    
    private static final String URL = "jdbc:sqlite:FruitsMarket.db"; // Replace with your actual database URL

    // Establish a connection to the database
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Insert data into the SharedData table
    public static void insertSharedData(SharedDataModel sharedData) {
        String sql = "INSERT INTO SharedData (amount1, fruitName, fruitPrice, imageUrls) VALUES (?, ?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, sharedData.getAmount1());
            preparedStatement.setString(2, sharedData.getFruitName2());
            preparedStatement.setString(3, sharedData.getFruitPrice());
            preparedStatement.setString(4, sharedData.getImageUrls());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

