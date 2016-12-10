
package JDBC_MySQL;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;


public class Prepared_Statement_Demo {
    public static void main(String args[]){
        try{
            String dbURL = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull";
            String user = "root";
            String password = "rajlocalsqlkanchan16";
            Connection myCon = DriverManager.getConnection( dbURL, user, password );
            //PreparedStatement myPdStmt = null;
            //ResultSet myRs = null;
            //myPdStmt = myCon.prepareStatement("Select * From empinfo where id = ? & last_name = ?");
            PreparedStatement myPdStmt = myCon.prepareStatement("Select * From empinfo where id = ? ");
            myPdStmt.setInt(1, 3);
            //myPdStmt.setString(2, "Brown");
           ResultSet myRs = myPdStmt.executeQuery();
            
            //System.out.println(myRs);
            if(myRs.next()){
            display(myRs);
            }
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    public static void display(ResultSet myRs) throws SQLException{
        while(myRs.next()){
        int id = myRs.getInt("id");
        String firstName = myRs.getString("first_name");
        String lastName = myRs.getString("last_name");
        //System.out.printf("%i %s %s \n", id, firstName, lastName);
        System.out.println(id + " ------ " + firstName + " ------- " + lastName);
        }
    }
/*
    private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}
*/
}
