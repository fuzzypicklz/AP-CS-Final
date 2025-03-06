package HotelManagement.Employees;

public class Employee {
    private int ID;
    private String fName;
    private String lName;
    private String position = "Intern";
    private double wage = 16.99;
    private double hoursWorked = 0;
    private double overtimeHours = 0;

    public Employee(int id,String f, String l){
        this.ID = id;
        this.fName = f;
        this.lName = l;
        
        
    }
    public Employee(int id, String f, String l, String p){  // p.equals("Receptionist")
        this.ID = id;
        this.fName = f;
        this.lName = l;
        this.position = p;
    }

    public double calculatePay(){
        return hoursWorked * wage;
    }

    public int getID(){
        return ID;
    }
    
    public double calculatePay(double extraHours){
        hoursWorked += extraHours;
        return hoursWorked * wage;
    }

    public double calculatePay(double extraHours, double extraOvertime){
        hoursWorked += extraHours;
        overtimeHours += extraOvertime;
        return hoursWorked * wage + overtimeHours * wage * 1.5;
    }

    public double getHoursWorked(){
        return hoursWorked + overtimeHours;
    }
    
    public double getOvertime(){
        return overtimeHours;
    }

    public String getHoursWorkedString(){
        return  "Standard hours: " + hoursWorked+
                "Overtime Hours: " + overtimeHours;
    }

    public String getPosition(){
        return this.position;
    }

    public void setWage(double w){
        wage = w;
    }

    public String getFname(){
        return fName;
    }
    
    public String getLname(){
        return lName;
    }

    public String toString(){
        return  "Employee ID: " + ID + 
                "\nName: " + fName + " " + lName + 
                "\nPosition: " + position + 
                "\nWage: " + wage + 
                "\nHours Worked: " + hoursWorked + 
                "\nOvertime Hours: " + overtimeHours + 
                "\nTotal pay: " + calculatePay();
    }
}
