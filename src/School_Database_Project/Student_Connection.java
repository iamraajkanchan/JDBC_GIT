package School_Database_Project;

import java.sql.*;
import java.util.Scanner;
import java.awt.event.KeyEvent;

public class Student_Connection extends Student {

    String dbURL = "jdbc:derby://localhost:1527/School_Database";
    String user = "school_admin";
    String password = "school_admin";

    public void menu() {
        System.out.println("Welcome to School Database");
        System.out.println("Please enter your options");

        System.out.println("Type 1 to look all the database");
        System.out.println("Type 2 to insert records in database");
        System.out.println("Type 3 to deleted record from database");
        System.out.println("Type 4 to update existing record in database");
        Scanner input = new Scanner(System.in);

        switch (input.nextInt()) {
            case 1:
                show_database();
                break;
            case 2:
                insert_record();
                break;
            case 3:
                delete_record();
                break;
            case 4:
                update_record();
            default:
                System.out.println("Please enter a valid input between 1 to 4");
        }
    }

    public void show_database() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);
            Statement myStmt = myCon.createStatement();
            String select_query = "SELECT * FROM STUDENT";
            ResultSet myRs = myStmt.executeQuery(select_query);
            while (myRs.next()) {
                roll_no = myRs.getInt("roll_no");
                fName = myRs.getString("fName");
                lName = myRs.getString("lName");
                class_div = myRs.getString("class_div");
                email = myRs.getString("email");
                System.out.println(roll_no + " -- " + fName + " -- " + lName + " -- " + class_div + " -- " + email);
            }

        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

    public void insert_record() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);
            Statement myStmt = myCon.createStatement();
            /*
            String select_query = "SELECT * FROM STUDENT";
            ResultSet myRs = myStmt.executeQuery(select_query);
            int i = 0;
            while (myRs.last()) {
                i++;
            }
            System.out.println("Your record will added after " + i + " row");
            myRs.close();
            myStmt.close();
             */
            System.out.println("");
            Scanner input = new Scanner(System.in);
            System.out.print("Roll No: ");
            setRoll_no(input.nextInt());
            System.out.println("");
            System.out.print("First Name: ");
            setfName(input.next());
            System.out.println("");
            System.out.print("Last Name: ");
            setlName(input.next());
            System.out.println("");
            System.out.print("Class & Divsion (eg:- V-A): ");
            setClass_div(input.next());
            System.out.println("");
            System.out.print("Email Address: ");
            setEmail(input.next());
            Scanner t = new Scanner(System.in);

            KeyEvent e = null;
            System.out.println("Press 1 to add the records or press 2 to get back to the Menu");
            switch (t.nextInt()) {
                //case KeyEvent.VK_ENTER:
                case 1:
                    //Statement myInsertStmt = myCon.createStatement();
                    //CREATE TABLE STUDENT (ROLL_NO INTEGER NOT NULL, FNAME VARCHAR(50) NOT NULL, LNAME VARCHAR(50) NOT NULL, CLASS_DIV VARCHAR(50) NOT NULL, EMAIL VARCHAR(100) NOT NULL);
                    String insert_query = "INSERT INTO SCHOOL_ADMIN.STUDENT (ROLL_NO, FNAME, LNAME, CLASS_DIV, EMAIL) VALUES (?,?,?,?,?)";
                    PreparedStatement myInsertStmt = myCon.prepareStatement(insert_query);
                    myInsertStmt.setInt(1, getRoll_no());
                    myInsertStmt.setString(2, getfName());
                    myInsertStmt.setString(3, getlName());
                    myInsertStmt.setString(4, getClass_div());
                    myInsertStmt.setString(5, getEmail());
                    int row_affected = myInsertStmt.executeUpdate();
                    //int row_affected = myInsertStmt.executeUpdate(insert_query);
                    System.out.println(row_affected + " row is inserted in Student Database");

                    break;
                //case KeyEvent.VK_ESCAPE:
                case 2:
                    menu();
                    break;
                default:
                    System.out.println("Please enter a valid input");
            }

        } catch (Exception E) {
            E.getMessage();
        }
    }

    public void delete_record() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a Roll Number to delete record from database");
            String delete_query = "DELETE FROM STUDENT WHERE ROLL_NO = " + input.nextInt();
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);
            Statement myStmt = myCon.createStatement();
            int row_affected = myStmt.executeUpdate(delete_query);
            System.out.println(row_affected + " records is deleted from Student Database");

        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

    public void update_record() {
        try {
            Scanner input = new Scanner(System.in);
            Scanner input_roll_no = new Scanner(System.in);
            Scanner temp_data = new Scanner(System.in);
            int row_affected = 0;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);

            System.out.println("Please enter the Roll Number which you wish to edit");
            int temp_roll_no = input_roll_no.nextInt();
            System.out.println("Please select a record /(except Roll Number /)you wish to update in Student Database");
            System.out.println("1 --------- First Name");
            System.out.println("2 --------- Last Name");
            System.out.println("3 --------- Class-Division");
            System.out.println("4 --------- Email Address");

            switch (input.nextInt()) {
                case 1:
                    System.out.print("Enter the First Name: ");
                    setfName(temp_data.next());
                    Statement myFNameStmt = myCon.createStatement();
                    String update_FName = "UPDATE STUDENT SET FNAME = '" + getfName() + "' WHERE ROLL_NO = " + temp_roll_no;
                    row_affected = myFNameStmt.executeUpdate(update_FName);
                    System.out.println(row_affected + " row is updated in Student Database");
                    break;
                case 2:
                    System.out.print("Enter the Last Name: ");
                    setlName(temp_data.next());
                    Statement myLNameStmt = myCon.createStatement();
                    String update_LName = " UPDATE STUDENT SET LNAME = '" + getlName() + "' WHERE ROLL_NO = " + temp_roll_no;
                    row_affected = myLNameStmt.executeUpdate(update_LName);
                    System.out.println(row_affected + " row is updated in Student Database");
                    break;
                case 3:
                    System.out.println("Enter the Class and Division /(e.g- V-A/)");
                    setClass_div(temp_data.next());
                    Statement myCDStmt = myCon.createStatement();
                    String update_CD = " UPDATE STUDENT SET CLASS_DIV = '" + getClass_div() + "' WHERE ROLL_NO = " + temp_roll_no;
                    row_affected = myCDStmt.executeUpdate(update_CD);
                    System.out.println(row_affected + " row is updated in Student Database");
                    break;
                case 4:
                    System.out.println("Enter the Email Id");
                    setEmail(temp_data.next());
                    Statement myEmailStmt = myCon.createStatement();
                    String update_Email = " UPDATE STUDENT SET EMAIL = '" + getEmail() + "' WHERE ROLL_NO = " + temp_roll_no;
                    row_affected = myEmailStmt.executeUpdate(update_Email);
                    System.out.println(row_affected + " row is updated in Student Database");
                    break;
                default:
                    System.out.println("Please enter the above options only");
                    update_record();
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }
}
