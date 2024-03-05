/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;
import java.sql.Connection;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class UserTb {
    public ArrayList<User> getAllUsers() throws SQLException {
        var connection = Connect.createConnection();
        String query = "select * from users";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            var user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setGender(resultSet.getString("gender"));
            user.setRole(resultSet.getString("role"));
            users.add(user);
        }
        connection.close();
        return users;
    }

    public boolean createUser(User user) throws SQLException {
        var connection = Connect.createConnection();
        String query = "insert into users(username, password, gender, role) values (?,?,?,?)";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, user.getUsername());
        prepareStatement.setString(2, user.getPassword());
        prepareStatement.setString(3, user.getGender());
        prepareStatement.setString(4, user.getRole());

        int rowAffected = prepareStatement.executeUpdate();
        
        connection.close();
        if(rowAffected > 0){
            return true;
        }else{
            return false;
        }

    }
    
     public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        //B1: Mo ket noi
        var connection = Connect.createConnection();
        String query = "select * from users where username like ? and password like ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, username);
        prepareStatement.setString(2, password);
        ResultSet resultSet = prepareStatement.executeQuery();

        
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setRole(resultSet.getString("role"));
            connection.close();
            return user;
        }
        return null;
    }
    
    
    public boolean insertUser(String username, String password, String gender) throws SQLException {
        // Default role is "User"
        String role = "User";

        //B1: Mo ket noi
        var connection = Connect.createConnection();

        String query = "INSERT INTO users (username, password, gender, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, role);

            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the user was successfully inserted
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
     
    public boolean updateUser(User user){
        return false;
    }
    public boolean deleteUser(User user){
        return false;
    }
    
    public void filterUser(String keyword, int pageIndex, int pageSize){
        
    }
}
