package jdbcdemo;

import Employee_Database_Project.Employee;
import java.sql.*;
import java.util.Scanner;

public class EmployeeDaoImpl extends Employee implements EmployeeDao {

    public void yesno()
    {
        System.out.println("Do You Want to continue?");
        String s=sc.next();
        if(s.equals("y") || s.equals("yes"))
        {
            menu();
        }
    }
    public void menu() {
        System.out.println("----------Menu----------");
        System.out.println("1. Add Employee");
        System.out.println("2. Delete Employee");
        System.out.println("3. Update Employee");
        System.out.println("Choose One Option");
        System.out.println("");

        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                addEmployee();
                break;
            case 2:
                deleteEmployee();
                break;
            case 3:
                updateEmployee();
                break;
            default:
                System.out.println("Invalid Option Please Try Again......");
                menu();
                break;

        }
        yesno();

    }
    Scanner sc = new Scanner(System.in);

    @Override
    public void addEmployee() {
        try {
            System.out.println("Enter Your Employee Id");
            setEmp_ID(sc.nextInt());
            System.out.println("Enter Your First Name");
            setFname(sc.next());
            System.out.println("Enter Your Last Name");
            setLname(sc.next());
            System.out.println("Enter Your Email Id");
            setEmail(sc.next());
            System.out.println("Enter Salary");
            setSalary(sc.nextInt());

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Employee", "Employee", "Employee");
            PreparedStatement ps = con.prepareStatement("insert into Employee values(?,?,?,?,?)");
            ps.setInt(1, getEmp_ID());
            ps.setString(2, getFname());
            ps.setString(3, getLname());
            ps.setString(4, getEmail());
            ps.setInt(5, getSalary());
            ps.executeUpdate();

            System.out.println("Record Saved Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEmployee() {

        try {
            System.out.println("Enter Your Employee Id");
            setEmp_ID(sc.nextInt());
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Employee", "Employee", "Employee");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("delete from employee where emp_id=" + getEmp_ID() + "");
            System.out.println("Deleted Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEmployee() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Employee", "Employee", "Employee");
            System.out.println("Enter Your Employee Id");
            setEmp_ID(sc.nextInt());
            PreparedStatement ps = null;
            System.out.println("--------Menu-------");
            System.out.println("1. Update First Name");
            System.out.println("2. Update Last Name");
            System.out.println("3. Update Email Id");
            System.out.println("4. Update Salary");
            System.out.println("Choose an option");
            System.out.println("");

            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter First Name which you want to update");
                    setFname(sc.next());
                    ps = con.prepareStatement("update Employee set fname=? where Emp_Id=?");
                    ps.setString(1, getFname());
                    ps.setInt(2, getEmp_ID());
                    break;
                case 2:
                    System.out.println("Enter Last Name which you want to update");
                    setLname(sc.next());
                    ps = con.prepareStatement("update employee set lname=? where emp_id=?");
                    ps.setString(1, getLname());
                    ps.setInt(2, getEmp_ID());
                    break;
                case 3:
                    System.out.println("Enter Email id which you want to update");
                    setEmail(sc.next());
                    ps = con.prepareStatement("update employee set email=? where emp_id=?");
                    ps.setString(1, getEmail());
                    ps.setInt(2, getEmp_ID());
                    break;
                case 4:
                    System.out.println("Enter Salary which you want to update");
                    setSalary(sc.nextInt());
                    ps = con.prepareStatement("update employee set salary=? where emp_id=? ");
                    ps.setInt(1, getSalary());
                    ps.setInt(2, getEmp_ID());
                    break;
                default:
                    System.out.println("Choose Right Option");
                    updateEmployee();
                    break;
            }
            
            ps.executeUpdate();
            System.out.println("Updated Successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void showDetails()
    {
        try
        {
          Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Employee", "Employee", "Employee");
            PreparedStatement ps=con.prepareStatement("select * from employee where emp_id=?");
            ps.setInt(1, 1);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next())
            {
                setEmp_ID(rs.getInt("Emp_Id"));
                setFname(rs.getString("fname"));
                setLname(rs.getString("lname"));
                setEmail(rs.getString("email"));
                setSalary(rs.getInt("salary"));
                
                System.out.println("Employee Id: "+getEmp_ID());
                System.out.println("First Name: "+getFname());
                System.out.println("Last Name: "+getLname());
                System.out.println("Email Id: "+getEmail());
                System.out.println("Salary : "+getSalary());
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
