
package JDBC_MySQL;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class Delete_Demo {
    public static void main(String args[]){
        try{
        String dbURL = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "********";
        Connection myCon = DriverManager.getConnection(dbURL, user, password);
        Statement myStmt = myCon.createStatement();
        int rows_affected = myStmt.executeUpdate("Delete from empinfo where id = 3");
            System.out.println("Rows Affected " + rows_affected);
            System.out.println("Query Complete");
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }
    }
}
