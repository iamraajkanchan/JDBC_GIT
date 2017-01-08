package Bank_Database_Project;

import java.sql.*;
import java.util.Scanner;
import java.util.*;

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
                menu();
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
            System.out.println("");
            System.out.println("Would you like to Sort further too... ");
            System.out.println("Type Sort to continue sorting or anyting else to go to the main menu");
            Scanner input = new Scanner(System.in);
            if ("Sort".equals(input.next()) || "sort".equals(input.next())) {
                select_result.close();
                select_statement.close();
                myCon.close();
                sort_resultSet();
            } else {
                quit();
            }
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
            System.out.println("");
            System.out.println("You are in Add Account Section.");
            System.out.print("Account Number: ");
            int account_number = input.nextInt();
            if (!verify_account(account_number)) {
                insert_statement.setInt(1, account_number);
            } else {
                System.out.println("");
                System.out.println("This Account Number already exists in Account Table");
                System.out.println("Please Enter a Unique Account Number");
                //Please give the provision of Goto 
                insert_statement.close();
                myCon.close();
                add_account();
            }
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
            if (verify_account(ref_account)) {
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
                switch (update_menu.nextInt()) {
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
            } else {
                System.out.println("");
                System.out.println("This Account Number do not exist in Account Table");
                System.out.println("Please Enter a Valid Account Number");
            }
            quit();
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

    @Override
    public void delete_account() {
        try {
            Scanner ref_account = new Scanner(System.in);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            PreparedStatement delete_record = null;
            int account_number = 0;
            System.out.println("");
            System.out.println("You are in Delete Section");
            System.out.println("Please Enter the Account Number which you wish to delete");
            account_number = ref_account.nextInt();
            if (verify_account(account_number)) {
                String delete_query = "DELETE FROM ACCOUNT WHERE ACCOUNT_NO = ?";
                delete_record = myCon.prepareStatement(delete_query);
                delete_record.setInt(1, account_number);
                row_affected = delete_record.executeUpdate();
                System.out.println(row_affected + " row is deleted from Account Table");
                delete_record.close();
            } else {
                System.out.println("");
                System.out.println("This Account Number do not exist in Account Table");
                System.out.println("Please Enter a Valid Account Number");
                myCon.close();
                delete_account();
            }
            quit();
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

    @Override
    public void withdraw() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }

    }

    @Override
    public void deposit() {
        try {
            Scanner input_amount = new Scanner(System.in);
            Scanner ref_account = new Scanner(System.in);
            int amount = 0;
            int account_number = 0;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            PreparedStatement deposit_record = null;
            System.out.println("");
            System.out.println("You are in Deposit Section");
            System.out.println("First Please Enter The Existing Account Number ");
            account_number = ref_account.nextInt();
            if (!verify_account(account_number)) {
                System.out.println("Please Enter The Amount You Wish To Deposit");
                amount = input_amount.nextInt();
                String deposit_query = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_NO = ?";
                deposit_record = myCon.prepareStatement(deposit_query);
                int new_balance = current_balance(account_number) + amount;
                deposit_record.setInt(1, new_balance);
                deposit_record.setInt(2, account_number);
                row_affected = deposit_record.executeUpdate();
                System.out.println(row_affected + " row is updated in Account Table");
                deposit_record.close();
            } else {
                System.out.println("");
                System.out.println("This Account Number do not exist in Account Table");
                System.out.println("Please Enter a Valid Account Number");
                myCon.close();
                deposit();
            }
        } catch (Exception E) {
            E.getMessage();
        }
    }

    public void quit() {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Please enter Yes to quit the application or press other keys twice to continue!!!");
        if ("Yes".equals(input.next())) {
            System.exit(0);
        } else {
            menu();
        }
    }

    public boolean verify_account(int ref_account) {
        boolean verified = false;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            Statement verify_statement = myCon.createStatement();
            String load_account = "SELECT ACCOUNT_NO FROM ACCOUNT";
            ResultSet verify_result = verify_statement.executeQuery(load_account);
            while (verify_result.next()) {
                if (ref_account == verify_result.getInt(1)) {
                    verified = true;
                } else {
                    verified = false;
                }
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
        return verified;
    }

    public boolean is_withdrawable(int balance, int amount) {
        boolean proceed = false;
        int current_amount = 0;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            Statement get_current_amount_statement = myCon.createStatement();
            String current_amount_query = "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = " + account_no;
            ResultSet get_current_amount_result = get_current_amount_statement.executeQuery(current_amount_query);
            current_amount = get_current_amount_result.getInt(1);
            if (current_amount >= amount) {
                proceed = true;
            } else {
                System.out.println("");
                System.out.println("This Account Do Not Have Sufficient Balance To WithDraw The Amount: " + amount);
                proceed = false;
            }
            get_current_amount_result.close();
            get_current_amount_statement.close();
            myCon.close();
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
        return proceed;
    }

    public int current_balance(int account_no) {
        int current_amount = 0;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            Statement get_current_amount_statement = myCon.createStatement();
            String current_amount_query = "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = " + account_no;
            ResultSet get_current_amount_result = get_current_amount_statement.executeQuery(current_amount_query);
            current_amount = get_current_amount_result.getInt(1);
            get_current_amount_result.close();
            get_current_amount_statement.close();
            myCon.close();
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
        return current_amount;
    }

    public void sort_resultSet() {
        ArrayList fname_list = new ArrayList<>();
        ArrayList lname_list = new ArrayList<>();
        ArrayList email_list = new ArrayList<>();
        ArrayList balance_list = new ArrayList<>();
        ArrayList age_list = new ArrayList<>();
        Scanner input_sort = new Scanner(System.in);
        Iterator it = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection myCon = DriverManager.getConnection(dbUrl, user, password);
            Statement sort_statement = myCon.createStatement();
            String sort_query = "SELECT * FROM ACCOUNT";
            ResultSet sort_resultset = sort_statement.executeQuery(sort_query);
            while (sort_resultset.next()) {
            //fname_list.add(sort_resultset.getString(2));
            //lname_list.add(sort_resultset.getString(3));
            //email_list.add(sort_resultset.getString(4));
            //balance_list.add(sort_resultset.getInt(7));
            //age_list.add(sort_resultset.getInt(8));
            fname_list.add(sort_resultset.getArray(2));
            lname_list.add(sort_resultset.getArray(3));
            email_list.add(sort_resultset.getArray(4));
            balance_list.add(sort_resultset.getArray(7));
            age_list.add(sort_resultset.getArray(8));
           }

            System.out.println("");
            System.out.println("You are in Sort Section");
            System.out.println("Enter 1 to sort First Name");
            System.out.println("Enter 2 to sort Second Name");
            System.out.println("Enter 3 to sort Email Id's");
            System.out.println("Enter 4 to sort Balance");
            System.out.println("Enter 5 to sort Age");

            switch (input_sort.nextInt()) {
                case 1:
                    Collections.sort(fname_list);
                    it = fname_list.iterator();
                    System.out.println("");
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                case 2:
                    Collections.sort(lname_list);
                    it = lname_list.iterator();
                    System.out.println("");
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                case 3:
                    Collections.sort(email_list);
                    it = email_list.iterator();
                    System.out.println("");
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                case 4:
                    Collections.sort(balance_list);
                    it = balance_list.iterator();
                    System.out.println("");
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                case 5:
                    Collections.sort(age_list);
                    it = age_list.iterator();
                    System.out.println("");
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                default:
                    System.out.println("Please Enter the valid Input");
                    sort_resultSet();
            }
            quit();
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

}
