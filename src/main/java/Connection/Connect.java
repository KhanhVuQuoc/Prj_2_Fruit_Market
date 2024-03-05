/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author admin
 */
public class Connect {
    public static Connection createConnection() throws SQLException{
        String rootFolder = System.getProperty("user.dir");
        String databaseLocation = Paths.get(rootFolder, "Fruit_Market_DB.db").toString();
        String connectionString = "jdbc:sqlite:"+databaseLocation;
        var connection = DriverManager.getConnection(connectionString);
        return connection;
        
    }
    public static void closeConnection(Connection connection) throws SQLException{
        connection.close();
    }
}
