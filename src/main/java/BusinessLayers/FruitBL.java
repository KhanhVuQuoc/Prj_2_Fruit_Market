/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayers;
import Connection.Connect;
import Connection.FruitTB;
import model.Fruit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class FruitBL {
    FruitTB fruitTB = new FruitTB();

    public ArrayList<Fruit> getAllFruits() throws SQLException {
        return fruitTB.getAllFruits();
    }

    public boolean createFruit(Fruit fruit) throws SQLException {
        return fruitTB.createFruit(fruit);
    }

    public boolean updateFruit(Fruit fruit) throws SQLException {
        return fruitTB.updateFruit(fruit);
    }

    public boolean deleteFruit(int fruitId) throws SQLException {
        return fruitTB.deleteFruit(fruitId);
    }

    public ArrayList<Fruit> filterFruits(String keyword, int pageIndex, int pageSize) throws SQLException {
        return fruitTB.filterFruits(keyword, pageIndex, pageSize);
    }
}
