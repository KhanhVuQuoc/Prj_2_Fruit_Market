/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayers;
import Connection.Connect;
import Connection.ProfitTB;
import model.Profit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class ProfitBL {
    private ProfitTB profitTB = new ProfitTB();

    public ArrayList<Profit> getAllProfits() throws SQLException {
        return profitTB.getAllProfits();
    }

    public boolean createProfit(Profit profit) throws SQLException {
        return profitTB.createProfit(profit);
    }

    public boolean updateProfit(Profit profit) throws SQLException {
        return profitTB.updateProfit(profit);
    }

    public boolean deleteProfit(int profitId) throws SQLException {
        return profitTB.deleteProfit(profitId);
    }

    public ArrayList<Profit> filterProfits(String keyword, int pageIndex, int pageSize) throws SQLException {
        return profitTB.filterProfits(keyword, pageIndex, pageSize);
    }
}
