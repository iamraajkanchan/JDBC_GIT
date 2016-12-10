//INSERT INTO RAJ.CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, "NAME", ADDRESSLINE1, ADDRESSLINE2, CITY, "STATE", PHONE, FAX, EMAIL, CREDIT_LIMIT) 
//	VALUES (410, 'M', '10096', 'Yankee Computer Repair Ltd', '9653 211th Ave', 'Floor 4', 'New York', 'NY', '212-555-0191', '212-555-0197', 'www.nycompltd@repair.example.com', 25000);

package Java_Oracle;

import java.sql.*;

public class Insert_Demo {

    public static void main(String args[]){
        try{
            String dbURL = "jdbc:derby://localhost:1527/My_Sample";
            String user = "raj";
            String password = "loveivy";
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);
            Statement myStmt =  myCon.createStatement();
            String insertQuery = "INSERT INTO RAJ.CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) \n" +
            "VALUES (410, 'M', '10096', 'Yankee Computer Repair Ltd', '9653 211th Ave', 'Floor 4', 'New York', 'NY', '212-555-0191', '212-555-0197', 'www.nycompltd@repair.example.com', 25000)"; 
            // You should not add semi-colon (;) like SQL query in the SQL query passed in JDBC
            myStmt.executeUpdate(insertQuery);
            
            System.out.println("Row is inserted in Raj.Customer Table");
            
        }
        catch (Exception E){
            System.out.println(E.getMessage());
        }
    }
    
}
