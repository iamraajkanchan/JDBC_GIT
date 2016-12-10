package JDBC_MySQL;
import java.sql.*;

public class Insert_Demo {
public static void main(String args[]){
    try{
    //Get a connection    
    String dbURL = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull";
    String user = "root";
    String password = "********";
    Connection myCon = DriverManager.getConnection(dbURL, user, password);
    //Create a statement    
    Statement myStmt = myCon.createStatement();
    //Execute a statement to insert or update tables in a database
    myStmt.executeUpdate("Insert into empinfo values (3, 'David', 'Brown')");
    //Execute a statement to insert or update tables in a database
    myStmt.executeUpdate("Insert into empinfo values (4, 'Jagoda', 'Trela')");
    ResultSet myRs = myStmt.executeQuery("Select * From empinfo");
    while(myRs.next()){
        System.out.print(myRs.getString("first_name"));
        System.out.println(" " + myRs.getString(3));
    }
    }
    catch(SQLException e){
        System.out.println(e.toString());
    }
}
}
