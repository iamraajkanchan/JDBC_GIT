package Bank_Database_Project;

import java.sql.*;
import java.util.Scanner;

public class AccountDAOImpl extends Account implements AccountDAO {

    String dbUrl = "jdbc:derby://localhost:1527/Bank_Database";
    String user = "bank_admin";
    String password = "bank_admin";
    int row_affected = 0;

    public void menu() {

        System.out.println("");
        System.out.println("Welcome to Interactive Bank Database");
        System.out.println("Please Enter One of The Options");
        System.out.println("Press 1 to Show Detail of Account Holders in Saraswat Bank");
        System.out.println("Press 2 to Add Account Holders in Saraswat Bank");
        System.out.println("Press 3 to Delete Account Holders from Saraswat Bank");
        System.out.println("Press 4 to Update Record of Account Holders in Saraswat Bank");
        System.out.println("Press 5 to Deposit Money in Account in Saraswat Bank");
        System.out.println("Press 6 to Withdraw Money from Account in Saraswat Bank");
        System.out.println("Press 7 to Quit the App");

        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 1:
                show_account();
                break;
            case 2:
                add_account();
                break;
            case 3:
                delete_account();
                break;
            case 4:
                update_account();
                break;
            case 5:
                deposit();
                break;
            case 6:
                withdraw();
                break;
            case 7:
                quit();
                break;
            default:
                System.out.println("Please Enter Valid Input");
        }
    }

    @Override
    public void show_account() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            Statement select_statement = myCon.createStatement();
            String select_query = "SELECT * FROM ACCOUNT";
            ResultSet select_result = select_statement.executeQuery(select_query);
            while (select_result.next()) {

                setAccount_no(select_result.getInt(1));
                setfName(select_result.getString(2));
                setlName(select_result.getString(3));
                setEmail(select_result.getString(4));
                setContact(select_result.getLong(5));
                setAddress(select_result.getString(6));
                setBalance(select_result.getInt(7));
                setAge(select_result.getInt(8));

                System.out.println(getAccount_no() + " --- " + getfName() + " --- " + getlName() + " --- " + getEmail() + " --- " + getContact() + " --- " + getAddress() + " --- " + getBalance() + " --- " + getAge());

            }
            quit();
        } catch (Exception E) {
            E.getMessage();
        }
    }

    @Override
    public void add_account() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            Scanner input = new Scanner(System.in);
            String insert_query = "INSERT INTO BANK_ADMIN.ACCOUNT (ACCOUNT_NO, FNAME, LNAME, EMAIL, CONTACT, ADDRESS, BALANCE, AGE) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement insert_statement = myCon.prepareStatement(insert_query);
            System.out.println("You are in Add Account Section.");
            System.out.print("Account Number: ");
            insert_statement.setInt(1, input.nextInt());
            System.out.println("");
            System.out.print("First Name: ");
            insert_statement.setString(2, input.next());
            System.out.println("");
            System.out.print("Last Name: ");
            insert_statement.setString(3, input.next());
            System.out.println("");
            System.out.print("Email Address: ");
            insert_statement.setString(4, input.next());
            System.out.println("");
            System.out.print("Contact Number: ");
            insert_statement.setLong(5, input.nextLong());
            System.out.println("");
            System.out.print("Address: ");
            insert_statement.setString(6, input.next());
            System.out.println("");
            System.out.print("Balance: ");
            insert_statement.setInt(7, input.nextInt());
            System.out.println("");
            System.out.print("Age: ");
            insert_statement.setInt(8, input.nextInt());
            row_affected = insert_statement.executeUpdate();
            System.out.println(row_affected + " row is added in inserted in Bank Database");
            quit();
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

    @Override
    public void update_account() {
        try {
            Scanner update_menu = new Scanner(System.in);
            Scanner account_update = new Scanner(System.in);
            Scanner input = new Scanner(System.in);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("You are in Update Section");
            System.out.println("Please Enter the Account Number Which You Want To Update");
            int ref_account = account_update.nextInt();
            System.out.println("");
            System.out.println("Please Select The Below Options To Update");
            System.out.println("");
            System.out.println("1 ----- First Name");
            System.out.println("2 ----- Last Name");
            System.out.println("3 ----- Email Address");
            System.out.println("4 ----- Contact Details");
            System.out.println("5 ----- Address");
            System.out.println("6 ----- Balance");
            System.out.println("7 ----- Age");
            switch(update_menu.nextInt()){
                case 1:
                    String first_name_update_query = "UPDATE ACCOUNT SET FNAME = ? WHERE ACCOUNT_NO = ?";
                    PreparedStatement first_name_update = myCon.prepareStatement(first_name_update_query);
                    first_name_update.setString(1, input.next());
                    first_name_update.setInt(2, ref_account);
                    row_affected = first_name_update.executeUpdate();
                    System.out.println(row_affected + " row is updated in Account Table");
                    break;
                case 2:
                    String second_name_update_query = "UPDATE ACCOUNT SET LNAME = ? WHERE ACCOUNT_NO = ?";
                    PreparedStatement second_name_update = myCon.prepareStatement(second_name_update_query);
                    second_name_update.setString(1, input.next());
                    second_name_update.setInt(2, ref_account);
                    row_affected = second_name_update.executeUpdate();
                    System.out.println(row_affected + " row is updated in Account Table");
                    break;
                case 3:
                    String email_update_query = "UPDATE ACCOUNT SET EMAIL = ? WHERE ACCOUNT_NO = ?";
                    PreparedStatement email_update = myCon.prepareStatement(email_update_query);
                    email_update.setString(1, input.next());
                    email_update.setInt(2, ref_account);
                    row_affected = email_update.executeUpdate();
                    System.out.println(row_affected + " row is updated in Account Table");
                    break;
                case 4:
                    String contact_update_query = "UPDATE ACCOUNT SET CONTACT = ? WHERE ACCOUNT_NO = ?";
                    PreparedStatement contact_update = myCon.prepareStatement(contact_update_query);
                    contact_update.setLong(1, input.nextLong());
                    contact_update.setInt(2, ref_account);
                    row_affected = contact_update.executeUpdate();
                    System.out.println(row_affected + " row is updated in Account Table");
                    break;
                case 5:
                    String address_update_query = "UPDATE ACCOUNT SET ADDRESS = ? WHERE ACCOUNT_NO = ?";
                    PreparedStatement address_update = myCon.prepareStatement(address_update_query);
                    address_update.setString(1, input.next());
                    address_update.setInt(2, ref_account);
                    row_affected = address_update.executeUpdate();
                    System.out.println(row_affected + " row is updated in Account Table");
                    break;
                case 6:
                    String balance_update_query = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_NO = ?";
                    PreparedStatement balance_update = myCon.prepareStatement(balance_update_query);
                    balance_update.setInt(1, input.nextInt());
                    balance_update.setInt(2, ref_account);
                    row_affected = balance_update.executeUpdate();
                    System.out.println(row_affected + " row is updated in Account Table");
                    break;
                case 7:
                    String age_update_query = "UPDATE ACCOUNT SET AGE = ? WHERE ACCOUNT_NO = ?";
                    PreparedStatement age_update = myCon.prepareStatement(age_update_query);
                    age_update.setInt(1, input.nextInt());
                    age_update.setInt(2, ref_account);
                    row_affected = age_update.executeUpdate();
                    System.out.println(row_affected + " row is updated in Account Table");
                    break;
                default:
                    System.out.println("Please Enter A Valid Input");
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

    @Override
    public void delete_account() {

    }

    @Override
    public void withdraw() {

    }

    @Override
    public void deposit() {

    }

    public void quit() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter YES to quit the application or press other keys twice to continue!!!");
        if ("Yes".equals(input.next())) {
            System.exit(0);
        } else {
            menu();
        }
    }

}
