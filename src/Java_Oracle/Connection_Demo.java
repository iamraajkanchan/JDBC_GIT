
package Java_Oracle;

import java.sql.*;

public class Connection_Demo {
    public static void main(String args[]){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String dbURL = "jdbc:derby://localhost:1527/My_Sample";
            String user = "raj";
            String password = "loveivy";
            Connection myCon = DriverManager.getConnection(dbURL,user,password);
            Statement myStmt = myCon.createStatement();
            String myQuery = "SELECT * FROM CUSTOMER";
            ResultSet myRs = myStmt.executeQuery(myQuery);
            
            while(myRs.next()){
                System.out.println(myRs.next());
            }
        }
        catch(Exception E){
            System.out.println(E.getMessage());
        }
    }
}
