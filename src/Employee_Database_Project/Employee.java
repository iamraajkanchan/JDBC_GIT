package Employee_Database_Project;

public class Employee
 {
    private int Emp_ID;
    private String fname;
    private String lname;
    private String email;
    private int salary;

//    public Employee(int Emp_ID, String fname, String lname, String email, int salary) {
//        this.Emp_ID = Emp_ID;
//        this.fname = fname;
//        this.lname = lname;
//        this.email = email;
//        this.salary = salary;
//    }

    
    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    
 }
