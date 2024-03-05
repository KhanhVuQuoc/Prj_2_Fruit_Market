/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;
import model.SharedDataModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class DataTb {
    public ArrayList<SharedDataModel> getAllData() throws SQLException {
        var connection = Connect.createConnection();
        String query = "select * from SharedData";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<SharedDataModel> datas = new ArrayList<>();
        while (resultSet.next()) {
            var data = new SharedDataModel();
            data.setId(resultSet.getInt("id"));
            data.setAmount1(resultSet.getInt("amount1"));
            data.setFruitName2(resultSet.getString("fruitName"));
            data.setFruitPrice(resultSet.getString("fruitPrice"));
            data.setImageUrls(resultSet.getString("imageUrls"));
            datas.add(data);
        }
        connection.close();
        return datas;
    }

    public boolean createUser(SharedDataModel data) throws SQLException {
        var connection = Connect.createConnection();
        String query = "insert into SharedData(fruitName, fruitPrice, imageUrls) values (?,?,?)";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, data.getFruitName2());
        prepareStatement.setString(2, data.getFruitPrice());
        prepareStatement.setString(3, data.getImageUrls());
       

        int rowAffected = prepareStatement.executeUpdate();
        
        connection.close();
        if(rowAffected > 0){
            return true;
        }else{
            return false;
        }

    }
    
    public boolean updateData(SharedDataModel sdm){
        return false;
    }
    public boolean deleteData(SharedDataModel sdm){
        return false;
    }
    
    public void filterData(String keyword, int pageIndex, int pageSize){
        
    }
}
