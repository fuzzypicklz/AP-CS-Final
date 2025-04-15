package HotelManagement.Management;
import java.util.ArrayList;
import java.util.Scanner;

import HotelManagement.Employees.Employee;
import HotelManagement.Employees.Housekeeping;
import HotelManagement.Employees.Manager;
import HotelManagement.Employees.Receptionist;

import HotelManagement.Rooms.Standard;
import HotelManagement.Rooms.Suite;

import java.io.File;
import java.io.IOException;

/**
 * Manages employees in the hotel management system.
 * Provides functionality to add, remove, and retrieve employees,
 * as well as export and import employee data.
 */
public class EmployeeManagement {
    public static ArrayList<Employee> employeeList = new ArrayList<>();

    /*
     * yet more debugging
     */
    /*
    public static void main(String[] args){
        
        EmployeeDebug();
        employeesToCSV();
        
        for(Employee e : employeeList){
            System.out.println(employeeSummary(e)+"\n");
        }
        
        //employeesFromCSV();
        //System.out.println(getEmployeesString());
        
    }*/
    /*
     * Method testing for Employee Management
     */
    public static void EmployeeDebug(){
        /*
        Employee e = new Employee(0, "John", "Doe");
        Employee e2 = new Employee(1, "Jane", "Doe", "Manager");
        Housekeeping h = new Housekeeping(2, "John", "Smith");
        */

        addEmployee("John", "Doe", "Intern");
        addEmployee("Jane", "Doe", "Manager");
        addEmployee("John", "Smith", "Housekeeping");
        addEmployee("Joe", "Doe", "Intern");
        addEmployee("Joe", "Shmoe", "Safety Officer");

        Standard r1 = new Standard(100);
        Standard r2 = new Standard(200, 4, false, 135);
        Suite s1 = new Suite(202, 5, false,true,300);
        Housekeeping h = (Housekeeping) employeeList.get(2);
        h.addRoom(r1);
        h.addRoom(r2);
        h.addRoom(s1);
    }
    /*
     * Adds a generic Employee with no attributes besides name. Default position is an intern.
     */
    /**
     * Adds a generic Employee with no attributes besides name.
     * Default position is an intern.
     * @param f The first name of the employee.
     * @param l The last name of the employee.
     */
    public static void addIntern(String f, String l) {
        Employee e = new Employee(employeeList.size(), f, l);
        addEmployee(e);
    }
    /*
     * Adds an Employee with a position attribute. if the position is new, it adds a generic employee with that position.
     */
    /**
     * Adds an Employee with a position attribute.
     * If the position is new, it adds a generic employee with that position.
     * @param f The first name of the employee.
     * @param l The last name of the employee.
     * @param p The position of the employee.
     */
    public static void addEmployee(String f, String l, String p) {
        p = p.toLowerCase();
        if (p.equalsIgnoreCase("housekeeping")){
            Housekeeping e = new Housekeeping(employeeList.size(), f, l);
            addEmployee(e);
        } 
        else if(p.equalsIgnoreCase("manager")){
            Manager e = new Manager(employeeList.size(), f, l);
            addEmployee(e);
        }
        else if(p.equalsIgnoreCase("receptionist")){
            Receptionist e = new Receptionist(employeeList.size(), f, l);
            addEmployee(e);
        }
        else {
            Employee e = new Employee(employeeList.size(), f, l, p);
            employeeList.add(e);
        }
    }

    /**
     * Adds an Employee object to the employee list.
     * @param e The Employee object to add.
     */
    public static void addEmployee(Employee e) {
        employeeList.add(e);
    }

