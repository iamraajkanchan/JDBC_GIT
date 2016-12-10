
package Java_Oracle;

import java.sql.*;

public class Delete_Demo {
    
    public static void main (String args[]){
        
        try{
            String dbURL = "jdbc:derby://localhost:1527/My_Sample";
            String user = "raj";
            String password = "loveivy";
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);
            Statement myStmt =  myCon.createStatement();
            String deleteQuery = "DELETE FROM RAJ.CUSTOMER WHERE EMAIL = 'www.nycompltd@repair.example.com' ";
            int row_affected = myStmt.executeUpdate(deleteQuery);
            
            System.out.println( row_affected + " record is deleted from Raj.Customer table");
        }
        
        catch(Exception E){
            System.out.println(E.getMessage());
        }
        
    }

}
