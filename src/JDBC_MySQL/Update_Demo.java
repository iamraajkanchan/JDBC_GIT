
package JDBC_MySQL;
import java.sql.*;
public class Update_Demo {
    public static void main(String args[]){
        try{
         //Get a connection    
        String dbURL = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "********";
        Connection myCon = DriverManager.getConnection(dbURL, user, password);
        //Create a statement    
        Statement myStmt = myCon.createStatement();
        // Update the database executing sql query using excuteUpdate method of Statement object       
        myStmt.executeUpdate("Update empinfo set last_name = 'Brown' where id = 3 ");
        // Can't execute Select * From empinfo at this moment, will figure it out later.
        /*
        ResultSet myRs = myStmt.executeQuery("Selet * From empinfo");
        while(myRs.next()){
            System.out.print(myRs.getString(2));
            System.out.println(" " + myRs.getString(3));
        }*/
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }
    }

}
