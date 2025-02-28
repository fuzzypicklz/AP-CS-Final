package HotelManagement;

public class Employee {
    private String fName;
    private String lName;
    private String position = "Intern";
    private double wage = 16.99;
    private double hoursWorked = 0;
    private double overtimeHours = 0;
    public Employee(String f, String l){
        this.fName = f;
        this.lName = l;
        
    }
    public Employee(String f, String l, String p){
        this.fName = f;
        this.lName = l;
        this.position = p;
    }

    public double calculatePay(){
        return hoursWorked * wage;
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

    public getPosition(){
        return this.position;
    }



}
