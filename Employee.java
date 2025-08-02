
class Employee {
    private int employeeid;
    private String firstName;
    private String lastName;
    private String gender;
   private Date date;
    private Address address1;

    private String phoneNumber;
    private String role;
    private int salary;

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Address getAddress1() {
        return address1;
    }

    public void setAddress1(Address address1) {
        this.address1 = address1;
    }

    public Date getDate() {
        return date;
    }

    public Employee(String firstName, String lastName, String gender, Date date, Address address1, String phoneNumber, String role, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.date = date;
        this.address1 = address1;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.salary = salary;
    }
}


