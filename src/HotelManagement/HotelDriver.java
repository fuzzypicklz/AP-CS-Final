package HotelManagement;

import HotelManagement.Management.EmployeeManagement;
import HotelManagement.Management.RoomManagement;

import HotelManagement.Employees.*;
import HotelManagement.Rooms.*;

import java.util.Scanner;
import java.util.ArrayList;

public class HotelDriver {
    public static void main(String[] Args){
        while (true){

        }
    }
    public static void AdminInterface(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("e")) {
            System.out.println("Hotel Management System");
            System.out.println("1. Employee Management");
            System.out.println("2. Room Management");
            System.out.println("3. Customer Interface");
            System.out.println("Type 'exit' to quit.");
            System.out.println(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    EmployeeManagement.EmployeeDebug();
                    break;
                case "2":
                    RoomManagement roomM = new RoomManagement();
                    roomM.roomDebug();
                    break;
                case "3":
                    CustomerInterface();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    break;
                case "e":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    public static void EmployeeSystem(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("e")) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Modify Employee");
            System.out.println("4. View Employees");
            System.out.println("5. Export Employee Summary");
            System.out.println("6. Export Employees to CSV");
            System.out.println("7. Import Employees from CSV");
            System.out.println("Type 'exit' to quit.");
            System.out.println(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    
                    break;
                case "2":
                    System.out.println
                    break;
                case "3":
                    // View employees logic
                    break;
                    case "exit":
                    System.out.println("Exiting...");
                    break;
                case "e":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

            System.out.println("\n\n");
        }
    }

    public static void CustomerInterface(){

    }


    public static void AddEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter employee first and last names:\n>> ");
        String name = input.nextLine();
        String[] names = name.split(" ");

        if (names.length != 2) {
            System.out.println("Please enter both first and last names.");
            return;
        }

        System.out.println("Enter employee porition (or leave empty for intern):\n>> ");
        String position = input.nextLine();

        if (position.isEmpty() || position.equalsIgnoreCase("intern")) {
            EmployeeManagement.addIntern(names[0], names[1]);
        }
        else {
            EmployeeManagement.addEmployee(names[0], names[1], position);
        }
        System.out.println("Employee added successfully.");
    }

    public static void RemoveEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.println("Remove employee by:\n1. ID\n2. Name\n>> ");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            System.out.println("Enter employee ID:\n>> ");
            int id = input.nextInt();
            EmployeeManagement.removeEmployee(EmployeeManagement.getEmployee(id));
        } else if (choice.equals("2")) {
            System.out.println("Enter employee first and last names:\n>> ");
            String name = input.nextLine();
            String[] names = name.split(" ");
            if (names.length != 2) {
                System.out.println("Please enter both first and last names.");
                return;
            }
            EmployeeManagement.removeEmployee(EmployeeManagement.getEmployee(names[0], names[1]);
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
