
package Java_Oracle;

import java.sql.*;

public class Scrollable_ResultSet_Demo {

    public static void main(String args[]){
        try{
            String dbURL = "jdbc:derby://localhost:1527/My_Sample";
            String user = "raj";
            String password = "loveivy";
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);
            Statement myStmt =  myCon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            String selectQuery = "SELECT * FROM RAJ.CUSTOMER";
            ResultSet myRs = myStmt.executeQuery(selectQuery);
//CREATE TABLE CUSTOMER (CUSTOMER_ID INTEGER NOT NULL, DISCOUNT_CODE CHAR(1) NOT NULL, ZIP VARCHAR(10) NOT NULL, "NAME" VARCHAR(30), ADDRESSLINE1 VARCHAR(30), ADDRESSLINE2 VARCHAR(30), CITY VARCHAR(25), "STATE" CHAR(2), PHONE CHAR(12), FAX CHAR(12), EMAIL VARCHAR(40), CREDIT_LIMIT INTEGER, PRIMARY KEY (CUSTOMER_ID));
            
            System.out.println("CUSTOMER_ID DISCOUNT_CODE ZIP \"NAME\" ADDRESSLINE1 ADDRESSLINE2 CITY \"STATE\" PHONE FAX EMAIL CREDIT LIMIT");
            
            while(myRs.next()){
                int cust_id = myRs.getInt("CUSTOMER_ID");
                String dis_code = myRs.getString("DISCOUNT_CODE");
                int zip = myRs.getInt("ZIP");
                String name = myRs.getString("Name");
                String add1 = myRs.getString("ADDRESSLINE1");
                String add2 = myRs.getString("ADDRESSLINE2");
                String city = myRs.getString("CITY");
                String state = myRs.getString("STATE");
                String phone = myRs.getString("PHONE");
                String fax = myRs.getString("FAX");
                String email = myRs.getString("EMAIL");
                int cd_limit = myRs.getInt("CREDIT_LIMIT");
                System.out.print(cust_id + " " + dis_code + " " + zip + " " + name + " " + add1 + " " + add2 + " " + city + " " + state + " " + phone + " " + fax + " " + email + " " + cd_limit);   
                System.out.println("");
            }
            
            myRs.last();
            
            while(myRs.previous()){
                int cust_id = myRs.getInt("CUSTOMER_ID");
                String dis_code = myRs.getString("DISCOUNT_CODE");
                int zip = myRs.getInt("ZIP");
                String name = myRs.getString("Name");
                String add1 = myRs.getString("ADDRESSLINE1");
                String add2 = myRs.getString("ADDRESSLINE2");
                String city = myRs.getString("CITY");
                String state = myRs.getString("STATE");
                String phone = myRs.getString("PHONE");
                String fax = myRs.getString("FAX");
                String email = myRs.getString("EMAIL");
                int cd_limit = myRs.getInt("CREDIT_LIMIT");
                System.out.print(cust_id + " " + dis_code + " " + zip + " " + name + " " + add1 + " " + add2 + " " + city + " " + state + " " + phone + " " + fax + " " + email + " " + cd_limit);   
                System.out.println("");
            }
            
        }
        catch(Exception E){
            System.out.println(E.getMessage());
        }
    }
}
