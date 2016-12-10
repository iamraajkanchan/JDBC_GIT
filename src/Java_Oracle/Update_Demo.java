
package Java_Oracle;

import java.sql.*;

public class Update_Demo {
    
    public static void main(String args[]){
        
        try{
            String dbURL = "jdbc:derby://localhost:1527/My_Sample";
            String user = "raj";
            String password = "loveivy";
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);
            Statement myStmt =  myCon.createStatement();
            String updateQuery = "UPDATE RAJ.CUSTOMER SET CREDIT_LIMIT = 30000 WHERE EMAIL = 'www.nycompltd@repair.example.com'";
            
            int row_affected = myStmt.executeUpdate(updateQuery);
            
            System.out.println( row_affected + " rows in Raj.Customer table is updated");
        }
        catch (Exception E){
            System.out.println(E.getMessage());
        }
    }

}
