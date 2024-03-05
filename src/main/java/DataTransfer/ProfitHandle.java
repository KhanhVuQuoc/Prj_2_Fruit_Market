/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransfer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Profit;
/**
 *
 * @author admin
 */
public class ProfitHandle {
    private static final String URL = "jdbc:sqlite:FruitsMarket.db"; // Replace with your actual database URL

    // Establish a connection to the database
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Insert data into the profit table
    public static void insertProfit(Profit profit) {
        String sql = "INSERT INTO profit (sale, buy, fruitName) VALUES (?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, profit.getSale());
            preparedStatement.setDouble(2, profit.getBuy());
            preparedStatement.setString(3, profit.getFruitName3());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     // Get the sum of all sale values in the profit table
    public static double getSumOfSales() {
        String sql = "SELECT SUM(sale) FROM profit";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getDouble(1); // Get the sum from the first column
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0.0; // Return 0.0 if there's an error or no data
    }
    
    public static void clearAllData() {
        String sql = "DELETE FROM profit";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
