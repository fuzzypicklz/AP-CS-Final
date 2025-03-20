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
            System.out.println("\nWelcome to the Hotel Management System");
            System.out.println("1. Admin Interface");
            System.out.println("2. Customer Interface");
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            System.out.println("");
            if(choice.equals("1")){
                
                AdminInterface();
            }
            /*if(choice.equals("2")){
                
            }*/
        }
    }
    public static void AdminInterface(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") && !choice.equalsIgnoreCase("e")) {
            System.out.println("Hotel Management System");
            System.out.println("1. Employee Management");
            System.out.println("2. Room Management");
            System.out.println("Type 'exit' to quit.");
            System.out.print(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    EmployeeSystem();
                    break;
                case "2":
                    RoomSystem();
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
        while (!choice.equalsIgnoreCase("exit") && !choice.equalsIgnoreCase("e")) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Modify Employee");
            System.out.println("4. View Employee");
            System.out.println("5. Export Employee Summary");
            System.out.println("6. Export Employees to CSV");
            System.out.println("7. Import Employees from CSV");
            System.out.println("Type 'exit' to quit.");
            System.out.print(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    AddEmployeeLogic();
                    break;
                case "2":
                    RemoveEmployeeLogic();
                    break;
                case "3":
                    ModifyEmployeeLogic();
                    break;
                case "4":
                    ViewEmployeeLogic();
                    break;
                case "5":
                    ExportEmployeeSummaryLogic();
                    break;
                case "6":
                    EmployeeManagement.employeesToCSV();
                    break;
                case "7":
                    EmployeeManagement.employeesFromCSV();
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

            System.out.println("\n");
        }
    }

    public static void AddEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter employee first and last names:\n>> ");
        String name = input.nextLine();
        String[] names = name.split(" ");

        if (names.length != 2) {
            System.out.println("Please enter both first and last names.");
            return;
        }

        System.out.print("Enter employee position (or leave empty for intern):\n>> ");
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
        System.out.print("\nRemove employee by:\n1. ID\n2. Name\n>> ");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            System.out.print("Enter employee ID:\n>> ");
            int id = input.nextInt();
            EmployeeManagement.removeEmployee(EmployeeManagement.getEmployee(id));
        } else if (choice.equals("2")) {
            System.out.print("Enter employee first and last names:\n>> ");
            String name = input.nextLine();
            String[] names = name.split(" ");
            if (names.length != 2) {
                System.out.println("Please enter both first and last names.");
                return;
            }
            EmployeeManagement.removeEmployee(EmployeeManagement.getEmployee(names[0], names[1]));
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public static void ModifyEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nChoose ID method:\n1. ID\n2. Full name\n>> ");
        int choice = input.nextInt();
        if(choice == 1){
            System.out.print("Input ID\n>> ");
            int ID = input.nextInt();
            Employee employee = EmployeeManagement.getEmployee(ID);

            if(employee == null){
                return;
            }

            System.out.println("\nEmployee detail to change:");
            System.out.println("1. Name");
            System.out.println("2. ID");
            System.out.println("3. Position");
            System.out.print(">> ");


        }
        else if(choice == 2){
            System.out.print("Input Full name\n>> ");
            String name = input.nextLine();
            String[] names = name.split(" ");
            Employee employee = EmployeeManagement.getEmployee(names[0], names[1]);

            if(employee == null){
                return;
            }

            System.out.println("\nEmployee detail to change:");
            System.out.println("1. Name");
            System.out.println("2. ID");
            System.out.println("3. Position");
            System.out.println("4. Wage");
            System.out.print(">> ");

            int j = input.nextInt();

            if(j == 1){
                System.out.print("Input new full name\n>> ");
                names = input.nextLine().split(" ");
                employee.setName(names[0], names[1]);
            }
            else if(j == 2){
                System.out.print("Input new ID\n>> ");
                int id = input.nextInt();
                employee.setID(id);
            }
            else if(j == 3){
                System.out.print("Input new Position\n>> ");
                String p = input.nextLine();
                if(p.equalsIgnoreCase("housekeeping")){
                    Housekeeping newHouseKeeping = new Housekeeping(employee.getID(), employee.getFname(), employee.getLname());
                    newHouseKeeping.setWage(employee.getWage());
                }
                else if(p.equalsIgnoreCase("manager")){
                    Manager newHouseManager = new Manager(employee.getID(), employee.getFname(), employee.getLname());
                    newHouseManager.setWage(employee.getWage());
                }
                else{
                    employee.setPosition(p.toLowerCase());
                }
            }
            else if(j == 4){
                System.out.print("Input new Wage\n>> ");
                double id = input.nextDouble();
                employee.setWage(id);
            }
        }
        else{
            System.out.print("Invalid choice. please input 1 or 2.");
            return;
        }

        

        
    }

    public static void ViewEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nChoose ID method:\n1. ID\n2. Full name\n>> ");
        int choice = input.nextInt();
        if(choice == 1){
            System.out.print("Input ID\n>> ");
            int ID = input.nextInt();
            Employee employee = EmployeeManagement.getEmployee(ID);
        }
        if(choice == 2){
            System.out.print("Input Full name\n>> ");
            String[] names = input.nextLine().split(" ");
            Employee employee = EmployeeManagement.getEmployee(names[0], names[1]);
        }
        
    }

    public static void ExportEmployeeSummaryLogic(){
        // TODO
    }

    public static void RoomSystem(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("e")) {
            System.out.println("\nRoom Management System");
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. Modify Room");
            System.out.println("4. View Room");
            System.out.println("5. Export Room Summary");
            System.out.println("6. Export Rooms to CSV");
            System.out.println("7. Import Rooms from CSV");
            System.out.println("Type 'exit' to quit.");
            System.out.print(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    AddRoomLogic();
                    break;
                case "2":
                    RemoveRoomLogic();
                    break;
                case "3":
                    ModifyRoomLogic();
                    break;
                case "4":
                    ViewRoomsLogic();
                    break;
                case "5":
                    RoomSummaryLogic();
                    break;
                case "6":
                    RoomManagement.roomsToCSV();
                    break;
                case "7":
                    RoomManagement.roomsFromCSV();
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

    public static void AddRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter room type (1 for Suite, 2 for Standard):\n>> ");
        int roomType = input.nextInt();
        if (roomType != 1 && roomType != 2) {
            System.out.print("Invalid room type. Please enter 1 for Suite or 2 for Standard.");
            return;
        }

        else if(roomType == 2){
            System.out.print("Enter room number:\n>> ");
            int number = input.nextInt();
            System.out.print("Enter room capacity:\n>> ");
            int capacity = input.nextInt();
            System.out.print("Enter room rate:\n>> ");
            double rate = input.nextDouble();
            System.out.print("Is the room occupied? (y/n)\n>> ");
            String occupied = input.nextLine();
            System.out.print("has balcony? (y/n)\n>> ");
            String hasBalcony = input.nextLine();
            RoomManagement.addRoom(new Suite(number, capacity, occupied.equalsIgnoreCase("y"), hasBalcony.equalsIgnoreCase("y"), rate));
        }

        else if(roomType == 1){
            System.out.print("Enter room number:\n>> ");
            int number = input.nextInt();
            System.out.print("Enter room capacity:\n>> ");
            int capacity = input.nextInt();
            System.out.print("Enter room rate:\n>> ");
            double rate = input.nextDouble();
            System.out.print("Is the room occupied? (y/n)\n>> ");
            String occupied = input.nextLine();
            RoomManagement.addRoom(new Room(number, capacity, occupied.equalsIgnoreCase("y"), rate));
        }
    }

    public static void RemoveRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter room number to remove:\n>> ");
        int number = input.nextInt();
        RoomManagement.removeRoom(RoomManagement.getRoom(number));
    }

    public static void ModifyRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter room number to modify:\n>> ");
        int number = input.nextInt();
        Room room = RoomManagement.getRoom(number);
        if (room == null) {
            return;
        }
        System.out.println("\nRoom detail to change:");
        System.out.println("1. Room capacity");
        System.out.println("2. Room rate");
        System.out.println("3. Room occupancy");
        System.out.println("4. Room type");
        System.out.print(">> ");
        String choice = input.nextLine();

        switch(choice){
            case "1":
                System.out.print("Enter new room capacity:\n>> ");
                int newCapacity = input.nextInt();
                room.setCapacity(newCapacity);
                break;
            case "2":
                System.out.print("Enter new room rate:\n>> ");
                double newRate = input.nextDouble();
                room.setRate(newRate);
                break;
            case "3":
                System.out.print("How many people are in the room?\n>> ");
                int customers = input.nextInt();
                room.setOccupancy(customers);
                break;
            case "4":
                System.out.print("Enter new room type (1 for Suite, 2 for Standard):\n>> ");
                int roomType = input.nextInt();
                if (roomType == 1) {
                    room = new Suite(room.getNumber(), room.getOccupancy(), room.getOccupancy()>0, ((Suite) room).hasBalcony(), room.getRate());
                } else if (roomType == 2) {
                    room = new Room(room.getNumber(), room.getOccupancy(), room.getOccupancy()>0, room.getRate());
                } else {
                    System.out.println("Invalid room type.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void ViewRoomsLogic(){
        Scanner s = new Scanner(System.in);
        RoomManagement.getRoomListString();
        System.out.print("Input room number for more details or type exit.\n>> ");
        String input = s.nextLine();
        if (!input.equalsIgnoreCase("exit") && !input.equalsIgnoreCase("e")) {
            Room room = RoomManagement.getRoom(Integer.parseInt(input));
            if(room!=null){
                System.out.println(room.toString());
            }
        }
    }

    public static void RoomSummaryLogic(){
        // TODO
    }
}
