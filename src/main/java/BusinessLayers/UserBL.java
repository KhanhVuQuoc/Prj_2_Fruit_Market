/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayers;

import Connection.Connect;
import Connection.UserTb;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author x
 */
public class UserBL {
    UserTb userTb = new UserTb();
    
    public ArrayList<User> getAllUsers() throws SQLException {
        var users = userTb.getAllUsers();
        return users;
    }

    

    public boolean createUser(User user) throws SQLException {
        var result = userTb.createUser(user);
        return result;
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
