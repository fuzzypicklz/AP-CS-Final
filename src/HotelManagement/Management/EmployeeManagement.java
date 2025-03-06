package HotelManagement.Management;
import java.util.ArrayList;
import java.util.Scanner;

import HotelManagement.Employees.Employee;
import HotelManagement.Employees.Housekeeping;

import java.io.File;
import java.io.IOException;

public class EmployeeManagement {
    public static ArrayList employeeList = new ArrayList<Employee>();

    public static void main(String[] args){
        /*
        EmployeeDebug();
        employeesToCSV();
        */

        employeesFromCSV();
        System.out.println(getEmployeesString());
    }
    
    public static void EmployeeDebug(){
        /*
        Employee e = new Employee(0, "John", "Doe");
        Employee e2 = new Employee(1, "Jane", "Doe", "Manager");
        Housekeeping h = new Housekeeping(2, "John", "Smith");
        */

        addEmployee("John", "Doe");
        addEmployee("Jane", "Doe", "Manager");
        addEmployee("John", "Smith", "Housekeeping");
    }

    public static void addIntern(String f, String l){
        Employee e = new Employee(employeeList.size(), f, l);
        addEmployee(e);
    }

    public static void addEmployee(String f, String l, String p){
        p = p.toLowerCase();
        if (p == "housekeeping"){
            Housekeeping e = new Housekeeping(employeeList.size(), f, l);
            addEmployee(e);
        } 

        /*else if(p == "manager"){
            Manager e = new Manager(employeeList.size(), f, l);
            addEmployee(e);
        }*/
        
        /*
        else if(p == "receptionist"){
            Receptionist e = new Receptionist(employeeList.size(), f, l);
            addEmployee(e);
        }
        */

        else {
            Employee e = new Employee(employeeList.size(), f, l, p);
            addEmployee(e);
        }
        Employee e = new Employee(employeeList.size(), f, l);
        addEmployee(e);
    }

    public static void addEmployee(Employee e){
        employeeList.add(e);
    }

    public static void removeEmployee(Employee e){
        employeeList.remove(e);
    }

    public static String getEmployeesString(){
        String s = "";
        for(int i = 0; i < employeeList.size(); i++){
            Employee employee = (Employee) employeeList.get(i);
            s += employee.toString() + "\n\n";
        }
        return s;
    }

    public static Employee getEmployee(int id){
        for (int i = 0; i < employeeList.size(); i++){
            Employee employee = (Employee) employeeList.get(i);
            if (employee.getID() == id){
                return employee;
            }
        }
        System.out.println("Employee not found.");
        return null;
    }

    public static void employeesToCSV(){
        String s = "ID,fName,lName,position\n";
        for(int i = 0; i < employeeList.size(); i++){
            Employee employee = (Employee) employeeList.get(i);
            s += employee.getID() + "," + employee.getFname() + "," + employee.getLname() + "," + employee.getPosition() + "\n";
        }
        
        try{
            File file = new File("data/employees.csv");
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            output.print(s);
            output.close();
            System.out.println("Employees saved to file.");
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void employeesFromCSV(){
        try{
            File file = new File("data/employees.csv");
            Scanner input = new Scanner(file);
            input.nextLine();
            while(input.hasNext()){
                String line = input.nextLine();
                String[] lineBreak = line.split(",");
                int id = Integer.parseInt(lineBreak[0]);
                String fName = lineBreak[1];
                String lName = lineBreak[2];
                String position = lineBreak[3];
                Employee employee = new Employee(id, fName, lName, position);
                employeeList.add(employee);
            }
            System.out.println("Employees loaded from file.");
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch(IndexOutOfBoundsException e){
            System.out.println("An error occurred. Check the file format.");
            e.printStackTrace();
        }
    }
    
    public static String employeeSummary(Employee employee){
        try{
            File file = new File("data/"+employee.getID()+employee.getLname()+employee.getFname()+".txt");
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            output.print(employee.toString());
            output.close();
            return employee.toString();
        } catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "Could not save to file!\n" +
                    employee.toString();
        }
    }
}
