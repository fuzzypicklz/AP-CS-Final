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
    public Employee(int id, String f, String l, String p){
        this.ID = id;
        this.fName = f;
        this.lName = l;
        this.position = p;
    }

    public int getID(){
        return ID;
    }
    
    public double getHoursWorked(){
        return hoursWorked + overtimeHours;
    }
    
    public double getOvertime(){
        return overtimeHours;
    }

    public String getPosition(){
        return this.position;
    }

    public String getFname(){
        return fName;
    }
    
    public String getLname(){
        return lName;
    }

    public double getWage(){
        return wage;
    }

    public String getHoursWorkedString(){
        return  "Standard hours: " + hoursWorked+
                "Overtime Hours: " + overtimeHours;
    }
    /*
     * Calculates pay based on currently logged hours.
     */
    public double calculatePay(){
        return hoursWorked * wage;
    }
    /*
     * Adds hours, then calculates pay.
     */
    public double calculatePay(double extraHours){
        hoursWorked += extraHours;
        return calculatePay();
    }
    /*
     * Adds hours, adds overtime hours, then calculates pay based on a 1.5x overtime bonus.
     */
    public double calculatePay(double extraHours, double extraOvertime){
        hoursWorked += extraHours;
        overtimeHours += extraOvertime;
        return calculatePay() + overtimeHours * wage * 1.5;
    }

    public void setWage(double w){
        wage = w;
    }

    public void setName(String f, String l){
        fName = f;
        lName = l;
    }

    public void setID(int i){
        ID = i;
    }
    
    public void setPosition(String p){
        position = p;
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
