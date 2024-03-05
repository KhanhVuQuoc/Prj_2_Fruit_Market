/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import model.Profit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class ProfitTB {

    public ArrayList<Profit> getAllProfits() throws SQLException {
        var connection = Connect.createConnection();
        String query = "SELECT * FROM profit";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<Profit> profits = new ArrayList<>();
        while (resultSet.next()) {
            var profit = new Profit();
            profit.setId(resultSet.getInt("id"));
            profit.setSale(resultSet.getDouble("sale"));
            profit.setBuy(resultSet.getDouble("buy"));
            profit.setFruitName3(resultSet.getString("fruitName"));
            profits.add(profit);
        }
        connection.close();
        return profits;
    }

    public boolean createProfit(Profit profit) throws SQLException {
        var connection = Connect.createConnection();
        String query = "INSERT INTO profit (sale, buy, fruitName) VALUES (?, ?, ?)";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setDouble(1, profit.getSale());
        prepareStatement.setDouble(2, profit.getBuy());
        prepareStatement.setString(3, profit.getFruitName3());

        int rowAffected = prepareStatement.executeUpdate();

        connection.close();
        return rowAffected > 0;
    }

    public boolean updateProfit(Profit profit) throws SQLException {
        var connection = Connect.createConnection();
        String query = "UPDATE profit SET sale=?, buy=?, fruitName=? WHERE id=?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setDouble(1, profit.getSale());
        prepareStatement.setDouble(2, profit.getBuy());
        prepareStatement.setString(3, profit.getFruitName3());
        prepareStatement.setInt(4, profit.getId());

        int rowAffected = prepareStatement.executeUpdate();

        connection.close();
        return rowAffected > 0;
    }

    public boolean deleteProfit(int profitId) throws SQLException {
        var connection = Connect.createConnection();
        String query = "DELETE FROM profit WHERE id=?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, profitId);

        int rowAffected = prepareStatement.executeUpdate();

        connection.close();
        return rowAffected > 0;
    }

    public ArrayList<Profit> filterProfits(String keyword, int pageIndex, int pageSize) throws SQLException {
        var connection = Connect.createConnection();
        String query = "SELECT * FROM profit WHERE fruitName LIKE ? LIMIT ? OFFSET ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, "%" + keyword + "%");
        prepareStatement.setInt(2, pageSize);
        prepareStatement.setInt(3, (pageIndex - 1) * pageSize);

        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<Profit> filteredProfits = new ArrayList<>();
        while (resultSet.next()) {
            var profit = new Profit();
            profit.setId(resultSet.getInt("id"));
            profit.setSale(resultSet.getDouble("sale"));
            profit.setBuy(resultSet.getDouble("buy"));
            profit.setFruitName3(resultSet.getString("fruitName"));
            filteredProfits.add(profit);
        }

        connection.close();
        return filteredProfits;
    }
}
