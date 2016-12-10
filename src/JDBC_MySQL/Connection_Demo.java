package JDBC_MySQL;

import java.sql.*;
public class Connection_Demo {

    public static void main(String[] args) {
        try{
        /*
            Step 1: Go to the Services Window and connect MySQL Server at localhost:3306
            Step 2: Connect the database you want to use in e.g. :- test
            Step 3: Do the same when such instance again occurs 
            Step 4: Get the property of database by right clicking on it and use Database URL not the Database name
            Step 5: Include jar file i.e. "mysql-connector-java-5.1.40-bin.jar" which you can easily found it on internet. The versions may vary over the ages.
            Step 6: Start coding you are ready to go
            Step 7: Please not the above instructions are to be followed when you are trying to connect MYSQL
            Step 9: Other variations are still to discover, will find it and add the instructions below.
        */
        
        // Get a connection
        String dbURL = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "********";
        Connection myCon = DriverManager.getConnection(dbURL, user, password);
        // Create a statement throught the connection you made
        Statement myStmt = myCon.createStatement();
        // Execute a statement and in store in an object of myRs
        ResultSet myRs = myStmt.executeQuery("Select * From empinfo");
        //Process the result set
        while(myRs.next()){
            System.out.print(myRs.getString("first_name")); // here name of the column is used.
            System.out.println(" " + myRs.getString(3)); // here index of the column is used.
        }
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }
    }

    private static Connection DriverManager(String dbURL, String user, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