    /**
     * Removes an Employee object from the employee list.
     * @param e The Employee object to remove.
     */
    public static void removeEmployee(Employee e) {
        employeeList.remove(e);
    }
    /*
     * Sends all of the Employee.toString() values to a singular, formatted String.
     */
    /**
     * Retrieves a string representation of all employees in the system.
     * @return A formatted string containing all employees.
     */
    public static String getEmployeesString() {
        String s = "\n";
        for(int i = 0; i < employeeList.size(); i++){
            Employee employee = (Employee) employeeList.get(i);
            s += employee.toString() + "\n\n";
        }
        return s;
    }
    /*
     * Finds an Employee from arraylist<Employee> employeeList by ID and returns it as its respective position class.
     */
    /**
     * Finds an Employee by ID and returns it as its respective position class.
     * @param id The ID of the employee.
     * @return The Employee object, or null if not found.
     */
    public static Employee getEmployee(int id) {
        for (int i = 0; i < employeeList.size(); i++){
            Employee employee = (Employee) employeeList.get(i);
            if (employee.getID() == id){
                if(employee.getPosition().toLowerCase() == "housekeeping") return (Housekeeping) employeeList.get(i);
                else if(employee.getPosition().toLowerCase() == "manager") return (Manager) employeeList.get(i);
                else return (Employee) employeeList.get(i);
            }
        }
        System.out.println("Employee not found.");
        return null;
    }
    /*
     * Finds an Employee from arraylist<Employee> employeeList by first and last name and returns it as its respective position class.
     */
    /**
     * Finds an Employee by first and last name and returns it as its respective position class.
     * @param fName The first name of the employee.
     * @param lName The last name of the employee.
     * @return The Employee object, or null if not found.
     */
    public static Employee getEmployee(String fName, String lName) {
        for (int i = 0; i < employeeList.size(); i++){
            Employee employee = (Employee) employeeList.get(i);
            if (employee.getFname().equalsIgnoreCase(fName) && employee.getLname().equalsIgnoreCase(lName)){
                if(employee.getPosition().toLowerCase() == "housekeeping") return (Housekeeping) employeeList.get(i);
                else if(employee.getPosition().toLowerCase() == "manager") return (Manager) employeeList.get(i);
                else return (Employee) employeeList.get(i);
            }
        }
        System.out.println("Employee not found.");
        return null;
    }
    /*
     * Exports All generic Employee data of Employees with employeeList NOT INCLUDING HOUSEKEEPING ROOM ASSIGNMENTS.
     * Room assignments can be exported using another method within the Housekeeping class.
     */
    /**
     * Exports all employee data to a CSV file.
     * Does not include housekeeping room assignments.
     */
    public static void employeesToCSV() {
        String s = "ID,first name,last name,position,roomAssgn\n";  // CSV Header
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getPosition().equalsIgnoreCase("housekeeping")){ // If the Employee is part of the Housekeeping staff,
                Housekeeping employee = (Housekeeping) employeeList.get(i);                       // we store their rooms in an array within the csv.
                s += employee.getID() + "," + employee.getFname() + "," + employee.getLname() + "," + employee.getPosition();
                for (int j = 0; j < employee.getRooms().size(); j++){
                    s+=";"+employee.getRooms().get(j).getNumber();
                }
            }
            else{ // Otherwise, we just store their information in the given columns.
                Employee employee = (Employee) employeeList.get(i);
                s += employee.getID() + "," + employee.getFname() + "," + employee.getLname() + "," + employee.getPosition() + "\n";
            }
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
    /*
     * APPENDS (does not replace) data from a correctly formatted CSV to the end of employeeList. DOES NOT IMPORT ROOM ASSIGNMENTS.
     * Room assignments can be imported to individual Housekeeping class members using another method.
     */
    /**
     * Imports employee data from a CSV file.
     * Appends data to the existing employee list.
     */
    public static void employeesFromCSV() {
        try{
            File file = new File("data/employees.csv");
            Scanner input = new Scanner(file);
            input.nextLine();

            String names = "";
            for(Employee e : employeeList){
                names += e.getFname() + " " + e.getLname() + "\n";
            }
            while(input.hasNext()){
                String line = input.nextLine();
                String[] lineBreak = line.split(",");
                int id = Integer.parseInt(lineBreak[0]);
                String fName = lineBreak[1];
                String lName = lineBreak[2];
                String position = lineBreak[3];
                
                if(names.contains(fName + " " + lName)) { // Prevents duplicates
                    System.out.println("Employee already exists: " + fName + " " + lName);
                    continue;
                }

                if(position.equalsIgnoreCase("housekeeping")){
                    employeeList.add(new Housekeeping(id, fName, lName));
                    if(lineBreak.length == 5){
                        String[] RoomNumbers = lineBreak[4].split(";");
                        for(String numString : RoomNumbers){
                            int num = Integer.parseInt(numString);
                            ((Housekeeping) employeeList.get(employeeList.size()-1)).addRoom(RoomManagement.getRoom(num));
                        }
                    }
                }
                else if (position.equalsIgnoreCase("manager")) employeeList.add(new Manager(id, fName, lName)); 
                else employeeList.add(new Employee(id, fName, lName, position));
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
    /*
     * Exports the Employee.toString() to a .txt file associated with the employee.
     */
    /**
     * Exports the details of an employee to a text file.
     * @param employee The Employee object to summarize.
     * @return A string representation of the employee.
     */
    public static String employeeSummary(Employee employee) {
        try{
            File file = new File("data/"+employee.getID()+employee.getFname()+employee.getLname()+".txt");
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
