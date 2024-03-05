/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;
import model.Fruit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class FruitTB {
    
    public ArrayList<Fruit> getAllFruits() throws SQLException {
        var connection = Connect.createConnection();
        String query = "SELECT * FROM fruits";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<Fruit> fruits = new ArrayList<>();
        while (resultSet.next()) {
            var fruit = new Fruit();
            fruit.setId(resultSet.getInt("id"));
            fruit.setFruitName(resultSet.getString("Fruitname"));
            fruit.setImgSrc(resultSet.getString("ImgSrc"));
            fruit.setPrice(resultSet.getDouble("Price"));
            fruit.setColor(resultSet.getString("Color"));
            fruits.add(fruit);
        }
        connection.close();
        return fruits;
    }

    public boolean createFruit(Fruit fruit) throws SQLException {
        var connection = Connect.createConnection();
        String query = "INSERT INTO fruits(Fruitname, ImgSrc, Price, Color) VALUES (?, ?, ?, ?)";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, fruit.getFruitName());
        prepareStatement.setString(2, fruit.getImgSrc());
        prepareStatement.setDouble(3, fruit.getPrice());
        prepareStatement.setString(4, fruit.getColor());

        int rowAffected = prepareStatement.executeUpdate();

        connection.close();
        return rowAffected > 0;
    }

    public boolean updateFruit(Fruit fruit) throws SQLException {
        var connection = Connect.createConnection();
        String query = "UPDATE fruits SET Fruitname=?, ImgSrc=?, Price=?, Color=? WHERE id=?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, fruit.getFruitName());
        prepareStatement.setString(2, fruit.getImgSrc());
        prepareStatement.setDouble(3, fruit.getPrice());
        prepareStatement.setString(4, fruit.getColor());
        prepareStatement.setInt(5, fruit.getId());

        int rowAffected = prepareStatement.executeUpdate();

        connection.close();
        return rowAffected > 0;
    }

    public boolean deleteFruit(int fruitId) throws SQLException {
        var connection = Connect.createConnection();
        String query = "DELETE FROM fruits WHERE id=?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, fruitId);

        int rowAffected = prepareStatement.executeUpdate();

        connection.close();
        return rowAffected > 0;
    }

    public ArrayList<Fruit> filterFruits(String keyword, int pageIndex, int pageSize) throws SQLException {
        var connection = Connect.createConnection();
        String query = "SELECT * FROM fruits WHERE Fruitname LIKE ? LIMIT ? OFFSET ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, "%" + keyword + "%");
        prepareStatement.setInt(2, pageSize);
        prepareStatement.setInt(3, (pageIndex - 1) * pageSize);

        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<Fruit> filteredFruits = new ArrayList<>();
        while (resultSet.next()) {
            var fruit = new Fruit();
            fruit.setId(resultSet.getInt("id"));
            fruit.setFruitName(resultSet.getString("Fruitname"));
            fruit.setImgSrc(resultSet.getString("ImgSrc"));
            fruit.setPrice(resultSet.getDouble("Price"));
            fruit.setColor(resultSet.getString("Color"));
            filteredFruits.add(fruit);
        }

        connection.close();
        return filteredFruits;
    }
}
