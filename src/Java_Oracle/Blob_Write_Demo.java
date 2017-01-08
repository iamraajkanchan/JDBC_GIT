
package Java_Oracle;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Blob_Write_Demo implements KeyListener{

    public static void main(String args[]){
        write_blob();
    }

    // ask if static methods can only call static methods, need to clear this doubt, this is urgent
    public static void write_blob(){
        try{
            String dbURL = "jdbc:derby://localhost:1527/My_Sample";
            String user = "raj";
            String password = "loveivy";
            Scanner file_name = new Scanner(System.in);
            Scanner input = new Scanner(System.in);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbURL, user, password);
            String selectQuery = "UPDATE CUSTOMER SET SOFT_COPY = ? WHERE CUSTOMER_ID = ?";
            PreparedStatement myPreStmt = myCon.prepareStatement(selectQuery);
            // Location of file should be in the source file E:\NIIT Training\My_Java_Programs\GitHub\JDBC_GitHub
            System.out.println("Please type the \"file_name\" you would like to store in database");
            File blobFile = new File("E:\\NIIT Training\\My_Java_Programs\\GitHub\\JDBC_GitHub\\Soft_Copy\\My_Sample\\Customer\\" + file_name.next() + ".txt");
            FileInputStream inputFile = new FileInputStream (blobFile);
            myPreStmt.setBinaryStream(1, inputFile);
            System.out.println("Please enter the Customer_ID");
            myPreStmt.setInt(2, input.nextInt());
            
            System.out.println("Reading File " +  blobFile.getAbsolutePath());
            System.out.println("Storing Resume in Database: " + blobFile);
            myPreStmt.executeUpdate();
            
            System.out.println("Completed Successfully");
            myPreStmt.close();
            myCon.close();
            System.out.println("Press Shift to Continue or Press Esc to Exit the Application");
            
            KeyListener kl = null;
            AWTEventMonitor.addKeyListener(kl);
        }
        catch(Exception E){
            System.out.println( E.getMessage() );
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                write_blob();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
